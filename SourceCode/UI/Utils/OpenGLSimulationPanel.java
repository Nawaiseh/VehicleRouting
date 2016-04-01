package UI.Utils;

import DockingInterface.docking.View;
import UI.UserInterface;
import UI.SimulationPanel.Utils.TravelersStatistics;
import UI.Utils.OpenGLUtils.OpenGLMouseListener;
import UI.Utils.OpenGLUtils.OpenGLRenderer;
import com.sun.opengl.util.Animator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import Data.Network;

/**
 *
 * @author TRL
 */
public class OpenGLSimulationPanel extends javax.swing.JPanel {

    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~ GUI Variables ~~~~~~~~~~~~~~~~~~~~~~ ">
    public static final Logger MyLogger = Logger.getLogger(OpenGLSimulationPanel.class.getName());
    private static final long serialVersionUID = 1L;
    private final ImageIcon HoverCloseIcon = new javax.swing.ImageIcon(getClass().getResource("/Resources/Close/CloseHoverCircle.png"));
    private final ImageIcon CloseIcon = new javax.swing.ImageIcon(getClass().getResource("/Resources/Close/Close.png"));
    public View View = null;
    public Clock Clock = new Clock();
    public MouseLocationPanel MouseLocationPanel = new MouseLocationPanel();
    //</editor-fold>
    public UserInterface DView = null;
    public Network Network = null;
    public OpenGLRenderer OpenGLRenderer = null;
    public Animator OpenGLAnimator = null;
    public boolean SimulationEnd = false;
    public boolean IsInitialized = false;
    protected Color DragColor = new Color(180, 180, 0, 120);
    public Point Position = new Point();
    public boolean CollapseState = false;
    public boolean dragged = false;
    private JTextArea LoggerTextArea = new JTextArea();
    private JScrollPane LoggerArea = new JScrollPane();
    public boolean NetworkIsPreparedAndLoaded = false;
    public Dimension ExpandSize = new Dimension(200, 140);
    public Dimension ShrinkSize = new Dimension(50, 50);
    public Dimension WaterMarkSize = new Dimension(190, 60);

    public OpenGLSimulationPanel() {
        initComponents();
        PostInitComponents();

    }

    public OpenGLSimulationPanel(UserInterface newDIRECTView) {
        DView = newDIRECTView;
        initComponents();
        PostInitComponents();
    }

    public synchronized OpenGLRenderer GetOpenGLRenderer() {
        return OpenGLRenderer;
    }

    public synchronized void SetNetwork(Network newNetwork, OpenGLRenderer newOpenGLRenderer) {

        if (OpenGLRenderer != null) {
            DeActivateOpenGLListeners();
        }
        if (newNetwork != null) {
            OpenGLRenderer = newOpenGLRenderer;

            SetOpenGLListeners();
            SwingUtilities.invokeLater(new Runnable() {
                @SuppressWarnings("ResultOfObjectAllocationIgnored")
                @Override
                public void run() {
                    Center.remove(OpenGLPanel);
                    Dimension Size = getSize();
                    OpenGLPanel.setBounds(0, 0, Size.width - 1, Size.height);
                    Center.add(OpenGLPanel, BorderLayout.CENTER);
                    Center.revalidate();
                    if (!OpenGLAnimator.isAnimating()) {
                        OpenGLAnimator.start();
                    }
                }
            });
        }
    }

    private void PostInitComponents() {
//          MouseLocationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
//        MouseLocationPanel.setOpaque(false);
//        Container.add(MouseLocationPanel);
//        MouseLocationPanel.setBounds(10, 290, 420, 50);

//        Clock.setMinimumSize(new java.awt.Dimension(0, 0));
//        Clock.setPreferredSize(new java.awt.Dimension(200, 85));
//        Container.add(Clock);
//        Clock.setBounds(320, 20, 200, 85);

        OpenGLPanel.addComponentListener(ComponentListener);
        //Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

        LoggerTextArea.setColumns(20);
        LoggerTextArea.setRows(5);
        LoggerArea.setViewportView(LoggerTextArea);

        Date Now = new Date();
        //    Now.getDa
        String[] Days = new String[]{"SUN  ", "MON  ", "TUE  ", "WED  ", "THU  ", "FRI  ", "SAT  "};
        String[] Months = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

//        Calendar TimeCalendar = Calendar.getInstance();
//        DecimalFormat DF = new DecimalFormat("00");
//        String Date = String.format("  %s %s", Months[TimeCalendar.get(Calendar.MONTH)], DF.format(TimeCalendar.get(Calendar.DAY_OF_MONTH)));
//        Clock.SetDay(Days[TimeCalendar.get(Calendar.DAY_OF_WEEK) - 1].toUpperCase());
//        Clock.SetDate(Date);

    }

    public void SetOpenGLListeners() {
        OpenGLMouseListener OpenGLMouseListener = OpenGLRenderer.getOpenGLMouseListener();
        OpenGLPanel.addGLEventListener(OpenGLRenderer);
        OpenGLPanel.addMouseListener(OpenGLMouseListener);
        OpenGLPanel.addMouseMotionListener(OpenGLMouseListener);
        OpenGLPanel.addMouseWheelListener(OpenGLMouseListener);
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        this.setCursor(cursor);
        OpenGLAnimator = new Animator(OpenGLPanel);
        OpenGLAnimator.setIgnoreExceptions(true);
        OpenGLAnimator.setRunAsFastAsPossible(false);
    }

    public void DeActivateOpenGLListeners() {
        if (OpenGLAnimator != null && OpenGLAnimator.isAnimating()) {
            OpenGLAnimator.stop();
        }
        OpenGLMouseListener OpenGLMouseListener = OpenGLRenderer.getOpenGLMouseListener();
        OpenGLPanel.removeGLEventListener(OpenGLRenderer);
        OpenGLPanel.removeMouseListener(OpenGLMouseListener);
        OpenGLPanel.removeMouseMotionListener(OpenGLMouseListener);
        OpenGLPanel.removeMouseWheelListener(OpenGLMouseListener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MethodGroup = new javax.swing.ButtonGroup();
        MainContainer = new javax.swing.JPanel();
        Center = new javax.swing.JPanel();
        OpenGLPanel = new javax.media.opengl.GLJPanel();
        Container = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(0, 0));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(437, 120));
        setLayout(new java.awt.BorderLayout());

        MainContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        MainContainer.setLayout(new java.awt.BorderLayout());

        Center.setLayout(new java.awt.BorderLayout());

        OpenGLPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        OpenGLPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        OpenGLPanel.setOpaque(false);
        OpenGLPanel.setRealized(true);
        OpenGLPanel.setLayout(new java.awt.BorderLayout());

        Container.setOpaque(false);
        Container.setPreferredSize(new java.awt.Dimension(10, 5));
        Container.setLayout(null);
        OpenGLPanel.add(Container, java.awt.BorderLayout.CENTER);

        Center.add(OpenGLPanel, java.awt.BorderLayout.CENTER);

        MainContainer.add(Center, java.awt.BorderLayout.CENTER);

        add(MainContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JPanel Center;
    public javax.swing.JPanel Container;
    protected javax.swing.JPanel MainContainer;
    private javax.swing.ButtonGroup MethodGroup;
    public javax.media.opengl.GLJPanel OpenGLPanel;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) throws Exception {
        // Docking windwos should be run in the Swing thread
        SwingUtilities.invokeLater(new Runnable() {
            @SuppressWarnings("ResultOfObjectAllocationIgnored")
            @Override
            public void run() {
                OpenGLSimulationPanel TrafficManagementPanel = new OpenGLSimulationPanel();
                JFrame Frame = new JFrame("DMS");
                Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // Frame.setBounds(new Rectangle(100, 100, 1400, 650));
                Frame.setVisible(true);
                Frame.getContentPane().add(TrafficManagementPanel);
                Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            }
        });
    }

    public void SetSimulationEnd() {
        this.SimulationEnd = true;
    }

    public JLabel GetMouseLocation() {
        return this.MouseLocationPanel.Location;
    }

    public JScrollPane GetLogArea() {
        return LoggerArea;
    }

    public JTextArea GetLoggerTextArea() {
        return LoggerTextArea;
    }
    public int LeftGap = 0;
    public int RightGap = 0;
    private boolean SizeIsSet = false;
    public ComponentListener ComponentListener = new ComponentListener() {
        @Override
        public void componentResized(ComponentEvent e) {
//            try {
//                Dimension Size = e.getComponent().getSize();
//                Toolkit.getDefaultToolkit().getScreenSize();
//                OpenGLPanel.setSize(Size);
//
//                Rectangle MouseLocationBounds = MouseLocationPanel.getBounds();
//                Rectangle ClockBounds = Clock.getBounds();
//
//                MouseLocationBounds.width = (MouseLocationPanel.IsShrinked) ? MouseLocationPanel.ShrinkSize.width : MouseLocationPanel.ExpandSize.width;
//                ClockBounds.width = (Clock.DigitalClock.IsShrinked) ? Clock.DigitalClock.ShrinkSize.width : Clock.DigitalClock.ExpandSize.width;
//
//                MouseLocationBounds.height = (MouseLocationPanel.IsShrinked) ? MouseLocationPanel.ShrinkSize.height : MouseLocationPanel.ExpandSize.height;
//                ClockBounds.height = (Clock.DigitalClock.IsShrinked) ? Clock.DigitalClock.ShrinkSize.height : Clock.DigitalClock.ExpandSize.height;
//
//                MouseLocationBounds.x = Size.width - (RightGap + LeftGap) - MouseLocationBounds.width - 30;
//                ClockBounds.x = Size.width - (RightGap + LeftGap) - ClockBounds.width - 30;
//
//                MouseLocationBounds.y = Size.height - MouseLocationBounds.height - 20;
//                ClockBounds.y = MouseLocationBounds.y - ClockBounds.height - 10;
//
//                MouseLocationPanel.setBounds(MouseLocationBounds);
//                Clock.setBounds(ClockBounds);
//            } catch (Exception Exception) {
//            }
        }

        @Override
        public void componentHidden(ComponentEvent e) {
        }

        @Override
        public void componentMoved(ComponentEvent e) {
        }

        @Override
        public void componentShown(ComponentEvent e) {
        }
    };

    public synchronized Network GetNetwork() {
        return Network;
    }
}
