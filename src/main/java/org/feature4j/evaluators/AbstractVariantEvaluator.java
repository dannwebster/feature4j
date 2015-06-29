/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.feature4j.evaluators;

import org.feature4j.FeaturesContext;
import org.feature4j.VariantEvaluator;
import org.hamcrest.Matcher;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractVariantEvaluator implements VariantEvaluator {

  private final String variantValue;

  public AbstractVariantEvaluator(String variantValue) {
    this.variantValue = checkNotNull(variantValue, "variantValue must be set to a non-null value");
  }

  public abstract boolean matches(FeaturesContext ctx);

  @Override
  public Optional<String> evaluateVariant(FeaturesContext ctx) {
    if (matches(ctx)) {
      return Optional.of(variantValue);
    } else {
      return Optional.empty();
    }
  }
}