package view;

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.RealMouseClass;
import model.Circle;
import model.EquilateralTriangle;
import model.Hallow;
import model.Line;
import model.Pendulum;
import model.Point2D;
import model.Rectangle;
import model.SnowPerson;
import model.Symbol;
import model.Triangle;


/**
 * This class represents the view which will be used by to both display the Symbols and allow for
 * the user to input data to be passed through the controller back (e.g. the drawn line in the
 * JPanel).
 */
public class GUI extends JFrame implements IGUI {
  private List<Symbol> modelList;
  private JPanel myPanel;
  private List<Point2D> pointsWhileDragging;
  private Point2D start;
  private Point2D end;

  /**
   * The Constructor for GUI. This sets JFrame attribues, initializes some object-specific
   * fields (even though some may be overwritten in later methods), creates a Panel and connects
   * it to the JFrame.
   */
  public GUI() {
    super("Symbol Recognizer");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500, 500);
    start = null;
    end = null;
    pointsWhileDragging = new ArrayList<>();
    myPanel = new MyPanel();
    add(myPanel, BorderLayout.CENTER);
    modelList = new ArrayList<>();
    this.setVisible(true);
  }


  /**
   * Creates the list of points to be drawn. This function is used by the controller to pass
   * the data list of points accrued by the Mouse to the view (to be drawn).
   *
   * @param drags a list of points that were collected from dragging the mouse.
   */
  public void assignDragger(List<Point2D> drags) {
    pointsWhileDragging = drags;
  }

  /**
   * Assigns the MouseMotion and MouseMotionListeners to the JPanel.
   *
   * @param mc a RealMouseClass.
   */
  public void assignMouseListener(RealMouseClass mc) {
    myPanel.addMouseListener(mc);
    myPanel.addMouseMotionListener(mc);
  }

  /**
   * Receives the symbols, utilized to access the symbols from the Controller. This assigns
   * the list of symbols received to the local object-specific list, that will be later drawn
   * int he JFrame/JPanel.
   *
   * @param listSymbol a list of symbols that the controller obtained from the model.
   */
  public void receiveSymbols(List<Symbol> listSymbol) {
    modelList = listSymbol;
  }


  /**
   * A class representing the JPanel used in JFrame.
   */
  class MyPanel extends JPanel {

    /**
     * This is the constructor for MyPanel. It only initializes the JPanel's constructor and no
     * additional fields.
     */
    public MyPanel() {
      super();
    }

    /**
     * Draws all the Symbols and the user's drawn line to the graphic. This also draws
     * while the user is dragging and will disappear after the user has released the mouse.
     *
     * @param g a graphic.
     */
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      //For each symbol, draw it.
      for (Symbol s : modelList) {
        drawSymbol(s, g);
      }

      //Used for drawing while dragging.
      for (int i = 0; i < pointsWhileDragging.size() - 1; i++) {
        if (i == 0) {
          start = pointsWhileDragging.get(i);
          end = pointsWhileDragging.get(i);
        } else {
          start = end;
          end = pointsWhileDragging.get(i + 1);
          g.drawLine((int) start.getX(), (int) start.getY(),
                  (int) end.getX(), (int) end.getY());
        }
      }

    }

    /**
     * A helper function to draw any type of Symbol.
     *
     * @param s a Symbol.
     * @param g a graphic.
     */
    private void drawSymbol(Symbol s, Graphics g) {
      if (s instanceof Circle) {
        drawCircle((Circle) s, g);
        g.drawString("Circle", (int) ((Circle) s).getOrigin().getX(),
                (int) ((Circle) s).getOrigin().getY());
      } else if (s instanceof Line) {
        drawLine((Line) s, g);
        g.drawString("Line", (int) ((Line) s).getEnd().getX(),
                (int) ((Line) s).getEnd().getY());
      } else if (s instanceof SnowPerson) {
        drawSnowPerson((SnowPerson) s, g);
        SnowPerson snow = (SnowPerson) s;
        List<Circle> circles;
        circles = snow.getCircles();
        g.drawString("SnowPerson", (int) circles.get(0).getOrigin().getX(), (int)
                circles.get(0).getOrigin().getY());
      } else if (s instanceof Triangle) {
        drawTriangle(s, g);
        if (s instanceof EquilateralTriangle) {
          int x = (int) ((EquilateralTriangle) s).getLines().get(0).getStart().getX();
          int y = (int) ((EquilateralTriangle) s).getLines().get(0).getStart().getY();
          g.drawString("Equilateral Triangle", x, y);
        } else {
          int x = (int) ((Triangle) s).getLines().get(0).getStart().getX();
          int y = (int) ((Triangle) s).getLines().get(0).getStart().getY();
          g.drawString("Triangle", x, y);
        }
      } else if (s instanceof Rectangle) {
        drawRectangle(s, g);
        int x = (int) ((Rectangle) s).getLines().get(0).getStart().getX();
        int y = (int) ((Rectangle) s).getLines().get(0).getStart().getY();
        g.drawString("Rectangle", x, y);
      } else if (s instanceof Hallow) {
        drawHallow(s, g);
      } else if (s instanceof Pendulum) {
        drawPendulum(s, g);
      }
    }

    /**
     * A helper function to draw a visual representation of a Circle.
     *
     * @param aCircle a Circle.
     * @param g       a graphic.
     */
    private void drawCircle(Circle aCircle, Graphics g) {
      int cirRadius = (int) aCircle.getRadius();
      int cirDiameter = cirRadius * 2;
      Point2D cirOrigin = aCircle.getOrigin();
      int x = (int) cirOrigin.getX();
      int y = (int) cirOrigin.getY();
      g.drawOval(x - cirRadius, y - cirRadius, cirDiameter, cirDiameter);
    }

    /**
     * A helper function to draw a visual representation of a Line.
     *
     * @param aLine a Line.
     * @param g     a graphic.
     */
    private void drawLine(Line aLine, Graphics g) {
      Point2D startPoint = aLine.getStart();
      Point2D endPoint = aLine.getEnd();
      g.drawLine((int) startPoint.getX(), (int) startPoint.getY(),
              (int) endPoint.getX(), (int) endPoint.getY());
    }

    /**
     * A helper function designed to draw a SnowPerson.
     *
     * @param s a Symbol (only should be called when a SnowPerson).
     * @param g a graphic.
     */
    private void drawSnowPerson(SnowPerson s, Graphics g) {
      List<Circle> circles;
      circles = s.getCircles();
      for (Circle c : circles) {
        drawCircle(c, g);
      }
    }

    /**
     * A helper function made to draw equilateral and non-equilateral triangles..
     *
     * @param s a Symbol (should only be called for Triangle Symbols).
     * @param g a graphic.
     */
    private void drawTriangle(Symbol s, Graphics g) {
      Triangle t = (Triangle) s;
      List<Line> triangles;
      triangles = t.getLines();
      for (Line l : triangles) {
        drawLine(l, g);
      }


    }

    private void drawRectangle(Symbol s, Graphics g) {
      Rectangle rectangle = (Rectangle) s;
      List<Line> rectanglesLines;
      rectanglesLines = rectangle.getLines();
      for (Line l : rectanglesLines) {
        drawLine(l, g);
      }
    }

    private void drawPendulum(Symbol s, Graphics g) {
      Pendulum p = (Pendulum) s;
      List<Symbol> pendulumComponents;
      pendulumComponents = p.getComponents();
      int x = 0;
      int y = 0;

      for (Symbol symbol : pendulumComponents) {
        if (symbol instanceof Line) {
          drawLine((Line) symbol, g);
        } else if (symbol instanceof Triangle) {
          drawTriangle(symbol, g);
        } else if (symbol instanceof Circle) {
          drawCircle((Circle) symbol, g);
          x = (int) ((Circle) symbol).getOrigin().getX();
          y = (int) ((Circle) symbol).getOrigin().getY();
        } else if (symbol instanceof Rectangle) {
          drawRectangle(symbol, g);
        }
      }
      g.drawString("Pendulum", x, y);
    }

    private void drawHallow(Symbol s, Graphics g) {
      Hallow h = (Hallow) s;
      List<Symbol> hallowComponents;
      hallowComponents = h.getComponents();
      int x = 0;
      int y = 0;

      for (Symbol symbol : hallowComponents) {
        if (symbol instanceof Line) {
          drawLine((Line) symbol, g);
        } else if (symbol instanceof Triangle) {
          drawTriangle(symbol, g);
        } else if (symbol instanceof Circle) {
          drawCircle((Circle) symbol, g);
          x = (int) ((Circle) symbol).getOrigin().getX();
          y = (int) ((Circle) symbol).getOrigin().getY();
        }
      }
      g.drawString("Hallow", x, y);


    }
  }
}