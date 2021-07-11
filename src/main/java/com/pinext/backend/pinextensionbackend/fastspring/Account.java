package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    public String id;
    public String account;
    public Contact contact;
    public String language;
    public String country;
    public Lookup lookup;
    public String url;
}
