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

// To run this integrations use:
//
// kamel run --env MY_ENV_VAR="hello world" Env.java --dev
//

import org.apache.camel.builder.RouteBuilder;

public class Env extends RouteBuilder {
  @Override
  public void configure() throws Exception {
	  from("timer:tick")
	  	/* 
		 * Invalid syntax with Simple Expression
		 */
        // .log("${env:MY_ENV_VAR}");
		
		// https://camel.apache.org/blog/2021/01/camel-quarkus-configuration-tips/
		/*
		 * Simple Expresson loading environment variable
		 *  kamel run --dev --env MY_ENV_VAR="hello world" Env.java
		 */
        // .log("${env.MY_ENV_VAR}");


		/* 
		 * Simple Expression loading properties with default
		 * 	kamel run --dev --property my-env-var="hello camel-k" Env.java
		 * or
		 *  kamel run --dev Env.java
		 */
        // .log("${properties:my-env-var:\"hello world\"}");

		/*
		 * Simple Expression loading properties using {{ ... }}, which is the same as ${properties: }
		 *  kamel run --dev --property my-env-var="hello camel-k" Env.java
		 * or, ()
		 *  kamel run --dev --env MY_ENV_VAR="hello camel-k" Env.java
		 */
        .log("{{my-env-var}}");
  }
}