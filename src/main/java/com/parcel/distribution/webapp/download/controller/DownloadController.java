package com.parcel.distribution.webapp.download.controller;

import com.itextpdf.text.DocumentException;
import com.parcel.distribution.db.entity.Parcel;
import com.parcel.distribution.db.repository.ParcelRepository;
import com.parcel.distribution.utils.PdfBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Controller
@RequestMapping("/parcel/distribution/download")
public class DownloadController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ParcelRepository parcelRepository;

    @RequestMapping(value = "/parcel/{id}", method = RequestMethod.GET)
    public void getFile(@PathVariable("id") Integer id, HttpServletResponse response) {
        String path = servletContext.getRealPath("/WEB-INF/documents/");
        try {
            PdfBuilder pdfBuilder = new PdfBuilder(path + String.valueOf(id) + ".pdf");
            Parcel parcel = parcelRepository.findById(id);
            pdfBuilder.setParcel(parcel);
            pdfBuilder.create();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        File fileToDownload = new File(path + String.valueOf(id) + ".pdf");
        try (
                InputStream is = new FileInputStream(fileToDownload)
        ) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileToDownload.getName());
            response.setHeader("Content-Length", String.valueOf(fileToDownload.length()));
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            log.info("Error writing file to output stream. Filename was '{}'", String.valueOf(id), ex);
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
