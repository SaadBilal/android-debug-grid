package ru.rambler.android.grid;

import android.util.TypedValue;
import android.view.Gravity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LineTest {

    @Test
    public void testInstantiate() throws Exception {
        Line left = Line.fromLeft(1, TypedValue.COMPLEX_UNIT_DIP);
        assertEquals(Gravity.LEFT, left.getGravity());
        assertEquals(1, left.getMargin());

        Line right = Line.fromRight(2, TypedValue.COMPLEX_UNIT_DIP);
        assertEquals(Gravity.RIGHT, right.getGravity());
        assertEquals(2, right.getMargin());

        Line top = Line.fromTop(3, TypedValue.COMPLEX_UNIT_DIP);
        assertEquals(Gravity.TOP, top.getGravity());
        assertEquals(3, top.getMargin());

        Line bottom = Line.fromBottom(4, TypedValue.COMPLEX_UNIT_DIP);
        assertEquals(Gravity.BOTTOM, bottom.getGravity());
        assertEquals(4, bottom.getMargin());


    }
}