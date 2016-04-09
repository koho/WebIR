package com.flyingleaf.myself;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Koho on 2016/4/8.
 */
public class RequestOptions {
    private Map<String, String> requestProperty;

    public RequestOptions() {
        requestProperty = new HashMap<>();
    }

    public RequestOptions add(String key, String value) {
        requestProperty.put(key ,value);
        return this;
    }

    public List<String> getRequestList() {
        List<String> list = new ArrayList<>();
        for (String key : requestProperty.keySet()) {
            list.add(key + ": " + requestProperty.get(key));
        }
        return list;
    }
}
