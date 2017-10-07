import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import controller.FakeMouseEvent;
import controller.IMouseFxObj;
import controller.RealMouseClass;
import model.Point2D;
import model.SymbolsBank;
import model.SymbolsBankImpl;
import view.IGUI;
import view.MockGUI;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the MouseListeners, specifically that the RealMouseRelease class
 * methods get executed properly.  We test this not by running the methods within RealMouseRelease,
 * because that is just the same as the Java MouseAdapter class, but rather by testing
 * the function objects, which are in the method bodies inside RealMouseRelease.
 */
public class MouseListenerTest {
  private Controller controller;
  private StringBuffer outMouseDrag;
  private StringBuffer outMouseRelease;
  private IMouseFxObj dragFx;
  private IMouseFxObj releaseFx;

  /**
   * Sets up the program for testing.  Rather than feeding it the actual logic that should happen
   * in the real program of drawing(via function objects), two new function objects are created
   * from the FakeMouseEvent class.
   * This replaces the logic of actually passing information to the controller, but instead
   * appends a string to a StringBuffer.  The StringBuffers are created at this level so we can
   * access them later in the adapter class.
   */
  @Before
  public void setup() {
    outMouseDrag = new StringBuffer();
    outMouseRelease = new StringBuffer();
    StringBuffer outView = new StringBuffer();
    SymbolsBank bank = new SymbolsBankImpl();
    controller = new Controller(bank);
    releaseFx = new FakeMouseEvent(outMouseRelease);
    dragFx = new FakeMouseEvent(outMouseDrag);
    RealMouseClass mc = new RealMouseClass(controller, dragFx, releaseFx);
    IGUI gui = new MockGUI(outView);
    controller.setView(gui, mc);
  }

  /**
   * This tests the function object that gets executed when a drag is done.
   */
  @Test
  public void testMouseDragOnce() {
    dragFx.mouse(new Point2D(4, 4), controller);
    assertEquals("event executed\n", outMouseDrag.toString());
  }

  /**
   * This tests multiple drag function objects methods being called.
   */
  @Test
  public void testMouseDragMultiple() {
    dragFx.mouse(new Point2D(4, 4), controller);
    assertEquals("event executed\n", outMouseDrag.toString());
    dragFx.mouse(new Point2D(4, 4), controller);
    assertEquals("event executed\n", outMouseDrag.toString());
    dragFx.mouse(new Point2D(4, 4), controller);
    assertEquals("event executed\n", outMouseDrag.toString());
    dragFx.mouse(new Point2D(4, 4), controller);
    dragFx.mouse(new Point2D(4, 4), controller);
    dragFx.mouse(new Point2D(4, 4), controller);
    dragFx.mouse(new Point2D(4, 4), controller);
    assertEquals("event executed\n", outMouseDrag.toString());
  }

  /**
   * This tests the function object that gets executed when a release is done.
   */
  @Test
  public void testMouseReleaseOne() {
    releaseFx.mouse(new Point2D(6, 6), controller);
    assertEquals("event executed\n", outMouseRelease.toString());
  }

  /**
   * This tests multiple release function object methods being called.
   */
  @Test
  public void testMouseReleaseMultiple() {
    releaseFx.mouse(new Point2D(6, 6), controller);
    assertEquals("event executed\n", outMouseRelease.toString());
    releaseFx.mouse(new Point2D(6, 6), controller);
    assertEquals("event executed\n", outMouseRelease.toString());
    releaseFx.mouse(new Point2D(6, 6), controller);
    assertEquals("event executed\n", outMouseRelease.toString());
    releaseFx.mouse(new Point2D(6, 6), controller);
    releaseFx.mouse(new Point2D(6, 6), controller);
    releaseFx.mouse(new Point2D(6, 6), controller);
    releaseFx.mouse(new Point2D(6, 6), controller);
    assertEquals("event executed\n", outMouseRelease.toString());
  }
}
