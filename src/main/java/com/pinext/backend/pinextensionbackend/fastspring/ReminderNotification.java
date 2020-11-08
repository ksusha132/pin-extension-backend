package com.pinext.backend.pinextensionbackend.fastspring;

import lombok.Data;

@Data
public class ReminderNotification {
    public boolean enabled;
    public String interval;
    public int intervalLength;
}
