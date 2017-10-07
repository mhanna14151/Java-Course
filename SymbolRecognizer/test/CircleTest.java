import org.junit.Before;
import org.junit.Test;

import model.Circle;
import model.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * A Class designed to test the model.Circle class' public methods.
 */
public class CircleTest {

  private Circle circleOne;
  private Circle circleTwo;
  private Circle circleThree;
  private Circle circleFour;
  private Circle circleFive;

  /**
   * Sets up the class for testing.
   */
  @Before
  public void setup() {
    circleOne = new Circle(new Point2D(0, 0), 5);
    circleTwo = new Circle(new Point2D(0, 0), 5);
    circleThree = new Circle(new Point2D(0, 0), 4);
    circleFour = new Circle(new Point2D(1, 1), 5);
    circleFive = new Circle(new Point2D(5, 5), 50);
  }

  /**
   * Tests that the constructor does not allow an invalid circle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    Circle circle = new Circle(new Point2D(0, 0), 0);
  }

  /**
   * Tests the equals method for model.Circle.
   */
  @Test
  public void testEquals() {
    assertTrue(circleOne.equals(circleTwo));
    assertFalse(circleOne.equals(circleThree));
    assertFalse(circleOne.equals(circleFour));
    assertFalse(circleOne.equals(circleFive));

  }

  /**
   * Tests the toString method.
   */
  @Test
  public void testToString() {
    circleOne = new Circle(new Point2D(0, 0), 5);
    assertEquals("Circle{Origin=Point2D{x=0.0, y=0.0}, radius=5.0}", circleOne.toString());
  }


}