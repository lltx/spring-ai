/*
 * Copyright 2024-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.ai.autoconfigure.stabilityai;

import org.springframework.ai.stabilityai.StabilityAiImageClient;
import org.springframework.ai.stabilityai.api.StabilityAiApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

/**
 * @author Mark Pollack
 * @since 0.8.0
 */
@EnableConfigurationProperties({ StabilityAiImageProperties.class })
public class StabilityAiImageAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public StabilityAiApi stabilityAiApi(StabilityAiImageProperties stabilityAiImageProperties,
			RestClient.Builder restClientBuilder) {
		return new StabilityAiApi(stabilityAiImageProperties.getApiKey(), stabilityAiImageProperties.getBaseUrl(),
				stabilityAiImageProperties.getOptions().getModel(), restClientBuilder);
	}

	@Bean
	@ConditionalOnMissingBean
	public StabilityAiImageClient stabilityAiImageClient(StabilityAiApi stabilityAiApi,
			StabilityAiImageProperties stabilityAiImageProperties) {
		return new StabilityAiImageClient(stabilityAiApi, stabilityAiImageProperties.getOptions());
	}

}
