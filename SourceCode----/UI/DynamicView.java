/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DockingInterface.docking.View;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author aalnawai
 */
public class DynamicView extends View {

    private static final long serialVersionUID = 1L;
    private String Title = "Dynamic View";
    private static int IDCounter = 0;
    private int ID = 0;
    private boolean isNew = true;
    private boolean isDeleted = false;
    private Component Component = null;
    private JScrollPane JScrollPane = null;

    DynamicView(String newTitle, Icon newIcon, Component newComponent) {
        super(newTitle, newIcon, newComponent);

        if (newComponent instanceof JScrollPane){
          JScrollPane = (JScrollPane)newComponent;
        Component = JScrollPane.getComponent(0);
        }else{
           JScrollPane = null;
           Component= newComponent;
        }
        
        
        ID = (IDCounter++);
        Title = newTitle;
        isNew = true;
    }

    public synchronized String getViewTitle() {
        return Title;
    }

    public synchronized int getID() {
        return ID;
    }

    public synchronized boolean IsNew() {
        return isNew;
    }

    public synchronized boolean IsDeleted() {
        return isDeleted;
    }

    public synchronized void SetIsDeleted() {
        isDeleted = true;
    }

    public synchronized void RemoveCurrentComponent() {
        if (Component != null) {
            JScrollPane.remove(Component);
        }
    }

    public synchronized Component getCurrentComponent() {
        return Component;
    }

//    @Override
//    public synchronized void setComponent(Component newComponent) {
//        JScrollPane.removeAll();
//        Component = newComponent;
//        JScrollPane.add(Component);
//    }
}
