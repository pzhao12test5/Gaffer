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

package koryphe.tuple.tuplen;

/**
 * A {@link koryphe.tuple.Tuple} with five values of the specified generic types.
 * @param <A> Type of first tuple value.
 * @param <B> Type of second tuple value.
 * @param <C> Type of third tuple value.
 * @param <D> Type of fourth tuple value.
 * @param <E> Type of fifth tuple value.
 */
public interface Tuple5<A, B, C, D, E> extends Tuple4<A, B, C, D> {
    /**
     * Get the value at index 4.
     * @return Value.
     */
    E get4();

    /**
     * Put a value into index 4.
     * @param e Value to put.
     */
    void put4(final E e);
}