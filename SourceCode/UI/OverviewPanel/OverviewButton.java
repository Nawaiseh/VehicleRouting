/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OverviewPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author aalnawai
 */
public class OverviewButton extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private Dimension Icon_Size = new Dimension(20, 15);
    private Color SeverColor = Color.RED;
    private Color MediumColor = Color.ORANGE;
    private Color LowColor = Color.YELLOW;
    private Color NormalColor = Color.GRAY;
    private int FlashingSpeed = 600; // In Milli seconds
    ActionListener SeverModeFlashing = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent evt) {
            color = (color == SeverColor) ? NormalColor : SeverColor;
            MainButton.setBackground(color);
        }
    };
    ActionListener MediumModeFlashing = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent evt) {
            color = (color == MediumColor) ? NormalColor : MediumColor;
            MainButton.setBackground(color);
        }
    };
    ActionListener LowModeFlashing = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent evt) {
            color = (color == LowColor) ? NormalColor : LowColor;
            MainButton.setBackground(color);
        }
    };
    private Timer SeverTimer = new Timer(FlashingSpeed, SeverModeFlashing);
    private Timer MediumTimer = new Timer(FlashingSpeed, MediumModeFlashing);
    private Timer LowTimer = new Timer(FlashingSpeed, LowModeFlashing);

    void setShowHideIcon(Image image) {
        ShowHideButton.setIcon(createResizedIcon(image, Icon_Size));
    }

    public static enum Mode {

        Severe, Medium, Low, Normal
    }
    /**
     * Creates new form OverviewButton
     */
    private String mainButtonText = "";

    public OverviewButton() {

        initComponents();
        NormalColor = MainButton.getBackground();
    }
    Color color = null;

    public OverviewButton(String text) {
        mainButtonText = text;
        //this.MainButton.setText(text);
        initComponents();
        MainButton.getColorModel();
        NormalColor = MainButton.getBackground();

    }

    public synchronized void setMode(Mode mode) {
        if (mode == Mode.Severe) {
            if (!SeverTimer.isRunning()) {
                SeverTimer.start();
            }
            if (MediumTimer.isRunning()) {
                MediumTimer.stop();
            }
            if (LowTimer.isRunning()) {
                LowTimer.stop();
            }
        } else if (mode == Mode.Medium) {
            if (!MediumTimer.isRunning()) {
                MediumTimer.start();
            }
            if (SeverTimer.isRunning()) {
                SeverTimer.stop();
            }
            if (LowTimer.isRunning()) {
                LowTimer.stop();
            }
        } else if (mode == Mode.Low) {
            if (!LowTimer.isRunning()) {
                LowTimer.start();
            }
            if (SeverTimer.isRunning()) {
                SeverTimer.stop();
            }
            if (MediumTimer.isRunning()) {
                MediumTimer.stop();
            }
        } else if (mode == Mode.Normal) {
            MainButton.setBackground(NormalColor);
            if (SeverTimer.isRunning()) {
                SeverTimer.stop();
            }
            if (MediumTimer.isRunning()) {
                MediumTimer.stop();
            }
            if (LowTimer.isRunning()) {
                LowTimer.stop();
            }
        }
    }

    public ImageIcon createResizedIcon(Image img, Dimension size) {
        ImageIcon icon = null;
        Image newimg = img.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("Custom Panels Demo");
        frame.setLayout(new FlowLayout());
        OverviewButton standardButton = new OverviewButton("Standard Button");
        standardButton.setPreferredSize(new Dimension(130, 28));
        frame.add(standardButton.getButtonsPanel());
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBackground(Color.WHITE);
        frame.setSize(700, 85);
        frame.setVisible(true);
    }

    public synchronized JPanel getButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        OverviewButton standardButton = new OverviewButton("Standard Button");
        standardButton.setPreferredSize(new Dimension(130, 28));


        panel.add(standardButton);

        return panel;

    }

    public synchronized JToggleButton getMainButton() {
        return this.MainButton;
    }

    public synchronized boolean getMainButtonSelectedStatus() {
        return this.MainButton.isSelected();
    }

    public synchronized void setMainButtonSelectedStatus(boolean Status) {
        MainButton.setSelected(Status);
    }

    public synchronized JToggleButton getEditButton() {
        return this.EditButton;
    }

    public synchronized boolean getEditButtonSelectedStatus() {
        return this.EditButton.isSelected();
    }

    public synchronized void setEditButtonSelectedStatus(boolean Status) {
        EditButton.setSelected(Status);
    }

    public synchronized JToggleButton getFilterButton() {
        return this.FilterButton;
    }

    public synchronized boolean getFilterButtonSelectedStatus() {
        return this.FilterButton.isSelected();
    }

    public synchronized void setFilterButtonSelectedStatus(boolean Status) {
        FilterButton.setSelected(Status);
    }

    public synchronized JToggleButton getShowHideButton() {
        return this.ShowHideButton;
    }

    public synchronized boolean getShowHideButtonSelectedStatus() {
        return this.ShowHideButton.isSelected();
    }

    public synchronized void setShowHideButtonSelectedStatus(boolean Status) {
        ShowHideButton.setSelected(Status);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ShowHideButton = new javax.swing.JToggleButton();
        FilterButton = new javax.swing.JToggleButton();
        MainButton = new javax.swing.JToggleButton();
        EditButton = new javax.swing.JToggleButton();

        setMaximumSize(new java.awt.Dimension(158, 27));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(158, 27));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        ShowHideButton.setBackground(new java.awt.Color(235, 235, 235));
        ShowHideButton.setIcon(createResizedIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/Intersection.png")).getImage(), Icon_Size));
        ShowHideButton.setMaximumSize(new java.awt.Dimension(27, 27));
        ShowHideButton.setMinimumSize(new java.awt.Dimension(27, 27));
        ShowHideButton.setOpaque(true);
        ShowHideButton.setPreferredSize(new java.awt.Dimension(27, 27));
        ShowHideButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ShowHideButtonStateChanged(evt);
            }
        });
        ShowHideButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ShowHideButtonItemStateChanged(evt);
            }
        });
        jPanel1.add(ShowHideButton);

        FilterButton.setBackground(new java.awt.Color(235, 235, 235));
        FilterButton.setIcon(createResizedIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Filter.png")).getImage(), Icon_Size));
        FilterButton.setMaximumSize(new java.awt.Dimension(27, 27));
        FilterButton.setMinimumSize(new java.awt.Dimension(27, 27));
        FilterButton.setOpaque(true);
        FilterButton.setPreferredSize(new java.awt.Dimension(27, 27));
        jPanel1.add(FilterButton);

        add(jPanel1, java.awt.BorderLayout.WEST);

        MainButton.setBackground(new java.awt.Color(235, 235, 235));
        MainButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MainButton.setText(mainButtonText);
        MainButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MainButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        MainButton.setOpaque(true);
        add(MainButton, java.awt.BorderLayout.CENTER);

        EditButton.setBackground(new java.awt.Color(235, 235, 235));
        EditButton.setIcon(createResizedIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Edit.png")).getImage(), Icon_Size));
        EditButton.setMaximumSize(new java.awt.Dimension(27, 27));
        EditButton.setMinimumSize(new java.awt.Dimension(27, 27));
        EditButton.setOpaque(true);
        EditButton.setPreferredSize(new java.awt.Dimension(27, 27));
        add(EditButton, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents
    private void ShowHideButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ShowHideButtonStateChanged
    }//GEN-LAST:event_ShowHideButtonStateChanged
    private void ShowHideButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ShowHideButtonItemStateChanged
        javax.swing.JToggleButton button = (javax.swing.JToggleButton) evt.getSource();
        if (button.isSelected()) {
            setBackground(Color.MAGENTA);
        } else {
            setBackground(Color.BLACK);
        }
    }//GEN-LAST:event_ShowHideButtonItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton EditButton;
    private javax.swing.JToggleButton FilterButton;
    private javax.swing.JToggleButton MainButton;
    private javax.swing.JToggleButton ShowHideButton;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
