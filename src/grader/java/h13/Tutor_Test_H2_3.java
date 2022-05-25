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
        assertTrue(components.contains(pcd.okButton), "Die Komponente \"okButton\" wurde nicht korrekt hinzugefügt.");
        assertTrue(components.contains(pcd.propertyJLabel),
                "Die Komponente \"propertyJLabel\" wurde nicht korrekt hinzugefügt.");
        assertTrue(components.contains(pcd.valueControlJSlider),
                "Die Komponente \"valueControlJSlider\" wurde nicht korrekt hinzugefügt.");
        assertTrue(components.contains(pcd.valueInputField),
                "Die Komponente \"valueInputField\" wurde nicht korrekt hinzugefügt.");

        // Values
        assertEquals(pcd.valueControlJSlider.getMinimum(), 2,
                "Das Minimum des valueControlJSliders wird nicht korrekt gesetzt.");
        assertEquals(pcd.valueControlJSlider.getMaximum(), 150,
                "Das Maximum des valueControlJSliders wird nicht korrekt gesetzt.");
        assertEquals(pcd.valueControlJSlider.getValue(), 42,
                "Der Initialwert des valueControlJSliders wird nicht korrekt gesetzt.");

        pcd.valueControlJSlider.setValue(67);
        assertEquals(67, modified.get(),
                "Der übergebene IntConsumer \"updateValue\" wird bei einer Änderung gar nicht, oder nicht mit dem korrekten Wert aufgerufen.");
    }

    @Test
    public void testShowEnumChangeDialogue() throws NoSuchMethodException, SecurityException, IllegalAccessException {
        AtomicReference<String> modified = new AtomicReference<String>();
        var items = new String[] { "a", "b", "c", "d", "e" };
        pcd.showEnumChangeDialogue("test", "TestProperty", 0, items, (x) -> {
            modified.set(x);
        });

        // Components
        var components = TestUtils.getAllComponents(pcd);
        assertTrue(components.contains(pcd.okButton), "Die Komponente \"okButton\" wurde nicht korrekt hinzugefügt.");
        assertTrue(components.contains(pcd.propertyJLabel),
                "Die Komponente \"propertyJLabel\" wurde nicht korrekt hinzugefügt.");
        assertTrue(components.contains(pcd.optionsComboBox),
                "Die Komponente \"optionsComboBox\" wurde nicht korrekt hinzugefügt.");

        // Values
        assertEquals(items.length, pcd.optionsComboBox.getItemCount(),
                "Die Anzahl der Angezeigten optionen stimmt nicht mit der Anzahl der gegebenen Optionen überein.");
        for (int i = 0; i < items.length; i++) {
            assertEquals(items[i], pcd.optionsComboBox.getItemAt(i),
                    "Die Option an Index <" + i + "> stimmt nicht mit der übergebenen Option überein.");
        }

        // assertEquals("a", modified.get());

        pcd.optionsComboBox.setSelectedIndex(2);
        assertEquals("c", modified.get(), "Der Konsumer wurde nicht korrekt aufgerufen.");
        pcd.optionsComboBox.setSelectedIndex(0);
        assertEquals("a", modified.get(), "Der Konsumer wurde nicht korrekt aufgerufen.");
    }
}
