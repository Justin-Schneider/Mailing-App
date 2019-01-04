package com.company.app.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CommonService {

    public static ResponseEntity<Map> getResponse(Date date, String httpStatus, String error, String message, String path){
        Map<String, String> json = new HashMap<>();
        json.put("timestamp", date.toString());
        json.put("status", httpStatus);
        if(error != null) json.put("error", error);
        json.put("message", message);
        json.put("path", path);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    public static Properties loadPropertyFile(String path) throws IOException {
        if(path == null){
            return null;
        }
        Properties prop = new Properties();
        prop.load(CommonService.class.getClassLoader().getResourceAsStream(path));
        return prop;
    }


}
