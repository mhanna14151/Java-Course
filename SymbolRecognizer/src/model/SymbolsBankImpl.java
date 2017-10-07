package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the methods mentioned in the model.SymbolsBank Interface. It is used to
 * store and report any added Symbols.
 */
public class SymbolsBankImpl implements SymbolsBank {

  protected List<Symbol> lineList;
  protected List<Symbol> circleList;
  protected List<Symbol> snowpersonList;
  protected List<Symbol> triangleList;
  protected List<Symbol> eqTriangleList;
  protected List<Symbol> rectangleList;
  protected List<Symbol> hallowList;
  protected List<Symbol> pendulumList;

  /**
   * Constructs the model.SymbolsBankImpl, which is where all the symbols are being stored. The
   * symbols are sorted into their respective lists to eliminate the need to later sort them for
   * purposes of interaction.
   */
  public SymbolsBankImpl() {
    lineList = new ArrayList<>();
    circleList = new ArrayList<>();
    snowpersonList = new ArrayList<>();
    triangleList = new ArrayList<>();
    eqTriangleList = new ArrayList<>();
    rectangleList = new ArrayList<>();
    hallowList = new ArrayList<>();
    pendulumList = new ArrayList<>();
  }

  @Override
  public void addSymbol(Symbol aSymbol) {
    if (aSymbol instanceof BasicSymbol) {
      if (aSymbol instanceof Line) {
        lineList.add(0, aSymbol);
        tryToMakeLineComposites();
      } else if (aSymbol instanceof Circle) {
        circleList.add(0, aSymbol);
        tryToMakeCircleComposites();
      }
    } else if (aSymbol instanceof CompositeSymbol) {
      throw new IllegalArgumentException("Cannot add a CompositeSymbol directly to the bank." +
              "They must be made by adding a BasicSymbols");
    }
  }

  /**
   * Tries to make any Composite Symbol that can be made with the addition of a model.Line.
   */
  protected void tryToMakeLineComposites() {
    tryToMakeTriangle();
    tryToMakeRectangle();
    tryToMakeHallow();
    tryToMakePendulum();
  }

  /**
   * Tries to make any Composite model.Symbol that can be made with the addition of a model.Circle.
   */
  protected void tryToMakeCircleComposites() {

    tryToMakeSnowPerson();
    tryToMakeHallow();
    tryToMakePendulum();
  }

  /**
   * Tries to make a pendulum symbol.  If it can, it makes it, adds it to the list, and removes
   * the respective component parts.
   */
  protected void tryToMakePendulum() {
    boolean canMakePendulum;
    Rectangle rectangle;
    EquilateralTriangle equilateralTriangle;
    Line line;
    Circle circle;

    if ((lineList.size() > 0) && (eqTriangleList.size() > 0) && (circleList.size() > 0)
            && rectangleList.size() > 0) {
      rectangle = (Rectangle) rectangleList.get(0);
      equilateralTriangle = (EquilateralTriangle) eqTriangleList.get(0);
      line = (Line) lineList.get(0);
      circle = (Circle) circleList.get(0);

      canMakePendulum = Pendulum.canMakePendulum(rectangle, equilateralTriangle, line, circle);

      if (canMakePendulum) {
        Pendulum pendulum = new Pendulum(rectangle, equilateralTriangle, line, circle);
        pendulumList.add(pendulum);
        rectangleList.remove(0);
        eqTriangleList.remove(0);
        lineList.remove(0);
        circleList.remove(0);
      }
    }
  }

  /**
   * Tries to make a Hallow.  If it can, it makes it, adds it to the list, and removes the
   * components from their respective lists.
   */
  protected void tryToMakeHallow() {
    boolean canMakeHallow;
    Circle circle;
    EquilateralTriangle eqTriangle;
    Line line;

    if ((lineList.size() > 0) && (eqTriangleList.size() > 0) && (circleList.size() > 0)) {
      circle = (Circle) circleList.get(0);
      eqTriangle = (EquilateralTriangle) eqTriangleList.get(0);
      line = (Line) lineList.get(0);

      canMakeHallow = Hallow.canMakeHallow(line, eqTriangle, circle);

      if (canMakeHallow) {
        Hallow hallow = new Hallow(line, eqTriangle, circle);
        hallowList.add(hallow);
        circleList.remove(0);
        eqTriangleList.remove(0);
        lineList.remove(0);
      }
    }


  }

  /**
   * Attempts to make a model.SnowPerson, if one is made, add it to the list of SnowPersons.
   */
  protected void tryToMakeSnowPerson() {
    boolean canMakeSnowPerson;
    if (circleList.size() >= 3) {
      Circle circleOne = (Circle) circleList.get(0);
      Circle circleTwo = (Circle) circleList.get(1);
      Circle circleThree = (Circle) circleList.get(2);

      canMakeSnowPerson = SnowPerson.canMakeSnowPerson(circleOne, circleTwo, circleThree);
      if (canMakeSnowPerson) {
        SnowPerson newSP = new SnowPerson(circleOne, circleTwo, circleThree);
        snowpersonList.add(0, newSP);
        circleList.remove(0);
        circleList.remove(0);
        circleList.remove(0);
      }
    }
  }

  /**
   * Tries to make a model.Triangle, if one is made, add it it's corresponding type of
   * model.Triangle. Checks for both Equilateral and non-equilateral triangles.
   */
  protected void tryToMakeTriangle() {
    boolean canMakeTriangle;
    boolean canMakeEqTriangle;
    if (lineList.size() >= 3) {
      Line lineOne = (Line) lineList.get(0);
      Line lineTwo = (Line) lineList.get(1);
      Line lineThree = (Line) lineList.get(2);

      canMakeTriangle = Triangle.canMakeTriangle(lineOne, lineTwo, lineThree);
      if (canMakeTriangle) {
        canMakeEqTriangle = EquilateralTriangle.canMakeEquilateralTriangle(lineOne,
                lineTwo, lineThree);
        //loSystem.out.println("trying to make eq and the result is: " + canMakeEqTriangle);
        if (canMakeEqTriangle) {
          EquilateralTriangle newEqTriangle = new EquilateralTriangle(lineOne,
                  lineTwo, lineThree);
          eqTriangleList.add(0, newEqTriangle);
        } else {
          Triangle newTriangle = new Triangle(lineOne, lineTwo, lineThree);
          triangleList.add(0, newTriangle);
        }
        lineList.remove(0);
        lineList.remove(0);
        lineList.remove(0);
      }
    }
  }

  protected void tryToMakeRectangle() {
    boolean canMakeRectangle;
    if (lineList.size() >= 4) {
      Line lineOne = (Line) lineList.get(0);
      Line lineTwo = (Line) lineList.get(1);
      Line lineThree = (Line) lineList.get(2);
      Line lineFour = (Line) lineList.get(3);

      canMakeRectangle = Rectangle.canMakeRectangle(lineOne, lineTwo, lineThree, lineFour);
      if (canMakeRectangle) {
        Rectangle newRectangle = new Rectangle(lineOne, lineTwo, lineThree, lineFour);
        rectangleList.add(0, newRectangle);
        lineList.remove(0);
        lineList.remove(0);
        lineList.remove(0);
        lineList.remove(0);
      }
    }
  }

  /**
   * Reports back the list of symbols added thus far.
   *
   * @return reports back the List of Symbols, sorted by their type into their respective lists.
   */
  public List<Symbol> symbolsReport() {
    List<Symbol> result;
    result = new ArrayList<>();
    result.addAll(lineList);
    result.addAll(circleList);
    result.addAll(triangleList);
    result.addAll(eqTriangleList);
    result.addAll(snowpersonList);
    result.addAll(rectangleList);
    result.addAll(hallowList);
    result.addAll(pendulumList);
    return result;
  }


  /**
   * Reports back a copy of the basicSymbols contained in the model.SymbolsBank.
   *
   * @return a list of symbols containing the basicSymbols.
   */
  public List<Symbol> basicSymbolsReport() {
    List<Symbol> result;
    result = new ArrayList<>();
    result.addAll(lineList);
    result.addAll(circleList);
    return result;
  }

  /**
   * Reports back a copy of the compositeSymbols contained in the model.SymbolsBank.
   *
   * @return a list of symbols containing the compositeSymbols.
   */
  public List<Symbol> compositeSymbolsReport() {
    List<Symbol> result;
    result = new ArrayList<>();
    result.addAll(triangleList);
    result.addAll(eqTriangleList);
    result.addAll(snowpersonList);
    result.addAll(rectangleList);
    result.addAll(hallowList);
    result.addAll(pendulumList);
    return result;

  }

}