package com.kourchenko.fileoptimize.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kourchenko.fileoptimize.models.Event;
import com.kourchenko.fileoptimize.service.FileOptimizeService;
import com.kourchenko.fileoptimize.service.ResponseService;

@RestController
public class Controller {

    @Autowired
    private FileOptimizeService fileOptimizeService;

    @Autowired
    private ResponseService responseService;

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String handleRequest(@RequestBody Event event) {
        String response = "";
        try {
            String result = fileOptimizeService.processFileEvent(event);
            response = responseService.parseResponse(result, null, HttpStatus.OK);
        } catch (Exception e) {
            response = responseService.parseResponse("", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
