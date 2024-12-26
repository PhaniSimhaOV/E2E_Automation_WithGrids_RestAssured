package com.autogrid.utils;

import java.util.List;
import java.util.Map;

public interface IDataReader {

    public List<Map<String, String>> getAllRows();


    public Map<String, String> getASingleRow();
}