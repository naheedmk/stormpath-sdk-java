/*
 * Copyright 2014 Stormpath, Inc.
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
package com.stormpath.sdk.oauth;

import com.stormpath.sdk.lang.Assert;
import com.stormpath.sdk.lang.Strings;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public interface ProviderAccountRequest {

    ProviderData getProviderData();

    abstract class Builder<T extends ProviderAccountRequestBuilder<T>> implements ProviderAccountRequestBuilder<T> {

        protected String accessToken;

        public T setAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return (T) this;
        }

        public ProviderAccountRequest build() {
            final String providerId = getProviderId();
            Assert.state(Strings.hasText(providerId), "The providerId property is missing.");

            Map<String, Object> properties = new LinkedHashMap<String, Object>();
            properties.put("providerId", providerId);

            return doBuild(Collections.unmodifiableMap(properties));
        }

        protected abstract String getProviderId();
        protected abstract ProviderAccountRequest doBuild(Map<String, Object> map);


    }

}
