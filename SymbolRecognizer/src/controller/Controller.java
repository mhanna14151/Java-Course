package controller;

import java.util.ArrayList;

import model.Point2D;
import model.Symbol;
import model.SymbolsBank;
import view.IGUI;

/**
 * This class represents the controller, which controls the interactions between the model and
 * view.
 */
public class Controller {

  private IGUI gui;
  private SymbolsBank bank;
  private SymbolResemblanceFromList pointsToBeDrawn;

  /**
   * Constructs the controller.  It takes in the model as a parameter.  The view is not
   * initialized as there is a separate method to set the view.
   *
   * @param bank the model that the controller will use methods to pass shapes into and report to
   *             pass to the view.
   */
  public Controller(SymbolsBank bank) {
    this.bank = bank;
    pointsToBeDrawn = new SymbolResemblanceFromList();
  }

  /**
   * Sets the view for the program.  It takes in an IGUI and RealMouseClass.  At this time it also
   * checks the model if there are any symbols in the bank to pass to the view.
   *
   * @param gui an implementation of IGUI.
   * @param mc  a RealMouseClass, which is an extension of MouseAdapter.
   */
  public void setView(IGUI gui, RealMouseClass mc) {
    this.gui = gui;
    setMouseListeners(mc);
    if (bank.symbolsReport().size() > 0) {
      gui.receiveSymbols(bank.symbolsReport());
    }
  }

  /**
   * Sets the Mouse Listeners for this controller.
   *
   * @param mc a MouseClass (containing methods for differing types of listening for mouse events).
   */
  public void setMouseListeners(RealMouseClass mc) {
    gui.assignMouseListener(mc);
  }

  /**
   * A method that adds symbols to the model (SymbolsBank) and then relays information from the
   * model representing the current list of symbols stored in the model to the view.
   *
   * @param s the Symbol to be added to the model.
   */
  public void adder(Symbol s) {
    bank.addSymbol(s);
    gui.receiveSymbols(bank.symbolsReport());
  }

  /**
   * Adds the points to be drawn to SymbolResemblanceFromList. This will be utilized to both draw
   * the user's drawn line in real time and also be used to calculate the the best fit line for
   * a line and circle.
   *
   * @param p a point to be added.
   */
  public void assignPointsToBeDrawn(Point2D p) {
    pointsToBeDrawn.add(p);
    gui.assignDragger(pointsToBeDrawn.getList());
    gui.repaint();
  }

  /**
   * Determines the symbol to be added to the model from the points collected, adds it to the model,
   * clears the points for the next shape, and prompts the view to draw the new state.
   */
  public void generateSymbolAndDraw() {
    if (pointsToBeDrawn.size() > 1) {
      Symbol s = pointsToBeDrawn.doLogicAndAddBestFitSymbol();
      adder(s);
      pointsToBeDrawn.clear();
      gui.assignDragger(new ArrayList<>());
      gui.repaint();
    }
  }
}