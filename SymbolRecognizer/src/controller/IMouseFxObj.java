package controller;

import model.Point2D;

/**
 * This interface represents a function object for MouseListener methods.  Creating a class
 * that implements this interface should have a specific implementation of methods from classes
 * that implement MouseListener.  For example, a class that implements this interface for a
 * MouseAdapater class should contain an implementation for mouseDragged().
 */
public interface IMouseFxObj {

  /**
   * A method that executes the methods of any function object passed to it.
   * @param p a Point2D.
   * @param c a Controller.
   */
  void mouse(Point2D p, Controller c);
}
