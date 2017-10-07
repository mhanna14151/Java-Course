import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import controller.MouseDraggedClass;
import controller.MouseReleasedClass;
import controller.RealMouseClass;
import model.Circle;
import model.Line;
import model.Point2D;
import model.SnowPerson;
import model.Symbol;
import model.SymbolsBank;
import model.SymbolsBankImpl;
import model.Triangle;
import view.IGUI;
import view.MockGUI;

import static org.junit.Assert.assertEquals;

/**
 * A Test designed to test the interactions/methods of the Controller.
 */
public class ControllerTest {
  private Controller controller;
  private StringBuffer outView;
  private RealMouseClass mc;
  // Controller 2
  private Controller controller1;
  private StringBuffer outView1;


  /**
   * Sets up the class for testing.
   */
  @Before
  public void setup() {
    outView = new StringBuffer();
    StringBuffer outMouse = new StringBuffer();
    SymbolsBank bank = new SymbolsBankImpl();
    bank.addSymbol(new Line(new Point2D(10, 10), new Point2D(15, 15)));
    controller = new Controller(bank);
    mc = new RealMouseClass(controller, new MouseDraggedClass(), new MouseReleasedClass());
    IGUI gui = new MockGUI(outView);
    controller.setView(gui, mc);
    // Controller 2
    outView1 = new StringBuffer();
    IGUI gui1 = new MockGUI(outView1);
    SymbolsBank bank1 = new SymbolsBankImpl();
    controller1 = new Controller(bank1);
    controller1.setView(gui1, mc);

  }

  /**
   * Adds a line to the bank and sees if it adds successfully and is drawn.
   */
  @Test
  public void testCapabilityToAddSymbolToBankAndNotifiesIfItsBeenDrawn() {
    assertEquals("Line{start=Point2D{x=10.0, y=10.0}, end=Point2D{x=15.0, y=15.0}}\n",
            outView.toString());

    controller.adder(new Line(new Point2D(12, 12), new Point2D(14, 16)));
    //controller.adder();

    assertEquals("Line{start=Point2D{x=12.0, y=12.0}, end=Point2D{x=14.0, y=16.0}}\n"
                    + "Line{start=Point2D{x=10.0, y=10.0}, end=Point2D{x=15.0, y=15.0}}\n",
            outView.toString());

  }

  /**
   * Tests the assignPointsToBeDrawn method.  This add points a list, but ultimately triggers
   * the view's assignDragger() and repaint() methods.  This tests that right methods are called
   * by checking that the view's methods are ultimately called and appending to the StringBuffer.
   */
  @Test
  public void testAssignPointsToBeDrawn() {
    controller1.assignPointsToBeDrawn(new Point2D(0, 0));
    assertEquals("assignMouseListener executed.\n"
            + "[Point2D{x=0.0, y=0.0}]\n"
            + "repaint executed.\n", outView1.toString());
  }

  /**
   * Tests that the controller sets the mouselistener in the view.
   */
  @Test
  public void testSetMouseListener() {
    controller.setMouseListeners(mc);
    assertEquals("assignMouseListener executed.\n", outView1.toString());
  }

  /**
   * This tests the generateSymbolAndDraw method.  Since no points are in the model,
   * all this will do is pass back an empty model, but will print that the mouseListener was
   * assigned from the initial setup.
   */
  @Test
  public void testGenerateSymbolAndDraw() {
    controller.generateSymbolAndDraw();
    assertEquals("assignMouseListener executed.\n", outView1.toString());
  }

  //-----------------

  /**
   * Tests before anything is added and after a Circle up until the point of a SnowPerson.
   * Checks if the MouseListeners are added.
   */
  @Test
  public void testCapabilityToAddCircleToBankandNotifiesIfItsBeenDrawn() {
    assertEquals("assignMouseListener executed.\n", outView1.toString());
    controller1.adder(new Circle(new Point2D(0, 0), 40));
    assertEquals("Circle{Origin=Point2D{x=0.0, y=0.0}, radius=40.0}\n", outView1.toString());
    controller1.adder(new Circle(new Point2D(0, 60), 20));
    assertEquals("Circle{Origin=Point2D{x=0.0, y=60.0}, radius=20.0}\n" +
            "Circle{Origin=Point2D{x=0.0, y=0.0}, radius=40.0}\n", outView1.toString());
    controller1.adder(new Circle(new Point2D(0, 90), 10));
    assertEquals("SnowPerson: with Circles: \n" +
            "C1) Origin: (0.00, 90.00) Radius: 10.00\n" +
            "C2) Origin: (0.00, 60.00) Radius: 20.00\n" +
            "C3) Origin: (0.00, 0.00) Radius: 40.00\n", outView1.toString());

  }

  /**
   * Tests drawing Lines and then a Triangle.
   */
  @Test
  public void testCapabilityToAddLinesForBothTriangles() {
    Symbol l0 = new Line(new Point2D(0.0, 0.0), new Point2D(15.0, 0.0));
    Symbol l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 15.0));
    Symbol l2 = new Line(new Point2D(15.0, 0.0), new Point2D(0.0, 15.0));
    controller1.adder(l0);
    controller1.adder(l1);
    assertEquals("Line{start=Point2D{x=0.0, y=0.0}, end=Point2D{x=0.0, y=15.0}}\n" +
            "Line{start=Point2D{x=0.0, y=0.0}, end=Point2D{x=15.0, y=0.0}}\n", outView1.toString());
    controller1.adder(l2);
    assertEquals("Equilateral Triangle with Sides: \n" +
            "L1: Start(15.00, 0.00) End:(0.00, 15.00)\n" +
            "L2: : Start(0.00, 0.00) End:(0.00, 15.00)\n" +
            "L2: Start(0.00, 0.00) End:(15.00, 0.00)\n", outView1.toString());

  }


  /**
   * Tests that an Equilateral Triangle, Triangle, SnowPerson, Line, and Circle, can all be drawn
   * as symbols are added to to the bank through the model.
   */
  @Test
  public void testCapabilityToDrawEverything() {
    Symbol l0 = new Line(new Point2D(0.0, 0.0), new Point2D(45.0, 0.0));
    Symbol l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 45.0));
    Symbol l2 = new Line(new Point2D(45, 0.0), new Point2D(0.0, 45.0));
    Symbol c1 = new Circle(new Point2D(0, 0), 40);
    Symbol c2 = new Circle(new Point2D(0, 60), 20);
    Symbol c3 = new Circle(new Point2D(0, 90), 10);
    Symbol e0 = new Line(new Point2D(0.0, 0.0), new Point2D(30.0, 0.0));
    Symbol e1 = new Line(new Point2D(0.0, 0.0), new Point2D(15, 26));
    Symbol e2 = new Line(new Point2D(30, 0.0), new Point2D(15, 26));

    controller1.adder(l0);
    controller1.adder(c2);
    controller1.adder(l1);
    controller1.adder(l2);
    controller1.adder(c1);
    controller1.adder(c2);
    controller1.adder(l1);
    controller1.adder(e0);
    controller1.adder(e1);
    controller1.adder(e2);
    controller1.adder(c3);

    assertEquals("Line{start=Point2D{x=0.0, y=0.0}, end=Point2D{x=0.0, y=45.0}}\n" +
            "Circle{Origin=Point2D{x=0.0, y=60.0}, radius=20.0}\n" +
            "Triangle with Sides:\n" +
            "L1: Start(45.00, 0.00) End:(0.00, 45.00)\n" +
            "L2: : Start(0.00, 0.00) End:(0.00, 45.00)\n" +
            "L2: Start(0.00, 0.00) End:(45.00, 0.00)\n" +
            "Equilateral Triangle with Sides: \n" +
            "L1: Start(30.00, 0.00) End:(15.00, 26.00)\n" +
            "L2: : Start(0.00, 0.00) End:(15.00, 26.00)\n" +
            "L2: Start(0.00, 0.00) End:(30.00, 0.00)\n" +
            "SnowPerson: with Circles: \n" +
            "C1) Origin: (0.00, 90.00) Radius: 10.00\n" +
            "C2) Origin: (0.00, 60.00) Radius: 20.00\n" +
            "C3) Origin: (0.00, 0.00) Radius: 40.00\n", outView1.toString());
  }


  /**
   * Tests that a non-basic Symbol cannot be added (instance Triangle).
   */
  @Test(expected = IllegalArgumentException.class)
  public void ExceptionCatchInstanceA() {
    Line l0 = new Line(new Point2D(0.0, 0.0), new Point2D(5.0, 0.0));
    Line l1 = new Line(new Point2D(0.0, 0.0), new Point2D(0.0, 5.0));
    Line l2 = new Line(new Point2D(5.0, 0.0), new Point2D(0.0, 5.0));
    Triangle triangle = new Triangle(l0, l1, l2);
    controller1.adder(triangle);
  }

  /**
   * Tests that a non-basic Symbol cannot be added (instance SnowPerson).
   */
  @Test(expected = IllegalArgumentException.class)
  public void ExceptionCatchInstanceB() {
    Circle c1 = new Circle(new Point2D(0, 0), 4);
    Circle c2 = new Circle(new Point2D(0, 6), 2);
    Circle c3 = new Circle(new Point2D(0, 9), 1);
    controller1.adder(new SnowPerson(c1, c2, c3));
  }


}