package com.aok.meta;

import lombok.Data;

import java.util.HashMap;

@Data
@MetaType("queue")
public class Queue extends Meta {

    private String vhost;
    
    private Boolean durable;
    
    private Boolean autoDelete;
    
    private boolean internal;
    
    private HashMap<String, String> arguments;
}
