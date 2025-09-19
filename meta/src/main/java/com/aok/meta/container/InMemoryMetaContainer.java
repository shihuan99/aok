/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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