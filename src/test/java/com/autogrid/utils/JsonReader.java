package com.autogrid.utils;

import com.autogrid.testData.FlightRegCustomerData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonReader {

    private static final Logger logger = LoggerFactory.getLogger(JsonReader.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestData(String path, Class<T> type){
        try(InputStream stream = ResourceLoader.getResource(path)){
            return  mapper.readValue(stream, type);
        }catch (Exception e){
            logger.error("Unable to read the test data from: {}", path);
        }
        return null;
    }



}
