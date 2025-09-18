package com.aok.meta.container;

import com.aok.meta.Meta;
import org.apache.kafka.common.Uuid;

import java.util.Map;

public class InMemoryMetaContainer implements MetaContainer<Meta> {

    private final Map<String, Map<Uuid, Meta>> metaMap = new HashMap<>();

    @Override
    public synchronized void add(Meta meta) {
        String type = getMetaType(meta);
        Uuid uuid = Uuid.randomUuid();
        meta.setUuid(uuid);
        me
    }

    @Override
    public synchronized void remove(Meta meta) {
        List<Meta> list = metaMap.get(meta.getClass());
        if (list != null) {
            list.remove(meta);
            if (list.isEmpty()) {
                metaMap.remove(meta.getClass());
            }
        }
    }

    @Override
    public List<Meta> list() {
        List<Meta> all = new ArrayList<>();
        for (List<Meta> metas : metaMap.values()) {
            all.addAll(metas);
        }
        return all;
    }

    @Override
    public synchronized void update(Meta meta) {
        List<Meta> list = metaMap.get(meta.getClass());
        if (list != null) {
            int idx = list.indexOf(meta);
            if (idx != -1) {
                list.set(idx, meta);
            }
        }
    }
    
    public List<Meta> listByType(Class<? extends Meta> type) {
        return new ArrayList<>(metaMap.getOrDefault(type, Collections.emptyList()));
    }
}