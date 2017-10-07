package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class representing a model.Triangle Class, another Composite model.Symbol, constructed by the
 * three Lines which overlap at their edges (or near enough) to be recognized as a triangle.
 */
public class Triangle implements CompositeSymbol {
  protected Line firstSide;
  protected Line secondSide;
  protected Line thirdSide;

  /**
   * Constructs a model.Triangle.
   *
   * @param firstSide  a model.Line.
   * @param secondSide another model.Line.
   * @param thirdSide  a third model.Line.
   */
  public Triangle(Line firstSide, Line secondSide, Line thirdSide) {
    if (!canMakeTriangle(firstSide, secondSide, thirdSide)) {
      throw new IllegalArgumentException("These sides don't make a model.Triangle!");
    }

    this.firstSide = firstSide;
    this.secondSide = secondSide;
    this.thirdSide = thirdSide;
  }

  /**
   * Returns a copy of the list of lines representing the Triangle.
   * @return a copy of the list of lines.
   */
  public List<Line> getLines() {
    List<Line> triangles = new ArrayList<>();
    triangles.add(firstSide);
    triangles.add(secondSide);
    triangles.add(thirdSide);
    return triangles;
  }

  /**
   * Reports if the conditions of a triangle being made are met by the three given Lines.
   * Conditions are:
   * - Each line shares a point with two other lines.
   * - Unique Slopes (to prevent a triangle being formed with overlapping lines).
   *
   * @param first  a model.Line.
   * @param second a model.Line.
   * @param third  a model.Line.
   * @return true if the conditions are met, false otherwise.
   */
  public static boolean canMakeTriangle(Line first, Line second, Line third) {
    if (first.uniqueSlopes(second, third)) {
      if (first.intersectAtEnd(second)) {
        if (first.intersectAtStart(third)) {
          return (third.intersectAtStart(second) || third.intersectAtEnd(second));
        }
      }
      if (first.intersectAtStart(second)) {
        if (first.intersectAtEnd(third)) {
          return (third.intersectAtStart(second) || third.intersectAtEnd(second));
        }
      }
    }
    return false;
  }

  /**
   * Redefines the equals method that checks that a model.Triangle and another Object are equal.
   *
   * @param other an Object, ideally a model.Triangle.
   * @return true if they're considered equal, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    assert (other instanceof Triangle);

    Triangle something = (Triangle) other;
    return (((this.firstSide.equals((something.firstSide)))
            || (this.firstSide.equals((something.secondSide)))
            || (this.firstSide.equals((something.thirdSide))))
            && ((this.secondSide.equals(something.firstSide))
            || (this.secondSide.equals(something.secondSide))
            || (this.secondSide.equals((something.thirdSide))))
            && ((this.thirdSide.equals(something.firstSide))
            || (this.thirdSide.equals((something.secondSide)))
            || (this.thirdSide.equals(something.thirdSide))));
  }

  /**
   * Redefines the hashCode function to reflect the change in the above equals method.
   *
   * @return an int representing the hash value.
   */
  @Override
  public int hashCode() {

    return Objects.hash(firstSide, secondSide, thirdSide);
  }

  /**
   * Calculates the center of the triangle.
   * @return a Point2D representing the center of the triangle.
   */
  protected Point2D calculateCenter() {
    int xSum = 0;
    int ySum = 0;
    Point2D newPoint;

    for (Line line: getLines()) {
      xSum += line.getMidpoint().getX();
      ySum += line.getMidpoint().getY();
    }
    return newPoint = new Point2D(xSum/3, ySum/3);
  }


  /**
   * Creates a String representation of the Triangle.
   *
   * @return a string representing the triangle.
   */
  @Override
  public String toString() {
    String str = "Triangle with Sides:\n" +
            String.format("L1: Start(%.2f, %.2f) End:(%.2f, %.2f)\n",
                    firstSide.getStart().getX(), firstSide.getStart().getY(),
                    firstSide.getEnd().getX(), firstSide.getEnd().getY())
            + String.format("L2: : Start(%.2f, %.2f) End:(%.2f, %.2f)\n",
            secondSide.getStart().getX(), secondSide.getStart().getY(),
            secondSide.getEnd().getX(), secondSide.getEnd().getY())
            + String.format("L2: Start(%.2f, %.2f) End:(%.2f, %.2f)",
            thirdSide.getStart().getX(), thirdSide.getStart().getY(),
            thirdSide.getEnd().getX(), thirdSide.getEnd().getY());
    return str;

  }


}
