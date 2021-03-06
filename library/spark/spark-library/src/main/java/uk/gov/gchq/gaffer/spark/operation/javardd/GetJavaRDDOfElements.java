/*
 * Copyright 2016 Crown Copyright
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
package uk.gov.gchq.gaffer.spark.operation.javardd;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.spark.api.java.JavaRDD;

import uk.gov.gchq.gaffer.data.element.Element;
import uk.gov.gchq.gaffer.data.element.id.DirectedType;
import uk.gov.gchq.gaffer.data.element.id.ElementId;
import uk.gov.gchq.gaffer.data.elementdefinition.view.View;
import uk.gov.gchq.gaffer.operation.Operation;
import uk.gov.gchq.gaffer.operation.graph.SeededGraphFilters;
import uk.gov.gchq.gaffer.operation.io.InputOutput;
import uk.gov.gchq.gaffer.operation.io.MultiInput;
import uk.gov.gchq.gaffer.spark.serialisation.TypeReferenceSparkImpl;

import java.util.Map;

/**
 * A {@code GetJavaRDDOfElements} operation retrieves all the {@link Element}s
 * for the input seeds from the target store, and returns them inside a {@link JavaRDD}.
 */
public class GetJavaRDDOfElements implements
        InputOutput<Iterable<? extends ElementId>, JavaRDD<Element>>,
        MultiInput<ElementId>,
        SeededGraphFilters {

    private Map<String, String> options;
    private Iterable<? extends ElementId> input;
    private IncludeIncomingOutgoingType inOutType;
    private View view;
    private DirectedType directedType;

    public GetJavaRDDOfElements() {
    }

    @Override
    public Map<String, String> getOptions() {
        return options;
    }

    @Override
    public void setOptions(final Map<String, String> options) {
        this.options = options;
    }

    @Override
    public TypeReference<JavaRDD<Element>> getOutputTypeReference() {
        return new TypeReferenceSparkImpl.JavaRDDElement();
    }

    @Override
    public Iterable<? extends ElementId> getInput() {
        return input;
    }

    @Override
    public void setInput(final Iterable<? extends ElementId> input) {
        this.input = input;
    }

    @Override
    public IncludeIncomingOutgoingType getIncludeIncomingOutGoing() {
        return inOutType;
    }

    @Override
    public void setIncludeIncomingOutGoing(final IncludeIncomingOutgoingType inOutType) {
        this.inOutType = inOutType;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setView(final View view) {
        this.view = view;
    }

    @Override
    public DirectedType getDirectedType() {
        return directedType;
    }

    @Override
    public void setDirectedType(final DirectedType directedType) {
        this.directedType = directedType;
    }

    @Override
    public GetJavaRDDOfElements shallowClone() {
        return new GetJavaRDDOfElements.Builder()
                .options(options)
                .input(input)
                .inOutType(inOutType)
                .view(view)
                .directedType(directedType)
                .build();
    }

    public static class Builder extends BaseBuilder<GetJavaRDDOfElements, Builder>
            implements InputOutput.Builder<GetJavaRDDOfElements, Iterable<? extends ElementId>, JavaRDD<Element>, Builder>,
            MultiInput.Builder<GetJavaRDDOfElements, ElementId, Builder>,
            SeededGraphFilters.Builder<GetJavaRDDOfElements, Builder>,
            Operation.Builder<GetJavaRDDOfElements, Builder> {
        public Builder() {
            super(new GetJavaRDDOfElements());
        }
    }
}
