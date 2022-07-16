package com.training.foodapp.services.util;

public class ApiRequestBuilder {
    private boolean firstQueryParamAdded = false;

    private String builtUrl;

    public ApiRequestBuilder(String baseUrl) {
        builtUrl = baseUrl;
    }

    public String build() {
        return builtUrl;
    }

    public ApiRequestBuilder addPath(String path) {
        this.builtUrl += "/" + path;
        return this;
    }

    public ApiRequestBuilder addQueryParam(String param, String value) {
        if (!firstQueryParamAdded) {
            builtUrl += "?" ;
            firstQueryParamAdded = true;
        } else {
            builtUrl += "&" ;
        }
        builtUrl += param + "=" + value;
        return this;
    }

}
