import controller.Controller;
import controller.MouseDraggedClass;
import controller.MouseReleasedClass;
import controller.RealMouseClass;
import model.SymbolsBank;
import model.SymbolsBankImpl;
import view.GUI;
import view.IGUI;

/**
 * Designed to run the SymbolRecognizer Program.
 */
public class Drawing {
  /**
   * This is the main method to run the program to draw and recognize symbols.
   * @param args the arguments for the main method.  This won't be used.
   */
  public static void main(String[] args) {
    StringBuffer out = new StringBuffer();
    SymbolsBank bank = new SymbolsBankImpl();
    Controller controller = new Controller(bank);
    RealMouseClass mc = new RealMouseClass(controller,
            new MouseDraggedClass(), new MouseReleasedClass());
    IGUI gui = new GUI();
    controller.setView(gui, mc);
  }
}