import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.BeforeAndAfter
import javax.swing.{JPanel}
import scala.collection.mutable.ArrayBuffer



class PixelHelperSCTest extends AnyFunSuite with BeforeAndAfter {
  var UUT: PixelHelperSC = _

  before {
    UUT = new PixelHelperSC(new JPanel())
    UUT.donePixels = new ArrayBuffer[(Int, Int)]
  }

  test("doPixelMuck.Outside") {
    UUT.createBoundaryBox(100,100,200,200)
    val n = UUT.donePixels.size
    UUT.doPixel(1,1)
    assert(!(UUT.donePixels.contains(1,1)))
    assert(UUT.donePixels.size == n)
  }

  test("doPixelMuck.Inside") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    val n = UUT.donePixels.size
    UUT.doPixel(101, 101)
    assert(UUT.donePixels.contains(101, 101))
    assert(UUT.donePixels.size == n+1)
  }

  test("doPixelMuck.OnBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    val n = UUT.donePixels.size
    UUT.doPixel(100, 100)
    assert(UUT.donePixels.contains(100, 100))
    assert(UUT.donePixels.size == n+1) //New pixel is placed atop of boundary box
  }

  test("drawLine.InBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    val n = UUT.donePixels.size
    UUT.DrawLine(101, 101, 101, 103)
    var i: Int = 101
    for (i <- 101 to 103) {
      assert(UUT.donePixels.contains(101, i))
    }
    assert(UUT.donePixels.size == n+3) //Assert that 3 new pixels are placed
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
    val n = UUT.donePixels.size
    UUT.DrawLine(99, 99, 99, 200)
    var i: Int = 99
    for (i <- 99 to 200) {
      assert(!UUT.donePixels.contains(99, i))
    }
    assert(UUT.donePixels.size == n) //No new pixels are placed
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

  test("drawCirle.OutsideBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.DrawCircle(300, 300, 50)
    assert(!UUT.donePixels.contains(350, 300))
    assert(!UUT.donePixels.contains(250, 300))
    assert(!UUT.donePixels.contains(300, 350))
    assert(!UUT.donePixels.contains(300, 250))
  }

  test("drawCirle.InsideBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.DrawCircle(150, 150, 5)
    assert(UUT.donePixels.contains(150, 155))
    assert(UUT.donePixels.contains(155, 150))
    assert(!UUT.donePixels.contains(155, 155)) //Is a circle not a rect
    assert(UUT.donePixels.contains(150, 145))
    assert(UUT.donePixels.contains(145, 150))
  }

  test("drawCirle.OnBorder") {
    UUT.createBoundaryBox(100, 100, 200, 200)
    UUT.DrawCircle(101, 101, 5)
    assert(UUT.donePixels.contains(101, 106))
    assert(UUT.donePixels.contains(106, 101))
    assert(!UUT.donePixels.contains(101, 96))
    assert(!UUT.donePixels.contains(96, 101))
  }
}
