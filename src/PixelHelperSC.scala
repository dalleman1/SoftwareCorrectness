import javax.swing._
import java.awt._
import java.awt.geom.AffineTransform
import scala.collection.mutable.ListBuffer

class PixelHelperSC {
  private var pan: JPanel = null
  private var boundaryBox: BoundaryBox = null //new BoundaryBox(0,0,0,0)
  private var shapes: ListBuffer[CustomShape] = ListBuffer.empty[CustomShape]
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
    if(!boundaryBox.insideBox(x, y)) {
      return
    }
      val g2d = pan.getGraphics.create.asInstanceOf[Graphics2D]
      g2d.translate(0, pan.getHeight)
      g2d.scale(1, -1)
      g2d.setColor(color)
      g2d.drawRect(x, y, 1, 1)
      g2d.dispose()
  }

  def Fill(color: String, g : String):Unit = {
    val parsedColor = Color.decode(color)
    val shapeOpt = shapes.find(_.id == g)
    shapeOpt.foreach(shape => shape match {
      case rect: MyRectangle => FillRect(parsedColor, rect.x, rect.y, rect.x2, rect.y2)
      case circle: Circle => FillCircle(parsedColor, circle.x, circle.y, circle.radius)
      case line: Line => DrawLine(line.x1, line.y1, line.x2, line.y2, parsedColor)
    })
  }

  private def FillRect(color: Color = Color.BLACK, x1: Int, y1: Int, x2: Int, y2: Int): Unit = {
    for (y <- y1 to y2) {
      DrawLine(x1, y, x2, y, color)
    }
  }

  private def FillCircle(color: Color = Color.BLACK, x : Int, y : Int, radius : Int): Unit = {
    for (i <- (x - radius) to (x + radius)) {
      for (j <- (y - radius) to (y + radius)) {
        val dist = math.sqrt((i - x) * (i - x) + (j - y) * (j - y))
        if (dist <= radius) {
          doPixel(i, j, color)
        }
      }
    }
  }

  def TextAt(x : Int, y : Int, text : String): Unit = {
    val g2d = pan.getGraphics.create.asInstanceOf[Graphics2D]
    g2d.setColor(Color.BLACK)

    // Flip the y-coordinate of the text
    val fm = g2d.getFontMetrics()
    val textWidth = fm.stringWidth(text)
    val textHeight = fm.getHeight()
    val newX = x
    val newY = pan.getHeight() - y - textHeight

    g2d.drawString(text, newX, newY)
    g2d.dispose()
  }

  def Draw(input : String, color : String): Unit = {
    // Parse the color string to a Color object
    val parsedColor = Color.decode(color)
    val shapeIds = input.split(",")

    for (shapeId <- shapeIds) {
      val shapeOpt = shapes.find(_.id == shapeId)
      shapeOpt.foreach(shape => shape match {
        case rect: MyRectangle => DrawRectangle(rect.x, rect.y, rect.x2, rect.y2,parsedColor)
        case circle: Circle => DrawCircle(circle.x, circle.y, circle.radius,parsedColor)
        case line: Line => DrawLine(line.x1, line.y1, line.x2, line.y2,parsedColor)
      })
    }
  }
// color: Color = Color.BLACK should be included in drawline, circle and rectangle.
  def DrawLine(x1: Int, y1: Int, x2: Int, y2: Int, color: Color = Color.BLACK): String = {
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
      doPixel(x1_mod, y1_mod, color)
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
    objectCounter = objectCounter + 1;
    val line = Line("o" + objectCounter, x1, y1, x2, y2)
    shapes += line
    return "o" + objectCounter
  }

  def DrawCircle(inputX: Int, inputY: Int, r: Int, color: Color = Color.BLACK): String = {
    var r_mod = r
    var y = 0
    var decisionOver2 = 1 - r
    while (y <= r_mod) {
      doPixel(inputX + r_mod, inputY + y,color)
      doPixel(inputX + y, inputY + r_mod,color)
      doPixel(inputX - r_mod, inputY + y,color)
      doPixel(inputX - y, inputY + r_mod,color)
      doPixel(inputX - r_mod, inputY - y,color)
      doPixel(inputX - y, inputY - r_mod,color)
      doPixel(inputX + r_mod, inputY - y,color)
      doPixel(inputX + y, inputY - r_mod,color)
      y += 1
      if (decisionOver2 <= 0) decisionOver2 += 2 * y + 1
      else {
        r_mod -= 1
        decisionOver2 += 2 * (y - r_mod) + 1
      }
    }
    objectCounter = objectCounter + 1;
    val circle = Circle("o" + objectCounter, inputX, inputY, r)
    shapes += circle
    return "o" + objectCounter
  }

  def DrawRectangle(x1: Int, y1: Int, x2: Int, y2: Int, color: Color = Color.BLACK): String = {
    //Up
    DrawLine(x1, y1, x1, y2,color)
    //Up right
    DrawLine(x1, y2, x2, y2,color)
    //Up right to down
    DrawLine(x2, y2, x2, y1,color)
    //Bottom right
    DrawLine(x1, y1, x2, y1,color)

    objectCounter = objectCounter + 1;
    val rect = MyRectangle("o" + objectCounter, x1, y1, x2, y2)
    shapes += rect
    return "o" + objectCounter
  }

}
