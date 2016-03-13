package ru.rambler.android.grid;


import android.view.Gravity;

public class Line {
    private final int width;
    private final int gravity;
    private final int margin;
    private final int marginType;
    private final int color;

    private Line(int width, int gravity, int margin, int marginType, int color) {
        this.width = width;
        this.gravity = gravity;
        this.margin = margin;
        this.marginType = marginType;
        this.color = color;
    }

    public static Line fromLeft(int margin, int marginType) {
        return new Line(1, Gravity.LEFT, margin, marginType, DebugColors.next());
    }

    public static Line fromRight(int margin, int marginType) {
        return new Line(1, Gravity.RIGHT, margin, marginType, DebugColors.next());
    }

    public static Line fromTop(int margin, int marginType) {
        return new Line(1, Gravity.TOP, margin, marginType, DebugColors.next());
    }

    public static Line fromBottom(int margin, int marginType) {
        return new Line(1, Gravity.BOTTOM, margin, marginType, DebugColors.next());
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

    public int getMarginType() {
        return marginType;
    }

    public int getColor() {
        return color;
    }
}
