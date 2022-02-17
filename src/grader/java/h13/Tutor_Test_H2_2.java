package h13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission("h13")
public class Tutor_Test_H2_2 {
    @Test
    public void testLayout() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        var mp = new MyPanel();
        var mf = new MainFrame(mp);
        mf.init();
        var cf = new ControlFrame(mf);
        cf.init();

        var layout = cf.getContentPane().getLayout();
        assertInstanceOf(GridLayout.class, layout);
        var gl = (GridLayout) layout;
        assertEquals(4, gl.getRows());
        assertEquals(3, gl.getColumns());
    }

    @Test
    public void testComponents() {
        var mp = new MyPanel();
        var mf = new MainFrame(mp);
        mf.init();
        var cf = new ControlFrame(mf);
        cf.init();

        var layout = cf.getContentPane().getLayout();
        assertInstanceOf(GridLayout.class, layout);
        var gl = (GridLayout) layout;
        assertEquals(4, gl.getRows());
        assertEquals(3, gl.getColumns());
        assertEquals(cf.addEllipseButton, cf.getContentPane().getComponent(0));
        assertEquals(cf.addRectangleButton, cf.getContentPane().getComponent(1));
        assertEquals(cf.addStringButton, cf.getContentPane().getComponent(2));
        assertEquals(cf.removeEllipseButton, cf.getContentPane().getComponent(3));
        assertEquals(cf.removeRectangleButton, cf.getContentPane().getComponent(4));
        assertEquals(cf.removeStringButton, cf.getContentPane().getComponent(5));
        assertEquals(cf.changeSaturationButton, cf.getContentPane().getComponent(6));
        assertEquals(cf.changeTransparencyButton, cf.getContentPane().getComponent(7));
        assertEquals(cf.changeBorderWidthButton, cf.getContentPane().getComponent(8));
        assertEquals(cf.changeFontButton, cf.getContentPane().getComponent(9));
        assertEquals(cf.changeZoomButton, cf.getContentPane().getComponent(10));
        assertEquals(cf.exitButton, cf.getContentPane().getComponent(11));
    }
}
