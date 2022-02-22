package h13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission("h13")
public class Tutor_Test_H2_3 {

    PropertyChangeDialogue pcd;

    @BeforeEach
    void before() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PCDReplacementTutor.mfInstances.clear();
        PCDReplacementTutor.cfInstances.clear();
        PCDReplacementTutor.pcdInstances.clear();
        pcd = spy(new PropertyChangeDialogue());
        doNothing().when(pcd).setVisible(ArgumentMatchers.anyBoolean());
        doNothing().when(pcd).setModal(ArgumentMatchers.anyBoolean());
    }

    @AfterEach
    void closeIt() {
        pcd.dispose();
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
    public void testShowNumberChangeDialog() throws NoSuchMethodException, SecurityException, IllegalAccessException {
        AtomicInteger modified = new AtomicInteger();
        pcd.showNumberChangeDialog("test", "TestProperty", 2, 150, 42, (x) -> modified.set(x));

        // Components
        var components = TestUtils.getAllComponents(pcd);
        assertTrue(components.contains(pcd.okButton));
        assertTrue(components.contains(pcd.propertyJLabel));
        assertTrue(components.contains(pcd.valueControlJSlider));
        assertTrue(components.contains(pcd.valueInputField));

        // Values
        assertEquals(pcd.valueControlJSlider.getMinimum(), 2);
        assertEquals(pcd.valueControlJSlider.getMaximum(), 150);
        assertEquals(pcd.valueControlJSlider.getValue(), 42);

        pcd.valueControlJSlider.setValue(67);
        assertEquals(67, modified.get());
    }

    @Test
    public void testShowEnumChangeDialogue() throws NoSuchMethodException, SecurityException, IllegalAccessException {
        AtomicReference<String> modified = new AtomicReference<String>();
        var items = new String[] { "a", "b", "c" };
        pcd.showEnumChangeDialogue("test", "TestProperty", 0, items, (x) -> {
            modified.set(x);
        });

        // Components
        var components = TestUtils.getAllComponents(pcd);
        assertTrue(components.contains(pcd.okButton));
        assertTrue(components.contains(pcd.propertyJLabel));
        assertTrue(components.contains(pcd.optionsComboBox));

        // Values
        assertEquals(items.length, pcd.optionsComboBox.getItemCount());
        for (int i = 0; i < items.length; i++) {
            assertEquals(items[i], pcd.optionsComboBox.getItemAt(i));
        }

        // assertEquals("a", modified.get());

        pcd.optionsComboBox.setSelectedIndex(2);
        assertEquals("c", modified.get());
        pcd.optionsComboBox.setSelectedIndex(0);
        assertEquals("a", modified.get());
    }
}
