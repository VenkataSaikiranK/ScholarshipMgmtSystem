package com.dal.group7.service.implementation;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class JsonFileReader {

    private static final String DELIMITER = "\n";

    public JSONObject readJson(String path) throws IOException {
        try(var bufferedReader = new BufferedReader(new FileReader(path))) {
            final String content = bufferedReader.lines().collect(Collectors.joining(DELIMITER));
            return new JSONObject(content);
        }
    }
}
