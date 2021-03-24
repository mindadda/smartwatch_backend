package com.htlabs.smartwatch.controller;

import com.htlabs.smartwatch.entity.Country;
import com.htlabs.smartwatch.repository.CountryRepository;
import com.htlabs.smartwatch.service.CountryService;
import com.htlabs.smartwatch.utils.UserPDFExporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/download")
@Validated
@Slf4j
@Component
public class DownloadPDFController  {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=countries_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Country> countryList = countryRepository.findAll();

        UserPDFExporter exporter = new UserPDFExporter(countryList);
        exporter.export(response);

    }
}

