package com.aok.meta.container;

import com.aok.meta.Meta;
import com.aok.meta.MetaType;
import org.apache.kafka.common.Uuid;

import java.util.List;

public interface MetaContainer<T extends Meta> {

    public void add(T meta);

    public void remove(T meta);

    public Meta getMeta(String type, String vhost, String name);

    public List<T> list();

    public void update(T meta);
    
    default String getMetaType(Meta meta) {
        MetaType type = meta.getClass().getAnnotation(MetaType.class);
        return type.value();
    }
}
