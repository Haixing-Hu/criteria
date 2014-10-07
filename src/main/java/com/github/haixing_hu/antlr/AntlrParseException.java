/*
 * Copyright (C) 2014 Haixing Hu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
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
