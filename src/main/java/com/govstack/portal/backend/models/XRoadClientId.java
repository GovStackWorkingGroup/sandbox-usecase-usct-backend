package com.govstack.portal.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class XRoadClientId {

    @JsonProperty("member_class")
    String member_class;

    @JsonProperty("member_code")
    String member_code;

    @JsonProperty("object_type")
    String object_type;

    @JsonProperty("xroad_instance")
    String xroad_instance;
}
