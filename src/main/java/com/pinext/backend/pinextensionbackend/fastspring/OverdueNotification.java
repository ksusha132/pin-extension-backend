package com.pinext.backend.pinextensionbackend.fastspring;

import lombok.Data;

@Data
public class OverdueNotification {
    public boolean enabled;
    public String interval;
    public int intervalLength;
    public int amount;
}
