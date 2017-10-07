package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Point2D;

/**
 * This class is a custom implementation of a MouseListener.  Two methods are overridden to
 * call function object methods that are given when constructed.  These function objects given
 * at construction (IMouseFxObj mFxDrag and mFxRelease) are specific implementations of what should
 * be done when a mouse is dragged and released, respectively.
 */
public class RealMouseClass extends MouseAdapter {
  protected Controller c;
  protected IMouseFxObj mFxDrag;
  protected IMouseFxObj mFxRelease;

  /**
   * This constructor takes in the two function objects, described in the class documentation,
   * that are specific implementations of what should be done when a mouse is dragged and released.
   * A controller is also passed to this so that its methods can be called by the aforementioned
   * function objects.
   *
   * @param c          the controller of the program.
   * @param mFxDrag    the specific implementation of what will ultimately be done when a mouse is
   *                   dragged.
   * @param mFxRelease the specific implementation of what will ultimately be done whhen a mouse is
   *                   released.
   */
  public RealMouseClass(Controller c, IMouseFxObj mFxDrag, IMouseFxObj mFxRelease) {
    this.c = c;
    this.mFxDrag = mFxDrag;
    this.mFxRelease = mFxRelease;
  }

  /**
   * Executes the MouseEvent when the mouse is released.
   *
   * @param e a MouseEvent.
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    this.mFxRelease.mouse(new Point2D(e.getX(), e.getY()), c);
  }

  /**
   * Executes the MouseEvent while the mouse is being dragged.
   *
   * @param e a MouseEvent.
   */
  @Override
  public void mouseDragged(MouseEvent e) {
    this.mFxDrag.mouse(new Point2D(e.getX(), e.getY()), c);
  }

}