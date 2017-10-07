package controller;

import model.Point2D;

/**
 * This class represents an implementation of a function object when a mouse is dragged.
 * It will take the points given and feed it into the controller given.
 */
public class MouseDraggedClass implements IMouseFxObj {

  /**
   * A Mouse Event that adds a Point2D to a controller (specifically is adding to a list of Point2D
   * contained in an instance of SymbolResemblanceFromList in the Controller).
   * @param p a Point2D that will be added to the controller that will later be recognized for
   *          a symbol.
   * @param c the controller used for the program.  This should be the same controller used across
   *          classes.
   */
  @Override
  public void mouse(Point2D p, Controller c) {
    c.assignPointsToBeDrawn(new Point2D(p.getX(), p.getY()));
  }
}
