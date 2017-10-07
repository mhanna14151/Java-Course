package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the model.SnowPerson symbol. This is a Composite model.Symbol constructed
 * by the collection of three Circles meeting the criteria of being connected
 * and in a straight line.
 */
public class SnowPerson implements CompositeSymbol {
  private Circle oneCircle;
  private Circle secondCircle;
  private Circle thirdCircle;

  /**
   * Constructs a model.SnowPerson out of three circles.
   *
   * @param oneCircle    a model.Circle.
   * @param secondCircle a second model.Circle.
   * @param thirdCircle  a third model.Circle.
   */
  public SnowPerson(Circle oneCircle, Circle secondCircle, Circle thirdCircle) {
    if (!canMakeSnowPerson(oneCircle, secondCircle, thirdCircle)) {
      throw new IllegalArgumentException("This is not a valid model.SnowPerson");
    }
    this.oneCircle = oneCircle;
    this.secondCircle = secondCircle;
    this.thirdCircle = thirdCircle;
  }

  /**
   * Returns a copy of the list of circles.
   *
   * @return a copy of the list of circles.
   */
  public List<Circle> getCircles() {
    List<Circle> circles;
    circles = new ArrayList<>();
    circles.add(oneCircle);
    circles.add(secondCircle);
    circles.add(thirdCircle);
    return circles;
  }

  /**
   * Checks if each model.Circle is touching one other circle, and if one of the Circles is
   * touching two Circles.
   */
  private static boolean threeTouchingCirclesCheck(Circle first, Circle second, Circle third) {
    if (first.circlesInLine(second, third)) {
      if (first.circlesTouching(second)) {
        return (first.circlesTouching(third) || second.circlesTouching(third));
      }
      if (first.circlesTouching(third)) {
        return (first.circlesTouching(second) || third.circlesTouching(second));
      }
    }
    return false;
  }

  /**
   * In a scenario of circles touching and are of the appropriate alignment, determines if the
   * sandwiched circle's size is less than one of the other circles, and greater than the other.
   *
   * @param first  a model.Circle.
   * @param second a model.Circle.
   * @param third  a model.Circle.
   * @return true if the above conditions are met, false otherwise.
   */
  private static boolean sequentialSizeCheck(Circle first, Circle second, Circle third) {
    if (first.circlesInLine(second, third)) {
      if (first.circlesTouching(second) && first.circlesTouching(third)) {
        return (first.getRadius() > second.getRadius() && first.getRadius() < third.getRadius())
                || (first.getRadius() < second.getRadius() && first.getRadius()
                > third.getRadius());
      }
      if (second.circlesTouching(first) && second.circlesTouching(third)) {
        return (second.getRadius() > first.getRadius() && second.getRadius()
                < third.getRadius()) || (second.getRadius() < first.getRadius()
                && second.getRadius() > third.getRadius());
      }
      if (third.circlesTouching(first) && third.circlesTouching(second)) {
        return (third.getRadius() > second.getRadius() && third.getRadius() < first.getRadius())
                || (third.getRadius() < second.getRadius()
                && third.getRadius() > first.getRadius());
      }
    }
    return false;
  }

  /**
   * Determines if all the conditions of a model.SnowPerson are met for a set of three circles. -
   * Alignment - Two Circles touching only one other circle, one model.Circle touching both
   * those circles.
   * - Decreasing/Increasing in size (Middle circle in position is also the 'middle' circle in
   * size).
   *
   * @param first  a model.Circle.
   * @param second another model.Circle.
   * @param third  a third model.Circle.
   * @return true if all the conditions are met, false otherwise.
   */
  public static boolean canMakeSnowPerson(Circle first, Circle second, Circle third) {
    return (threeTouchingCirclesCheck(first, second, third)
            && sequentialSizeCheck(first, second, third));
  }

  /**
   * Used to determine if a model.SnowPerson and another model.SnowPerson are equal.
   *
   * @param other Ideally a model.Point2D, but any Object.
   * @return true if the two objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    assert (other instanceof SnowPerson);
    boolean isEqual = true;
    SnowPerson something = (SnowPerson) other;
    List<Circle> thisList = new ArrayList<>();
    List<Circle> otherList = new ArrayList<>();
    thisList.add(oneCircle);
    thisList.add(secondCircle);
    thisList.add(thirdCircle);
    otherList.add(something.oneCircle);
    otherList.add(something.secondCircle);
    otherList.add(something.thirdCircle);
    for (Circle circle : thisList) {
      if (!otherList.contains(circle)) {
        isEqual = false;
      }
    }
    return isEqual;
  }

  /**
   * Redefines the hashCode function to reflect the change in the above equals method.
   *
   * @return an int representing the hash value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.oneCircle, this.secondCircle, this.thirdCircle);

  }

  /**
   * Creates a String representation of the SnowPerson.
   *
   * @return a string representing the SnowPerson.
   */
  @Override
  public String toString() {
    String str = "SnowPerson: with Circles: \n" +
            String.format("C1) Origin: (%.2f, %.2f) Radius: %.2f\n", oneCircle.getOrigin().getX(),
                    oneCircle.getOrigin().getY(), oneCircle.getRadius())
            + String.format("C2) Origin: (%.2f, %.2f) Radius: %.2f\n",
            secondCircle.getOrigin().getX(),
            secondCircle.getOrigin().getY(), secondCircle.getRadius())
            + String.format("C3) Origin: (%.2f, %.2f) Radius: %.2f", thirdCircle.getOrigin().getX(),
            thirdCircle.getOrigin().getY(), thirdCircle.getRadius());
    return str;

  }


}