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

package uk.gov.gchq.gaffer.operation.impl.get;

import org.junit.Test;

import uk.gov.gchq.gaffer.commonutil.StringUtil;
import uk.gov.gchq.gaffer.operation.OperationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GetElementsDAOTest extends OperationTest<GetElementsDAO> {
    @Test
    public void shouldJsonDeserialiseWithoutGetElementsClassName() {
        // Given
        final byte[] json = StringUtil.toBytes(String.format("  {%n" +
                "    \"view\" : {%n" +
                "      \"edges\" : {%n" +
                "        \"BasicEdge\" : {%n" +
                "          \"properties\" : [ \"count\" ]%n" +
                "        }%n" +
                "      },%n" +
                "      \"entities\" : { }%n" +
                "    }%n" +
                "  } ]%n" +
                "}"));

        // When
        final GetElementsDAO deserialisedObj = fromJson(json);

        // Then
        assertEquals(GetElementsDAO.class, deserialisedObj.getClass());
    }

    @Test
    public void shouldJsonDeserialiseWithGetElementsClassName() {
        // Given
        final byte[] json = StringUtil.toBytes(String.format("  {%n" +
                "    \"class\" : \"uk.gov.gchq.gaffer.operation.impl.get.GetElements\",%n" +
                "    \"view\" : {%n" +
                "      \"edges\" : {%n" +
                "        \"BasicEdge\" : {%n" +
                "          \"properties\" : [ \"count\" ]%n" +
                "        }%n" +
                "      },%n" +
                "      \"entities\" : { }%n" +
                "    }%n" +
                "  } ]%n" +
                "}"));

        // When
        final GetElementsDAO deserialisedObj = fromJson(json);

        // Then
        assertEquals(GetElementsDAO.class, deserialisedObj.getClass());
    }

    @Test
    public void shouldThrowExceptionIfDeserialiedWithInvalidOperationClass() {
        // Given
        final byte[] json = StringUtil.toBytes(String.format("  {%n" +
                "    \"class\" : \"uk.gov.gchq.gaffer.operation.impl.get.GetAdjacentIds\",%n" +
                "    \"view\" : {%n" +
                "      \"edges\" : {%n" +
                "        \"BasicEdge\" : {%n" +
                "          \"properties\" : [ \"count\" ]%n" +
                "        }%n" +
                "      },%n" +
                "      \"entities\" : { }%n" +
                "    }%n" +
                "  } ]%n" +
                "}"));

        // When / Then
        try {
            fromJson(json);
            fail("Exception expected");
        } catch (final Exception e) {
            assertTrue(e.getMessage().contains("Invalid class"));
        }
    }

    @Override
    protected GetElementsDAO getTestObject() {
        return new GetElementsDAO();
    }

    @Override
    public void builderShouldCreatePopulatedOperation() {
        // Not required for the DAO as it is only used for JSON serialisation
    }

    @Override
    public void shouldShallowCloneOperation() {
        // Not required for the DAO as it is only used for JSON serialisation
    }
}
