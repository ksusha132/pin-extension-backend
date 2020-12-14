package com.pinext.backend.pinextensionbackend.callback;

import com.pinext.backend.pinextensionbackend.fastspring.Order;
import lombok.Data;

@Data
public class SubscriptionChargeCompletedCallback extends SubscriptionCallbackCommon {
    public Order order;
    public int total;
    public String status;
    public long timestamp;
    public long timestampValue;
    public int timestampInSeconds;
    public String timestampDisplay;
}
