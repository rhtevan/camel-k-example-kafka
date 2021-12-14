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

// camel-k: language=java 
// camel-k: dependency=mvn:org.apache.camel.quarkus:camel-quarkus-kafka
// camel-k: dependency=mvn:io.quarkus:quarkus-apicurio-registry-avro
// camel-k: dependency=mvn:io.apicurio:apicurio-registry-serdes-avro-serde
// camel-k: dependency=github:rhtevan:camel-k-example-kafka:svcreg-SNAPSHOT

import java.util.Random;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.acme.kafka.User;

public class SvcRegKafkaProducer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:foo?period={{timer.period}}&delay={{timer.delay}}")
                .routeId("FromTimer2Kafka")
                .setBody(SvcRegKafkaProducer::generateUser)
                .to("kafka:{{kafka.topic.name}}")
                .log("Message sent correctly sent to the topic! : \"${body}\" ");
    }

    private static User generateUser(Exchange in) {
        Random random = new Random();
        User user = new User();
        user.setName("User-" + random.nextInt(100));
        user.setAge(random.nextInt(100) + 1);
        return user;
    }
}
