package ru.rambler.android.grid;


public final class DebugColors {
    private static int lastIndex = 0;
    private static int[] colors = new int[]{
            0xFF0000,
            0x00FF00,
            0x0000FF
    };

    private DebugColors() {
    }

    public static int next() {
        return colors[(lastIndex++) % colors.length];
    }
}
