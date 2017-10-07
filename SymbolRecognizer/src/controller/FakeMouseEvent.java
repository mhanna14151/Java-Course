package controller;

import model.Point2D;

/**
 * This class is created specifically for testing.  It is a class that appends
 * to a StringBuffer every time it's method is called.  This will be passed into the
 * RealMouseClass(class that extends MouseAdapater) to be executed when mouseDragged and
 * mouseRelease methods are called.
 */
public class FakeMouseEvent implements IMouseFxObj {

  private StringBuffer b;

  /**
   * The Constructor for FakeMouseEvent. It only takes one parameter, a string buffer.  This
   * stringbuffer can be accessed by the class constructing this class.
   *
   * @param b a StringBuffer.
   */
  public FakeMouseEvent(StringBuffer b) {
    this.b = b;
  }

  /**
   * A method utilized to indicate that the hypothetical mouse events have been executed.
   * Normally, both of these parameters can be used, but for the purposes of this class will be
   * completely ignored and we will just manipulate the stringbuffer for proof that the method
   * was called.
   *
   * @param p a Point2D.
   * @param c a Controller.
   */
  @Override
  public void mouse(Point2D p, Controller c) {
    b.delete(0, b.length());
    b.append("event executed\n");
  }

}
