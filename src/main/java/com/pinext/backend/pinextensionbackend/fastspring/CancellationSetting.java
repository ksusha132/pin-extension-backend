package com.pinext.backend.pinextensionbackend.fastspring;

import lombok.Data;

@Data
public class CancellationSetting {
    public String cancellation;
    public String intervalUnit;
    public int intervalLength;
}
