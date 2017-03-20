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

package uk.gov.gchq.gaffer.sparkaccumulo.operation.handler

import org.apache.spark.graphx.{Edge, Graph, VertexRDD}
import org.apache.spark.rdd.RDD
import uk.gov.gchq.gaffer.data.element
import uk.gov.gchq.gaffer.data.element.Entity
import uk.gov.gchq.gaffer.spark.operation.graphx.IterableToGraphX
import uk.gov.gchq.gaffer.store.operation.handler.OutputOperationHandler
import uk.gov.gchq.gaffer.store.{Context, Store}

class IterableToGraphXHandler {}

//  extends OutputOperationHandler[IterableToGraphX, Graph[Entity, uk.gov.gchq.gaffer.data.element.Edge]] {
//
//  override def doOperation(operation: IterableToGraphX, context: Context, store: Store): Graph[Entity, element.Edge] = {
////    val input = operation.getInput
////
////    val sc = operation.getSparkContext
////
////    val vertices: VertexRDD[Entity] = VertexRDD(sc.parallelize(input.filter(p => p.isInstanceOf[Entity]).toSeq)
////      .map(e => (e.hashCode(), e.asInstanceOf[Entity])))
////
////    val edges: RDD[Edge[uk.gov.gchq.gaffer.data.element.Edge]]
////    = sc.parallelize(input.filter(p => p.isInstanceOf[uk.gov.gchq.gaffer.data.element.Edge])
////      .map(e => e.asInstanceOf[uk.gov.gchq.gaffer.data.element.Edge])
////      .map(e => new Edge(e.getSource.hashCode(), e.getDestination.hashCode(), e))
////      .toSeq)
////
////    Graph(vertices, edges)
//  null
//  }
//}
