class BoundaryBox (x1: Int, y1: Int, x2: Int, y2: Int){
  private val bx1: Int = x1
  private val by1: Int = y1
  private val bx2: Int = x2
  private val by2: Int = y2


  def insideBox(x: Int, y: Int): Boolean = {
    var res = true
    if(x < bx1 && x < bx2)
      res = false
    if (x > bx1 && x > bx2)
      res = false
    if (y < by1 && y < by2)
      res = false
    if (y > by1 && x > by2)
      res = false
    return res
  }
}
