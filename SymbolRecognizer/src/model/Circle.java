package model;

import java.util.Objects;

/**
 * Represents a model.Circle class, a Basic model.Symbol, often used to construct Composite Symbols
 * (e.g. a model.SnowPerson).
 */
public class Circle implements BasicSymbol {
  private Point2D origin;
  private double radius;

  /**
   * Constructs a model.Circle, represented by an Origin and a radius.
   *
   * @param origin a model.Point2D representing the center of the circle.
   * @param radius a double representing the distance extending outward from the origin.
   */
  public Circle(Point2D origin, double radius) {
    if (radius <= 0) {
      throw new IllegalArgumentException("Radius must be greater than zero");
    }
    this.origin = origin;
    this.radius = radius;

  }

  /**
   * Determines if two circles are 'touching', i.e. are within a certain threshold of one another.
   * The boundaries must overlap near a singular point, not merely intersect.
   *
   * @param other another circle.
   * @return true if they're touching, false otherwise.
   */
  protected boolean circlesTouching(Circle other) {
    double distanceOfRadii = radius + other.radius;
    return origin.euclidianDistance(other.origin) - distanceOfRadii < Constants.circleThreshold;
  }

  /**
   * Check if three Circles's origins are within the same plane (origins could be connected
   * by a singular straight line) as one another.
   *
   * @param second another model.Circle.
   * @param third  another model.Circle.
   * @return true if they're in the same plane, false otherwise.
   */
  protected boolean circlesInLine(Circle second, Circle third) {
    double slopeBetweenThisAndSecond = slopeBetweenTwoCircleOrigins(second);
    double slopeBetweenThisAndThird = second.slopeBetweenTwoCircleOrigins(third);
    return Math.abs(slopeBetweenThisAndSecond - slopeBetweenThisAndThird)
            < Constants.circleThresholdForAlignment;

  }

  /**
   * Calculates the slope between two Circles. This is used as a helper function for circlesInLine.
   *
   * @param other another model.Circle.
   * @return a double representing the slope between two model.Circle's origins.
   */
  private double slopeBetweenTwoCircleOrigins(Circle other) {
    double y = origin.getY() - other.origin.getY();
    double x = origin.getX() - other.origin.getX();
    if (x == 0 && y != 0) {
      return Integer.MAX_VALUE;
    }
    return y / x;
  }

  /**
   * Gets the radius of the model.Circle.
   *
   * @return the radius of the model.Circle as a double.
   */
  public double getRadius() {
    double x = radius;
    return x;

  }

  /**
   * Returns the origin of this circle.
   * @return the origin of this circle.
   */
  public Point2D getOrigin() {
    Point2D resultOrigin;
    resultOrigin = new Point2D(this.origin.getX(), this.origin.getY());
    return resultOrigin;

  }

  /**
   * Used to determine if a model.Circle and another Object are equal.
   *
   * @param other Ideally a model.Circle, but any Object.
   * @return true if the two objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    assert (other instanceof Circle);
    Circle something = (Circle) other;
    return (this.origin.equals(something.origin) && this.radius == something.radius);
  }


  @Override
  public String toString() {
    return "Circle{" +
            "Origin=" + getOrigin() +
            ", radius=" + getRadius() +
            '}';
  }

  /**
   * Redefines the hashCode function to reflect the change in the above equals method.
   *
   * @return an int representing the hash value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.origin, this.radius);
  }

}
