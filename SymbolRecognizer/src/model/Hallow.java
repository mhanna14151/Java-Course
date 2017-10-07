package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the Deathly Hollow Symbol.
 */
public class Hallow implements CompositeSymbol {
  protected Line verticalLine;
  protected EquilateralTriangle eqTriangle;
  protected Circle circle;

  /**
   * Constructs a Hallow out of a line, equilateral Triangle, and a Circle.
   * @param verticalLine a Line.
   * @param eqTriangle an Equilateral Triangle.
   * @param circle a Circle.
   */
  public Hallow(Line verticalLine, EquilateralTriangle eqTriangle, Circle circle) {
    if (!canMakeHallow(verticalLine, eqTriangle, circle)) {
      throw new IllegalArgumentException("This is not a valid model.Hallow");
    }
    this.verticalLine = verticalLine;
    this.eqTriangle = eqTriangle;
    this.circle = circle;
  }

  /**
   * Returns the list of symbols which comprise the Hallow.
   * @return a list of symbols.
   */
  public List<Symbol> getComponents() {
    List<Symbol> symbols = new ArrayList<>();
    symbols.add(verticalLine);
    symbols.add(eqTriangle);
    symbols.add(circle);
    return symbols;
  }


  /**
   * Returns if a Hallow can be made given it's appropriate symbols.
   * @param aLine a Line.
   * @param anEqTriangle an Equilateral Triangle.
   * @param aCircle a Circle.
   * @return true if a Hallow can be made, false otherwise.
   */
  public static boolean canMakeHallow(Line aLine, EquilateralTriangle anEqTriangle, Circle aCircle)
  {
    // First Condition: euclidian Distance between Origin and Midpoints for each line, are within a
    // certain threshold with the length of the radius. This gets us the circle inside of the Triangle.
    //if (circleWithinTriangle(anEqTriangle, aCircle))
    return circleWithinTriangle(anEqTriangle, aCircle) && verticalLineMidPoint(aLine, anEqTriangle)
            && triangleAndCircleAligned(aCircle, anEqTriangle);

  }

  /**
   * Helper function to determine if a circle is within a triangle.
   * @param anEqTriangle an Equilateral Triangle.
   * @param aCircle a Circle.
   * @return true if the Circle is appropriately aligned and within an Equilateral Triangle.
   */
  private static boolean circleWithinTriangle(EquilateralTriangle anEqTriangle, Circle aCircle) {
      List<Point2D> listOfMidpointsFromTriangle = new ArrayList<>();
      List<Line> linesfromTriangle = new ArrayList<Line>();
      linesfromTriangle.addAll(anEqTriangle.getLines());
      // This gives us the list of midpoints.
      for (Line line: linesfromTriangle) {
        listOfMidpointsFromTriangle.add(line.getMidpoint());
      }
      // Now that we have a list of midpoints, we need to calculate the Euclidian distance between
      // them and the origin.
      Point2D circlesOrigin = aCircle.getOrigin();
      for (int i = 0; i < listOfMidpointsFromTriangle.size(); i++) {
        if (Math.abs(aCircle.getRadius() - listOfMidpointsFromTriangle.get(i).euclidianDistance(circlesOrigin))
                < Constants.midpointCircleDistance) {
          // keep iterating
        } else {
          return false;
        }
      }
      return true;
    }

  /**
   * Helper function to determine if the line bissects the midpoint and the top angle of the
   * opposite side of the triangle.
   * @param aLine a Line.
   * @param anEqTriangle an Equilateral Triangle.
   * @return true if the conditions described above are met, false otherwise.
   */
    private static boolean verticalLineMidPoint(Line aLine, EquilateralTriangle anEqTriangle) {
      List<Point2D> listOfMidpointsFromTriangle = new ArrayList<>();
      List<Line> linesfromTriangle = new ArrayList<Line>();
      linesfromTriangle.addAll(anEqTriangle.getLines());
      // This gives us the list of midpoints.
      for (Line line : linesfromTriangle) {
        listOfMidpointsFromTriangle.add(line.getMidpoint());
      }
      int count = 0;
      // lineNumber is used to track which line was the one that had the midpoint bisected.
      int lineNumber = 500;
      Point2D start = aLine.getStart();
      Point2D end = aLine.getEnd();
      // tracker is used to determine if the start or the end of the vertical line was already used
      int tracker = -1;
      for (int i = 0; i < listOfMidpointsFromTriangle.size(); i++) {
        if (start.euclidianDistance(listOfMidpointsFromTriangle.get(i))
                < Constants.hallowTriangleLineThreshold) {
          count++;
          lineNumber = i;
          tracker = 1;
        }
        if (end.euclidianDistance(listOfMidpointsFromTriangle.get(i))
                < Constants.hallowTriangleLineThreshold) {
          count++;
          lineNumber = i;
          tracker = 2;
        }
      }
      // if count equals one, we know it's only intersected(near) one of the midpoints .
      if (count == 1) {
        List<Line> aNewList = new ArrayList<>();
        aNewList.addAll(linesfromTriangle);
        aNewList.remove(lineNumber);
        Line line1 = aNewList.get(0);
        Line line2 = aNewList.get(1);
        // average will be the Point2D which represents the convergence of the two other lines.
        // those two lines are selected because they were not bisected.
        Point2D average;
        double x = 1000000000;
        double y = 1000000000;
        // if line1's start is where the intersection occurs:
        if (line1.intersectAtStart(line2)) {
          // find if it was the end or the start of line 2 which intersected.
          if (line2.intersectAtStart(line1)) {
            x = (line1.getStart().getX() + line2.getStart().getX()) / 2;
            y = (line1.getStart().getY() + line2.getStart().getY()) / 2;
          }
          if (line2.intersectAtEnd(line1)) {
            x = (line1.getStart().getX() + line2.getEnd().getX()) / 2;
            y = (line1.getStart().getY() + line2.getEnd().getY()) / 2;
          }
        }
        // if line 1's end was the one that intersected line 2
        if (line1.intersectAtEnd(line2)) {
          // find if it was the start or end of line 2 that intersected line 1.
          if (line2.intersectAtStart(line1)) {
            x = (line1.getEnd().getX() + line2.getStart().getX()) / 2;
            y = (line1.getEnd().getY() + line2.getStart().getY()) / 2;
          }
          if (line2.intersectAtEnd(line1)) {
            x = (line1.getEnd().getX() + line2.getEnd().getX()) / 2;
            y = (line1.getEnd().getY() + line2.getEnd().getY()) / 2;
          }
        }
        average = new Point2D(x, y);
        // one means start was used for the vertical line already, so must use end
        // this checks if the euclidian distance of that point and the original line, are within
        // a certain threshold.
        if (tracker == 1) {
          if (end.euclidianDistance(average) < Constants.hallowTriangleLineThreshold) {
            return true;
          }
        }
        // 2 means the end was used previously, so must use the start of the line.
        else if (tracker == 2) {
          if (start.euclidianDistance(average) < Constants.hallowTriangleLineThreshold) {
            return true;
          }
        }
      }
        return false;
    }

  /**
   * Checks if the Circle and the Triangle of the potential deathly hollow are aligned appropriately.
   * @param circle a Circle.
   * @param triangle a Triangle.
   * @return true if they are aligned appropriately, false otherwise.
   */
  private static boolean triangleAndCircleAligned(Circle circle, EquilateralTriangle triangle) {
    return (circle.getOrigin().euclidianDistance(triangle.calculateCenter()) <
            Constants.thresholdForCircleTriangleHollow);


  }
}