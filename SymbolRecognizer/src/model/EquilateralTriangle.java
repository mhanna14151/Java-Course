package model;

/**
 * Represents an Equilateral model.Triangle, which is a special case of a model.Triangle,
 * where all sides of the triangle are equal.
 */
public class EquilateralTriangle extends Triangle {

  /**
   * The constructor for the equalateral triangle.  If the sides are not equal in length,
   * you cannot construct this.
   *
   * @param firstSide  the first side of the triangle.
   * @param secondSide the second side of the triangle.
   * @param thirdSide  the third side of the triangle.
   */
  public EquilateralTriangle(Line firstSide, Line secondSide, Line thirdSide) {
    super(firstSide, secondSide, thirdSide);
    double differenceOne = Math.abs(firstSide.getLength() - secondSide.getLength());
    double differenceTwo = Math.abs(secondSide.getLength() - thirdSide.getLength());
    if (differenceOne > Constants.equilateralTriangleThreshold
            || differenceTwo > Constants.equilateralTriangleThreshold) {
      throw new IllegalArgumentException("Sides must be equal");
    }
  }

  /**
   * Checks if the three lines are the same length or not.
   *
   * @param firstSide  a model.Line.
   * @param secondSide a model.Line.
   * @param thirdSide  a model.Line.
   * @return true if they're all the same size (within a small threshold).
   */
  protected static boolean sidesEqual(Line firstSide, Line secondSide, Line thirdSide) {
    double differenceOne = Math.abs(firstSide.getLength() - secondSide.getLength());
    double differenceTwo = Math.abs(secondSide.getLength() - thirdSide.getLength());
    return (differenceOne < Constants.equilateralTriangleThreshold
            && differenceTwo < Constants.equilateralTriangleThreshold);
  }

  /**
   * Determines if one can make an equilateral triangle given three Lines.
   *
   * @param first  a model.Line.
   * @param second a second model.Line.
   * @param third  a third model.Line.
   * @return true if the conditions for an equilateral triangle are met, false otherwise.
   */
  public static boolean canMakeEquilateralTriangle(Line first, Line second, Line third) {
    if (sidesEqual(first, second, third)) {
      return canMakeTriangle(first, second, third);
    }
    return false;
  }

  /**
   * Creates a String representation of the Equilateral Triangle.
   * @return a string representing the eqilateral triangle.
   */
  @Override
  public String toString() {
    String str = "Equilateral Triangle with Sides: \n" +
            String.format("L1: Start(%.2f, %.2f) End:(%.2f, %.2f)\n",
                    firstSide.getStart().getX(), firstSide.getStart().getY(),
                    firstSide.getEnd().getX(),  firstSide.getEnd().getY())
            + String.format("L2: : Start(%.2f, %.2f) End:(%.2f, %.2f)\n",
            secondSide.getStart().getX(), secondSide.getStart().getY(),
            secondSide.getEnd().getX(), secondSide.getEnd().getY())
            + String.format("L2: Start(%.2f, %.2f) End:(%.2f, %.2f)",
            thirdSide.getStart().getX(), thirdSide.getStart().getY(),
            thirdSide.getEnd().getX(),  thirdSide.getEnd().getY());
    return str;
  }
}
