package h13;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PropertyChangeDialogue extends JDialog {
    JLabel propertyJLabel = new JLabel("NaN");
    JSlider valueControlJSlider = new JSlider();
    JTextField valueInputField = new JTextField("50");
    JButton okButton = new JButton("Ok");

    public PropertyChangeDialogue() {
        setTitle("Change property");
    }

    public void init(
            String title,
            String propertyName,
            int min,
            int max,
            int current,
            IntConsumer updateValue) {
        init(title, propertyName, min, max, current, false, 1, 1, updateValue);
    }

    public void init(
            String title,
            String propertyName,
            int min,
            int max,
            int current,
            boolean showTicks,
            int minorTickSize,
            int majorTickSize,
            IntConsumer updateValue) {
        setTitle(title);
        propertyJLabel.setText(propertyName);
        valueControlJSlider.setMinimum(min);
        valueControlJSlider.setMaximum(max);
        valueControlJSlider.setValue(current);
        valueControlJSlider.setMinorTickSpacing(minorTickSize);
        valueControlJSlider.setMajorTickSpacing(majorTickSize);
        valueControlJSlider.setSnapToTicks(showTicks);
        valueControlJSlider.setPaintTicks(showTicks);
        valueControlJSlider.setPaintLabels(showTicks);
        valueInputField.setText("" + current);
        setLayout(new BorderLayout());
        add(propertyJLabel, BorderLayout.NORTH);
        add(valueControlJSlider, BorderLayout.CENTER);
        add(valueInputField, BorderLayout.EAST);
        add(okButton, BorderLayout.SOUTH);

        // Listeners
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        valueControlJSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                valueInputField.setText("" + valueControlJSlider.getValue());
                updateValue.accept(valueControlJSlider.getValue());
            }
        });

        setModal(true);
        setMinimumSize(new Dimension(300, 300));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
