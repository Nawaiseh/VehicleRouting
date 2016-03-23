/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author aalnawai
 */
public final class OptionPaneUtils {

    public OptionPaneUtils() {
    }

    public JOptionPane getNarrowOptionPane(int maxCharactersPerLineCount) {
        // Our inner class definition
        class NarrowOptionPane extends JOptionPane {

            int maxCharactersPerLineCount;

            NarrowOptionPane(int maxCharactersPerLineCount) {
                this.maxCharactersPerLineCount = maxCharactersPerLineCount;
            }

            @Override
            public int getMaxCharactersPerLineCount() {
                return maxCharactersPerLineCount;
            }
        }

        return new NarrowOptionPane(maxCharactersPerLineCount);
    }

    public JButton getButton(final JOptionPane optionPane, String text, Icon icon) {
        final JButton button = new JButton(text, icon);
        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Return current text label, instead of argument to method
                optionPane.setValue(button.getText());
            }
        };
        button.addActionListener(actionListener);
        return button;
    }

    public JSlider getSlider(final JOptionPane optionPane) {
        JSlider slider = new JSlider();
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        ChangeListener changeListener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider theSlider = (JSlider) changeEvent.getSource();
                if (!theSlider.getValueIsAdjusting()) {
                    optionPane.setInputValue(new Integer(theSlider.getValue()));
                }
            }
        };
        slider.addChangeListener(changeListener);
        return slider;
    }

    public int getSelection(JOptionPane optionPane) {
        // Default return value, signals nothing selected
        int returnValue = JOptionPane.CLOSED_OPTION;

        // Get selected Value
        Object selectedValue = optionPane.getValue();
        System.out.println(selectedValue);

        // If none, then nothing selected
        if (selectedValue != null) {
            Object options[] = optionPane.getOptions();
            if (options == null) {
                // default buttons, no array specified
                if (selectedValue instanceof Integer) {
                    returnValue = ((Integer) selectedValue).intValue();
                }
            } else {
                // Array of option buttons specified
                for (int i = 0, n = options.length; i < n; i++) {
                    if (options[i].equals(selectedValue)) {
                        returnValue = i;
                        break; // out of for loop
                    }
                }
            }
        }
        return returnValue;
    }
}
