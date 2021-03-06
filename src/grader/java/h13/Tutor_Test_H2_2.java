package h13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

import javax.swing.JButton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

@TestForSubmission("h13")
public class Tutor_Test_H2_2 {

    MyPanel mp;
    MainFrame mf;
    ControlFrame cf;
    JButton addEllipseButton;
    JButton removeEllipseButton;

    @BeforeEach
    void before() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PCDReplacementTutor.mfInstances.clear();
        PCDReplacementTutor.cfInstances.clear();
        PCDReplacementTutor.pcdInstances.clear();
        mp = spy(new MyPanel());
        mf = spy(new MainFrame(mp));
        cf = spy(new ControlFrame(mf));
        doNothing().when(mf).setVisible(ArgumentMatchers.anyBoolean());
        doNothing().when(cf).setVisible(ArgumentMatchers.anyBoolean());
        cf.pcd = spy(cf.pcd);
        doNothing().when(cf.pcd).showNumberChangeDialog(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
                ArgumentMatchers.any());
        doNothing().when(cf.pcd).showEnumChangeDialogue(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt(), ArgumentMatchers.any(), ArgumentMatchers.any());
        try {
            mf.init();
            cf.init();
        } catch (Exception e) {
            // Try anyways
        }
        try {
            addEllipseButton = (JButton) ControlFrame.class.getDeclaredField("addEllipseButton").get(cf);
        } catch (Exception e) {
            addEllipseButton = (JButton) ControlFrame.class.getDeclaredField("addElipseButton").get(cf);
        }
        try {
            removeEllipseButton = (JButton) ControlFrame.class.getDeclaredField("removeEllipseButton").get(cf);
        } catch (Exception e) {
            removeEllipseButton = (JButton) ControlFrame.class.getDeclaredField("removeElipseButton").get(cf);
        }
    }

    @AfterEach
    void closeIt() {
        mf.dispose();
        cf.dispose();
        for (var mf : PCDReplacementTutor.mfInstances) {
            mf.dispose();
        }
        PCDReplacementTutor.mfInstances.clear();
        for (var cf : PCDReplacementTutor.cfInstances) {
            cf.dispose();
        }
        PCDReplacementTutor.mfInstances.clear();
        for (var pcd : PCDReplacementTutor.pcdInstances) {
            pcd.dispose();
        }
        PCDReplacementTutor.pcdInstances.clear();
    }

    @Test
    public void testLayout() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        var layout = cf.getContentPane().getLayout();
        assertInstanceOf(GridLayout.class, layout);
        var gl = (GridLayout) layout;
        assertEquals(4, gl.getRows(), "The Grid Layout was not used as Layout manager.");
        assertEquals(3, gl.getColumns());
    }

    @Test
    public void testComponents()
            throws IllegalArgumentException, IllegalAccessException, SecurityException, RuntimeException, Throwable {
        var layout = cf.getContentPane().getLayout();
        assertInstanceOf(GridLayout.class, layout, "The Grid Layout was not used as Layout manager.");
        var gl = (GridLayout) layout;
        assertEquals(4, gl.getRows(), "Inkorrekte Zeilenanzahl des GridLayouts.");
        assertEquals(3, gl.getColumns(), "Inkorrekte Spaltenanzahl des GridLayouts.");
        assertEquals(addEllipseButton, cf.getContentPane().getComponent(0),
                "Die Komponennte addEllipseButton wurde nicht an der korrekten Position hinzugef??gt.");
        assertEquals(cf.addRectangleButton, cf.getContentPane().getComponent(1),
                "Die Komponennte addRectangleButton wurde nicht an der korrekten Position hinzugef??gt.");
        assertEquals(cf.addStringButton, cf.getContentPane().getComponent(2),
                "Die Komponennte addStringButton wurde nicht an der korrekten Position hinzugef??gt.");
        assertEquals(removeEllipseButton, cf.getContentPane().getComponent(3),
                "Die Komponennte removeEllipseButton wurde nicht an der korrekten Position hinzugef??gt.");
        assertEquals(cf.removeRectangleButton, cf.getContentPane().getComponent(4),
                "Die Komponennte removeRectangleButton wurde nicht an der korrekten Position hinzugef??gt.");
        assertEquals(cf.removeStringButton, cf.getContentPane().getComponent(5),
                "Die Komponennte removeStringButton wurde nicht an der korrekten Position hinzugef??gt.");
        assertEquals(cf.changeSaturationButton, cf.getContentPane().getComponent(6),
                "Die Komponennte changeSaturationButton wurde nicht an der korrekten Position hinzugef??gt.");

        assertEquals(
                ((JButton) Arrays.stream(cf.getClass().getDeclaredFields())
                        .filter(x -> x.getName().equals("changeTransparencyButton")
                                || x.getName().equals("changeAlphaButton"))
                        .peek(x -> System.out.println(x.getName())).findFirst()
                        .orElseThrow(() -> fail("Change Alpha Button not Found")).get(cf)),
                cf.getContentPane().getComponent(7),
                "Die Komponennte changeAlphaButton(bzw changeTransparencyButton bei alter Vorlagenversion) wurde nicht an der korrekten Position hinzugef??gt.");
        assertEquals(cf.changeBorderWidthButton, cf.getContentPane().getComponent(8),
                "Die Komponente changeBorderWidthButton wurde nicht an der korrekten Position hinzugef??gt.");
        assertEquals(cf.changeFontButton, cf.getContentPane().getComponent(9),
                "Die Komponente changeFontButton wurde nicht an der korrekten Position hinzugef??gt.");
        assertEquals(cf.changeZoomButton, cf.getContentPane().getComponent(10),
                "Die Komponente changeZoomButton wurde nicht an der korrekten Position hinzugef??gt.");
        assertEquals(cf.exitButton, cf.getContentPane().getComponent(11),
                "Die Komponente exitButton wurde nicht an der korrekten Position hinzugef??gt.");
    }

    @Test
    public void testAddButtons()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // Green Ellipse
        try {
            addEllipseButton.doClick();
        } catch (Exception e) {
        }
        verify(mp, times(1)).addGreenEllipse();

        // Yellow Rectangle
        try {
            cf.addRectangleButton.doClick();
        } catch (Exception e) {
        }
        verify(mp, times(1)).addYellowRectangle();

        // Blue String
        try {
            cf.addStringButton.doClick();
        } catch (Exception e) {
        }
        verify(mp, times(1)).addBlueString();
    }

    @Test
    public void testRemoveButtons()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        // Green Ellipse
        try {
            removeEllipseButton.doClick();
        } catch (Exception e) {
        }
        verify(mp, times(1)).removeGreenEllipse();

        // Yellow Rectangle
        try {
            cf.removeRectangleButton.doClick();
        } catch (Exception e) {
        }
        verify(mp, times(1)).removeYellowRectangle();

        // Blue String
        try {
            cf.removeStringButton.doClick();
        } catch (Exception e) {
        }
        verify(mp, times(1)).removeBlueString();
    }

    @Test
    public void testChangeSaturationButtons() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException, Throwable {
        // Green Ellipse
        var mockingDetails = Mockito.mockingDetails(cf.pcd);
        try {
            cf.changeSaturationButton.doClick();
        } catch (Exception e) {
        }
        var x = mockingDetails.getInvocations().stream()
                .filter(y -> y.getMethod().getName() == "showNumberChangeDialog" && y.getArguments().length == 6)
                .findFirst().orElseThrow(() -> fail("showNumberChangeDialog was not called."));
        var params = x.getArguments();
        // String title = (String) params[0];
        // String propertyName = (String) params[1];
        int min = (int) params[2];
        int max = (int) params[3];
        // int current = (int) params[4];
        IntConsumer updateValue = (IntConsumer) params[5];
        assertTrue(min <= max, "min muss kleiner als max sein.");
        assertTrue(min >= 0, "min muss >= 0 sein.");
        assertTrue(max <= 100, "max darf nicht > 100 sein.");
        mp.saturation = 1;
        updateValue.accept(10);
        TestUtils.assertEqualInRange(mp.saturation, 0.1f, 0.01, "Die eingestellte S??ttigung wurde nicht ??bernommen.");
    }

    @Test
    public void testChangeAlphaButton() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException, Throwable {
        // var logger = Jagr.Default.getInjector().getInstance(Logger.class);
        // logger.warn("AM HERE");
        // Green Ellipse
        var mockingDetails = Mockito.mockingDetails(cf.pcd);
        try {
            ((JButton) Arrays.stream(ControlFrame.class.getDeclaredFields())
                    .filter(x -> x.getName().equals("changeTransparencyButton")
                            || x.getName().equals("changeAlphaButton"))
                    .peek(x -> System.out.println(x.getName())).findFirst()
                    .orElseThrow(() -> fail("Change Alpha Button not Found")).get(cf)).doClick();
        } catch (Exception e) {
        }
        // logger.warn("Here Also");
        var x = mockingDetails.getInvocations().stream()
                .filter(y -> y.getMethod().getName() == "showNumberChangeDialog" && y.getArguments().length == 6)
                .findFirst().orElseThrow(() -> fail("showNumberChangeDialog was not called."));
        // logger.warn("BUT LIKELY NOT HERE");

        var params = x.getArguments();
        // String title = (String) params[0];
        // String propertyName = (String) params[1];
        int min = (int) params[2];
        int max = (int) params[3];
        // int current = (int) params[4];
        IntConsumer updateValue = (IntConsumer) params[5];
        assertTrue(min <= max, "min muss kleiner als max sein.");
        assertTrue(min >= 0, "min muss >= 0 sein.");
        assertTrue(max <= 100, "max darf nicht > 100 sein.");
        mp.alpha = 1;
        updateValue.accept(10);
        TestUtils.assertEqualInRange(mp.alpha, 0.1f, 0.01);
        // logger.warn("AAAAND IT WORKS SOMEHOW");
    }

    @Test
    public void testChangeZoomButtons() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException, Throwable {
        // Green Ellipse
        var mockingDetails = Mockito.mockingDetails(cf.pcd);
        try {
            cf.changeZoomButton.doClick();
        } catch (Exception e) {
        }
        var x = mockingDetails.getInvocations().stream()
                .filter(y -> y.getMethod().getName() == "showNumberChangeDialog" && y.getArguments().length == 6)
                .findFirst().orElseThrow(() -> fail("showNumberChangeDialog was not called."));
        var params = x.getArguments();
        // String title = (String) params[0];
        // String propertyName = (String) params[1];
        int min = (int) params[2];
        int max = (int) params[3];
        // int current = (int) params[4];
        IntConsumer updateValue = (IntConsumer) params[5];
        assertTrue(min <= max, "min muss kleiner als max sein.");
        assertTrue(min > 0, "min muss > 0 sein");
        // assertTrue(max >= 0);
        mp.zoom = 1;
        updateValue.accept(10);
        TestUtils.assertEqualInRange(mp.zoom, 0.1f, 0.01,
                "Zoomfaktor wurde vom IntConsumer nicht korrekt aktualisiert.");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChangeFontButton() {
        // Green Ellipse
        var mockingDetails = Mockito.mockingDetails(cf.pcd);
        try {
            cf.changeFontButton.doClick();
        } catch (Exception e) {
        }
        Invocation x = null;
        try {
            x = mockingDetails.getInvocations().stream()
                    .filter(y -> y.getMethod().getName() == "showEnumChangeDialogue" && y.getArguments().length == 5)
                    .findFirst().orElse(null);
        } catch (Exception e) {
        }
        assertNotNull(x, "showEnumChangeDialog was not called.");
        var params = x.getArguments();
        String[] options = (String[]) params[3];
        Consumer<String> updateValue = (Consumer<String>) params[4];
        assertNotNull(options, "keine Optionen gegeben.");
        assertTrue(Arrays.stream(options).collect(Collectors.toSet()).size() >= 5,
                "zu wenige unterschiedliche Schriftarten gegeben");
        var curFont = new Font("Arial", Font.ITALIC, 42);
        mp.font = curFont;
        updateValue.accept(options[0]);
        assertNotEquals(curFont, mp.font, "Die eingestellte Font wurde nicht ??bernommen.");
    }

    @Test
    // @SuppressWarnings("deprecation")
    @ExtendWith(JagrExecutionCondition.class)
    public void testExitButton()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        try {
            cf.exitButton.doClick();
        } catch (Exception e) {
        }
        assertTrue(TutorSystem.exitCalled, "System.exit() wurde nicht aufgerufen.");
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testExitButton_alt()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        try {
            cf.exitButton.doClick();
        } catch (Exception e) {
        }
        assertFalse(cf.isVisible(), "Die Fenster sind noch sichtbar oder wurden nicht korrekt geschlossen.");
        assertFalse(cf.isDisplayable(), "Die Fenster sind noch sichtbar oder wurden nicht korrekt geschlossen.");
        assertFalse(mf.isVisible(), "Die Fenster sind noch sichtbar oder wurden nicht korrekt geschlossen.");
        assertFalse(mf.isDisplayable(), "Die Fenster sind noch sichtbar oder wurden nicht korrekt geschlossen.");
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testExitButton_alt2()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        EventQueueReplacementTutor.postedEvents.clear();
        try {
            cf.exitButton.doClick();
        } catch (Exception e) {
        }
        // assertTrue(EventQueueReplacementTutor.postedEvents.size() > 0);
        assertTrue(
                EventQueueReplacementTutor.postedEvents.stream().anyMatch(x -> x.getID() == WindowEvent.WINDOW_CLOSING),
                "Kein ClosingEvent wurde gefeuert.");
    }
}
