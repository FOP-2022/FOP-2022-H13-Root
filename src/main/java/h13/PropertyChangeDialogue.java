package h13;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Property Change Dialogue for changing Values
 *
 * @author Ruben Deisenroth
 */
public class PropertyChangeDialogue extends JDialog {
    /**
     * The Property JLabel to display the Property name
     */
    JLabel propertyJLabel = new JLabel("NaN");
    /**
     * The JSlider that controls the Value
     */
    JSlider valueControlJSlider = new JSlider();
    /**
     * The JTextField that accepts valid value Inputs
     */
    JTextField valueInputField = new JTextField("50");
    /**
     * The Confirmation Button
     */
    JButton okButton = new JButton("Ok");
    /**
     * The ComboBox for Enum-like Properties
     */
    JComboBox<String> optionsComboBox = new JComboBox<String>();

    /**
     * Creates a new {@link PropertyChangeDialogue}
     */
    public PropertyChangeDialogue() {
        setTitle("Change property");
        setMinimumSize(new Dimension(300, 300));
        setModal(true);
    }

    /**
     * Initializes the Dialog for a Number based property
     *
     * @param title        the Dialog Title
     * @param propertyName the Property Name
     * @param min          the minimum Value of the Property
     * @param max          the Maximum Value of the Property
     * @param current      the current Value of the Property
     * @param updateValue  a consumer that is executed every time the value changes
     */
    public void showNumberChangeDialog(
            String title,
            String propertyName,
            int min,
            int max,
            int current,
            IntConsumer updateValue) {
        // Frame Properties
        setTitle(title);
        propertyJLabel.setText(propertyName);
        valueControlJSlider.setMinimum(min);
        valueControlJSlider.setMaximum(max);
        valueControlJSlider.setValue(current);
        valueInputField.setText("" + current);

        remove(optionsComboBox);

        setLayout(new BorderLayout());
        // Clear previous Components
        // Add Components
        add(propertyJLabel, BorderLayout.NORTH);
        add(valueControlJSlider, BorderLayout.CENTER);
        add(valueInputField, BorderLayout.EAST);
        add(okButton, BorderLayout.SOUTH);

        // Add Listeners
        for (ActionListener al : okButton.getActionListeners()) {
            okButton.removeActionListener(al);
        }
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        for (ChangeListener al : valueControlJSlider.getChangeListeners()) {
            valueControlJSlider.removeChangeListener(al);
        }
        valueControlJSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                valueInputField.setText("" + valueControlJSlider.getValue());
                updateValue.accept(valueControlJSlider.getValue());
            }
        });

        // Set Dimension and Position
        // setMinimumSize(new Dimension(300, 300));
        // setLocationRelativeTo(null);
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // var insets = getInsets();
        setSize((int) (screenSize.getWidth() / 2), (int) (screenSize.getHeight() / 2));
        setLocation((int) (screenSize.getWidth() / 2), (int) (screenSize.getHeight() / 2));

        // Show Frame
        setModal(true);
        setVisible(true);
    }

    /**
     * Initializes the Dialog for an Enum-Like property
     *
     * @param title        the Dialog Title
     * @param propertyName the Property Name
     * @param current      the index currently selected Item
     * @param options      the Options for the Combo Box
     * @param updateValue  a consumer that is executed every time the value
     *                     changes
     */
    public void showEnumChangeDialogue(
            String title,
            String propertyName,
            int current,
            String[] options,
            Consumer<String> updateValue) {
        setTitle(title);
        propertyJLabel.setText(propertyName);
        optionsComboBox.setModel(new DefaultComboBoxModel<>(options));
        if (options != null && options.length > 0) {
            optionsComboBox.setSelectedIndex(current);
        }
        setLayout(new BorderLayout());
        remove(valueInputField);
        remove(valueControlJSlider);

        add(propertyJLabel, BorderLayout.NORTH);
        add(optionsComboBox, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);

        // Listeners
        for (ActionListener al : okButton.getActionListeners()) {
            okButton.removeActionListener(al);
        }
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        for (ActionListener al : optionsComboBox.getActionListeners()) {
            optionsComboBox.removeActionListener(al);
        }
        optionsComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateValue.accept(optionsComboBox.getModel().getElementAt(optionsComboBox.getSelectedIndex()));
            }
        });

        setModal(true);
        pack();
        setMinimumSize(new Dimension(300, 300));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets insets = getInsets();
        setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() / 2));
        setLocation((int) (screenSize.getWidth() / 2 + insets.left), (int) (screenSize.getHeight() / 2 + insets.top));
        // setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initializes the Dialog
     *
     * @param title         the Dialog Title
     * @param propertyName  the Property Name
     * @param min           the minimum Value of the Property
     * @param max           the Maximum Value of the Property
     * @param current       the current Value of the Property
     * @param showTicks     whether or not to render the ticks
     * @param minorTickSize the minor tick size
     * @param majorTickSize the major tick size
     * @param updateValue   a consumer that is executed every time the value changes
     */
    public void showNumberChangeDialog(
            String title,
            String propertyName,
            int min,
            int max,
            int current,
            boolean showTicks,
            int minorTickSize,
            int majorTickSize,
            IntConsumer updateValue) {
        valueControlJSlider.setMinorTickSpacing(minorTickSize);
        valueControlJSlider.setMajorTickSpacing(majorTickSize);
        // valueControlJSlider.setSnapToTicks(showTicks);
        valueControlJSlider.setPaintTicks(showTicks);
        valueControlJSlider.setPaintLabels(showTicks);
        showNumberChangeDialog(title, propertyName, min, max, current, updateValue);
    }
}
