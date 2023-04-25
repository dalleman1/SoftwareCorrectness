import org.scalatest.funsuite.AnyFunSuite

import java.awt.{Color, Dimension}
import javax.swing.{BorderFactory, JPanel}



class PixelHelperSCTest extends AnyFunSuite {
  test("PixelHelperSC.doPixel") {
    var mainPanel: DrawPanel = new DrawPanel
    var UUT = new PixelHelperSC(mainPanel)
    mainPanel.setPreferredSize(new Dimension(320, 220))
    mainPanel.setBorder(BorderFactory.createLineBorder(Color.black))
    println(mainPanel.getGraphics)

    UUT.createBoundaryBox(0,0,320,220)
    UUT.doPixel(1, 1)
    mainPanel.revalidate()
    assert(mainPanel.contains(1,1))
  }
}
