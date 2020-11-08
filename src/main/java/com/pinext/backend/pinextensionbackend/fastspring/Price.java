package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Price {
    @JsonProperty("USD")
    public int usd;
}
