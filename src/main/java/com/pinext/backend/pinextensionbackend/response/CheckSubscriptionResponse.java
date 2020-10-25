package com.pinext.backend.pinextensionbackend.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CheckSubscriptionResponse extends CommonResponse {
    private LocalDateTime time;
    private Boolean status;
}
