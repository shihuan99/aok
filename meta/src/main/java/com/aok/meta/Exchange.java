package com.aok.meta;

import lombok.Data;

import java.util.HashMap;

@Data
@MetaType("exchange")
public class Exchange extends Meta {

    private ExchangeType type;

    private Boolean durable;

    private Boolean autoDelete;

    private boolean internal;

    private HashMap<String, String> arguments;
}
