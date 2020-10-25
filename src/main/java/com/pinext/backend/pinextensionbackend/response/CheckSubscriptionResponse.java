package com.pinext.backend.pinextensionbackend.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@ApiModel(description = "Check user subscription response")
public class CheckSubscriptionResponse extends CommonResponse {
    @ApiModelProperty("date time checking")
    private LocalDateTime time;
    @ApiModelProperty("checking status")
    private Boolean status;
}
