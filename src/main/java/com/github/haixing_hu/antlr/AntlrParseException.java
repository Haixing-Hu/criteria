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
package com.github.haixing_hu.antlr;

/**
 * A runtime exception thrown when a parsing error occurs.
 *
 * @author Haixing Hu
 */
public class AntlrParseException extends RuntimeException {

  private static final long serialVersionUID = 3330615060710104689L;

  private final String input;
  private final int line;
  private final int position;

  public AntlrParseException(String input, int line, int position,
      String message) {
    super(message);
    this.input = input;
    this.line = line;
    this.position = position;
  }

  /**
   * Gets the input.
   *
   * @return the input.
   */
  public String getInput() {
    return input;
  }

  /**
   * Gets the line.
   *
   * @return the line.
   */
  public int getLine() {
    return line;
  }

  /**
   * Gets the position.
   *
   * @return the position.
   */
  public int getPosition() {
    return position;
  }
}
