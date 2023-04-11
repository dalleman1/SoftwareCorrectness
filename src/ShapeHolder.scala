import java.awt.image.BufferedImage
import java.awt.{BasicStroke, Color, Font, Graphics2D}
import java.awt.geom._
import javax.swing.JPanel

class ShapeHolder() {
  var g : Graphics2D = null

  def createBoundingBox(x1:Int, y1:Int, x2:Int, y2:Int, panel: JPanel) : Graphics2D = {

    g = panel.getGraphics.asInstanceOf[Graphics2D]

    g.translate(0,panel.getHeight - 1)
    g.scale(1,-1)
    g.setColor(Color.black)
    g.drawRect(x1,y1, x2, y2)

    g.setClip(x1,y1,x2,y2)

    return g
  }

  def createLine(x1:Int, y1:Int, x2:Int, y2:Int) : Unit = {
    g.drawLine(x1,y1,x2,y2);
  }

  def createRect(x1:Int, y1:Int, x2:Int, y2:Int): Unit = {
    g.drawRect(x1, y1, x2, y2);
  }

  //Not working as expected
  def createCircle(x1: Int, y1: Int, r: Int): Unit = {
    g.drawOval(x1-r,y1-r,r+x1,r+y1);//Draws an oval filling the rect(x1,y1,w,h)
  }
}
