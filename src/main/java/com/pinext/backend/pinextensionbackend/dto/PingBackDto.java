package com.pinext.backend.pinextensionbackend.dto;

import lombok.Data;

@Data
public class PingBackDto {
    private String uid;
    private String goodsid;
    private int slength;
    private String speriod;
    private int type;
    private String ref;
    private int signVersion;
    private String sig;
}
