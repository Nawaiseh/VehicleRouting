package Execution;

import UI.UserInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
// 2QBP3-289MF-9364X-37XGX-24W6P

/**
 *
 * @author aalnawai
 */
public class Master {

    private static final Logger MyLogger = Logger.getLogger(Master.class.getName());
    public static boolean Release = false;
    public static boolean AutomatedOpenWithoutRun = false;
    public static boolean AutomatedOpenAndRun = false;
    public static boolean UserInterfaceIsDisabled = false;
    public static long BackupPeriod = 31L * 24L * 3600L * 1000L;
    public static boolean EnableBackup = false;

  

    public static void main(String[] args) throws Exception {
       
        try {
            AutomatedOpenAndRun = (args[0].equalsIgnoreCase("Automated"));
            AutomatedOpenWithoutRun = (args[0].equalsIgnoreCase("AutomatedOpenOnly"));
        } catch (Exception Exception) {
        }
        try {
            UserInterfaceIsDisabled = (args[1].equalsIgnoreCase("NoGUI"));
            BackupPeriod = (args[2].equalsIgnoreCase("NA")) ? Long.MAX_VALUE : Long.parseLong(args[2]) * 24L * 3600L * 1000L;
            EnableBackup = (BackupPeriod < Long.MAX_VALUE) ? true : false;
        } catch (Exception Exception) {
        }
        DateFormat df = new SimpleDateFormat("MM:dd:yyyy");

        Date now = new Date();
        String st = df.format(now);
        int month = Integer.parseInt(st.substring(0, 2));
        int day = Integer.parseInt(st.substring(3, 5));
        int year = Integer.parseInt(st.substring(6, 10));
        //<editor-fold defaultstate="collapsed" desc=" Look and Feel Setting">

        String[] LAFs = new String[]{"Metal", "Nimbus", "CDE/Motif", "Windows", "Windows Classic"};
        try {
            for (UIManager.LookAndFeelInfo LookAndFeelInfo : UIManager.getInstalledLookAndFeels()) {
                if (LAFs[1].equals(LookAndFeelInfo.getName())) {
                    javax.swing.UIManager.setLookAndFeel(LookAndFeelInfo.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            MyLogger.log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            MyLogger.log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            MyLogger.log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            MyLogger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        if (year > 2008) {
//            System.exit(0);
//        }
        //   UIManager.setLookAndFeel(new InfoNodeLookAndFeel());
        // Docking windwos should be run in the Swing thread
        SwingUtilities.invokeLater(new Runnable() {
            @SuppressWarnings("ResultOfObjectAllocationIgnored")
            @Override
            public void run() {
//                DateFormat DateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
//                Date TimeNowFromDate = new Date();
//                        StartTime = DateFormatter.format(TimeNowFromDate);

                new UserInterface();

            }
        });
        // initialize and display the interface
        //  DIRECTView frontEnd = new DIRECTView();

        //frontEnd.setVisible(true);
        // frontEnd.createAllListeners();
    }
}
