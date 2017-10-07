/**
 * A class representing a Point2D as denoted by position(x,y) of any point.
 */

public class Point2D {
  private double x;
  private double y;

  /**
   * Constructs a Point2D.
   *
   * @param x the x value of a point.
   * @param y the y value of a point.
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;

  }

  /**
   * A class made to retrieve the x-value of a Point2D.
   *
   * @return the value of x as an integer.
   */
  public double getX() {
    return this.x;
  }

  /**
   * A class made to retrieve the y-value of a Point2D.
   *
   * @return the value of y as an integer.
   */
  public double getY() {
    return this.y;

  }

  /**
   * Calculates the Euclidian Distance between two Point2Ds.
   *
   * @param other a Point2D.
   * @return the distance between two points as a double.
   */
  public double euclidianDistance(Point2D other) {
    double xVal = Math.pow((this.x - other.x), 2);
    double yVal = Math.pow((this.y - other.y), 2);
    return Math.sqrt(xVal + yVal);

  }

  /**
   * Used to determine if a Point2D and another Object are equal.
   *
   * @param other Ideally a Point2D, but any Object.
   * @return true if the two objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    assert (other instanceof Point2D);

    Point2D something = (Point2D) other;
    return (this.x == something.getX() && this.y == something.getY());
  }

  @Override
  public int hashCode() {
    return hashCode();
  }

}
