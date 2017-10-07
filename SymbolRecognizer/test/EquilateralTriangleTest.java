import org.junit.Test;

import model.EquilateralTriangle;
import model.Line;
import model.Point2D;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 * This class tests the static method of canMakeEquilateralTriangle within
 * the model.EquilateralTriangle class.
 */
public class EquilateralTriangleTest {

  private Line l0;
  private Line l1;
  private Line l2;

  /**
   * This tests three parallel lines that aren't close to making a model.EquilateralTriangle.
   * Sides are not equal.
   */
  @Test
  public void testThreeLinesNotClose() {
    l0 = new Line(new Point2D(0, 0), new Point2D(5, 0));
    l1 = new Line(new Point2D(1, 0), new Point2D(5, 1));
    l2 = new Line(new Point2D(2, 2), new Point2D(5, 2));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * This tests two lines that are about to form a model.EquilateralTriangle,
   * but the third doesn't close it and sides aren't equal.
   */
  @Test
  public void testTwoLinesClose() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(10, 0.0));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 10));
    l2 = new Line(new Point2D(35.0, 0.0), new Point2D(10, 0.0));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * This tests three lines that don't form an equilateral triangle, but are of equal size.
   */
  @Test
  public void testMakesANotEquilateralTriangle() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(25.0, 0.0));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 25.0));
    l2 = new Line(new Point2D(25.0, 0.0), new Point2D(0.0, 0));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * This tests three lines that do form an equilteral triangle.
   */
  @Test
  public void testMakesAEquilateralTriangle() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(3.0, 0.0));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(1.5, 2.6));
    l2 = new Line(new Point2D(3.0, 0.0), new Point2D(1.5, 2.6));
    assertTrue(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * This tests three lines that do form an equalteral triangle.
   */
  @Test
  public void testMakesAEquilateralTriangleOtherQuadrant() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(-5.0, 0.0));
    l1 = new Line(new Point2D(-2.50, 4.33), new Point2D(-5.0, 0.0));
    l2 = new Line(new Point2D(-2.50, 4.33), new Point2D(0.00, 0.00));
    assertTrue(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * This tests three lines that do form an equalteral triangle.
   */
  @Test
  public void testMakesAEquilateralTriangleOtherQuadrantTwo() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(-5.0, 0.0));
    l1 = new Line(new Point2D(-2.50, -4.33), new Point2D(-5.0, 0.0));
    l2 = new Line(new Point2D(-2.50, -4.33), new Point2D(0.00, 0.00));
    assertTrue(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * This tests three lines that do form an equalteral triangle in a different order.
   */
  @Test
  public void testMakesAEquilateralTriangleOtherQuadrantThree() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(-5.0, 0.0));
    l1 = new Line(new Point2D(-2.50, -4.33), new Point2D(-5.0, 0.0));
    l2 = new Line(new Point2D(-2.50, -4.33), new Point2D(0.00, 0.00));
    assertTrue(EquilateralTriangle.canMakeEquilateralTriangle(l2, l1, l0));
  }

  /**
   * This tests three lines that do form an equalteral triangle in a different order.
   */
  @Test
  public void testMakesAEquilateralTriangleOtherQuadrantFour() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(-5.0, 0.0));
    l1 = new Line(new Point2D(-2.50, -4.33), new Point2D(-5.0, 0.0));
    l2 = new Line(new Point2D(-2.50, -4.33), new Point2D(0.00, 0.00));
    assertTrue(EquilateralTriangle.canMakeEquilateralTriangle(l1, l2, l0));
  }


  /**
   * Tests makes a model.EquilateralTriangle with intersections within a threshold, but
   * sides are not equal.
   */
  @Test
  public void testMakesAEquilateralTriangleWithinThreshold() {
    l0 = new Line(new Point2D(0.2, 0.2), new Point2D(25.2, 0.2));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 25.0));
    l2 = new Line(new Point2D(30.1, 0.1), new Point2D(0.1, 30.1));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * Tests makes a model.EquilateralTriangle with intersections within a threshold,
   * changing the order of EquilateralTriangles and sides are not equal.
   */
  @Test
  public void testMakesAEquilateralTriangleWithinThresholdTwo() {
    l0 = new Line(new Point2D(0.2, 45), new Point2D(45, 0.2));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 45));
    l2 = new Line(new Point2D(45, 0.1), new Point2D(0.1, 45));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l0, l2, l1));
  }

  /**
   * Tests makes a model.EquilateralTriangle with intersections within a threshold,
   * changing the order of EquilateralTriangles. Sides are not equal.
   */
  @Test
  public void testMakesAEquilateralTriangleWithinThresholdThree() {
    l0 = new Line(new Point2D(0.2, 0.2), new Point2D(25.2, 0.2));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(25.1, 0.1), new Point2D(0.1, 25.1));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l1, l0, l2));
  }

  /**
   * Tests intersections within a threshold, changing the order of EquilateralTriangles. Sides
   * are not equal.
   */
  @Test
  public void testMakesAEquilateralTriangleWithinThresholdFour() {
    l0 = new Line(new Point2D(0.2, 0.2), new Point2D(25.2, 0.2));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 25.0));
    l2 = new Line(new Point2D(25.1, 0.1), new Point2D(0.1, 25.1));
    assertTrue(EquilateralTriangle.canMakeEquilateralTriangle(l1, l2, l0));
  }

  /**
   * Tests makes a model.EquilateralTriangle with intersections outside of threshold.
   * Sides are not equal.
   */
  @Test
  public void testMakesAEquilateralTriangleOutsideThreshold() {
    l0 = new Line(new Point2D(0.6, 0.6), new Point2D(25.6, 0.6));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 25.0));
    l2 = new Line(new Point2D(30.1, 0.1), new Point2D(0.1, 30.1));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that have correct slope but x and y position are outside of threshold.
   * Sides are not equal.
   */
  @Test
  public void testDoesntMakeEquilateralTriangleOutsideOfPositionalTreshhold() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(25.0, 0.0));
    l1 = new Line(new Point2D(1.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(7.0, 0.0), new Point2D(0.0, 3.0));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that go into quadrant three (negative x and y values). Sides are not equal.
   */
  @Test
  public void testMakeEquilateralTriangleQuadrantThree() {
    l0 = new Line(new Point2D(-1.0, 0.0), new Point2D(-25.0, 0.0));
    l1 = new Line(new Point2D(-1.0, 0.0), new Point2D(-1.0, -5.0));
    l2 = new Line(new Point2D(-5.0, 0.0), new Point2D(-1.0, -7.0));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that go into quadrant three (negative x and y values). Sides are not equal,
   * but within threshold.
   */
  @Test
  public void testMakeEquilateralTriangleQuadrantThreeSecond() {
    l0 = new Line(new Point2D(-1.0, 0.0), new Point2D(-25.0, -25.0));
    l1 = new Line(new Point2D(-1.0, -25.0), new Point2D(-25.0, -25.0));
    l2 = new Line(new Point2D(-1.0, 0.0), new Point2D(-1.0, -25.0));
    assertTrue(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that overlap each other vertically. Sides are not equal.
   */
  @Test
  public void testOverlappedLinesVertical() {
    l0 = new Line(new Point2D(-7.0, 0.0), new Point2D(-7.0, -1.0));
    l1 = new Line(new Point2D(-7.0, 1.0), new Point2D(-7.0, -2.0));
    l2 = new Line(new Point2D(-7.0, 2.0), new Point2D(-7.0, -3.0));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that overlap each other horizontally. Sides are not equal.
   */
  @Test
  public void testOverlappedLinesHorizontal() {
    l0 = new Line(new Point2D(-1.0, -1.0), new Point2D(1.0, -1.0));
    l1 = new Line(new Point2D(-2.0, -1.0), new Point2D(2.0, -1.0));
    l2 = new Line(new Point2D(-3.0, -1.0), new Point2D(3.0, -1.0));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l0, l1, l2));
  }

  /**
   * Tests three lines that are the same. Sides are not equal.
   */
  @Test
  public void testSameLines() {
    l0 = new Line(new Point2D(-1.0, -1.0), new Point2D(1.0, -1.0));
    assertFalse(EquilateralTriangle.canMakeEquilateralTriangle(l0, l0, l0));
  }

  /**
   * Tests that an invalid equilateral triangle cannot be made. Same side length but invalid
   * triangle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInstanceOne() {
    l0 = new Line(new Point2D(-1.0, -1.0), new Point2D(1.0, -1.0));
    EquilateralTriangle eTri = new EquilateralTriangle(l0, l0, l0);
  }

  /**
   * Tests the toString method.
   */
  @Test
  public void testToString() {
    l0 = new Line(new Point2D(0.0, 0.0), new Point2D(-5.0, 0.0));
    l1 = new Line(new Point2D(-2.50, -4.33), new Point2D(-5.0, 0.0));
    l2 = new Line(new Point2D(-2.50, -4.33), new Point2D(0.00, 0.00));
    EquilateralTriangle tri = new EquilateralTriangle(l0, l1, l2);
    assertEquals("Equilateral Triangle with Sides: \n" +
            "L1: Start(0.00, 0.00) End:(-5.00, 0.00)\n" +
            "L2: : Start(-2.50, -4.33) End:(-5.00, 0.00)\n" +
            "L2: Start(-2.50, -4.33) End:(0.00, 0.00)", tri.toString());
  }

}