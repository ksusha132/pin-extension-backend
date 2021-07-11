package com.pinext.backend.pinextensionbackend.callback;

import lombok.Data;

@Data
public class Event {
    public String id;
    public boolean processed;
    public long created;
    public String type;
    public boolean live;
    public SubscriptionCallbackCommon data;
}
