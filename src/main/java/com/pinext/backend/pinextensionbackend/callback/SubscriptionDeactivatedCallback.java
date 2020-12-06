package com.pinext.backend.pinextensionbackend.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionDeactivatedCallback extends SubscriptionCallbackCommon{
}
