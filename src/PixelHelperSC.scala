import javax.swing._
import java.awt._

class PixelHelperSC {
  private var pan: JPanel = null
  private var boundaryBox: BoundaryBox = null //new BoundaryBox(0,0,0,0)
  private var shapes: List[CustomShape] = List()
  private var objectCounter = 0
  def this(panel: JPanel) {
    this()
    pan = panel
  }

  def createBoundaryBox(x1: Int, y1: Int, x2: Int, y2: Int): Unit = {
    boundaryBox = new BoundaryBox(x1,y1,x2,y2)
    DrawRectangle(x1, y1, x2, y2)
  }

  def doPixel(x: Int, y: Int, color: Color = Color.BLACK): Unit = {
    if(boundaryBox.insideBox(x, y)) {
      val g2d = pan.getGraphics.create.asInstanceOf[Graphics2D]
      g2d.translate(0, pan.getHeight)
      g2d.scale(1, -1)
      g2d.setColor(color)
      g2d.drawRect(x, y, 1, 1)
      g2d.dispose()
    }
  }


  def Draw(input : String, color : String): Unit = {
    // Parse the color string to a Color object
    val parsedColor = Color.decode(color)
    val shapeIds = input.split(",")

    for (shapeId <- shapeIds) {
      val shapeOpt = shapes.find(_.id == shapeId)
      shapeOpt.foreach(shape => shape match {
        case rect: Rectangle => DrawRectangle()
        case circle: Circle => DrawCircle()
        case line: Line => DrawLine()
      })
    }
  }
// color: Color = Color.BLACK should be included in drawline, circle and rectangle.
  def DrawLine(x1: Int, y1: Int, x2: Int, y2: Int): Unit = {
    var x1_mod = x1
    var y1_mod = y1
    var endLoop = false
    val dx = Math.abs(x2 - x1)
    val dy = Math.abs(y2 - y1)
    val sx = if (x1 < x2) 1
    else -1
    val sy = if (y1 < y2) 1
    else -1
    var err = dx - dy
    //Do while may be deprivated
    while(!endLoop) {
      doPixel(x1_mod, y1_mod)
      if(x1_mod == x2 && y1_mod == y2) {
        endLoop = true
      }
      val e2 = 2 * err
      if (e2 > -dy) {
        err -= dy
        x1_mod += sx
      }
      if (e2 < dx) {
        err += dx
        y1_mod += sy
      }
    }
    val line = Line("o" + objectCounter, x1, y1, x2, y2)
    shapes = line :: shapes
  }

  def DrawCircle(inputX: Int, inputY: Int, r: Int): Unit = {
    var r_mod = r
    var y = 0
    var decisionOver2 = 1 - r
    while (y <= r_mod) {
      doPixel(inputX + r_mod, inputY + y)
      doPixel(inputX + y, inputY + r_mod)
      doPixel(inputX - r_mod, inputY + y)
      doPixel(inputX - y, inputY + r_mod)
      doPixel(inputX - r_mod, inputY - y)
      doPixel(inputX - y, inputY - r_mod)
      doPixel(inputX + r_mod, inputY - y)
      doPixel(inputX + y, inputY - r_mod)
      y += 1
      if (decisionOver2 <= 0) decisionOver2 += 2 * y + 1
      else {
        r_mod -= 1
        decisionOver2 += 2 * (y - r_mod) + 1
      }
    }
    val circle = Circle("o" + objectCounter, inputX, inputY, r)
    shapes = circle :: shapes
  }

  def DrawRectangle(x1: Int, y1: Int, x2: Int, y2: Int): Unit = {
    //Up
    DrawLine(x1, y1, x1, y2)
    //Up right
    DrawLine(x1, y2, x2, y2)
    //Up right to down
    DrawLine(x2, y2, x2, y1)
    //Bottom right
    DrawLine(x1, y1, x2, y1)

    val line = Rectangle("o" + objectCounter, x1, y1, x2, y2)
    shapes = line :: shapes
  }

}
