import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A class designed to test Point2D methods.
 */
public class Point2DTest {
  private Point2D point1;
  private Point2D point2;
  private Point2D point3;
  private Point2D point4;
  private Point2D point5;


  /**
   * Sets up the class for testing.
   */
  @Before
  public void setUp() {
    point1 = new Point2D(4, 5);
    point2 = new Point2D(0, 0);
    point3 = new Point2D(-3, -5);
    point4 = new Point2D(6, 8);


  }

  @Test
  public void testGetX() {
    assertEquals(point1.getX(), 4, 0.01);
    assertEquals(point2.getX(), 0, 0.01);
    assertEquals(point3.getX(), -3, 0.01);

  }

  @Test
  public void testGetY() {
    assertEquals(point1.getY(), 5, 0.01);
    assertEquals(point2.getY(), 0, 0.01);
    assertEquals(point3.getY(), -5, 0.01);

  }

  @Test
  public void testEuclidianDistance() {
    assertEquals(point2.euclidianDistance(point4), 10, 0.01);
  }

}