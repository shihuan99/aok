package com.aok.meta.container;

import com.aok.meta.Meta;
import com.aok.meta.MetaKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMetaContainer implements MetaContainer<Meta> {

    private final Map<MetaKey, Meta> map = new ConcurrentHashMap<>();

    @Override
    public synchronized void add(Meta meta) {
        String type = getMetaType(meta);
        MetaKey key = new MetaKey(type, meta.getVhost(), meta.getName());
        map.put(key, meta);
    }

    @Override
    public synchronized void remove(Meta meta) {
        String type = getMetaType(meta);
        MetaKey key = new MetaKey(type, meta.getVhost(), meta.getName());
        map.remove(key);
    }

    public Meta getMeta(String type, String vhost, String name) {
        MetaKey key = new MetaKey(type, vhost, name);
        return map.get(key);
    }

    @Override
    public List<Meta> list() {
        return new ArrayList<>(map.values());
    }

    @Override
    public synchronized void update(Meta meta) {
        String type = getMetaType(meta);
        MetaKey key = new MetaKey(type, meta.getVhost(), meta.getName());
        map.put(key, meta);
    }
}