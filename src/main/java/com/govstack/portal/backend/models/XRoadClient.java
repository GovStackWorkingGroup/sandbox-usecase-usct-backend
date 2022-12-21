package com.govstack.portal.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class XRoadClient {
    @JsonProperty("id")
    XRoadClientId id;
}
