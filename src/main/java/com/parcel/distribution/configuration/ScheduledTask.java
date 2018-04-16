package com.parcel.distribution.configuration;

import com.parcel.distribution.db.entity.Link;
import com.parcel.distribution.db.repository.LinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private LinkRepository linkRepository;

    @Value("${hours}")
    private String numberOfHours;


    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void checkFiles() throws IOException {
        String path = servletContext.getRealPath("/WEB-INF/documents");
        FileUtils.cleanDirectory(new File(path));
        log.info("DELETED FILES FROM documents");
    }

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void checkLinks() {
        List<Link> links = linkRepository.findAll();

        for(Link link:links){
            if(link.getData().plusHours(Long.parseLong(numberOfHours)).isBefore(LocalDateTime.now())){
                linkRepository.delete(link);
                log.info("DELETED LINK: " + link.getId() + "," + link.getType() + ", " + link.getLink());
            }
        }
    }
}