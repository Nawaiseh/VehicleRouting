package UI.ToolBar;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;

public class RibbonBand extends JRibbonBand {

    private static Dimension ButtonSize = new Dimension(60, 60);

    public static ResizableIcon getResizableIconFromResource(String resource) {
        try {
            return ImageWrapperResizableIcon.getIcon(RibbonBand.class.getClassLoader().getResource(resource), ButtonSize);
        } catch (Exception e) {
            return null;
        }
    }

    public RibbonBand(String Title, ResizableIcon ResizableIcon, int nButtons) {
        super(Title, ResizableIcon);
        setPolicies(nButtons);
    }

    private void setPolicies(int nButtons) {
        if (nButtons == 1) {
            setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(getControlPanel())));
        } else if (nButtons <= 3) {
            setResizePolicies((List) Arrays.asList(new IconRibbonBandResizePolicy(getControlPanel())));
            setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(getControlPanel()), new IconRibbonBandResizePolicy(getControlPanel())));
        } else {
            setResizePolicies((List) Arrays.asList(new IconRibbonBandResizePolicy(getControlPanel())));
            setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(getControlPanel()), new IconRibbonBandResizePolicy(getControlPanel())));
            setResizePolicies((List) Arrays.asList(
                    new CoreRibbonResizePolicies.None(getControlPanel()),
                    new CoreRibbonResizePolicies.Mirror(getControlPanel()),
                    new IconRibbonBandResizePolicy(getControlPanel())));
        }
    }
}