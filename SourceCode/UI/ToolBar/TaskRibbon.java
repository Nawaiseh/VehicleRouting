/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.ToolBar;

import org.pushingpixels.flamingo.api.ribbon.AbstractRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;

/**
 *
 * @author aalnawai
 */
public class TaskRibbon extends RibbonTask {

    Object BandSet = null;

    public TaskRibbon(String title, Object newBandSet, AbstractRibbonBand<?>[] bands) {
        super(title, bands);
        BandSet = newBandSet;
    }

    public Object getBandSet() {
        return  BandSet;
    }
    public ProjectBandSet getProjectBandSet() {
        return (ProjectBandSet) BandSet;
    }

    public SimulationBandSet getSimulationBandSet() {
        return (SimulationBandSet) BandSet;
    }

   
}
