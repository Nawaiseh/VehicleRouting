/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import UI.Utils.OpenGLSimulationPanel;
import java.io.IOException;
import javax.swing.JTextArea;

/**
 *
 * @author Alaa
 */
public class DSystem {
// <editor-fold defaultstate="collapsed" desc="Variables">
    OpenGLSimulationPanel OpenGLSimulationPanel = null;
    JTextArea Logger = null;
// </editor-fold>
    public DSystem(OpenGLSimulationPanel newSimulationPanel) {
        OpenGLSimulationPanel = newSimulationPanel;

        Logger = OpenGLSimulationPanel.GetLoggerTextArea();
          if (Logger == null){
              System.err.println("Error");
          }else{
            Logger.setEditable(false);
          }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public synchronized void println() {
        Logger.append("\n\r");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public synchronized void print(String str) {
        Logger.append(str);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public synchronized void print(int n) {
        Logger.append("" + n);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public synchronized void print(long n) {
        Logger.append("" + n);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public synchronized void print(float n) {
        Logger.append("" + n);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public synchronized void print(double n) {
        Logger.append("" + n);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public synchronized void println(String str) {
        Logger.append(str + "\n\r");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public synchronized void println(int n) {
        Logger.append("" + n + "\n\r");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public synchronized void println(long n) {
        Logger.append("" + n + "\n\r");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void println(float n) {
        Logger.append("" + n + "\n\r");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public synchronized void println(double n) {
        Logger.append("" + n + "\n\r");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void println(CloneNotSupportedException ex) {
        Logger.append(ex.toString() + "\n\r");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void print(CloneNotSupportedException ex) {
        Logger.append(ex.toString());
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void print(IOException ex) {
        Logger.append(ex.toString() + "\n\r");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void println(IOException ex) {
        Logger.append(ex.toString());
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

