import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public DrawPanel[][] panels;
    public MainPanel(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
        panels = new DrawPanel[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                DrawPanel panel = new DrawPanel();
                panels[i][j] = panel;
                MainPanel.this.add(panel);
            }
        }
    }

    public void AddDrawablePanel(DrawPanel draw, Drawable o)
    {
        draw.setDrawable(o);
    }
}

