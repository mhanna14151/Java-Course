import org.junit.Test;

import model.Line;
import model.Point2D;
import model.Rectangle;

import static org.junit.Assert.*;

/**
 * A class made to test the Rectangle class.
 */
public class RectangleTest {

  /**
   * Tests if a Rectangle can be made.
   */
  @Test
  public void testRectangle() {
    boolean please = false;

    Point2D zero = new Point2D(0, 0);
    Point2D one = new Point2D(5, 0);
    Point2D two = new Point2D(5, 5);
    Point2D three = new Point2D(0, 5);

    Line a = new Line(zero, one);
    Line b = new Line(one, two);
    Line c = new Line(two, three);
    Line d = new Line(three, zero);
    Line e = new Line(new Point2D(420, 1520), new Point2D(650, 503));

    please = Rectangle.canMakeRectangle(a, b, c, e);
    boolean next = Rectangle.canMakeRectangle(a, b, c, d);

    assertFalse(please);
    assertTrue(next);

  }

}