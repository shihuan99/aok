package com.aok.meta;

import org.apache.kafka.common.Uuid;

import java.io.Serializable;


public class Meta implements Serializable {
    private String uuid;

    public Uuid getUuid() {
        return uuid;
    }

    public void setUuid(Uuid uuid) {
        this.uuid = uuid;
    }
}
