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
package com.github.haixing_hu.criteria;

import com.github.haixing_hu.lang.Cloneable;

/**
 * A {@link Criterion} object represent a criterion used in filtering or
 * searching.
 *
 * @author Haixing Hu
 */
public abstract class Criterion implements Cloneable<Criterion> {

  protected CriterionType type;

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
