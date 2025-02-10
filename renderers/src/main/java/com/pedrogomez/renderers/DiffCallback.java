/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pedrogomez.renderers;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

class DiffCallback<T> extends DiffUtil.Callback {

  private final List<T> oldList;
  private final List<T> newList;

  /**
   * @deprecated Use {@link #DiffCallback(List, List)} function instead.
   * This constructor is going to be removed in upcoming version.
   */
  @Deprecated
  DiffCallback(AdapteeCollection<T> oldList, List<T> newList) {
    try {
      this.oldList = (List) oldList;
    } catch (ClassCastException exception) {
      throw new ClassCastException("oldList parameter needs to implement List interface. "
              + "AdapteeCollection has been deprecated and will disappear in upcoming version");
    }
    this.newList = newList;
  }

  DiffCallback(List<T> oldList, List<T> newList) {
    this.oldList = oldList;
    this.newList = newList;
  }

  @Override public int getOldListSize() {
    return oldList.size();
  }

  @Override public int getNewListSize() {
    return newList.size();
  }

  @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    T oldItem = oldList.get(oldItemPosition);
    T newItem = newList.get(newItemPosition);
    boolean areTheSameInstance = oldItem == newItem;
    boolean hasTheSameType = oldItem.getClass().equals(newItem.getClass());
    boolean hasTheSameHash = oldItem.hashCode() == newItem.hashCode();
    return areTheSameInstance || hasTheSameType && hasTheSameHash;
  }

  @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
  }
}
