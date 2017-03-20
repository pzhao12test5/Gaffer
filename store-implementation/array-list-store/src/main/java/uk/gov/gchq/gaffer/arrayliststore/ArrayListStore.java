/*
 * Copyright 2016-2017 Crown Copyright
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

package uk.gov.gchq.gaffer.arrayliststore;

import com.google.common.collect.Sets;
import uk.gov.gchq.gaffer.arrayliststore.operation.handler.AddElementsHandler;
import uk.gov.gchq.gaffer.arrayliststore.operation.handler.GetAdjacentIdsHandler;
import uk.gov.gchq.gaffer.arrayliststore.operation.handler.GetAllElementsHandler;
import uk.gov.gchq.gaffer.arrayliststore.operation.handler.GetElementsHandler;
import uk.gov.gchq.gaffer.commonutil.iterable.CloseableIterable;
import uk.gov.gchq.gaffer.data.element.Edge;
import uk.gov.gchq.gaffer.data.element.Element;
import uk.gov.gchq.gaffer.data.element.Entity;
import uk.gov.gchq.gaffer.data.element.id.EntityId;
import uk.gov.gchq.gaffer.operation.Operation;
import uk.gov.gchq.gaffer.operation.impl.add.AddElements;
import uk.gov.gchq.gaffer.operation.impl.get.GetAdjacentIds;
import uk.gov.gchq.gaffer.operation.impl.get.GetAllElements;
import uk.gov.gchq.gaffer.operation.impl.get.GetElements;
import uk.gov.gchq.gaffer.store.Context;
import uk.gov.gchq.gaffer.store.Store;
import uk.gov.gchq.gaffer.store.StoreTrait;
import uk.gov.gchq.gaffer.store.operation.handler.OperationHandler;
import uk.gov.gchq.gaffer.store.operation.handler.OutputOperationHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static uk.gov.gchq.gaffer.store.StoreTrait.POST_AGGREGATION_FILTERING;
import static uk.gov.gchq.gaffer.store.StoreTrait.POST_TRANSFORMATION_FILTERING;
import static uk.gov.gchq.gaffer.store.StoreTrait.PRE_AGGREGATION_FILTERING;


/**
 * A simple in memory {@link java.util.ArrayList} implementation of {@link Store}.
 * <p>
 * This store holds 2 {@link java.util.ArrayList}s one for {@link Entity} and one for
 * {@link Edge}. As the elements are simply
 * stored in lists they are not serialised and not indexed, so look ups require full scans.
 */
public class ArrayListStore extends Store {
    private static final Set<StoreTrait> TRAITS = Sets.newHashSet(PRE_AGGREGATION_FILTERING, POST_AGGREGATION_FILTERING, POST_TRANSFORMATION_FILTERING);
    private final List<Entity> entities = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();

    @Override
    public Set<StoreTrait> getTraits() {
        return TRAITS;
    }

    @Override
    public boolean isValidationRequired() {
        return false;
    }


    @Override
    protected OutputOperationHandler<GetElements, CloseableIterable<Element>> getGetElementsHandler() {
        return new GetElementsHandler();
    }

    @Override
    protected OutputOperationHandler<GetAllElements, CloseableIterable<Element>> getGetAllElementsHandler() {
        return new GetAllElementsHandler();
    }

    @Override
    protected OutputOperationHandler<GetAdjacentIds, CloseableIterable<EntityId>> getAdjacentIdsHandler() {
        return new GetAdjacentIdsHandler();
    }

    @Override
    protected OperationHandler<? extends AddElements> getAddElementsHandler() {
        return new AddElementsHandler();
    }

    /**
     * This store does not support any other optional operations.
     */
    @Override
    protected void addAdditionalOperationHandlers() {
    }

    @Override
    protected Object doUnhandledOperation(final Operation operation, final Context context) {
        throw new UnsupportedOperationException("I do not know how to handle: " + operation.getClass().getSimpleName());
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addElements(final Iterable<Element> elements) {
        for (final Element element : elements) {
            if (element instanceof Entity) {
                entities.add((Entity) element);
            } else {
                // Assume it is an Edge
                edges.add((Edge) element);
            }
        }
    }
}