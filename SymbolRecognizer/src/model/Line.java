package model;

import java.util.Objects;

/**
 * Represents the model.Line, a Basic model.Symbol used for constructing Composite Symbols
 * (e.g. a model.Triangle).
 */
public class Line implements BasicSymbol {
  private Point2D start;
  private Point2D end;

  /**
   * Constructs a model.Line (represented by the distance between two Points.
   *
   * @param start a start model.Point2D.
   * @param end   an end model.Point2D.
   */
  public Line(Point2D start, Point2D end) {
    if (start.equals(end)) {
      throw new IllegalArgumentException("A line cannot be a single point");
    }
    this.start = start;
    this.end = end;
  }

  /**
   * Gets the starting point of this line.
   * @return the starting point of this line.
   */
  public Point2D getStart() {
    Point2D result;
    result = new Point2D(this.start.getX(), this.start.getY());
    return result;
  }

  /**
   * Gets the starting point of this line.
   * @return the starting point of this line.
   */
  public Point2D getEnd() {
    Point2D result;
    result = new Point2D(this.end.getX(), this.end.getY());
    return result;
  }

  /**
   * Checks if two Lines overlap at the end of the model.Line.
   *
   * @param other another model.Line.
   * @return true if they overlap within a certain threshold.
   */
  protected boolean intersectAtEnd(Line other) {
    return (this.end.euclidianDistance(other.end) < Constants.lineThreshold) ||
            (this.end.euclidianDistance(other.start) < Constants.lineThreshold);
  }

  /**
   * Checks if two Lines overlap at the start of the model.Line.
   *
   * @param other another model.Line.
   * @return true if they overlap within a certain threshold.
   */
  protected boolean intersectAtStart(Line other) {
    return (this.start.euclidianDistance(other.end) < Constants.lineThreshold) ||
            (this.start.euclidianDistance(other.start) < Constants.lineThreshold);
  }

  /**
   * Calculates the slope of a line.
   *
   * @return the slope of the line as a double.
   */
  protected double slope() {
    double y = this.end.getY() - this.start.getY();
    double x = this.end.getX() - this.start.getX();
    if (x == 0 && y != 0) {
      return Double.MAX_VALUE;
    }
    return y / x;
  }

  /**
   * Calculates the angle between this line and another.
   * @param other Line.
   * @return the angle between the two lines assuming they intersect as a double.
   */
  public double calculateAngle(Line other) {
    double top = this.slope() - other.slope();
    double bottom = (1 + this.slope() * other.slope());
    double val = Math.abs(Math.atan(top/bottom));
    return val * 180/Math.PI;
  }

  /**
   * Determines if three Lines slopes are unique from one another. 0.005 is used due to
   * a double's tendency to be inexact.
   *
   * @param second a second model.Line.
   * @param third  a third model.Line.
   * @return true if they're unique, false otherwise.
   */
  protected boolean uniqueSlopes(Line second, Line third) {
    return (!((Math.abs(this.slope() - second.slope()) < 0.005) ||
            (Math.abs(this.slope() - third.slope()) < 0.005) ||
            (Math.abs(second.slope() - third.slope()) < 0.005)));
  }

  /**
   * Gets the length of a model.Line.
   *
   * @return length of the model.Line as a double.
   */
  protected double getLength() {
    return start.euclidianDistance(end);
  }

  /**
   * Gets the midpoint of a line.
   * @return a Point2D representing the midpoint.
   */
  protected Point2D getMidpoint() {
    Point2D newPoint;
    double x;
    double y;
    x = (start.getX() + end.getX())/2;
    y = (start.getY() + end.getY())/2;
    newPoint = new Point2D(x, y);
    return newPoint;
  }


  /**
   * Redefines the parameters to make a model.Line and Object to be considered equal.
   *
   * @param other another Object.
   * @return true, if considered to be equal.
   */
  @Override
  public boolean equals(Object other) {
    assert (other instanceof Line);
    Line something = (Line) other;
    return (((this.start.equals(something.start)) || (this.start.equals(something.end)))
            && ((this.end.equals(something.start)) || (this.end.equals(something.end))));
  }

  /**
   * A string representation of the Line.
   * @return a string representation of the line.
   */
  @Override
  public String toString() {
    return "Line{" +
            "start=" + start +
            ", end=" + end +
            '}';
  }

  /**
   * Redefines the hashCode function to reflect the change in the above equals method.
   *
   * @return an int representing the hash value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.start, this.end);
  }

}