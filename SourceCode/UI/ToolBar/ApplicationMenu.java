/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.ToolBar;

import UI.UserInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import jurassic.transcoded.text_html;
import org.pushingpixels.flamingo.api.common.CommandButtonDisplayState;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandButton.CommandButtonKind;
import org.pushingpixels.flamingo.api.common.JCommandButtonPanel;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntrySecondary;
import test.svg.transcoded.document_open;
import test.svg.transcoded.document_print;
import test.svg.transcoded.document_print_preview;
import test.svg.transcoded.printer;

/**
 *
 * @author aalnawai
 */
public class ApplicationMenu extends RibbonApplicationMenu {
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~        Static Method        ~~~~~~~~~~~~~~~ ">

    public static ResizableIcon getResizableIconFromResource(String resource) {
        return ImageWrapperResizableIcon.getIcon(ProjectBandSet.class.getClassLoader().getResource(resource), new Dimension(80, 80));
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~          Variables          ~~~~~~~~~~~~~~~ ">
    public ResizableIcon ApplicationIcon = getResizableIconFromResource("Resources/RibbonBands/Project.png");
    private UserInterface DView = null;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~       Action Listeners      ~~~~~~~~~~~~~~~ ">
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~ ApplicationMenu Constructos ~~~~~~~~~~~~~~~ ">

    public ApplicationMenu() {
        this.addMenuEntry(createOpenMenu());
        this.addMenuEntry(createPrintMenu());
        setDefault_CallBack();
    }

    public ApplicationMenu(UserInterface newDIRECTView) {
        DView = newDIRECTView;
      
//        this.addMenuEntry(createOpenMenu());
//        this.addMenuEntry(createPrintMenu());
//        setDefault_CallBack();
    }
    // </editor-fold>

    private void setDefault_CallBack() {
        setDefaultCallback(new RibbonApplicationMenuEntryPrimary.PrimaryRolloverCallback() {

            @Override
            public void menuEntryActivated(JPanel targetPanel) {
                targetPanel.removeAll();
                JCommandButtonPanel openHistoryPanel = new JCommandButtonPanel(CommandButtonDisplayState.MEDIUM);
                String groupName = "Default Documents";
                openHistoryPanel.addButtonGroup(groupName);
                for (int i = 0; i < 5; i++) {
                    JCommandButton historyButton = new JCommandButton(i + "    " + "default" + i + ".html", new text_html());
                    historyButton.setHorizontalAlignment(SwingUtilities.LEFT);
                    openHistoryPanel.addButtonToLastGroup(historyButton);
                }
                openHistoryPanel.setMaxButtonColumns(1);
                targetPanel.setLayout(new BorderLayout());
                targetPanel.add(openHistoryPanel, BorderLayout.CENTER);
            }
        });
    }

    private RibbonApplicationMenuEntryPrimary createOpenMenu() {
        RibbonApplicationMenuEntryPrimary amEntryOpen = new RibbonApplicationMenuEntryPrimary(
                new document_open(), "Open", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Invoked opening document");
            }
        }, CommandButtonKind.ACTION_ONLY);
        amEntryOpen.setRolloverCallback(new RibbonApplicationMenuEntryPrimary.PrimaryRolloverCallback() {

            @Override
            public void menuEntryActivated(JPanel targetPanel) {
                targetPanel.removeAll();
                JCommandButtonPanel openHistoryPanel = new JCommandButtonPanel(CommandButtonDisplayState.MEDIUM);
                String groupName = "Recent Documents";
                openHistoryPanel.addButtonGroup(groupName);
                for (int i = 0; i < 5; i++) {
                    JCommandButton historyButton = new JCommandButton(i + "    " + "document" + i + ".html", new text_html());
                    historyButton.setHorizontalAlignment(SwingUtilities.LEFT);
                    openHistoryPanel.addButtonToLastGroup(historyButton);
                }
                openHistoryPanel.setMaxButtonColumns(1);
                targetPanel.setLayout(new BorderLayout());
                targetPanel.add(openHistoryPanel, BorderLayout.CENTER);
            }
        });
        amEntryOpen.setActionKeyTip("O");
        return amEntryOpen;
    }

    private RibbonApplicationMenuEntryPrimary createPrintMenu() {
        RibbonApplicationMenuEntryPrimary amEntryPrint = new RibbonApplicationMenuEntryPrimary(
                new document_print(), "Print", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Invoked printing document");
            }
        }, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        amEntryPrint.setActionKeyTip("P");
        amEntryPrint.setPopupKeyTip("W");
        RibbonApplicationMenuEntrySecondary amEntryPrintSelect = new RibbonApplicationMenuEntrySecondary(new printer(), "Print", null, CommandButtonKind.ACTION_ONLY);
        amEntryPrintSelect.setDescriptionText("Select a printer, number of copies and other printing options before printing");
        amEntryPrintSelect.setActionKeyTip("P");
        RibbonApplicationMenuEntrySecondary amEntryPrintDefault = new RibbonApplicationMenuEntrySecondary(new document_print(), "Quick Print", null, CommandButtonKind.ACTION_ONLY);
        amEntryPrintDefault.setDescriptionText("Send the document directly to the default printer without making changes");
        amEntryPrintDefault.setActionKeyTip("Q");
        RibbonApplicationMenuEntrySecondary amEntryPrintPreview = new RibbonApplicationMenuEntrySecondary(new document_print_preview(), "Print Preview", null, CommandButtonKind.ACTION_ONLY);
        amEntryPrintPreview.setDescriptionText("Preview and make changes to the pages before printing");

        amEntryPrintPreview.setActionKeyTip("V");

        amEntryPrint.addSecondaryMenuGroup("Preview and print the document", amEntryPrintSelect, amEntryPrintDefault, amEntryPrintPreview);
        return amEntryPrint;
    }
}
