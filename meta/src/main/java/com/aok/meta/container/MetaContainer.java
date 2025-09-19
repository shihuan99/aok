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
import com.aok.meta.MetaType;

import java.util.List;

/**
 * Container interface for managing Meta objects.
 * @param <T> the type of Meta
 */
public interface MetaContainer<T extends Meta> {

    /**
     * Adds a Meta object to the container.
     * @param meta the Meta object to add
     */
    void add(T meta);

    /**
     * Removes a Meta object from the container.
     * @param meta the Meta object to remove
     */
    void remove(T meta);

    /**
     * Retrieves a Meta object by type, vhost, and name.
     * @param type the type of Meta
     * @param vhost the virtual host
     * @param name the name of Meta
     * @return the Meta object if found, otherwise null
     */
    Meta getMeta(String type, String vhost, String name);

    /**
     * Lists all Meta objects in the container.
     * @return a list of Meta objects
     */
    List<T> list();

    /**
     * Updates a Meta object in the container.
     * @param meta the Meta object to update
     */
    void update(T meta);

    /**
     * Gets the MetaType value from the Meta object's annotation.
     * @param meta the Meta object
     * @return the MetaType value
     */
    default String getMetaType(Meta meta) {
        MetaType type = meta.getClass().getAnnotation(MetaType.class);
        return type.value();
    }
}