package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class made to represent the Composite Symbol, Pendulum.
 */
public class Pendulum implements CompositeSymbol{
  private Rectangle aRectangle;
  private EquilateralTriangle anEquilateralTriangle;
  private Line aLine;
  private Circle aCircle;

  /**
   * This is the constructor for the Pendulum.
   * @param aRectangle a Retangle.
   * @param anEquilateralTriangle an Equilateral Triangle.
   * @param aLine a Line.
   * @param aCircle a Circle.
   */
  public Pendulum(Rectangle aRectangle, EquilateralTriangle anEquilateralTriangle, Line aLine,
                  Circle aCircle) {
    if (!canMakePendulum(aRectangle, anEquilateralTriangle, aLine, aCircle)) {
      throw new IllegalArgumentException("This is not a Pendulum!");
    }
    this.aRectangle = aRectangle;
    this.anEquilateralTriangle = anEquilateralTriangle;
    this.aLine = aLine;
    this.aCircle = aCircle;

  }

  /**
   * Returns the symbols that comprise the Pendulum.
   * @return a List of Symbols.
   */
  public List<Symbol> getComponents() {
    List symbolsList = new ArrayList();
    symbolsList.add(aCircle);
    symbolsList.add(aLine);
    symbolsList.add(anEquilateralTriangle);
    symbolsList.add(aRectangle);
    return symbolsList;
  }


  /**
   * This method determines if a pendulum can be made given it's appropriate symbols.
   * @param rect a Rectangle.
   * @param tri an Equilateral Triangle.
   * @param line a Line.
   * @param circle a Circle.
   * @return true if a Pendulum can be made, false otherwise.
   */
  public static boolean canMakePendulum(Rectangle rect, EquilateralTriangle tri, Line line, Circle
                                        circle) {
    return lineConnectingCircleAndTriangle(line, circle, tri)
            && rectangleEngulfsCircle(rect, circle);
  }

  /**
   * Checks if a line connects a equilateral triangle and a circle and is an appropriate distance
   * apart.
   * @param line a Line.
   * @param circle a Circle.
   * @param triangle a Triangle.
   * @return true if the circle and triangle are appropriately connected via a line, and are not
   * otherwise physically touching.
   */
  private static boolean lineConnectingCircleAndTriangle(Line line, Circle circle,
                                                         EquilateralTriangle triangle) {
    Point2D lineStart = line.getStart();
    Point2D lineEnd = line.getEnd();
    Point2D circleCenter = circle.getOrigin();
    Point2D triangleCenter = triangle.calculateCenter();
    double lengthThreshold = triangle.getLines().get(0).getLength() + 2*circle.getRadius();
    boolean areConnected = false;

    if (Math.abs(lineStart.euclidianDistance(circleCenter)) <= Constants.pendulumThreshold) {
      if (Math.abs(lineEnd.euclidianDistance(triangleCenter)) <= Constants.pendulumThreshold) {
        areConnected = true;
        //System.out.println("AreConnected!!");
      }
    }
    else if (Math.abs(lineEnd.euclidianDistance(circleCenter)) <= Constants.pendulumThreshold) {
      if (Math.abs(lineStart.euclidianDistance(triangleCenter)) <= Constants.pendulumThreshold) {
        areConnected = true;
        //System.out.println("AreConnected!!");
      }
    }
    if (areConnected) {
      //System.out.print("Checking the length threshold condition");
      return lineStart.euclidianDistance(lineEnd) > lengthThreshold;
    }
    return false;
  }

  /**
   * Calculates if the Rectangle size encapsulates the Circle and is of an appropriate size.
   * @param rect a Rectangle.
   * @param circ a Circle.
   * @return true if the rectangle surrounds the circle appropriately, false otherwise.
   */
  private static boolean rectangleEngulfsCircle(Rectangle rect, Circle circ) {
    Point2D centerOfTheRectangle = rect.calculateCenter();
    Point2D centerOfTheCircle = circ.getOrigin();
    //System.out.println("Does this engulf this other thing");
    if (centerOfTheCircle.euclidianDistance(centerOfTheRectangle) < Constants.pendulumThreshold) {
      for (Line line: rect.getLines()) {
        if (line.getLength() > circ.getRadius()*1.5 && line.getLength() < circ.getRadius() * 4) {
          //System.out.println("True thus far");
        }
        else {
          //System.out.println("FALSE");
          return false;
        }
      }
      //System.out.println("True");
      return true;
    }
    //System.out.println("false");
    return false;
  }

}
