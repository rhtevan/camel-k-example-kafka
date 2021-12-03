/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// kamel run --secret kafka-props SaslSSLKafkaProducer.java --dev
// camel-k: language=java 
// camel-k: dependency=mvn:org.apache.camel.quarkus:camel-quarkus-kafka
/**
 * // camel-k: dependency=mvn:io.strimzi:kafka-oauth-client:0.7.1.redhat-00003
 */ 

import org.apache.camel.builder.RouteBuilder;

public class SaslSSLKafkaProducer extends RouteBuilder {
  @Override
  public void configure() throws Exception {
  log.info("About to start route: Timer -> Kafka ");
  from("timer:foo")
    .routeId("FromTimer2Kafka")
    .setBody()
      .simple("Message #${exchangeProperty.CamelTimerCounter}")
    .to("kafka:{{topic}}")
    .log("Message correctly sent to the topic!");
  }
}
