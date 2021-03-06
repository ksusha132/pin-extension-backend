package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipient2 {
    public String first;
    public String last;
    public String email;
    public Object company;
    public Object phone;
    public String account;
    public Address2 address;
}
