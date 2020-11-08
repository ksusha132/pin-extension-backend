package com.pinext.backend.pinextensionbackend.fastspring;

import lombok.Data;

@Data
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
