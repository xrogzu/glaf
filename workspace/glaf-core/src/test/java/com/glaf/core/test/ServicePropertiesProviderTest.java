/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.glaf.core.test;

import java.util.Properties;
import org.junit.Test;
import com.glaf.core.provider.ServicePropertiesProvider;
import com.glaf.core.provider.ServiceProvider;
import com.glaf.core.provider.SimpleService;

public class ServicePropertiesProviderTest {

	@Test
	public void test() {
		Properties props = ServicePropertiesProvider.getInstance()
				.getCoreProperties();
		java.util.Enumeration<?> e = props.keys();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.println(key + "=" + props.getProperty(key));
		}
		System.out.println(ServiceProvider.getInstance().get("simpleService"));
		System.out.println(ServiceProvider.getInstance().get(SimpleService.class));
	}

}
