package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A Class representing the Composite Symbol, Rectangle.
 */
public class Rectangle implements CompositeSymbol {
  protected Line firstSide;
  protected Line secondSide;
  protected Line thirdSide;
  protected Line fourthSide;


  /**
   * The Constructor for a Rectangle.
   * @param firstSide a Line.
   * @param secondSide a Line.
   * @param thirdSide a Line.
   * @param fourthSide a Line.
   */
  public Rectangle(Line firstSide, Line secondSide, Line thirdSide, Line fourthSide) {
    if (!canMakeRectangle(firstSide, secondSide, thirdSide, fourthSide)) {
      throw new IllegalArgumentException("These sides don't make a model.Rectangle!");
    }
    this.firstSide = firstSide;
    this.secondSide = secondSide;
    this.thirdSide = thirdSide;
    this.fourthSide = fourthSide;
  }

  /**
   * A method to determine if a Rectangle can be made given four lines.
   * @param first a Line.
   * @param second a Line.
   * @param third a Line.
   * @param fourth a Line.
   * @return true if a Rectangle can be made with these four lines, false otherwise.
   */
  public static boolean canMakeRectangle(Line first, Line second, Line third, Line fourth) {
    List<Line> lines = new ArrayList<>();
    lines.add(first);
    lines.add(second);
    lines.add(third);
    lines.add(fourth);
    //for every line, check if it intersects with two other lines, and those intersects are at a 90
    return intersectWithTwoOthers(lines) && angleMethod(lines);
  }

  /**
   * Takes in a list of lines (should only ever be size 4) and checks if each line intersects
   * with exactly two other lines.
   * @param lines a List of Lines.
   * @return true, if each line intersects with exactly two others.
   */
  private static boolean intersectWithTwoOthers(List<Line> lines) {
    List<Line> tempList;
    tempList = new ArrayList<>();
    tempList.addAll(lines);
    for (int i = 0; i < lines.size(); i++) {
      // gets the current line I want to compare with the other lines
      Line thisLine = lines.get(i);
      // removes this line so it's not compared against itself
      tempList.remove(i);
      // checks if thisLine's start intersects with the first line.
      if (thisLine.intersectAtStart(tempList.get(0))) {
        if (thisLine.intersectAtEnd(tempList.get(1)) ||
                thisLine.intersectAtEnd(tempList.get(2))) {
          tempList.clear();
          tempList.addAll(lines);
        } else {
          return false;
        }
      } else if (thisLine.intersectAtStart(tempList.get(1))) {
        if (thisLine.intersectAtEnd(tempList.get(0)) || thisLine.intersectAtEnd(tempList.get(2))) {
          tempList.clear();
          tempList.addAll(lines);
        } else {
          return false;
        }
      } else if (thisLine.intersectAtStart(tempList.get(2))) {
        if (thisLine.intersectAtEnd(tempList.get(1)) || thisLine.intersectAtEnd(tempList.get(0))) {
          tempList.clear();
          tempList.addAll(lines);
        } else {
          return false;
        }
      }
      else if (!thisLine.intersectAtStart(tempList.get(0)) && !thisLine.intersectAtStart(tempList.get(1)) &&
      !thisLine.intersectAtStart(tempList.get(2))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Helper function that determines if the four lines fed into it, report back exactly four
   * perpendicular intersections.
   * @param lines a List of Lines.
   * @return true if the appropriate angles are found, false otherwise.
   */
  private static boolean angleMethod(List<Line> lines) {
    int count0 = 0;
    int count90 = 0;
    //List<Line> tempList = new ArrayList<>();
    //tempList.addAll(lines);
    for (int i = 0; i < lines.size(); i++) {
      if (lines.get(i).calculateAngle(lines.get(0)) < Constants.thresholdForRectangleAngle) {
        count0++;
      }
      if (Math.abs(90 - lines.get(i).calculateAngle(lines.get(0)))
              < Constants.thresholdForRectangleAngle) {
        count90++;
      }
      if (lines.get(i).calculateAngle(lines.get(1))
              < Constants.thresholdForRectangleAngle) {
        count0++;
      }
      if (Math.abs(90 - lines.get(i).calculateAngle(lines.get(1)))
              < Constants.thresholdForRectangleAngle) {
        count90++;
      }
      if (lines.get(i).calculateAngle(lines.get(2))
              < Constants.thresholdForRectangleAngle) {
        count0++;
      }
      if (Math.abs(90 - lines.get(i).calculateAngle(lines.get(2)))
              < Constants.thresholdForRectangleAngle) {
        count90++;
      }
      if (lines.get(i).calculateAngle(lines.get(3))
              < Constants.thresholdForRectangleAngle) {
        count0++;
      }
      if (Math.abs(90 - lines.get(i).calculateAngle(lines.get(3)))
              < Constants.thresholdForRectangleAngle) {
        count90++;
      }
      if (count0 == 2 && count90 == 2) {
        count0 = 0;
        count90 = 0;
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns a copy of the list of lines representing the Rectangles' lines.
   * @return a copy of the list of lines.
   */
  public List<Line> getLines() {
    List<Line> rectangles = new ArrayList<>();
    rectangles.add(firstSide);
    rectangles.add(secondSide);
    rectangles.add(thirdSide);
    rectangles.add(fourthSide);
    return rectangles;
  }

  /**
   * Calculates the center of the rectangle.
   * @return the Point2D representing the center of the rectangle.
   */
  protected Point2D calculateCenter() {
    int xSum = 0;
    int ySum = 0;
    Point2D newPoint;
    for (Line line: getLines()) {
      xSum += line.getMidpoint().getX();
      ySum += line.getMidpoint().getY();
    }
    return newPoint = new Point2D(xSum/4, ySum/4);
  }

}
