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

package uk.gov.gchq.gaffer.federatedstore.operation;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.exception.CloneFailedException;
import uk.gov.gchq.gaffer.commonutil.Required;
import uk.gov.gchq.gaffer.operation.Operation;
import uk.gov.gchq.gaffer.store.StoreProperties;
import uk.gov.gchq.gaffer.store.schema.Schema;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * An Operation used for adding graphs to a FederatedStore.
 * <p>Requires:
 * <ul>
 * <li>graphId
 * <li>properties
 * <li>schema
 * </ul>
 *
 * @see uk.gov.gchq.gaffer.federatedstore.FederatedStore
 * @see uk.gov.gchq.gaffer.operation.Operation
 * @see uk.gov.gchq.gaffer.store.schema.Schema
 * @see uk.gov.gchq.gaffer.data.element.Properties
 * @see uk.gov.gchq.gaffer.graph.Graph
 */
public class AddGraph implements Operation {

    @Required
    private String graphId;
    private StoreProperties storeProperties;
    private String parentPropertiesId;
    private Schema schema;
    private List<String> parentSchemaIds;
    private Set<String> graphAuths = Sets.newHashSet();

    public String getGraphId() {
        return graphId;
    }

    public void setGraphId(final String graphId) {
        this.graphId = graphId;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(final Schema schema) {
        this.schema = schema;
    }

    @Override
    public AddGraph shallowClone() throws CloneFailedException {
        return new Builder()
                .setGraphId(graphId)
                .schema(schema)
                .storeProperties(storeProperties)
                .parentSchemaIds(parentSchemaIds)
                .parentPropertiesId(parentPropertiesId)
                .build();
    }


    public List<String> getParentSchemaIds() {
        return parentSchemaIds;
    }

    public void setParentSchemaIds(final List<String> parentSchemaIds) {
        this.parentSchemaIds = parentSchemaIds;
    }

    public StoreProperties getStoreProperties() {
        return storeProperties;
    }

    public void setStoreProperties(final StoreProperties properties) {
        this.storeProperties = properties;
    }

    public String getParentPropertiesId() {
        return parentPropertiesId;
    }

    public void setParentPropertiesId(final String parentPropertiesId) {
        this.parentPropertiesId = parentPropertiesId;
    }

    public Set<String> getGraphAuths() {
        return graphAuths;
    }

    public AddGraph setGraphAuths(final Set<String> graphAuths) {
        if (null == graphAuths) {
            this.getGraphAuths().clear();
        } else {
            this.graphAuths = graphAuths;
        }
        return this;
    }

    public static class Builder extends BaseBuilder<AddGraph, Builder> {

        public Builder() {
            super(new AddGraph());
        }

        public Builder setGraphId(final String graphId) {
            _getOp().setGraphId(graphId);
            return this;
        }

        public Builder storeProperties(final StoreProperties storeProperties) {
            _getOp().setStoreProperties(storeProperties);
            return this;
        }

        public Builder schema(final Schema schema) {
            _getOp().setSchema(schema);
            return _self();
        }

        public Builder parentPropertiesId(final String parentPropertiesId) {
            this._getOp().setParentPropertiesId(parentPropertiesId);
            return _self();
        }

        public Builder parentSchemaIds(final List<String> parentSchemaIds) {
            _getOp().setParentSchemaIds(parentSchemaIds);
            return _self();
        }

        public Builder graphAuths(final String... graphAuths) {
            Collections.addAll(_getOp().graphAuths, graphAuths);
            return _self();
        }
    }
}
