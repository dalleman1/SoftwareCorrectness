sealed trait CustomShape {
  val id: String
}

case class Line(id: String, x1: Int, y1: Int, x2: Int, y2: Int) extends CustomShape

case class Rectangle(id: String, x: Int, y: Int, x2: Int, y2: Int) extends CustomShape

case class Circle(id: String, x: Int, y: Int, radius: Int) extends CustomShape