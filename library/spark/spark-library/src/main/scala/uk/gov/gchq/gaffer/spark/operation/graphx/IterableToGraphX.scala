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
import org.apache.spark.SparkContext
import org.apache.spark.graphx.Graph
import uk.gov.gchq.gaffer.data.element.Element
import uk.gov.gchq.gaffer.operation.Operation
import uk.gov.gchq.gaffer.operation.io.InputOutput
import uk.gov.gchq.gaffer.spark.operation.scalardd.Rdd

class IterableToGraphX extends Operation with InputOutput[java.lang.Iterable[Element], Graph[_, _]] with Rdd{

  var input: java.lang.Iterable[Element] = null
  var sc: SparkContext = null

  override def getOutputTypeReference: TypeReference[Graph[_, _]] = ???

  override def getInput: java.lang.Iterable[Element] = input

  override def setInput(in: java.lang.Iterable[Element]) = input = in

  override def getSparkContext: SparkContext = sc

  override def setSparkContext(sparkContext: SparkContext): Unit = sc = sparkContext
}
