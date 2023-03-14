import javax.swing.*;
import java.awt.*;

public class GridObject implements Drawable {
    private int x, y;
    private JPanel surface;

    public GridObject(int x, int y, JPanel surface) {
        this.x = x;
        this.y = y;
        this.surface = surface;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(x,y,surface.getWidth(),surface.getHeight());
    }
}
