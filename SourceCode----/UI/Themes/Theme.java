package UI.Themes;

import java.awt.Color;
import java.awt.Font;

public class Theme {

    public enum Category {

        Button, Panel, TextField, Label, ToolBar, TabbedPane
    }
    public CategoryColor[] Colors = new CategoryColor[Category.values().length];

    public Theme() {
        for (int Index = 0; Index < Colors.length; Index++) {
            Colors[Index] = new CategoryColor();
        }
    }

    public class CategoryColor {

        public Color Background = Color.LIGHT_GRAY;
        public Color Forground = Color.BLACK;
        public Font Font = new Font("Tahoma", 0, 12);
    }
}
