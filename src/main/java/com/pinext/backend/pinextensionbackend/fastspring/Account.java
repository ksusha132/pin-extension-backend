package com.pinext.backend.pinextensionbackend.fastspring;

import lombok.Data;

@Data
public class Account {
    public String id;
    public String account;
    public Contact contact;
    public String language;
    public String country;
    public Lookup lookup;
    public String url;
}
