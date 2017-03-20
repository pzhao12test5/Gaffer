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
package uk.gov.gchq.gaffer.spark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.gov.gchq.gaffer.exception.SerialisationException;
import uk.gov.gchq.gaffer.jsonserialisation.JSONSerialiser;
import uk.gov.gchq.gaffer.operation.OperationChain;
import uk.gov.gchq.gaffer.operation.OperationChain.Builder;
import uk.gov.gchq.gaffer.operation.impl.get.GetAllElements;
import uk.gov.gchq.gaffer.spark.operation.graphx.IterableToGraphX;
import uk.gov.gchq.gaffer.spark.operation.graphx.PageRank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OperationChainTest {
    private static final JSONSerialiser serialiser = new JSONSerialiser();

    @Test
    public void shouldSerialiseAndDeserialiseOperationChain() throws SerialisationException, JsonProcessingException {
        // Given
        final OperationChain opChain = new Builder()
                .first(new GetAllElements())
//                .then(new IterableToGraphX())
//                .then(new PageRank())
                .build();

        // When
        byte[] json = serialiser.serialise(opChain, true);
        final OperationChain deserialisedOp = serialiser.deserialise(json, OperationChain.class);

        final ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(opChain));

        // Then
        assertNotNull(deserialisedOp);
        assertEquals(1, deserialisedOp.getOperations().size());
        assertEquals(PageRank.class, deserialisedOp.getOperations()
                                                   .get(0)
                                                   .getClass());
    }
}
