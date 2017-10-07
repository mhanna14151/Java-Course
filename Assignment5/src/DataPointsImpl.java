import java.util.ArrayList;
import java.util.List;


/**
 * A class designed to implement all the DataPoints methods. This Class
 * is focused on working with Lists of Point2D(s).
 */
public class DataPointsImpl implements DataPoints {

  private List<Point2D> point2DList;

  /**
   * Constructs the initial list of points.
   */
  public DataPointsImpl() {
    point2DList = new ArrayList<Point2D>();

  }

  @Override
  public void addPoint(double x, double y) {
    point2DList.add(0, new Point2D(x, y));

  }

  @Override
  public List<Point2D> seeListOfPoints() {
    return point2DList;

  }

  /**
   * Calculates the sum of all the x-values of a list.
   *
   * @return the sum as an integer.
   */
  private double sumOfX() {
    double sum = 0;
    for (Point2D point : point2DList) {
      sum = point.getX() + sum;
    }
    return sum;
  }

  /**
   * Calculates the sum of all the y-values of a list.
   *
   * @return the sum as an integer.
   */
  private double sumOfY() {
    double sum = 0;
    for (Point2D point : point2DList) {
      sum = point.getY() + sum;
    }
    return sum;
  }

  /**
   * Calculates the sum of all the (x-values)^2 of a list.
   *
   * @return the sum as an integer.
   */
  private double sumOfXX() {
    double sum = 0;
    for (Point2D point : point2DList) {
      sum = (point.getX() * point.getX()) + sum;
    }
    return sum;
  }

  /**
   * Calculates the sum of all the x*y values of a list.
   *
   * @return the sum as an integer.
   */
  private double sumOfXY() {
    double sum = 0;
    for (Point2D point : point2DList) {
      sum = (point.getX() * point.getY()) + sum;
    }
    return sum;
  }

  /**
   * Calculates one of components necessary to calculate the slope and y intercept.
   *
   * @return the value as an integer.
   */
  private double dComponent() {
    double d = (sumOfXX() * point2DList.size()) - (sumOfX() * sumOfX());
    return d;
  }

  /**
   * Calculates one of the values necessary to find the slope of a line.
   *
   * @return the value as an integer.
   */
  private double dmComponent() {
    double dm = sumOfXY() * point2DList.size() - (sumOfX() * sumOfY());
    return dm;
  }

  /**
   * Calculates one of the values necessary to find the y intercept of a line.
   *
   * @return the value as an integer.
   */
  private double dbComponent() {
    double db = sumOfY() * sumOfXX() - (sumOfX() * sumOfXY());
    return db;
  }

  @Override
  public Line fitLine() throws IllegalArgumentException {
    if (point2DList.size() <= 1) {
      throw new IllegalArgumentException("Need at least two points to plot a line.");
    }
    double slope = dmComponent() / dComponent();
    double intercept = dbComponent() / dComponent();
    //System.out.println("DM " + dmComponent());
    //System.out.println("DB " + dbComponent());
    //System.out.println("D " + dComponent());
    Line line1 = new Line(slope, intercept);
    //System.out.println("Slope is " + slope);
    //System.out.println("Intercept is " + intercept);
    return line1;
  }

  // KMEANS SECTION
  //kmeans lists
  List<Integer> listOfInt = new ArrayList<Integer>();
  List<Point2D> tempCenterList = new ArrayList<Point2D>(); // THE LIST OF NEW CENTERS
  List<Integer> listOfCounters = new ArrayList<Integer>();
  List<Point2D> kPoints = new ArrayList<Point2D>();

  @Override
  public List<Integer> kmeans(int k) throws IllegalArgumentException {
    double error = Double.POSITIVE_INFINITY;
    //System.out.print("Error initial is: " + error);
    int attemptsLeft = 100;
    int kTemp = k;
    if (k <= 0) {
      throw new IllegalArgumentException("k must be a positive integer");
    }
    while (error > 0.01 || attemptsLeft > 0) {
      if (point2DList.size() < k) {
        throw new IllegalArgumentException("Not enough points to be assigned to clusters");
      }
      selectRandomPoints(k);
      clusterBelonging(k);
      counterList(k);
      findCenters(k);
      double newError = theNewError(k);
      double newError1 = newError;
      double resetCondition = Math.abs(newError - error) / error;
      //System.out.println("resetCondition is: " + resetCondition);
      // CHECK IF THE 0.01% CONDITION SPECIFIED BY THE INSTRUCTIONS IS MET
      if (resetCondition < 0.0001) {
        System.out.println("Success!");
        return listOfInt;
      } else {
        error = newError1;
        attemptsLeft--;
        //System.out.println("Error is this!" + error + "Attempts left are: " + attemptsLeft);
      }
    }
    return listOfInt;
  }

  /**
   * Adds randomly selected points to be used for clustering.
   *
   * @param k the amount of clusters.
   */
  private void selectRandomPoints(int k) {
    int kTemp = k;
    while (kPoints.size() != 0) {
      kPoints.remove(0);
    }
    // SELECTS THE RANDOMLY SELECTED POINTS TO BE USED FOR CLUSTERING
    while (kTemp > 0) {
      double rand = Math.random() * 100;
      double randNum = rand % (point2DList.size());
      kPoints.add(0, point2DList.get((int) randNum));
      kTemp--;
    }
  }

  /**
   * Determines the cluster each Point2D belongs to.
   *
   * @param k the k value in kmeans clustering (the amount of clusters).
   * @return the list of integers, representing each index's associated cluster.
   */
  private List<Integer> clusterBelonging(int k) {
    while (listOfInt.size() != 0) {
      listOfInt.remove(0);
    }
    // DETERMINES WHICH CLUSTER EACH POINT BELONGS TO BASED ON EUCLIDIAN DISTANCE
    for (int i = 0; i < point2DList.size(); i++) {
      double min = Double.POSITIVE_INFINITY;
      int minIndex = 0;
      for (int j = 0; j < kPoints.size(); j++) {
        if (min > point2DList.get(i).euclidianDistance(kPoints.get(j))) {
          min = point2DList.get(i).euclidianDistance(kPoints.get(j));
          minIndex = j;
        }
      }
      listOfInt.add(0, minIndex);
      //System.out.println(listOfInt);
    }
    return listOfInt;
  }

  /**
   * Adds to a list which holds the amount of points each cluster has.
   *
   * @param k the amount of clusters.
   */
  private void counterList(int k) {
    while (listOfCounters.size() != 0) {
      listOfCounters.remove(0);
    }
    // COUNTER-LIST: ADDS THE AMOUNT OF POINTS BELONGING TO EACH CLUSTER IN A LIST OF COUNTERS
    for (int a = 0; a < k; a++) {
      int count = 0;
      for (int i = 0; i < point2DList.size(); i++) {
        if (listOfInt.get(i) == a) {
          count++;
        }
      }
      listOfCounters.add(a, count);
    }
  }

  /**
   * Finds the centers of the clusters.
   *
   * @param k the value of k, in kmeans clustering (this is the amount of clusters).
   * @return a list of centers (Point2Ds).
   */
  private List<Point2D> findCenters(int k) {
    while (tempCenterList.size() != 0) {
      tempCenterList.remove(0);
    }
    for (int c = 0; c < k; c++) {
      List<Point2D> tempList = new ArrayList<Point2D>();
      // A TEMPORARY LIST IS UTILIZED, I WIPE IT CLEAN WITH EACH ITERATION
      // IN ORDER TO REUSE THE SAME LIST
      while (tempList.size() != 0) {
        tempList.remove(0);
      }
      double sumX = 0;
      double sumY = 0;
      for (int d = 0; d < point2DList.size(); d++) {
        if (listOfInt.get(d) == c) {
          tempList.add(0, new Point2D(point2DList.get(d).getX(), point2DList.get(d).getY()));
        }
      }
      for (Point2D term : tempList) {
        sumX = term.getX() + sumX;
        sumY = term.getY() + sumY;
      }
      tempCenterList.add(0, new Point2D(
              sumX / listOfCounters.get(c), sumY / listOfCounters.get(c)));
    }
    //for (Point2D term : tempCenterList) {
    //System.out.println("These are the centers: " + term.getX() + " " + term.getY());
    //}
    return tempCenterList;
  }

  /**
   * Calculates the new error
   *
   * @param k is the amount of clusters.
   * @return the newError as a double.
   */
  private double theNewError(int k) {
    double newError;
    double sumEuclid = 0;
    // CALCULATE THE NEW ERROR BY DETERMINING THE EUCLIDIAN DISTANCE BETWEEN THE NEW CENTERS
    // AND THEIR RESPECTIVE LIST OF POINTS.
    for (int c = 0; c < k; c++) {
      for (int d = 0; d < point2DList.size(); d++) {
        if (listOfInt.get(d) == c) {
          sumEuclid = tempCenterList.get(c).euclidianDistance(point2DList.get(d)) + sumEuclid;
        }
      }
    }
    newError = sumEuclid / point2DList.size();
    //System.out.println("Error is: " + newError);
    return newError;
  }

}
