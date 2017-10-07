import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Circle;
import model.EquilateralTriangle;
import model.Line;
import model.Point2D;
import model.SnowPerson;
import model.Symbol;
import model.SymbolsBankImpl;
import model.Triangle;

import static org.junit.Assert.assertEquals;

/**
 * This class represents test for the model.SymbolsBankImpl class.  It contains
 * tests to make sure things are added to the correct list and are reported out in the order
 * expected.
 */
public class SymbolsBankImplTest {

  SymbolsBankImpl bank;
  List<Symbol> list;
  Symbol line;
  Symbol line2;
  Symbol circle;
  double x1;
  double y1;
  double x2;
  double y2;
  double x3;
  double y3;

  /**
   * This test sets up a bank and a list to compare against what is reported out.
   */
  @Before
  public void setup() {
    bank = new SymbolsBankImpl();
    list = new ArrayList<>();
    line = new Line(new Point2D(0, 0), new Point2D(5, 5));

  }

  @Test
  public void AddingShapesThatDontMakeComposite() {
    bank.addSymbol(line);
    list.add(0, line);
    assertEquals(list, bank.symbolsReport());
    x1 = 0;
    y1 = 0;
    x2 = 5;
    y2 = 3;
    for (int i = 0; i < 50; i++) {
      line2 = new Line(new Point2D(x1, y1), new Point2D(x2, y2));
      bank.addSymbol(line2);
      list.add(0, line2);
      x1 += 21;
      y1 += 44;
      x2 -= 35;
      y2 -= 51;
    }
    assertEquals(list, bank.symbolsReport());
  }

  @Test
  public void testCompositesMadeWithBasicsThrownIn() {
    Symbol l0 = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    Symbol l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    Symbol l2 = new Line(new Point2D(5.0, 0.0), new Point2D(0.0, 5.0));
    Symbol t0 = new Triangle((Line) l2, (Line) l1, (Line) l0);

    bank.addSymbol(l0);
    bank.addSymbol(l1);
    bank.addSymbol(l2);

    list.add(t0);

    assertEquals(list, bank.compositeSymbolsReport());

  }


  /**
   * Tests that trying to add a Composite model.Symbol should fail - instance: model.SnowPerson.
   */
  @Test(expected = IllegalArgumentException.class)
  public void attemptToAddCompositeSymbol() {
    Circle firstCircle = new Circle(new Point2D(0, 0), 5);
    Circle secondCircle = new Circle(new Point2D(9, 0), 4);
    Circle thirdCircle = new Circle(new Point2D(16, 0), 3);
    SnowPerson snow = new SnowPerson(firstCircle, secondCircle, thirdCircle);
    bank.addSymbol(snow);

  }

  /**
   * Tests that trying to add a Composite model.Symbol should fail - instance: model.Triangle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void attemptToAddCompositeSymbolInstanceTwo() {
    Line l0 = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    Line l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    Line l2 = new Line(new Point2D(5.0, 0.0), new Point2D(0.0, 5.0));
    Triangle triangle = new Triangle(l0, l1, l2);
    bank.addSymbol(triangle);

  }

  /**
   * Tests that trying to add a Composite model.Symbol should fail - instance: Equilateral Triangle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void attemptToAddCompositeSymbolInstanceThree() {
    Line l0 = new Line(new Point2D(0.0, 0.0), new Point2D(-5.0, 0.0));
    Line l1 = new Line(new Point2D(-2.50, -4.33), new Point2D(-5.0, 0.0));
    Line l2 = new Line(new Point2D(-2.50, -4.33), new Point2D(0.00, 0.00));
    EquilateralTriangle eTriangle = new EquilateralTriangle(l0, l1, l2);
    bank.addSymbol(eTriangle);

  }


  /**
   * A comprehensive test with comments embedded in the code above each assertion, showing that
   * the code works as intended at each step (where a new Symbol is added). At the end, it also
   * checks for the functionality of the model's capability to report the entire list of symbols,
   * or it's subtypes, basic and composite.
   */
  @Test
  public void comprehensiveTest() {
    Circle firstCircle = new Circle(new Point2D(0, 0), 5);
    Circle secondCircle = new Circle(new Point2D(9, 0), 4);
    Circle thirdCircle = new Circle(new Point2D(16, 0), 3);
    Line l0 = new Line(new Point2D(0.0, 0.0), new Point2D(-5.0, 0.0));
    Line l1 = new Line(new Point2D(-2.50, -4.33), new Point2D(-5.0, 0.0));
    Line l2 = new Line(new Point2D(-2.50, -4.33), new Point2D(0.00, 0.00));
    bank.addSymbol(firstCircle);
    bank.addSymbol(secondCircle);
    list.add(0, firstCircle);
    list.add(0, secondCircle);
    // Tests two circles can be added in a row.
    assertEquals(bank.symbolsReport(), list);
    bank.addSymbol(thirdCircle);
    list.remove(0);
    list.remove(0);
    list.add(new SnowPerson(firstCircle, secondCircle, thirdCircle));
    // Tests that the three Circles compress to a model.SnowPerson
    assertEquals(bank.symbolsReport(), list);
    bank.addSymbol(l0);
    bank.addSymbol(l2);
    list.add(0, l0);
    list.add(0, l2);
    // model.SnowPerson and Two Lines
    assertEquals(bank.symbolsReport(), list);
    bank.addSymbol(l1);
    list.remove(0);
    list.remove(0);
    list.add(0, new EquilateralTriangle(l0, l1, l2));
    // model.SnowPerson and model.EquilateralTriangle
    assertEquals(bank.symbolsReport(), list);
    bank.addSymbol(l0);
    bank.addSymbol(firstCircle);
    bank.addSymbol(l1);
    bank.addSymbol(thirdCircle);
    list.add(0, firstCircle);
    list.add(0, thirdCircle);
    list.add(0, l0);
    list.add(0, l1);
    // model.SnowPerson, Equilateral model.Triangle, 2 Lines, 2 Circles
    assertEquals(bank.symbolsReport(), list);
    bank.addSymbol(l2);
    bank.addSymbol(secondCircle);
    list.remove(0);
    list.remove(0);
    list.remove(0);
    list.remove(0);
    list.add(new SnowPerson(firstCircle, secondCircle, thirdCircle));
    list.add(0, new EquilateralTriangle(l0, l1, l2));
    // Checks a circle and equilateral triangle can both be made when their components
    // are added in an interrupted manner.
    assertEquals(bank.symbolsReport(), list);
    bank.addSymbol(firstCircle);
    bank.addSymbol(secondCircle);
    bank.addSymbol(firstCircle);
    list.add(0, firstCircle);
    list.add(0, secondCircle);
    list.add(0, firstCircle);
    // Checks if three Circles that shouldn't make a model.Circle are not made into a SnowPerson
    assertEquals(bank.symbolsReport(), list);
    bank.addSymbol(thirdCircle);
    list.remove(0);
    list.remove(0);
    list.add(new SnowPerson(firstCircle, secondCircle, thirdCircle));
    // 0 Lines, 1 model.Circle, 1 Equilateral model.Triangle, 3 SnowPersons
    assertEquals(bank.symbolsReport(), list);
    l0 = new Line(new Point2D(0.2, 0.2), new Point2D(5.2, 0.2));
    l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    l2 = new Line(new Point2D(5.1, 0.1), new Point2D(0.1, 5.1));
    bank.addSymbol(l0);
    bank.addSymbol(l1);
    bank.addSymbol(l2);
    list.add(1, new Triangle(l0, l1, l2));
    // checks if Triangle can be added to the bank appropriately upon three lines within a certain
    // threshold being added.
    assertEquals(bank.symbolsReport(), list);
    bank.addSymbol(l0);
    list.add(0, l0);
    // Ends having one of every type of Symbol (Basic and Composite) to check the capability of
    // report to accurately reflect all the Symbols added.
    assertEquals(bank.symbolsReport(), list);
    list.remove(0);
    list.remove(0);
    // checks if the compositeSymbolsReport() functions correctly.
    assertEquals(bank.compositeSymbolsReport(), list);
    while (!list.isEmpty()) {
      list.remove(0);
    }
    list.add(l0);
    list.add(firstCircle);
    bank.addSymbol(secondCircle);
    list.add(1, secondCircle);
    // Checks the basicSymbolsReport functions correctly.
    assertEquals(list, bank.basicSymbolsReport());
  }


  /**
   * This test adds basic shapes, lines and circles, none of which should make a composite shape.
   */
  @Test
  public void AddingBasicShapesThatDontMakeComposite() {
    line = new Line(new Point2D(0, 0), new Point2D(5, 5));
    x2 = 40;

    for (int i = 0; i < 50; i++) {
      circle = new Circle(new Point2D(x1, y1), x2);
      bank.addSymbol(circle);
      list.add(0, circle);
      x1 *= 2.12;
      y1 -= 45.2;
    }
    assertEquals(list, bank.symbolsReport());

    bank.addSymbol(line);
    list.add(0, line);
    assertEquals(list, bank.symbolsReport());

    x1 = 0;
    y1 = 0;
    x2 = 5;
    y2 = 3;

    for (int i = 0; i < 50; i++) {
      line2 = new Line(new Point2D(x1, y1), new Point2D(x2, y2));
      bank.addSymbol(line2);
      list.add(0, line2);
      x1 += 29;
      y1 += 49;
      x2 -= 35;
      y2 -= 53;
    }
    assertEquals(list, bank.symbolsReport());
  }

  /**
   * This tests composites shapes that are made with basic shapes that form a composite.
   * It uses the other reporting methods to compare.
   */
  @Test
  public void testCompositeTriangle() {
    Symbol l0 = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    Symbol l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    Symbol l2 = new Line(new Point2D(5.0, 0.0), new Point2D(0.0, 5.0));
    Symbol t0 = new Triangle((Line) l2, (Line) l1, (Line) l0);

    bank.addSymbol(l0);
    bank.addSymbol(l1);
    bank.addSymbol(l2);

    list.add(t0);

    assertEquals(list, bank.compositeSymbolsReport());

  }

  /**
   * This tests composites shapes that are made with basic shapes that form a composite
   * with other basic shapes that don't form a triangle.
   */
  @Test
  public void testTriangleWithBasicsThrownIn() {
    Symbol l0 = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    Symbol l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    Symbol l2 = new Line(new Point2D(5.0, 0.0), new Point2D(0.0, 5.0));
    Symbol t0 = new Triangle((Line) l2, (Line) l1, (Line) l0);

    bank.addSymbol(l0);
    bank.addSymbol(l1);
    bank.addSymbol(l2);
    bank.addSymbol(l0);
    bank.addSymbol(l0);

    list.add(t0);
    list.add(0, l0);
    list.add(0, l0);

    assertEquals(list, bank.symbolsReport());

  }

  /**
   * Tests making multiple triangles.
   */
  @Test
  public void testMultipleTriangles() {
    x1 = 0.00;
    y1 = 0.00;
    x2 = 5.00;
    y2 = 0.00;
    x3 = 0.00;
    y3 = 5.00;

    for (int i = 0; i < 10; i++) {
      Symbol l0 = new Line(new Point2D(x1, y1), new Point2D(x2, y2));
      Symbol l1 = new Line(new Point2D(x2, y2), new Point2D(x3, y3));
      Symbol l2 = new Line(new Point2D(x1, y1), new Point2D(x3, y3));
      Symbol t0 = new Triangle((Line) l2, (Line) l1, (Line) l0);
      bank.addSymbol(l0);
      bank.addSymbol(l1);
      bank.addSymbol(l2);
      list.add(0, t0);

      x1 -= 15;
      y1 += 20;
      x2 += 30;
      y2 -= 50;
      x3 -= 80;
      y3 -= 100;
    }

    for (int i = 0; i < 10; i++) {
      Symbol l0 = new Line(new Point2D(x1, y1), new Point2D(x2, y2));
      Symbol l1 = new Line(new Point2D(x2, y2), new Point2D(x3, y3));
      Symbol l2 = new Line(new Point2D(x1, y1), new Point2D(x3, y3));
      Symbol t0 = new Triangle((Line) l2, (Line) l1, (Line) l0);
      bank.addSymbol(l0);
      bank.addSymbol(l1);
      bank.addSymbol(l2);
      list.add(0, t0);

      x1 += 51;
      y1 -= 43;
      x2 -= 30;
      y2 -= 37;
      x3 -= 72;
      y3 -= 48;
    }

    assertEquals(list, bank.symbolsReport());

  }

  /**
   * Tests making multiple triangles with circles in the list.
   */
  @Test
  public void testMultipleTrianglesWithCircles() {
    x1 = 0.00;
    y1 = 0.00;
    x2 = 5.00;
    y2 = 0.00;
    x3 = 0.00;
    y3 = 5.00;

    List<Symbol> circleList;
    circleList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      Symbol l0 = new Line(new Point2D(x1, y1), new Point2D(x2, y2));
      Symbol l1 = new Line(new Point2D(x2, y2), new Point2D(x3, y3));
      Symbol l2 = new Line(new Point2D(x1, y1), new Point2D(x3, y3));
      Symbol t0 = new Triangle((Line) l2, (Line) l1, (Line) l0);
      Symbol c0 = new Circle(new Point2D(x1, y1), 5);
      circleList.add(0, c0);
      bank.addSymbol(c0);
      bank.addSymbol(l0);
      bank.addSymbol(l1);
      bank.addSymbol(l2);
      list.add(0, t0);

      x1 -= 15;
      y1 += 20;
      x2 += 30;
      y2 -= 50;
      x3 -= 80;
      y3 -= 100;
    }

    for (int i = 0; i < 10; i++) {
      Symbol l0 = new Line(new Point2D(x1, y1), new Point2D(x2, y2));
      Symbol l1 = new Line(new Point2D(x2, y2), new Point2D(x3, y3));
      Symbol l2 = new Line(new Point2D(x1, y1), new Point2D(x3, y3));
      Symbol t0 = new Triangle((Line) l2, (Line) l1, (Line) l0);
      bank.addSymbol(l0);
      bank.addSymbol(l1);
      bank.addSymbol(l2);
      list.add(0, t0);

      x1 += 51;
      y1 -= 43;
      x2 -= 30;
      y2 -= 37;
      x3 -= 72;
      y3 -= 48;
    }

    list.addAll(0, circleList);
    assertEquals(list, bank.symbolsReport());
  }


}