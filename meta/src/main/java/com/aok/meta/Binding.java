package com.aok.meta;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MetaType("vhost")
public class Binding extends Meta {
    
    private String vhost;

    private String source;

    private String destination;

    private String routingKey;
}
