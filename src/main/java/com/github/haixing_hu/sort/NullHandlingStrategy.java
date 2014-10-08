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

package com.github.haixing_hu.sort;

/**
 * The enumeration of strategies for handling {@code null} values in sorting.
 *
 * @author Haixing Hu
 */
public enum NullHandlingStrategy {

  /**
   * Lets the data store decide what to do with nulls.
   */
  NATIVE,

  /**
   * A hint to the used data store to order entries with null values before non
   * null entries.
   */
  NULLS_FIRST,

  /**
   * A hint to the used data store to order entries with null values after non
   * null entries.
   */
  NULLS_LAST,
}
