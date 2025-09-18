package com.aok.meta;

import lombok.Data;
import org.apache.kafka.common.Uuid;

import java.io.Serializable;

@Data
public class Meta implements Serializable {

    private String vhost;
    
    private String name;
}
