package com.kourchenko.fileoptimize.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ResponseService {

    public String parseResponse(String response, String error, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        try {
            // {"timestamp":"2023-03-27T04:57:19.028+00:00","status":500,"error":"Internal Server
            // Error","path":"/"}

            map.put("timestamp", Instant.now().toString());
            map.put("response", response);
            map.put("status", status.toString());
            map.put("error", error);

            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
