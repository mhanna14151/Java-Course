package view;

import java.util.ArrayList;
import java.util.List;

import controller.RealMouseClass;
import model.Point2D;
import model.Symbol;

/**
 * A class designed to be utilized in testing the controller's capability of accessing the view.
 */
public class MockGUI implements IGUI {
  private StringBuffer out;
  private List<Symbol> modelList;

  /**
   * The Constructor of the MockGUI. This takes in a string buffer that can be accessed to the
   * class that constructs this, to ensure methods from this object were called. This also
   * initializes a list that will append to the string buffer for every item in the list.
   */
  public MockGUI(StringBuffer out) {
    this.out = out;
    modelList = new ArrayList<>();
  }

  /**
   * Creates the list of points to be drawn. This function is used by the controller to pass
   * the list of points accrued by the Mouse to the view (to be drawn). The parameter
   * will ultimately be ignored and the string buffer will be appended.
   *
   * @param drags a list of points, that will ultimately be ignored.
   */
  public void assignDragger(List<Point2D> drags) {
    out.append(drags.toString() + "\n");
  }

  /**
   * Draws all the Symbols and the user's drawn line to the graphic. The string buffer is appended
   * in this implementation.
   */
  @Override
  public void repaint() {
    out.append("repaint executed.\n");
  }

  /**
   * Assigns the MouseMotion and MouseMotionListeners. In this case the RealMouseClass is ignored
   * and the string buffer is appended.
   *
   * @param mouseClass a MouseClass.
   */
  @Override
  public void assignMouseListener(RealMouseClass mouseClass) {
    out.append("assignMouseListener executed.\n");
  }

  /**
   * Receives the symbols, utilized to access the symbols from the Controller.
   *
   * @param listSymbol a list of symbols received from the controller that it obtained from the
   *                   model.
   */
  public void receiveSymbols(List<Symbol> listSymbol) {
    modelList = listSymbol;
    out.delete(0, out.length());
    for (Symbol s : modelList) {
      out.append(s.toString() + "\n");
    }
  }
}