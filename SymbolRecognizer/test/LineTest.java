import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import model.Line;
import model.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A class designed to test the model.Line Class.
 */
public class LineTest {
  private Line lineC;
  private Point2D pointA1;


  /**
   * Sets up the class for testing.
   */
  @Before
  public void setup() {
    pointA1 = new Point2D(0, 0);

  }

  /**
   * Tests that an invalid line throws an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    lineC = new Line(pointA1, pointA1);
  }

  /**
   * Tests the equals method.
   */
  @Test
  public void testEquals() {
    Point2D pointB1 = new Point2D(0, 5);
    Point2D pointA2 = new Point2D(0, 5);
    Point2D pointB2 = new Point2D(0, 0);
    Point2D pointC = new Point2D(5, 5);
    Point2D pointD = new Point2D(10, 10);
    Line lineAB = new Line(pointA1, pointB1);
    Line lineBA = new Line(pointA2, pointB2);
    lineC = new Line(pointC, pointD);
    assertTrue(lineAB.equals(lineBA));
    assertFalse(lineAB.equals(lineC));


  }

  /**
   * Tests the toString method.
   */
  @Test
  public void testToString() {
    Point2D pointB1 = new Point2D(0, 5);
    Line lineAB = new Line(pointA1, pointB1);
    assertEquals("Line{start=Point2D{x=0.0, y=0.0}, end=Point2D{x=0.0, y=5.0}}",
            lineAB.toString());
  }

  /**
   * Line test
   */
  @Test
  public void testAngle() {
    Point2D pointB1 = new Point2D(5, 0);
    Point2D pointB2 = new Point2D(0, 5);
    Point2D pointB3 = new Point2D(5, 5);
    Line line1 = new Line(pointB1, pointA1);
    Line line2 = new Line(pointB1, pointB2);
    Line line3 = new Line(pointB1, pointB3);
    Line line4 = new Line(pointA1, pointB1);
    Line line5 = new Line(pointA1, new Point2D(-5, 0));
    assertEquals(45, line1.calculateAngle(line2), 0.5);
    assertEquals(90, line1.calculateAngle(line3), 0.5);
    //Line line6 = new Line (new Point2D())
    //assertEquals(30, line);
    assertEquals(0, line4.calculateAngle(line5), 0.5);





  }


}