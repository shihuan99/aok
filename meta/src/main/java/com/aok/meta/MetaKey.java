package com.aok.meta;

public class MetaKey {

    private final String type;

    private final String vhost;

    private final String name;

    public MetaKey(String type, String vhost, String name) {
        this.type = type;
        this.vhost = vhost;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetaKey)) return false;
        MetaKey metaKey = (MetaKey) o;
        return Objects.equals(type, metaKey.type) &&
               Objects.equals(vhost, metaKey.vhost) &&
               Objects.equals(name, metaKey.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, vhost, name);
    }
}