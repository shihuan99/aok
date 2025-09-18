package com.aok.meta;

import lombok.Data;

@Data
@MetaType("vhost")
public class Binding extends Meta {

    private String source;

    private String destination;

    private String routingKey;
}
