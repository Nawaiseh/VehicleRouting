/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.ToolBar;

import UI.UserInterface;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.AbstractRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbon;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;

/**
 *
 * @author aalnawai
 */
public class ProjectBandSet {
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~        Static Method        ~~~~~~~~~~~~~~~ ">

    public static ResizableIcon getResizableIconFromResource(String resource) {
        return ImageWrapperResizableIcon.getIcon(ProjectBandSet.class.getClassLoader().getResource(resource), new Dimension(48, 48));
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~          Variables          ~~~~~~~~~~~~~~~ ">
    private UserInterface DView = null;
    private ProjectBand ProjectBand = null;
//    private ImportBand ImportBand = null;
//    private ExportBand ExportBand = null;
//    private SystemBand SystemBand = null;
    private TaskRibbon TaskRibbon = null;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~   ProjectBand Constructor   ~~~~~~~~~~~~~~~ ">

    public ProjectBandSet() {
    }

    public ProjectBandSet(UserInterface newDIRECTView) {

        DView = newDIRECTView;
        ProjectBand = new ProjectBand();
//        ImportBand = new ImportBand();
//        ExportBand = new ExportBand();
//        SystemBand = new SystemBand();
//        TaskRibbon = new TaskRibbon("Project", this, new AbstractRibbonBand[]{ProjectBand, ImportBand, ExportBand, SystemBand});
        TaskRibbon = new TaskRibbon("Project", this, new AbstractRibbonBand[]{ProjectBand});
        TaskRibbon.setKeyTip("P");
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~  Synchronized Set Methods   ~~~~~~~~~~~~~~~ ">

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~  Synchronized Get Methods   ~~~~~~~~~~~~~~~ ">
    public synchronized TaskRibbon getTaskRibbon() {
        return TaskRibbon;
    }

    public synchronized ProjectBand getProjectBand() {
        return ProjectBand;
    }

//    public synchronized ImportBand getImportBand() {
//        return ImportBand;
//    }
    // </editor-fold>
    public class ProjectBand extends RibbonBand {
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~            Variables            ~~~~~~~~~~~~~~~ ">

//        private JCommandButton NewProjectButton = new JCommandButton("New", getResizableIconFromResource("Resources/RibbonBands/ProjectBand/NewProject.png"));
        private JCommandButton OpenProjectButton = new JCommandButton("Open", getResizableIconFromResource("Resources/RibbonBands/ProjectBand/OpenProject.png"));
//        private JCommandButton OpenProjectAndRunButton = new JCommandButton("Open & Run Simulation", getResizableIconFromResource("Resources/RibbonBands/ProjectBand/OpenProject.png"));
//        private JCommandButton SaveProjectButton = new JCommandButton("Save", getResizableIconFromResource("Resources/RibbonBands/ProjectBand/SaveProject.png"));
//        private JCommandButton CloseProjectButton = new JCommandButton("Close", getResizableIconFromResource("Resources/RibbonBands/ProjectBand/CloseProject.png"));
        private Timer RefreshTimer = null;
        private int RefreshRate = 5; // in Milliseconds
//          private RibbonTask RibbonTask = null;
        // private ImportBand ImportBand = new ImportBand();
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~        Action Listeners         ~~~~~~~~~~~~~~~ ">
//        private ActionListener NewProject_ActionListener = new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                CreateNewProject();
//            }
//        };
        private ActionListener OpenProject_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenProject();

            }
        };
//        private ActionListener OpenProjectAndRun_ActionListener = new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                OpenProjectAndRun();
//
//            }
//        };
//        private ActionListener SaveProject_ActionListener = new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SaveProject();
//            }
//        };
//        private ActionListener CloseProject_ActionListener = new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                CloseProject();
//            }
//        };
//        private ActionListener Exit_ActionListener = new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        };
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~     ProjectBand Constructor     ~~~~~~~~~~~~~~~ ">

        public ProjectBand() {
            super("Project", getResizableIconFromResource("Resources/RibbonBands/ProjectBand/Project.png"), 1);
            Initialize();
        }

        private synchronized void Initialize() {

            OpenProjectButton.addActionListener(OpenProject_ActionListener);
            addCommandButton(OpenProjectButton, RibbonElementPriority.TOP);
            OpenProjectButton.setActionKeyTip("O");

        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~    Synchronized Get Methods     ~~~~~~~~~~~~~~~ ">

        public synchronized TaskRibbon getTaskRibbon() {
            return TaskRibbon;
        }

//        public synchronized JCommandButton getNewProjectButton() {
//            return NewProjectButton;
//        }
//
//        public synchronized JCommandButton getOpenProjectButton() {
//            return OpenProjectButton;
//        }
//
//        public synchronized JCommandButton getSaveProjectButton() {
//            return SaveProjectButton;
//        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~     Synchronized  Methods       ~~~~~~~~~~~~~~~ ">
        private synchronized void CreateNewProject() {
        }

        private synchronized void OpenProject() {
            boolean LoadResult = DView.DViewListner.LoadProject();
            if (LoadResult) {
                final JRibbon DJRibbon = DView.getRibbon();
                final TaskRibbon SimulationTaskRibbon = (TaskRibbon) DJRibbon.getTask(2);
                SimulationBandSet SimulationBandSet = SimulationTaskRibbon.getSimulationBandSet();
                //  SimulateBand SimulateBand = SimulationBandSet.getSimulateBand();

                ActionListener RefreshUpdater = new ActionListener() {
                    @Override
                    public synchronized void actionPerformed(ActionEvent evt) {
                        if (DView.MainSimulationPanel.NetworkIsPreparedAndLoaded) {
                            RefreshTimer.stop();
                            DJRibbon.setSelectedTask(SimulationTaskRibbon);

                        }
                    }
                };
                RefreshTimer = new Timer(RefreshRate, RefreshUpdater);
                RefreshTimer.start();
            }
        }

        public synchronized void OpenProjectAndRun() {
            boolean LoadResult = DView.DViewListner.LoadProject();
         }

        private synchronized void SaveProject() {
        }

        private synchronized void CloseProject() {
        }
        // </editor-fold>
    }


}
