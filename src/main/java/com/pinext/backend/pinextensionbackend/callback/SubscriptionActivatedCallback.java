package com.pinext.backend.pinextensionbackend.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionActivatedCallback extends SubscriptionCallbackCommon {
}
