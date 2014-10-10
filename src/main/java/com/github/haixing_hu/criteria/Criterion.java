/******************************************************************************
 *
 * Copyright (c) 2014  Haixing Hu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/
package com.github.haixing_hu.criteria;

import com.github.haixing_hu.lang.Cloneable;

/**
 * A {@link Criterion} object represent a criterion used in filtering or
 * searching.
 *
 * @author Haixing Hu
 */
public abstract class Criterion implements Cloneable<Criterion> {

  private final CriterionType type;

  /**
   * Constructs a {@link Criterion}.
   *
   * @param type
   *          the type of the new {@link Criterion}.
   */
  protected Criterion(final CriterionType type) {
    this.type = type;
  }

  /**
   * Gets the type of this criterion.
   *
   * @return the type of this criterion.
   */
  public final CriterionType getType() {
    return type;
  }

  @Override
  public abstract Criterion clone();
}
