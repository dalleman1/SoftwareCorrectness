class PixelHelper(g) {
  var graphics : g

  def doPixel(int x, int y): Unit{
      graphics.fillRect(x, y, 1, 1);
  }
}
