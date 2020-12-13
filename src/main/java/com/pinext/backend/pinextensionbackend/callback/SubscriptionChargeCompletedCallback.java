package com.pinext.backend.pinextensionbackend.callback;

import com.pinext.backend.pinextensionbackend.fastspring.Order;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionChargeCompletedCallback extends SubscriptionCallbackCommon {
    public Order order;
    public int total;
    public String status;
    public long timestamp;
    public long timestampValue;
    public int timestampInSeconds;
    public String timestampDisplay;
}
