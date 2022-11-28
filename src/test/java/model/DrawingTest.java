package model;

import drawing_software.model.DrawableRectangle;
import drawing_software.model.Drawing;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DrawingTest {
    private Drawing d;
    private DrawableRectangle dr;

    @Before
    public void setUp() {
        d = new Drawing();
        dr = new DrawableRectangle(33, 33);
    }

    @Test
    public void testAddDrawable() {
        d.addDrawable(dr);
        DrawableRectangle d2 = (DrawableRectangle) d.removeDrawable(0);
        assertEquals(d2, dr);
    }

    @Test
    public void testGetDrawable() {
        d.addDrawable(dr);
        DrawableRectangle d3 = (DrawableRectangle) d.getDrawable(0);
        assertEquals(d3, dr);
    }

    @Test
    public void testContainsDrawable() {
        d.addDrawable(dr);
        assertTrue(d.containsDrawable(dr));
    }
}
