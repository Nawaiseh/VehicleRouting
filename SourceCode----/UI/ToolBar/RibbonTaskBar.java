package UI.ToolBar;

import Data.Network;
import Data.ReaderWriter.Writer;
import UI.UserInterface;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

/**
 *
 * @author aalnawai
 */
public class RibbonTaskBar extends Container {
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~        Static Method        ~~~~~~~~~~~~~~~ ">

    private static Dimension ButtonSize = new Dimension(15, 15);

    public static ResizableIcon getResizableIconFromResource(String resource) {
        return ImageWrapperResizableIcon.getIcon(ProjectBandSet.class.getClassLoader().getResource(resource), ButtonSize);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~          Variables          ~~~~~~~~~~~~~~~ ">
    private UserInterface DView = null;
    private JCommandButton OpenProjectButton = new JCommandButton("Open Project", getResizableIconFromResource("Resources/RibbonBands/ProjectBand/OpenProject.png"));
    private JCommandButton OpenProjectAndRunButton = new JCommandButton("Open Project", getResizableIconFromResource("Resources/RibbonBands/ProjectBand/OpenProjectAndRun.png"));
    private JCommandButton ExitButton = new JCommandButton("Exit", getResizableIconFromResource("Resources/RibbonBands/ProjectBand/Exit.png"));
    private int NumerOfButtons = 4;
    private int HorizontalGap = 1;
    private Dimension RibbonTaskBarSize = new Dimension((ButtonSize.width + HorizontalGap + 2) * NumerOfButtons, ButtonSize.height);
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~      Action Listeners       ~~~~~~~~~~~~~~~ ">
    private ActionListener OpenProject_ActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            OpenProject();

        }
    };
    private ActionListener OpenProjectAndRun_ActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            OpenProjectAndRun();

        }
    };
    private ActionListener Exit_ActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~  RibbonTaskBar Constructor  ~~~~~~~~~~~~~~~ ">

    public RibbonTaskBar() {
        setButtonPriorites();

        OpenProjectButton.setActionKeyTip("O");
        ExitButton.setActionKeyTip("Q");
    }

    public RibbonTaskBar(UserInterface newDIRECTView) {
        DView = newDIRECTView;
        setButtonPriorites();

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~  Synchronized Set Methods   ~~~~~~~~~~~~~~~ ">

    private void setButtonPriorites() {
        GridLayout GridLayout = new GridLayout();
        this.setLayout(GridLayout);
        GridLayout.setColumns(4);
        GridLayout.setRows(1);
        GridLayout.setHgap(1);
        add(OpenProjectAndRunButton);
        add(OpenProjectButton);
        add(ExitButton);

        OpenProjectButton.addActionListener(OpenProject_ActionListener);
        OpenProjectAndRunButton.addActionListener(OpenProjectAndRun_ActionListener);
        ExitButton.addActionListener(Exit_ActionListener);

        OpenProjectButton.setPreferredSize(ButtonSize);
        setPreferredSize(RibbonTaskBarSize);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~  Synchronized Get Methods   ~~~~~~~~~~~~~~~ ">
    public synchronized JCommandButton getOpenProjectButton() {
        return OpenProjectButton;
    }

    public synchronized JCommandButton getExitButton() {
        return ExitButton;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~    Synchronized Methods     ~~~~~~~~~~~~~~~ ">
    private synchronized void OpenProject() {
        //  DView.DViewListner.LoadProject();
        //  DView.getRibbon().setSelectedTask(DView.getRibbon().getTask(1));
        Network Network = DView.MainSimulationPanel.Network;
        Writer Writer = new Writer(Network.Directory);
        Writer.WriteNetworkData(Network);
    }

    private synchronized void OpenProjectAndRun() {
        //  DView.DViewListner.LoadProject();

        Network Network = DView.MainSimulationPanel.Network;
        Writer Writer = new Writer(Network.Directory);
        Writer.WriteNetworkData(Network);
    }

    // </editor-fold>
}
