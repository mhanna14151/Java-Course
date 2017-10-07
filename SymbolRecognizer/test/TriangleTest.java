import org.junit.Test;

import model.Line;
import model.Point2D;
import model.Triangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * This class tests the static method of canMakeTriangle within the model.Triangle class.
 */
public class TriangleTest {

  private Line l0;
  private Line l1;
  private Line l2;

  /**
   * This tests three parallel lines that aren't close to making a triangle.
   */
  @Test
  public void testThreeLinesNotClose() {
    l0 = new Line(new Point2D(0, 0), new Point2D(5, 0));
    l1 = new Line(new Point2D(0, 1), new Point2D(5, 1));
    l2 = new Line(new Point2D(0, 2), new Point2D(5, 2));
    assertFalse(Triangle.canMakeTriangle(l0, l1, l2));
  }

  /**
   * This tests two lines that are about to form a triangle, but the third doesn't close it.
   */
  @Test
  public void testTwoLinesClose() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(22, 22), new Point2D(40, 40));
    assertFalse(Triangle.canMakeTriangle(l0, l1, l2));
  }

  /**
   * This tests three lines that form a triangle.
   */
  @Test
  public void testMakesATriangle() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(5.0, 0.0), new Point2D(0.0, 5.0));
    assertTrue(Triangle.canMakeTriangle(l0, l1, l2));
  }

  /**
   * Tests makes a triangle with intersections within a threshold.
   */
  @Test
  public void testMakesATriangleWithinThreshold() {
    l0 = new Line(new Point2D(0.2, 0.2), new Point2D(5.2, 0.2));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(5.1, 0.1), new Point2D(0.1, 5.1));
    assertTrue(Triangle.canMakeTriangle(l0, l1, l2));
  }

  /**
   * Tests makes a triangle with intersections within a threshold, changing the order of triangles.
   */
  @Test
  public void testMakesATriangleWithinThresholdTwo() {
    l0 = new Line(new Point2D(0.2, 0.2), new Point2D(5.2, 0.2));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(5.1, 0.1), new Point2D(0.1, 5.1));
    assertTrue(Triangle.canMakeTriangle(l0, l2, l1));
  }

  /**
   * Tests makes a triangle with intersections within a threshold, changing the order of triangles.
   */
  @Test
  public void testMakesATriangleWithinThresholdThree() {
    l0 = new Line(new Point2D(0.2, 0.2), new Point2D(5.2, 0.2));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(5.1, 0.1), new Point2D(0.1, 5.1));
    assertTrue(Triangle.canMakeTriangle(l1, l0, l2));
  }

  /**
   * Tests makes a triangle with intersections within a threshold, changing the order of triangles.
   */
  @Test
  public void testMakesATriangleWithinThresholdFour() {
    l0 = new Line(new Point2D(0.2, 0.2), new Point2D(5.2, 0.2));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(5.1, 0.1), new Point2D(0.1, 5.1));
    assertTrue(Triangle.canMakeTriangle(l1, l2, l0));
  }

  /**
   * Tests makes a triangle with intersections outside of threshold.
   */
  @Test
  public void testMakesATriangleOutsideThreshold() {
    l0 = new Line(new Point2D(0.6, 0.6), new Point2D(7.6, 0.6));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(20, 0.1), new Point2D(-20, 6.1));
    assertFalse(Triangle.canMakeTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that have correct slope but x and y position are outside of threshold.
   */
  @Test
  public void testDoesntMakeTriangleOutsideOfPositionalTreshhold() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    l1 = new Line(new Point2D(3.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(25, 0.0), new Point2D(-17, 3.0));
    assertFalse(Triangle.canMakeTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that go into quadrant three (negative x and y values).
   */
  @Test
  public void testMakeTriangleQuadrantThree() {
    l0 = new Line(new Point2D(-1.0, 0.0), new Point2D(-5.0, 0.0));
    l1 = new Line(new Point2D(-1.0, 0.0), new Point2D(-1.0, -5.0));
    l2 = new Line(new Point2D(-5.0, 0.0), new Point2D(-1.0, -5.0));
    assertTrue(Triangle.canMakeTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that go into quadrant three (negative x and y values).
   */
  @Test
  public void testMakeTriangleQuadrantThreeSecond() {
    l0 = new Line(new Point2D(-1.0, 0.0), new Point2D(-5.0, -5.0));
    l1 = new Line(new Point2D(-1.0, -5.0), new Point2D(-5.0, -5.0));
    l2 = new Line(new Point2D(-1.0, 0.0), new Point2D(-1.0, -5.0));
    assertTrue(Triangle.canMakeTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that overlap each other vertically.
   */
  @Test
  public void testOverlappedLinesVertical() {
    l0 = new Line(new Point2D(-7.0, 0.0), new Point2D(-7.0, -1.0));
    l1 = new Line(new Point2D(-7.0, 1.0), new Point2D(-7.0, -2.0));
    l2 = new Line(new Point2D(-7.0, 2.0), new Point2D(-7.0, -3.0));
    assertFalse(Triangle.canMakeTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that overlap each other horizontally.
   */
  @Test
  public void testOverlappedLinesHorizontal() {
    l0 = new Line(new Point2D(-1.0, -1.0), new Point2D(1.0, -1.0));
    l1 = new Line(new Point2D(-2.0, -1.0), new Point2D(2.0, -1.0));
    l2 = new Line(new Point2D(-3.0, -1.0), new Point2D(3.0, -1.0));
    assertFalse(Triangle.canMakeTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that are the same.
   */
  @Test
  public void testSameLines() {
    l0 = new Line(new Point2D(-1.0, -1.0), new Point2D(1.0, -1.0));
    assertFalse(Triangle.canMakeTriangle(l0, l0, l0));
  }

  /**
   * Tests that an invalid model.Triangle cannot be made.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTheConstructor() {
    l0 = new Line(new Point2D(-1.0, -1.0), new Point2D(1.0, -1.0));
    l1 = new Line(new Point2D(-2.0, -1.0), new Point2D(2.0, -1.0));
    l2 = new Line(new Point2D(-3.0, -1.0), new Point2D(3.0, -1.0));
    Triangle tri1 = new Triangle(l0, l1, l2);

  }

  /**
   * Tests the equals method.
   */
  @Test
  public void testEquals() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(5.0, 0.0), new Point2D(0.0, 5.0));
    Line l0a = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    Line l1a = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    Line l2a = new Line(new Point2D(5.0, 0.0), new Point2D(0.0, 5.0));
    Line l0b = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    Line l1b = new Line(new Point2D(5.0, 0.0), new Point2D(0.0, 6.0));
    Line l2b = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 6.0));
    Triangle tri1 = new Triangle(l0, l1, l2);
    Triangle tri2 = new Triangle(l1a, l0a, l2a);
    Triangle tri3 = new Triangle(l1b, l0b, l2b);
    assertTrue(tri1.equals(tri2));
    assertFalse(tri1.equals(tri3));

  }

  /**
   * Tests the toString method.
   */
  @Test
  public void testToString() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(5.0, 0.0), new Point2D(0.0, 5.0));
    Triangle tri = new Triangle(l0, l1, l2);
    assertEquals("Triangle with Sides:\n" +
            "L1: Start(0.00, 0.00) End:(5.00, 0.00)\n" +
            "L2: : Start(0.00, 0.00) End:(0.00, 5.00)\n" +
            "L2: Start(5.00, 0.00) End:(0.00, 5.00)", tri.toString());


  }

}