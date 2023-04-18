import javax.swing.*;
import java.awt.*;

public class PixelHelper {

    private JPanel pan;

    PixelHelper(JPanel panel)
    {
        pan = panel;
    }
    public void doPixel(int x, int y){
        pan.getGraphics().fillRect(x, y, 1, 1);
    }

    public void DrawLine(int x1, int y1, int x2, int y2)
    {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int D = 2*dy - dx;
        int y = y1;

        for (int x = x1; x < x2; x++) {
            doPixel(x,y);
            if (D > 0)
            {
                y = y+1;
                D = D - 2 *dx;
            }
            D = D + 2*dy;
        }

    }
}
