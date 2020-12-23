package com.pinext.backend.pinextensionbackend.callback;

import lombok.Data;

import java.util.List;

@Data
public class MainRequest {
    List<Event> events;
}
