/*
 * Copyright (C) 2011 The Android Open Source Project
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
package ru.github.igla.ferriswheel;

import android.util.Property;

/**
 * An implementation of {@link android.util.Property} to be used specifically with fields of type
 * <code>float</code>. This type-specific subclass enables performance benefit by allowing
 * calls to a {@link #setValue(Object, float) setValue()} function that takes the primitive
 * <code>float</code> type and avoids autoboxing and other overhead associated with the
 * <code>Float</code> class.
 *
 * @param <T> The class on which the Property is declared.
 */
public abstract class FloatProperty<T> extends Property<T, Float> {
 
    private FloatProperty(final String name) {
        super(Float.class, name);
    }

    @Override
    public Float get(final T object) {
        return 0f;
    }

    /**
     * A type-specific variant of {@link #set(Object, Float)} that is faster when dealing
     * with fields of type <code>float</code>.
     */
    public abstract void setValue(T object, float value);

    @Override
	public final void set(final T object, final Float value) {
        setValue(object, value);
    }

    public static FloatProperty<OnAngleChangeListener> createAngleProperty(final OnAngleChangeListener listener) {
        return new FloatProperty<>("angle") {
            @Override
            public void setValue(final OnAngleChangeListener obj, final float value) {
                listener.onValueChange(value);
            }
        };
    }
}