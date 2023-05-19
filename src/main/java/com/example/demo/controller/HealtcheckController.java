package com.example.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@RestController
class HealthcheckController {

    private final ObjectMapper om = new ObjectMapper();

    private final String formatter = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);


    @GetMapping(value = "/healthcheck", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> healthcheck(@RequestParam("format") String format) throws JsonProcessingException {
        Map<String, String> resultMap = new HashMap<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String formattedResult = ZonedDateTime.now(ZoneId.of("UTC")).format(dateTimeFormatter);
        boolean fullFormat = format.equals("full");
        boolean shortFormat = format.equals("short");
        if (!fullFormat && !shortFormat) return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);

        if(fullFormat) {
            resultMap.put("status", "OK");
            resultMap.put("currentTime", formattedResult);
        }
        if (shortFormat) {
            resultMap.put("status", "OK");
        }
        return new ResponseEntity<>(om.writeValueAsString(resultMap), httpHeaders, HttpStatus.OK);
    }

    @PutMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity healthcheckPut() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(httpHeaders, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PostMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity healthcheckPost() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(httpHeaders, HttpStatus.METHOD_NOT_ALLOWED);
    }


    @DeleteMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity healthcheckDelete() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(httpHeaders, HttpStatus.METHOD_NOT_ALLOWED);
    }
}