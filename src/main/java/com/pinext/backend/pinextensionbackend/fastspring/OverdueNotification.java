package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OverdueNotification {
    public boolean enabled;
    public String interval;
    public int intervalLength;
    public int amount;
}
