import javax.swing.*;
import java.awt.*;

public class PixelHelper {

    private JPanel pan;

    PixelHelper(JPanel panel)
    {
        pan = panel;
    }
    public void doPixel(int x, int y){
        Graphics2D g2d = (Graphics2D) pan.getGraphics().create();
        g2d.translate(0, pan.getHeight());
        g2d.scale(1, -1);


        g2d.drawRect(x, y, 1, 1);

        g2d.dispose();
    }

    public void DrawLine(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            doPixel(x1, y1);
            if (x1 == x2 && y1 == y2) {
                break;
            }
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }

    public void DrawCircle(int inputX, int inputY, int r) {
        int y = 0;
        int decisionOver2 = 1 - r;

        while (y <= r) {
            doPixel(inputX + r, inputY + y);
            doPixel(inputX + y, inputY + r);
            doPixel(inputX - r, inputY + y);
            doPixel(inputX - y, inputY + r);
            doPixel(inputX - r, inputY - y);
            doPixel(inputX - y, inputY - r);
            doPixel(inputX + r, inputY - y);
            doPixel(inputX + y, inputY - r);
            y++;
            if (decisionOver2 <= 0) {
                decisionOver2 += 2 * y + 1;
            } else {
                r--;
                decisionOver2 += 2 * (y - r) + 1;
            }
        }
    }

    public void DrawRectangle(int x1, int y1, int x2, int y2)
    {
        //Up
        DrawLine(x1,y1, x1,y2);

        //Up right
        DrawLine(x1,y2, x2,y2);

        //Up right to down
        DrawLine(x2,y2, x2,y1);

        //Bottom right
        DrawLine(x1,y1, x2,y1);
    }

}
