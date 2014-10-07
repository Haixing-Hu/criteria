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
import java.util.HashMap;
import java.util.Map;

import com.github.haixing_hu.lang.Equality;
import com.github.haixing_hu.lang.Hash;
import com.github.haixing_hu.lang.StringUtils;
import com.github.haixing_hu.text.tostring.ToStringBuilder;

import static com.github.haixing_hu.lang.Argument.requireNonNull;

/**
 * A Sort object represents a sorting rule while selecting objects.
 *
 * @author Haixing Hu
 */
public final class Sort {

  private Map<String, SortOrder> rules;

  public Sort() {
    rules = null;
  }

  public Sort(final String property, final SortOrder order) {
    requireNonNull("property", property);
    requireNonNull("order", order);
    rules = new HashMap<String, SortOrder>();
    rules.put(property, order);
  }

  public void add(final String property, final SortOrder order) {
    requireNonNull("property", property);
    requireNonNull("order", order);
    if (rules == null) {
      rules = new HashMap<String, SortOrder>();
    }
    rules.put(property, order);
  }

  public String toSql() {
    if ((rules == null) || rules.isEmpty()) {
      return StringUtils.EMPTY;
    }
    final StringBuilder builder = new StringBuilder();
    int index = 0;
    for (final Map.Entry<String, SortOrder> entry : rules.entrySet()) {
      if (index > 0) {
        builder.append(',').append(' ');
      }
      final String property = entry.getKey();
      final SortOrder order = entry.getValue();
      builder.append(property)
             .append(' ')
             .append(order.symbol());
      ++index;
    }
    return builder.toString();
  }

  @Override
  public int hashCode() {
    final int multiplier = 357;
    int code = 799;
    code = Hash.combine(code, multiplier, rules);
    return code;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Sort other = (Sort) obj;
    return Equality.equals(rules, other.rules);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
                  .append("rules", rules)
                  .toString();
  }
}
