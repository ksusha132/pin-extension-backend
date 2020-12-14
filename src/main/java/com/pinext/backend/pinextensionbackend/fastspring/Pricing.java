package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pricing {
    public int trial;
    public String interval;
    public int intervalLength;
    public String quantityBehavior;
    public int quantityDefault;
    public Price price;
    public boolean dateLimitsEnabled;
    public ReminderNotification reminderNotification;
    public OverdueNotification overdueNotification;
    public Cancellation cancellation;
}
