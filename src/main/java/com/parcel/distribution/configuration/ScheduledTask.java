package com.parcel.distribution.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private ServletContext servletContext;

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void checkLinks() throws IOException {
        String path = servletContext.getRealPath("/WEB-INF/documents");
        FileUtils.cleanDirectory(new File(path));
        log.info("DELETED FILES FROM documents");
    }
}