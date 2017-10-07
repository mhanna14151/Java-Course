package controller;

import model.Point2D;

/**
 * This class represents an implementation of a function object when a mouse is released.
 * It will ignore the point given, and call one of the controllers methods.  This method called
 * in the controller class will generate the symbols in the controller and draw them in the view.
 */
public class MouseReleasedClass implements IMouseFxObj {

  /**
   * A function object that prompts the controller's method to generate a symbol and then pass
   * it and the rest of the symbols from the model to the view to be drawn.
   *
   * @param p a Point2D that will be added to the controller that will later be recognized for a
   *          symbol.
   * @param c the controller used for the program.  This should be the same controller used across
   *          classes.
   */
  @Override
  public void mouse(Point2D p, Controller c) {
    c.generateSymbolAndDraw();
  }

}
