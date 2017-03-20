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

package uk.gov.gchq.gaffer.spark.operation.graphx

import com.fasterxml.jackson.core.`type`.TypeReference
import org.apache.spark.graphx.{Graph, VertexRDD}
import uk.gov.gchq.gaffer.operation.Operation
import uk.gov.gchq.gaffer.operation.io.InputOutput

class ToDegrees[VD, ED] {
//  extends Operation with InputOutput[Graph[VD, ED], VertexRDD[Int]] {
//
//  override def getOutputTypeReference: TypeReference[VertexRDD[Int]] = ???
//
//  override def getInput: Graph[VD, ED] = {Some[Object]}
//
//  override def setInput(input: Graph[VD, ED]): Unit = ???
}
