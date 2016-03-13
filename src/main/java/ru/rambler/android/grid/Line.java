package ru.rambler.android.grid;


import android.view.Gravity;

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

    public static Line fromLeft(int margin, int marginUnit) {
        return new Line(1, Gravity.LEFT, margin, marginUnit, DebugColors.next());
    }

    public static Line fromRight(int margin, int marginUnit) {
        return new Line(1, Gravity.RIGHT, margin, marginUnit, DebugColors.next());
    }

    public static Line fromTop(int margin, int marginUnit) {
        return new Line(1, Gravity.TOP, margin, marginUnit, DebugColors.next());
    }

    public static Line fromBottom(int margin, int marginUnit) {
        return new Line(1, Gravity.BOTTOM, margin, marginUnit, DebugColors.next());
    }

    public int getWidth() {
        return width;
    }

    public int getGravity() {
        return gravity;
    }

    public int getMargin() {
        return margin;
    }

    public int getMarginUnit() {
        return marginUnit;
    }

    public int getColor() {
        return color;
    }
}
