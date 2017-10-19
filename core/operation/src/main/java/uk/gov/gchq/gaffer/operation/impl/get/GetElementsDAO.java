/*
 * Copyright 2017 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.gchq.gaffer.operation.impl.get;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Simple data access object which enables the serialisation and deserialisation
 * of {@link GetElements} without needing the class information.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class GetElementsDAO extends GetElements {

    /**
     * Get the class name of this class. This is set to always return {@code null}
     * in order to prevent the serialised version of this class from containing
     * the JSON type information that Jackson would use to deserialise JSON representations
     * of this class.
     *
     * @return null
     */
    @JsonGetter("class")
    public String getClassName() {
        return null;
    }

    @JsonSetter("class")
    public void setClassName(final String className) {
        if (!GetElements.class.getName().equals(className) && !getClass().getName().equals(className)) {
            throw new IllegalArgumentException("Invalid class " + className + ". The class should be " + GetElements.class.getName());
        }
    }
}
