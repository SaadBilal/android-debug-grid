/*
 * Copyright 2016 Rambler&Co
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.github.nnesterov.android.grid;

import android.view.Gravity;

/**
 * Single line for debug grid
 */
public class Line {
    private final int width;
    private final int gravity;
    private final int margin;
    private final int marginUnit;
    private final int color;

    private Line(int width, int gravity, int margin, int marginUnit, int color) {
        this.width = width;
        this.gravity = gravity;
        this.margin = margin;
        this.marginUnit = marginUnit;
        this.color = color;
    }

    /**
     * Create and return new line aligned at left edge of screen
     *
     * @param margin     margin value from left edge
     * @param marginUnit unit of margin value. Must be one of TypedValue.COMPLEX_UNIT_PX TypedValue.COMPLEX_UNIT_DIP,
     *                   TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_MM
     * @return newly created line
     */
    public static Line fromLeft(int margin, int marginUnit) {
        return new Line(1, Gravity.LEFT, margin, marginUnit, DebugColors.next());
    }

    /**
     * Create and return new line aligned at right edge of screen
     *
     * @param margin     margin value from right edge
     * @param marginUnit unit of margin value. Must be one of TypedValue.COMPLEX_UNIT_PX TypedValue.COMPLEX_UNIT_DIP,
     *                   TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_MM
     * @return newly created line
     */
    public static Line fromRight(int margin, int marginUnit) {
        return new Line(1, Gravity.RIGHT, margin, marginUnit, DebugColors.next());
    }

    /**
     * Create and return new line aligned at top edge of screen
     *
     * @param margin     margin value from top edge
     * @param marginUnit unit of margin value. Must be one of TypedValue.COMPLEX_UNIT_PX TypedValue.COMPLEX_UNIT_DIP,
     *                   TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_MM
     * @return newly created line
     */
    public static Line fromTop(int margin, int marginUnit) {
        return new Line(1, Gravity.TOP, margin, marginUnit, DebugColors.next());
    }

    /**
     * Create and return new line aligned at bottom edge of screen
     *
     * @param margin     margin value from bottom edge
     * @param marginUnit unit of margin value. Must be one of TypedValue.COMPLEX_UNIT_PX TypedValue.COMPLEX_UNIT_DIP,
     *                   TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_MM
     * @return newly created line
     */
    public static Line fromBottom(int margin, int marginUnit) {
        return new Line(1, Gravity.BOTTOM, margin, marginUnit, DebugColors.next());
    }

    /**
     * @return width in px of line
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return gravity of this line
     */
    public int getGravity() {
        return gravity;
    }

    /**
     * @return margin value from edge of this line
     */
    public int getMargin() {
        return margin;
    }

    /**
     * @return margin unit of this line
     */
    public int getMarginUnit() {
        return marginUnit;
    }

    /**
     * @return hex value of color for this line
     */
    public int getColor() {
        return color;
    }
}
