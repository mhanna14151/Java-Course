import org.junit.Before;
import org.junit.Test;

import model.Circle;
import model.Point2D;
import model.SnowPerson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * A class designed to test the model.SnowPerson class's methods.
 */
public class SnowPersonTest {
  private Circle firstCircle;
  private Circle secondCircle;
  private Circle thirdCircle;
  private Circle fourthCircle;
  private Circle fifthCircle;
  private Circle sixthCircle;
  private Circle firstCircleNeg;
  private Circle secondCircleNeg;
  private Circle thirdCircleNeg;
  private Circle firstCircleAngle;
  private Circle secondCircleAngle;
  private Circle thirdCircleAngle;
  private Circle firstCircle2;
  private Circle secondCircle2;
  private Circle thirdCircle2;

  /**
   * Sets up the class for testing.
   */
  @Before
  public void setup() {
    firstCircle = new Circle(new Point2D(0, 0), 5);
    secondCircle = new Circle(new Point2D(9, 0), 4);
    thirdCircle = new Circle(new Point2D(16, 0), 3);
    fourthCircle = new Circle(new Point2D(17, 4), 4);
    fifthCircle = new Circle(new Point2D(20, 0), 3);
    sixthCircle = new Circle(new Point2D(9, 7), 3);
    Circle firstCircleThreshold = new Circle(new Point2D(0.01, -0.01), 5);
    firstCircleNeg = new Circle(new Point2D(0, -1), 5);
    secondCircleNeg = new Circle(new Point2D(0, -10), 4);
    thirdCircleNeg = new Circle(new Point2D(0, -17), 3);
    firstCircleAngle = new Circle(new Point2D(0, 0), 20);
    secondCircleAngle = new Circle(new Point2D(18, 24), 10);
    thirdCircleAngle = new Circle(new Point2D(27, 36), 5);
    firstCircle2 = new Circle(new Point2D(10, 5), 5);
    secondCircle2 = new Circle(new Point2D(19, 5), 4);
    thirdCircle2 = new Circle(new Point2D(26, 5), 3);

  }

  /**
   * Tests a model.SnowPerson of increasing size, 5, 4, 3.
   * Tests order of input also does not matter.
   * These so happen to be horizontal snowpersons.
   */
  @Test
  public void testCanMakeSnowPerson() {
    assertTrue(SnowPerson.canMakeSnowPerson(firstCircle, secondCircle, thirdCircle));
    assertTrue(SnowPerson.canMakeSnowPerson(secondCircle, firstCircle, thirdCircle));
    assertTrue(SnowPerson.canMakeSnowPerson(thirdCircle, secondCircle, firstCircle));
    // Tests with it's base not being on model.Point2D(Zero Zero) Origin
    assertTrue(SnowPerson.canMakeSnowPerson(firstCircle2, secondCircle2, thirdCircle2));

  }

  /**
   * Tests the threshold component works successfully.
   */
  @Test
  public void testCanMakeSnowPersonInstanceTwo() {
    assertTrue(SnowPerson.canMakeSnowPerson(firstCircle, secondCircle, fifthCircle));

  }


  /**
   * Tests a snowperson in the negative quadrants. This is also a vertical snowperson.
   */
  @Test
  public void testCanMakeSnowPersonInstanceThree() {
    assertTrue(SnowPerson.canMakeSnowPerson(firstCircleNeg, secondCircleNeg, thirdCircleNeg));

  }

  /**
   * Tests that a SnowPerson that is not oriented in one of the Cardinal Directions can be made.
   * The Circles radii were calculated utilizing three right angle triangles with different sizes
   * of the 3-4-5 ratio. This would ensure that the same angle would be formed. Notes on each
   * origin's position are mentioned below.
   */
  @Test
  public void testCanMakeSnowPersonInstanceFour() {
    assertTrue(SnowPerson.canMakeSnowPerson(firstCircleAngle, secondCircleAngle, thirdCircleAngle));

    /*
     * First was radius 20, so we had a 12, 16, 20
     * Second was radius 10, so we had an 6, 8, 10
     * Third was radius 5, so we had 3, 4, 5
     * firstCircleAngle has model.Point2D(0, 0) radius 20:
     * distance to secondCircleAngle's Origin would then be for the X access, 12 + 6, Y axis 16 + 8
     * distance to the thirdCircleAngle's Origin then would be, 18 (prior origin) + 6 + 3 for the x,
     * 24 + 8 + 4 for the y.
     */

  }

  /**
   * Tests that False is reached when 5, 4, 4 (no longer changing size).
   */
  @Test
  public void notASnowPersonInstanceOne() {
    assertFalse(SnowPerson.canMakeSnowPerson(firstCircle, secondCircle, fourthCircle));
  }

  /**
   * Tests that false is reached when circles of appropriate size are not within reach of one
   * another.
   */
  @Test
  public void notASnowPersonInstanceTwo() {
    assertFalse(SnowPerson.canMakeSnowPerson(
            firstCircle, secondCircle, new Circle(new Point2D(25, 0), 3)));
  }


  /**
   * Tests that circles that are of appropriate size but out of alignment, fail appropriately.
   */
  @Test
  public void notASnowPersonInstanceThree() {
    assertFalse(SnowPerson.canMakeSnowPerson(firstCircle, secondCircle, sixthCircle));
  }

  /**
   * Tests that three circles that are directly on top of one
   * another, don't make a model.SnowPerson.
   */
  @Test
  public void notASnowPersonInstanceFour() {
    assertFalse(SnowPerson.canMakeSnowPerson(firstCircle, firstCircle, firstCircle));

  }

  /**
   * Tests three circles on top of each other but with different radii.
   */
  @Test
  public void notASnowPersonSameCenterZero() {
    Circle one = new Circle(new Point2D(0.00, 0.00), 4);
    Circle two = new Circle(new Point2D(0.00, 0.00), 5);
    Circle three = new Circle(new Point2D(0.00, 0.00), 6);
    assertFalse(SnowPerson.canMakeSnowPerson(one, two, three));
  }

  /**
   * Tests three circles on top of each other but with different radiuses at a different position.
   */
  @Test
  public void notASnowPersonSameCenterZeroSecond() {
    Circle one = new Circle(new Point2D(0.00, -5.00), 4);
    Circle two = new Circle(new Point2D(0.00, -5.00), 5);
    Circle three = new Circle(new Point2D(0.00, -5.00), 6);
    assertFalse(SnowPerson.canMakeSnowPerson(one, two, three));
  }

  /**
   * Tests three circles on top of each other but with different radiuses at a different position.
   */
  @Test
  public void notASnowPersonSameCenterZeroThree() {
    Circle one = new Circle(new Point2D(-5.00, 0.00), 5);
    Circle two = new Circle(new Point2D(-5.00, 0.00), 4);
    Circle three = new Circle(new Point2D(-5.00, 0.00), 6);
    assertFalse(SnowPerson.canMakeSnowPerson(one, two, three));
  }

  /**
   * Tests the equals method for model.SnowPerson.
   */
  @Test
  public void testEquals() {
    SnowPerson snowMan = new SnowPerson(firstCircle, secondCircle, thirdCircle);
    SnowPerson snowWoman = new SnowPerson(new Circle((new Point2D(0, 0)), 5),
            thirdCircle, secondCircle);
    SnowPerson snowChild = new SnowPerson(firstCircle, secondCircle, fifthCircle);
    assertTrue(snowMan.equals(snowWoman));
    assertFalse(snowChild.equals(snowWoman));


  }

  /**
   * Tests that the constructor does not allow an invalid model.SnowPerson to be made.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    SnowPerson snowBad = new SnowPerson(firstCircle, firstCircle, secondCircle);

  }

  /**
   * Tests the toString method.
   */
  @Test
  public void testToString() {
    SnowPerson snowMan = new SnowPerson(firstCircle, secondCircle, thirdCircle);
    assertEquals("SnowPerson: with Circles: \n" +
            "C1) Origin: (0.00, 0.00) Radius: 5.00\n" +
            "C2) Origin: (9.00, 0.00) Radius: 4.00\n" +
            "C3) Origin: (16.00, 0.00) Radius: 3.00", snowMan.toString());
  }

}