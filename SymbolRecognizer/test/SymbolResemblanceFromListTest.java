import org.junit.Before;
import org.junit.Test;

import controller.SymbolResemblanceFromList;
import model.Circle;
import model.Line;
import model.Point2D;
import model.Symbol;

import static org.junit.Assert.assertEquals;

/**
 * A class designed to test the functionality of the SymbolResemblenceFromList.
 */
public class SymbolResemblanceFromListTest {

  private Point2D point0;
  private Point2D point1;
  private Point2D point2;
  private Point2D point3;
  private Point2D point4;
  private Point2D point5;
  private Point2D point6;
  private Point2D point7;
  private SymbolResemblanceFromList pointList;
  private Point2D point1C;
  private Point2D point2C;
  private Point2D point3C;
  private Point2D point4C;
  private Point2D point5C;
  private Point2D point6C;
  private Point2D point7C;
  private Point2D point8C;

  /**
   * Sets up the class for testing.
   */
  @Before
  public void setup() {
    point0 = new Point2D(-10, -10);
    point1 = new Point2D(0, 0);
    point2 = new Point2D(1, 1);
    point3 = new Point2D(2, 2);
    point4 = new Point2D(4, 4);
    point5 = new Point2D(8, 8);
    point6 = new Point2D(10, 9);
    point7 = new Point2D(9, 10);
    point3C = new Point2D(0, 4);
    point4C = new Point2D(4, 0);
    point5C = new Point2D(0, -4);
    point7C = new Point2D(-4, 0);
    point8C = new Point2D(5, 0);
    point1C = new Point2D(-5, 0);
    point2C = new Point2D(0, -5);
    point6C = new Point2D(0, 5);
    pointList = new SymbolResemblanceFromList();

  }

  /**
   * Tests that a Line is correctly drawn (perfectly drawn line instance).
   */
  @Test
  public void bestFitLineTestInstanceA() {
    pointList.add(point0);
    pointList.add(point1);
    pointList.add(point2);
    pointList.add(point5);
    pointList.add(point4);
    pointList.add(point3);
    Symbol symbol = pointList.doLogicAndAddBestFitSymbol();
    // First checks if symbol is a line.
    assert (symbol instanceof Line);
    Line line1 = (Line) symbol;
    double x1 = line1.getStart().getX();
    double y1 = line1.getStart().getY();
    double x2 = line1.getEnd().getX();
    double y2 = line1.getEnd().getY();
    // Now checks that the line is correctly constructed.
    assertEquals(x1, -10, 0.01);
    assertEquals(y1, -10, 0.01);
    assertEquals(x2, 8, 0.01);
    assertEquals(y2, 8, 0.01);

  }

  /**
   * Draws a line for an imperfectly drawn line with a high goodness of fit.
   */
  @Test
  public void bestFitLineTestInstanceB() {
    pointList.add(point1);
    pointList.add(point2);
    pointList.add(point3);
    pointList.add(point4);
    pointList.add(point5);
    pointList.add(point6);
    pointList.add(point7);
    Symbol symbol = pointList.doLogicAndAddBestFitSymbol();
    // First checks if symbol is a line.
    assert (symbol instanceof Line);
    Line line1 = (Line) symbol;
    double x1 = line1.getStart().getX();
    double y1 = line1.getStart().getY();
    double x2 = line1.getEnd().getX();
    double y2 = line1.getEnd().getY();
    // Now checks if the line is correctly constructed.
    assertEquals(0, x1, 0.01);
    assertEquals(0, y1, 0.01);
    assertEquals(9.5, x2, 0.01);
    assertEquals(9.5, y2, 0.01);

  }

  /**
   * Tests for a well drawn Circle).
   */
  @Test
  public void bestFitCircleTestInstanceA() {
    pointList.add(point3C);
    pointList.add(point4C);
    pointList.add(point5C);
    pointList.add(point7C);
    Symbol symbol = pointList.doLogicAndAddBestFitSymbol();
    // First checks if the symbol produced is correctly a Circle.
    assert (symbol instanceof Circle);
    Circle circle = (Circle) symbol;
    double originX = circle.getOrigin().getX();
    double originY = circle.getOrigin().getY();
    double radius = circle.getRadius();
    // Now check if the Circle is correctly constructed.
    assertEquals(originX, 0, 0.01);
    assertEquals(originY, 0, 0.01);
    assertEquals(radius, 4, 0.01);

  }

  /**
   * Tests a less elegantly drawn circle.
   */
  @Test
  public void bestFitCircleTestInstanceB() {
    pointList.add(point1C);
    pointList.add(point2C);
    pointList.add(point3C);
    pointList.add(point4C);
    pointList.add(point5C);
    pointList.add(point6C);
    pointList.add(point7C);
    pointList.add(point8C);
    Symbol symbol = pointList.doLogicAndAddBestFitSymbol();
    // First checks if the symbol produced is correctly a Circle.
    assert (symbol instanceof Circle);
    Circle circle = (Circle) symbol;
    double originX = circle.getOrigin().getX();
    double originY = circle.getOrigin().getY();
    double radius = circle.getRadius();
    // Now check if the Circle is correctly constructed.
    assertEquals(0, originX, 0.01);
    assertEquals(0, originY, 0.01);
    assertEquals(4.5, radius, 0.03);
  }


}