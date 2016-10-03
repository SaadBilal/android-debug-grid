package com.github.nnesterov.android.grid;

import android.util.TypedValue;
import android.view.Gravity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LineTest {

    @Test
    public void instantiateLeft_setCorrectValues() throws Exception {
        Line left = Line.fromLeft(1, TypedValue.COMPLEX_UNIT_DIP);

        assertEquals(Gravity.LEFT, left.getGravity());
        assertEquals(1, left.getMargin());
    }

    @Test
    public void instantiateRight_setCorrectValues() throws Exception {
        Line right = Line.fromRight(2, TypedValue.COMPLEX_UNIT_DIP);

        assertEquals(Gravity.RIGHT, right.getGravity());
        assertEquals(2, right.getMargin());
    }

    @Test
    public void instantiateTop_setCorrectValues() throws Exception {
        Line top = Line.fromTop(3, TypedValue.COMPLEX_UNIT_DIP);

        assertEquals(Gravity.TOP, top.getGravity());
        assertEquals(3, top.getMargin());
    }

    @Test
    public void instantiateBottom_setCorrectValues() throws Exception {
        Line bottom = Line.fromBottom(4, TypedValue.COMPLEX_UNIT_DIP);

        assertEquals(Gravity.BOTTOM, bottom.getGravity());
        assertEquals(4, bottom.getMargin());
    }


}