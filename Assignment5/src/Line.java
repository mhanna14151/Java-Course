/**
 * A class desgined to represent a line.
 */
public class Line {
  private double m;
  private double b;

  /**
   * Constructs a line.
   *
   * @param slope      the slope of the line, typically known as m in the equation: y = mx + b.
   * @param yIntercept the y-intercept of the line, typically known as b in the equation: y = mdx +
   *                   b.
   */
  public Line(double slope, double yIntercept) {
    this.m = slope;
    this.b = yIntercept;
  }


  /**
   * Returns the slope of a line.
   *
   * @return the 'm' value of a line.
   */
  public double getM() {
    return m;

  }

  /**
   * Returns the y-intercept of a line.
   *
   * @return the 'b' value of a line.
   */
  public double getB() {
    return b;
  }

  /**
   * Overwrites the equals method to be used to test equality.
   *
   * @param other another Object, ideally another Line.
   * @return a boolean, true if equal, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    assert (other instanceof Line);
    Line something = (Line) other;
    return (this.m == something.getM() && this.b == something.getB());
  }

  @Override
  public int hashCode() {
    return hashCode();
  }

}

