package controller;

import java.util.ArrayList;
import java.util.List;

import model.Circle;
import model.Line;
import model.Point2D;
import model.Symbol;

/**
 * This class is utilized to determine which Symbol a list of points best resembles.
 * Note: we did consider using inheritance by extending this class from a List/ArrayList but
 * we wished to restrict the methods to the ones provided.
 */
public class SymbolResemblanceFromList {
  private List<Point2D> pointsList;
  double cx;
  double cy;
  double radius;
  Point2D startOfLine;
  Point2D endOfLine;

  /**
   * The Constructor for the class, representing a list of points. It implements
   * our point list as an array list.
   */
  public SymbolResemblanceFromList() {
    pointsList = new ArrayList<>();
  }

  /**
   * Clears the list of all the points.
   */
  protected void clear() {
    pointsList.clear();
  }

  /**
   * Returns a copy of the list of points.
   *
   * @return the list of points collected so far.
   */
  protected List<Point2D> getList() {
    List<Point2D> result = new ArrayList<>();
    result.addAll(pointsList);
    return result;
  }

  /**
   * Adds a Point2D to the list to eventually represent a symbol.
   *
   * @param aPoint a Point2D to be added to the list.
   */
  public void add(Point2D aPoint) {
    pointsList.add(aPoint);
  }

  /**
   * Returns the size of the list.
   *
   * @return the size of the list as an int.
   */
  protected int size() {
    return pointsList.size();
  }

  /**
   * Determines the Symbol that the list of points best resembles largely based on
   * goodness of fit.
   *
   * @return the Symbol that the list of points best resembles.
   */
  public Symbol doLogicAndAddBestFitSymbol() {
    double line = goodnessOfFitForTheLine();
    double circle = goodnessOfFitForCircle();
    //System.out.println("model.Line: " + line);
    //System.out.println("model.Circle: " + circle);
    if (line > .80) {
      return makeLineOutOfList();
    } else if (circle > line) {
      return makeCircleOutOfList();
    } else {
      return makeLineOutOfList();
    }
  }

  /**
   * Calculates the goodness of fit for a Circle (utilizing this class's list of Point2D).
   *
   * @return a double representing the goodness of fit value for a Circle.
   */
  private double goodnessOfFitForCircle() {
    int n = size();
    double sX = sumX();
    //System.out.println("sX: " + sX);
    double sY = sumY();
    //System.out.println("sY: " + sY);
    double sXY = sumXY();
    //System.out.println("sXY: " + sXY);
    double sXX = sumX2();
    //System.out.println("sXX: " + sXX);
    double sYY = sumY2();
    //System.out.println("sY2: " + sYY);
    double sX2Y2 = sumX2Y2();
    //System.out.println("sX2Y2: " + sX2Y2);
    double sXX2Y2 = sumXX2Y2();
    //System.out.println("sXX2Y2: " + sXX2Y2);
    double sYX2Y2 = sumYX2Y2();
    //System.out.println("sYX2Y2: " + sYX2Y2);
    double d = sXX * (n * sYY - sY * sY)
            - sXY * (n * sXY - sX * sY)
            + sX * (sXY * sY - sYY * sX);
    //System.out.println("d: " + d);
    double da = sXX2Y2 * (n * sYY - sY * sY)
            - sXY * (n * sYX2Y2 - sX2Y2 * sY)
            + sX * (sYX2Y2 * sY - sYY * sX2Y2);

    double db = sXX * (n * sYX2Y2 - sX2Y2 * sY)
            - sXX2Y2 * (n * sXY - sX * sY)
            + sX * (sXY * sX2Y2 - sYX2Y2 * sX);

    double dc = sXX * (sYY * sX2Y2 - sYX2Y2 * sY)
            - sXY * (sXY * sX2Y2 - sYX2Y2 * sX)
            + sXX2Y2 * (sXY * sY - sYY * sX);

    if (d == 0) {
      return 0;
    }

    double a = da / d;
    //System.out.println("a: " + a);
    double b = db / d;
    //System.out.println("b: " + b);
    double c = dc / d;
    //System.out.println("a: " + c);
    // parameters
    this.cx = a / 2;
    //this.cx = sumX()/n;
    //System.out.println("cx: " + cx);
    this.cy = b / 2;
    //this.cy = sumY()/n;
    //System.out.println("cy: " + cy);
    this.radius = Math.sqrt(c + cx * cx + cy * cy);
    double goodnessOfFit;
    //goodnessOfFit = Math.abs();
    double m = 0;
    // calculating each di for each point
    for (Point2D point : pointsList) {
      m += Math.abs(((point.getX() - cx)
              * (point.getX() - cx))
              + ((point.getY() - cy)
              * (point.getY() - cy)) - (radius * radius));
    }
    double q = Math.sqrt(m) / n;
    goodnessOfFit = 1 - Math.min(1, (q / radius));
    return goodnessOfFit;
  }

  /**
   * Calculates the goodness of fit for a Line (using this class' list of Point2D).
   *
   * @return the goodness of fit value as a double.
   */
  private double goodnessOfFitForTheLine() {
    int n = size();
    double averageX = sumX() / n;
    double averageY = sumY() / n;
    // These are different than the sXY, sXX, and sYY in model.Circle's goodness of fit!
    double sXY = 0;
    for (Point2D point : pointsList) {
      sXY += (point.getX() - averageX) * (point.getY() - averageY);
    }
    double sXX = 0;
    for (Point2D point : pointsList) {
      sXX += (point.getX() - averageX) * (point.getX() - averageX);
    }
    double sYY = 0;
    for (Point2D point : pointsList) {
      sYY += (point.getY() - averageY) * (point.getY() - averageY);
    }
    double q = 2 * sXY / (sXX - sYY);
    double thetaOne = Math.atan(q);
    // NEED TO CONVERT TO DEGREES FOR THIS TO WORK! MATH.ATAN IS NOT IN DEGREES!!!!!!
    double thetaTwo = thetaOne + Math.PI;

    double fOfMThetaOne = 2 * sXY * Math.sin(thetaOne) - (sYY - sXX) * Math.cos(thetaOne);

    double fOfMThetaTwo = 2 * sXY * Math.sin(thetaTwo) - (sYY - sXX) * Math.cos(thetaTwo);

    double m = 0;
    if (fOfMThetaOne > fOfMThetaTwo) {
      m = thetaOne;
    }
    if (fOfMThetaTwo > fOfMThetaOne) {
      m = thetaTwo;
    }
    double a = Math.cos(m / 2);
    double b = Math.sin(m / 2);
    Point2D s = new Point2D(averageX, averageY);
    //p = c + r cos (theta) check the range of thetas
    // FIND THE LINE SEGMENT
    double maxT = 0;
    double minT = 0;
    //List<Double> tValues = new LinkedList<Double>();
    for (int i = 0; i < pointsList.size(); i++) {
      double t = a * (pointsList.get(i).getX() - averageX)
              + b * (pointsList.get(i).getY() - averageY);
      if (i == 0) {
        maxT = t;
        minT = t;
      } else {
        if (minT > t) {
          minT = t;
        }
        if (maxT < t) {
          maxT = t;
        }
      }
    }

    double x1 = s.getX() + minT * a;
    double y1 = s.getY() + minT * b;
    double x2 = s.getX() + maxT * a;
    double y2 = s.getY() + maxT * b;

    startOfLine = new Point2D(x1, y1);
    endOfLine = new Point2D(x2, y2);

    double tOMin = 0;
    double tOMax = 0;
    for (int i = 0; i < pointsList.size(); i++) {
      double tOrtho = b * (pointsList.get(i).getX() - averageX) - a
              * (pointsList.get(i).getY() - averageY);
      if (i == 0) {
        tOMin = tOrtho;
        tOMax = tOrtho;
      } else {
        if (tOMax < tOrtho) {
          tOMax = tOrtho;
        }
        if (tOMin > tOrtho) {
          tOMin = tOrtho;
        }
      }
    }
    double goodnessOfFit = 1 - Math.min(1, ((tOMax - tOMin) / (maxT - minT)));
    double value = (tOMax - tOMin) / (maxT - minT);
    //System.out.println("(tOMax-tOMin) / (maxT-minT): " + value);
    return goodnessOfFit;

  }


  /**
   * Calculates the sum of all the x-values of a list.
   *
   * @return the sum as an integer.
   */
  private double sumX() {
    double sum = 0;
    for (Point2D point : pointsList) {
      sum = point.getX() + sum;
    }
    return sum;
  }

  /**
   * Calculates the sum of all the y-values of a list.
   *
   * @return the sum as an integer.
   */
  private double sumY() {
    double sum = 0;
    for (Point2D point : pointsList) {
      sum = point.getY() + sum;
    }
    return sum;
  }

  /**
   * Calculates the sum of all the (x-values)^2 of a list.
   *
   * @return the sum as an integer.
   */
  private double sumX2() {
    double sum = 0;
    for (Point2D point : pointsList) {
      sum += (point.getX() * point.getX());
    }
    return sum;
  }

  /**
   * Calculates the sum of all the (y-values)^2 of a list.
   *
   * @return the sum as an integer.
   */
  private double sumY2() {
    double sum = 0;
    for (Point2D point : pointsList) {
      sum = (point.getY() * point.getY()) + sum;
    }
    return sum;
  }

  /**
   * Calculates the sum of all the x*y values of a list.
   *
   * @return the sum as an integer.
   */
  private double sumXY() {
    double sum = 0;
    for (Point2D point : pointsList) {
      sum = (point.getX() * point.getY()) + sum;
    }
    return sum;
  }

  /**
   * Calculates the sum of x^2+y^2.
   *
   * @return the sum as a double.
   */
  private double sumX2Y2() {
    double sum = 0;
    for (Point2D point : pointsList) {
      sum += (point.getX() * point.getX() + point.getY() * point.getY());
    }
    return sum;
  }

  /**
   * Calculates the sum of x*(x^2+y^2).
   *
   * @return the sum as a double.
   */
  private double sumXX2Y2() {
    double sum = 0;
    for (Point2D point : pointsList) {
      sum += point.getX() * (Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2));

    }
    return sum;
  }

  /**
   * Calculates the sum of y*(x^2+y^2).
   *
   * @return the sum as a double.
   */
  private double sumYX2Y2() {
    double sum = 0;
    for (Point2D point : pointsList) {
      sum += point.getY() * (Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2));

    }
    return sum;
  }


  /**
   * A helper function that creates a Circle. This always need to be preceeded by
   * the method: goodnessOfFitForCircle() as it creates the needed cx and cy values seen in the
   * method body below.
   *
   * @return a Circle.
   */
  private Circle makeCircleOutOfList() {
    Circle newCircle = new Circle(new Point2D(cx, cy), radius);
    return newCircle;
  }

  /**
   * A helper function that creates a Line. This always need to be preceeded by
   * the method: goodnessOfFitForLine() as it creates the needed Point2D values (startOfLine and
   * endOfLine) seen in the method body below.
   *
   * @return a Line.
   */
  private Line makeLineOutOfList() {
    Line theLine = new Line(startOfLine, endOfLine);
    return theLine;
  }

}
