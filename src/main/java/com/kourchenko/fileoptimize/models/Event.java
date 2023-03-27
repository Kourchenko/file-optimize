package com.kourchenko.fileoptimize.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    private String URL;

    private String resizeMode;

    private Integer height;

    private Integer width;

    private String srcKey;

    private String srcBucket;

    private String destKey;

    private String destBucket;

    @JsonProperty("URL")
    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @JsonProperty("resizeMode")
    public String getResizeMode() {
        return this.resizeMode;
    }

    public void setResizeMode(String resizeMode) {
        this.resizeMode = resizeMode;
    }

    @JsonProperty("height")
    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("width")
    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @JsonProperty("srcKey")
    public String getSrcKey() {
        return this.srcKey;
    }

    public void setSrcKey(String srcKey) {
        this.srcKey = srcKey;
    }

    @JsonProperty("srcBucket")
    public String getSrcBucket() {
        return this.srcBucket;
    }

    public void setSrcBucket(String srcBucket) {
        this.srcBucket = srcBucket;
    }

    @JsonProperty("destKey")
    public String getDestKey() {
        return this.destKey;
    }

    public void setDestKey(String destKey) {
        this.destKey = destKey;
    }

    @JsonProperty("destBucket")
    public String getDestBucket() {
        return this.destBucket;
    }

    public void setDestBucket(String destBucket) {
        this.destBucket = destBucket;
    }
}
