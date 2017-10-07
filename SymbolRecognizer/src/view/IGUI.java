package view;

import java.util.List;

import controller.RealMouseClass;
import model.Point2D;
import model.Symbol;

/**
 * An interface which includes the methods necessary for our representation of the View of
 * this Symbols Recognizer program.
 */
public interface IGUI {

  /**
   * Receives the symbols, utilized to acccess the symbols from the Controller.
   *
   * @param symbols a list of symbols.
   */
  void receiveSymbols(List<Symbol> symbols);

  /**
   * Creates the list of points to be drawn. This function is used by the controller to pass
   * the data (list of points) accrued by the Mouse to the view (to be drawn).
   *
   * @param points a list of points.
   */
  void assignDragger(List<Point2D> points);

  /**
   * Draws all the Symbols and the user's drawn line to the graphic.
   */
  void repaint();

  /**
   * Assigns the MouseMotion and MouseMotionListeners.
   *
   * @param mouseClass a MouseClass.
   */
  void assignMouseListener(RealMouseClass mouseClass);


}