import org.scalatest.funsuite.AnyFunSuite

import java.awt.{Color, Dimension}
import javax.swing.{BorderFactory, JPanel}
import scala.collection.mutable.ArrayBuffer



class PixelHelperSCTest extends AnyFunSuite {
  var UUT = new PixelHelperSC(new JPanel())

  test("doPixelMuck.Outside") {
    UUT.createBoundaryBox(100,100,200,200)
    UUT.doPixel(1,1)
    assert(!(UUT.donePixels.contains(1,1)))
  }

  test("doPixelMuck.Inside") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.doPixel(101, 101)
    assert(UUT.donePixels.contains(101, 101))
  }

  test("doPixelMuck.OnBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.doPixel(100, 100)
    assert(UUT.donePixels.contains(100, 100))
  }

  test("drawLine.InBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.DrawLine(101, 101, 101, 199)
    var i: Int = 101
    for (i <- 101 to 199) {
      assert(UUT.donePixels.contains(101, i))
    }
  }

  test("drawLine.InBorderDiagonal") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.DrawLine(101, 101, 199, 199)
    var i: Int = 101
    for (i <- 101 to 199) {
      assert(UUT.donePixels.contains(i, i))
    }
  }

  test("drawLine.OutsideBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.DrawLine(99, 99, 99, 200)
    var i: Int = 99
    for (i <- 99 to 200) {
      assert(!UUT.donePixels.contains(99, i))
    }
  }

  test("drawLine.OnBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.DrawLine(100, 100, 100, 200)
    var i:Int = 100
    for(i <- 100 to 200){
      assert(UUT.donePixels.contains(100, i))
    }
  }

  test("drawRect.InBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.DrawRectangle(101, 101, 199, 199)
    var i: Int = 101
    for (i <- 101 to 199) {
      assert(UUT.donePixels.contains(101, i))
    }
  }

  test("drawRect.OutsideBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.DrawRectangle(99, 99, 201, 201)
    var i: Int = 99
    for (i <- 99 to 201) {
      assert(!UUT.donePixels.contains(99, i))
    }
  }

  test("drawRect.OnBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.DrawRectangle(100, 100, 100, 200)
    var i: Int = 100
    for (i <- 100 to 200) {
      assert(UUT.donePixels.contains(100, i))
    }
  }

  test("drawCirle.OnBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.DrawCircle(100, 100, 100)
    var i: Int = 100
    for (i <- 100 to 200) {
      assert(UUT.donePixels.contains(100, i))
    }
  }
}
