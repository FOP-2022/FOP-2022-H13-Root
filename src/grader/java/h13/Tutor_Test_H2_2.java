package h13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mockConstructionWithAnswer;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle.Control;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.ArgumentMatchers;
import org.mockito.MockSettings;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
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
    public void testComponents() throws IllegalArgumentException, IllegalAccessException, SecurityException, RuntimeException {
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

        assertEquals(((JButton) Arrays
                .stream(cf.getClass().getDeclaredFields())
                .filter(x -> x.getName().equals("changeTransparencyButton")
                        || x.getName().equals("changeAlphaButton"))
                .peek(x -> System.out.println(x.getName()))
                .findFirst()
                .orElseThrow(
                        () -> fail(
                                "Change Alpha Button not Found"))
                .get(cf)), cf.getContentPane().getComponent(7));
        assertEquals(cf.changeBorderWidthButton, cf.getContentPane().getComponent(8));
        assertEquals(cf.changeFontButton, cf.getContentPane().getComponent(9));
        assertEquals(cf.changeZoomButton, cf.getContentPane().getComponent(10));
        assertEquals(cf.exitButton, cf.getContentPane().getComponent(11));
    }

    @Test
    public void testAddButtons()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // Modifier
        var mp = spy(new MyPanel());
        var mf = new MainFrame(mp);
        var cf = new ControlFrame(mf);
        var componentField = Container.class.getDeclaredField("component");
        componentField.setAccessible(true);
        componentField.set(mf, new ArrayList<Component>());
        try {
            mf.init();
            cf.init();
        } catch (Exception e) {
            // TODO: handle exception
        }

        // var buttons = new JButton[] {
        // cf.addEllipseButton,
        // cf.addRectangleButton,
        // cf.addStringButton,
        // cf.removeEllipseButton,
        // cf.removeRectangleButton,
        // cf.removeStringButton,
        // cf.changeSaturationButton,
        // cf.changeTransparencyButton,
        // cf.changeBorderWidthButton,
        // cf.changeFontButton,
        // cf.changeZoomButton,
        // cf.exitButton
        // };

        // Green Ellipse
        try {
            cf.addEllipseButton.doClick();
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
        var mp = spy(new MyPanel());
        var mf = new MainFrame(mp);
        var cf = new ControlFrame(mf);

        try {
            mf.init();
            cf.init();
        } catch (Exception e) {
            // Try anyways
        }

        // Green Ellipse
        try {
            cf.removeEllipseButton.doClick();
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
    public void testChangeSaturationButtons()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        var mp = new MyPanel();
        var mf = new MainFrame(mp);
        var cf = new ControlFrame(mf);

        var pcdField = cf.getClass().getDeclaredField("pcd");
        pcdField.setAccessible(true);
        pcdField.set(cf, spy(mock(PropertyChangeDialogue.class)));

        // cf.pcd = spy(cf.pcd);
        var componentField = Container.class.getDeclaredField("component");
        componentField.setAccessible(true);
        componentField.set(mf, new ArrayList<Component>());
        try {
            mf.init();
            cf.init();
        } catch (Exception e) {
            // Try anyways
        }

        // Green Ellipse
        var mockingDetails = Mockito.mockingDetails(cf.pcd);
        try {
            cf.changeSaturationButton.doClick();
        } catch (Exception e) {
        }
        mockingDetails.getInvocations().stream()
                .filter(x -> x.getMethod().getName() == "showNumberChangeDialog" && x.getArguments().length == 6)
                .forEach(
                        x -> {
                            var params = x.getArguments();
                            String title = (String) params[0];
                            String propertyName = (String) params[1];
                            int min = (int) params[2];
                            int max = (int) params[3];
                            int current = (int) params[4];
                            IntConsumer updateValue = (IntConsumer) params[5];
                            assertTrue(min <= max, "min ist kleiner als max");
                            assertTrue(min >= 0, "min muss >= 0 sein.");
                            assertTrue(max <= 100, "max darf nicht > 100 sein");
                            mp.saturation = 1;
                            updateValue.accept(10);
                            TestUtils.assertEqualFloored(mp.saturation, 0.1);
                        });
    }

    @Test
    public void testChangeAlphaButton()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        var mp = new MyPanel();
        var mf = new MainFrame(mp);
        var cf = new ControlFrame(mf);

        var pcdField = cf.getClass().getDeclaredField("pcd");
        pcdField.setAccessible(true);
        pcdField.set(cf, spy(mock(PropertyChangeDialogue.class)));
        // cf.pcd = spy(cf.pcd);
        var componentField = Container.class.getDeclaredField("component");
        componentField.setAccessible(true);
        componentField.set(mf, new ArrayList<Component>());
        try {
            mf.init();
            cf.init();
        } catch (Exception e) {
            // Try anyways
        }

        // Green Ellipse
        var mockingDetails = Mockito.mockingDetails(cf.pcd);
        try {
            ((JButton) Arrays
                    .stream(cf.getClass().getDeclaredFields())
                    .filter(x -> x.getName().equals("changeTransparencyButton")
                            || x.getName().equals("changeAlphaButton"))
                    .peek(x -> System.out.println(x.getName()))
                    .findFirst()
                    .orElseThrow(
                            () -> fail(
                                    "Change Alpha Button not Found"))
                    .get(cf))
                            .doClick();
        } catch (Exception e) {
        }
        mockingDetails.getInvocations().stream()
                .filter(x -> x.getMethod().getName() == "showNumberChangeDialog" && x.getArguments().length == 6)
                .forEach(
                        x -> {
                            var params = x.getArguments();
                            String title = (String) params[0];
                            String propertyName = (String) params[1];
                            int min = (int) params[2];
                            int max = (int) params[3];
                            int current = (int) params[4];
                            IntConsumer updateValue = (IntConsumer) params[5];
                            assertTrue(min <= max, "min ist kleiner als max");
                            assertTrue(min >= 0, "min muss >= 0 sein.");
                            assertTrue(max <= 100, "max darf nicht > 100 sein");
                            mp.alpha = 1;
                            updateValue.accept(10);
                            TestUtils.assertEqualFloored(mp.alpha, 0.1);
                        });
    }

    @Test
    public void testChangeZoomButtons()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        var mp = new MyPanel();
        var mf = new MainFrame(mp);
        var cf = new ControlFrame(mf);

        var pcdField = cf.getClass().getDeclaredField("pcd");
        pcdField.setAccessible(true);
        pcdField.set(cf, spy(cf.pcd));
        doNothing().when(cf.pcd).showNumberChangeDialog(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
                ArgumentMatchers.any());
        doNothing().when(cf.pcd).showEnumChangeDialogue(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt(), ArgumentMatchers.any(), ArgumentMatchers.any());
        // cf.pcd = spy(cf.pcd);
        var componentField = Container.class.getDeclaredField("component");
        componentField.setAccessible(true);
        componentField.set(mf, new ArrayList<Component>());
        try {
            mf.init();
            cf.init();
        } catch (Exception e) {
            // Try anyways
        }

        // Green Ellipse
        var mockingDetails = Mockito.mockingDetails(cf.pcd);
        try {
            cf.changeZoomButton.doClick();
        } catch (Exception e) {
        }
        mockingDetails.getInvocations().stream()
                .filter(x -> x.getMethod().getName() == "showNumberChangeDialog" && x.getArguments().length == 6)
                .forEach(
                        x -> {
                            var params = x.getArguments();
                            String title = (String) params[0];
                            String propertyName = (String) params[1];
                            int min = (int) params[2];
                            int max = (int) params[3];
                            int current = (int) params[4];
                            IntConsumer updateValue = (IntConsumer) params[5];
                            assertTrue(min <= max, "min ist kleiner als max");
                            assertTrue(min > 0, "min muss > 0 sein");
                            // assertTrue(max >= 0);
                            mp.zoom = 1;
                            updateValue.accept(10);
                            TestUtils.assertEqualFloored(mp.zoom, 0.1,
                                    "Zoomfaktor wurde vom IntConsumer nicht korrekt aktualisiert.");
                        });
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChangeFontButton()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        var mp = new MyPanel();
        var mf = new MainFrame(mp);
        var cf = new ControlFrame(mf);

        var pcdField = cf.getClass().getDeclaredField("pcd");
        pcdField.setAccessible(true);
        pcdField.set(cf, spy(cf.pcd));
        doNothing().when(cf.pcd).showNumberChangeDialog(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.any());
        doNothing().when(cf.pcd).showEnumChangeDialogue(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt(), ArgumentMatchers.any(), ArgumentMatchers.any());
        // cf.pcd = spy(cf.pcd);
        var componentField = Container.class.getDeclaredField("component");
        componentField.setAccessible(true);
        componentField.set(mf, new ArrayList<Component>());
        try {
            mf.init();
            cf.init();
        } catch (Exception e) {
            // Try anyways
        }

        // Green Ellipse
        var mockingDetails = Mockito.mockingDetails(cf.pcd);
        try {
            cf.changeFontButton.doClick();
        } catch (Exception e) {
        }
        mockingDetails.getInvocations().stream()
                .filter(x -> x.getMethod().getName() == "showEnumChangeDialogue" && x.getArguments().length == 5)
                .forEach(
                        x -> {
                            var params = x.getArguments();
                            String title = (String) params[0];
                            String propertyName = (String) params[1];
                            int current = (int) params[2];
                            String[] options = (String[]) params[3];
                            Consumer<String> updateValue = (Consumer<String>) params[4];
                            assertNotNull(options, "keine Optionen gegeben.");
                            assertTrue(Arrays.stream(options).collect(Collectors.toSet()).size() >= 5,
                                    "zu wenige unterschiedliche Schriftarten gegeben");
                            var curFont = new Font("Arial", Font.ITALIC, 42);
                            mp.font = curFont;
                            updateValue.accept(options[0]);
                            assertNotEquals(curFont, mp.font);
                        });
    }

    @Test
    public void testExitButton()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        var mp = spy(new MyPanel());
        var mf = new MainFrame(mp);
        var cf = new ControlFrame(mf);
        var componentField = Container.class.getDeclaredField("component");
        componentField.setAccessible(true);
        componentField.set(mf, new ArrayList<Component>());
        try {
            mf.init();
            cf.init();
        } catch (Exception e) {
            // Try anyways
        }

        var oldSM = System.getSecurityManager();
        SecurityManager sm = new SecurityManager() {
            @Override
            public void checkExit(int status) {
                throw new IllegalAccessError();
            }

            @Override
            public void checkPermission(Permission perm) {
            }
        };
        System.setSecurityManager(sm);

        // Green Ellipse
        assertThrows(IllegalAccessError.class, () -> cf.exitButton.doClick());

        // Reset Security Manager
        System.setSecurityManager(oldSM);
    }

    @Test
    public void testExitButton_alt()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        // try (MockedConstruction<ControlFrame> mc = mockConstruction(
        // ControlFrame.class,
        // withSettings().defaultAnswer(
        // Answers.CALLS_REAL_METHODS),
        // (mock, context) -> {
        // doNothing().when(mock).setVisible(ArgumentMatchers.anyBoolean());
        // })) {
        // var mp = spy(new MyPanel());
        // var mf = spy(new MainFrame(mp));
        // var cf = spy(new ControlFrame(mf));
        // doNothing().when(mf).setVisible(ArgumentMatchers.anyBoolean());
        // doNothing().when(cf).setVisible(ArgumentMatchers.anyBoolean());

        // try {
        // mf.init();
        // cf.init();
        // } catch (Exception e) {
        // // Try anyways
        // }

        // try {
        // cf.exitButton.doClick();
        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        // assertTrue(Mockito.mockingDetails(cf).getInvocations().stream().anyMatch(x ->
        // x.getMethod().getName().equals("setVisible")));
        // assertFalse(cf.isVisible());
        // assertFalse(cf.isDisplayable());
        // // assertFalse(mf.isVisible());
        // // assertFalse(mf.isDisplayable());
        // }

        var mp = spy(new MyPanel());
        var mf = spy(new MainFrame(mp));
        var cf = spy(new ControlFrame(mf));
        doNothing().when(mf).setVisible(ArgumentMatchers.anyBoolean());
        doNothing().when(cf).setVisible(ArgumentMatchers.anyBoolean());

        try {
            mf.init();
            cf.init();
        } catch (Exception e) {
            // Try anyways
        }

        try {
            cf.exitButton.doClick();
        } catch (Exception e) {
        }
        assertFalse(cf.isVisible());
        assertFalse(cf.isDisplayable());
        assertFalse(mf.isVisible());
        assertFalse(mf.isDisplayable());
    }
}
