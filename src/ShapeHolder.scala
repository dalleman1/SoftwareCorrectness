import java.awt.image.BufferedImage
import java.awt.{BasicStroke, Color, Font, Graphics2D}
import java.awt.geom._
import javax.swing.JPanel

class ShapeHolder() {

  def createBoundingBox(x1:Int, y1:Int, x2:Int, y2:Int, panel: JPanel) : Graphics2D = {

    val g = panel.getGraphics.asInstanceOf[Graphics2D]
    print(g)
    print(panel.getHeight)

    g.translate(0,panel.getHeight - 1)
    g.scale(1,-1)
    g.setColor(Color.black)
    g.drawRect(x1,y1, x2, y2)

    g.setClip(x1,y1,x2,y2)

    return g
  }

  def createLine(x1:Int, y1:Int, x2:Int, y2:Int, panel: JPanel) : Unit = {
    val g = panel.getGraphics.asInstanceOf[Graphics2D]
    g.drawLine(x1,y1,x2,y2);

  }
}
