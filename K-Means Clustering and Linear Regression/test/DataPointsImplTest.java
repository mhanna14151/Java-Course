import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * DataPointsImplTest is a class designed to test the methods of DataPointsImpl.
 */
public class DataPointsImplTest {

  private DataPoints emptyList;
  private DataPoints list1;
  private DataPoints list2;
  private DataPoints list3;
  private DataPoints list4;
  private DataPoints list5;
  private DataPoints list6;
  private DataPoints list7;
  private DataPoints list8;

  /**
   * Sets up the class for testing.
   */
  @Before
  public void setup() {
    emptyList = new DataPointsImpl();
    list1 = new DataPointsImpl();
    list2 = new DataPointsImpl();
    list3 = new DataPointsImpl();
    list4 = new DataPointsImpl();
    list5 = new DataPointsImpl();
    list6 = new DataPointsImpl();
    list7 = new DataPointsImpl();
    list5.addPoint(0, 0);
    list5.addPoint(1, 1);
    list5.addPoint(2, 2);
    list5.addPoint(3, 3);
    list4.addPoint(4, 5);
    list4.addPoint(2, 3);
    list4.addPoint(-3, 10);
    list4.addPoint(0, 0);
    list4.addPoint(5, -50);
    list4.addPoint(-40, -51);
    list4.addPoint(-22, -43);
    list4.addPoint(-113, -110);
    list4.addPoint(0, 0);
    list4.addPoint(5, -50);
    list7.addPoint(4, 5);
    list7.addPoint(2, 3);
    list7.addPoint(-3, 10);
    list7.addPoint(0, 0);
    list7.addPoint(5, -50);
    list7.addPoint(-40, -51);
    list7.addPoint(-22, -43);
    list7.addPoint(-113, -110);
    list7.addPoint(0, 0);
    list7.addPoint(5, -50);
    for (int i = 0; i < 40; i++) {
      list7.addPoint(10000000, 10000000);
    }
    for (int i = 0; i < 40; i++) {
      list7.addPoint(-10000000, -10000000);
    }
    for (int i = 0; i < 40; i++) {
      list7.addPoint(0, 0);
    }

  }

  @Test
  public void addPoint() {
    assertEquals(list1.seeListOfPoints().size(), 0);
    list1.addPoint(4, 5);
    assertEquals(list1.seeListOfPoints().get(0).getX(), 4, 0.01);
    assertEquals(list1.seeListOfPoints().get(0).getY(), 5, 0.01);
    assertEquals(list1.seeListOfPoints().size(), 1, 0.01);
    list1.addPoint(-2, -3.0);
    assertEquals(list1.seeListOfPoints().size(), 2, 0.01);
    assertEquals(list1.seeListOfPoints().get(0).getX(), -2, 0.01);
    assertEquals(list1.seeListOfPoints().get(0).getY(), -3, 0.01);
    assertEquals(list1.seeListOfPoints().get(1).getX(), 4, 0.01);
    assertEquals(list1.seeListOfPoints().get(1).getY(), 5, 0.01);

  }

  @Test //This tests both addPoint and seeListOfPoints;
  public void seeListOfPoints() {
    list1.addPoint(4, 5);
    list1.addPoint(2, 3);
    list1.addPoint(-3, 10);
    list1.addPoint(0, 0);
    list1.addPoint(5, -5);
    List<Point2D> listP2 = new LinkedList<>();
    listP2.add(0, new Point2D(4, 5));
    listP2.add(0, new Point2D(2, 3));
    listP2.add(0, new Point2D(-3, 10));
    listP2.add(0, new Point2D(0, 0));
    listP2.add(0, new Point2D(5, -5));
    List<Point2D> listP = list1.seeListOfPoints();
    List<Point2D> list2P = list2.seeListOfPoints();
    List<Point2D> list3P = list3.seeListOfPoints();
    List<Point2D> list4P = list4.seeListOfPoints();
    list4P.add(0, new Point2D(4, 5));
    list4P.add(0, new Point2D(2, 3));
    list4P.add(0, new Point2D(-3, 10));
    list4P.add(0, new Point2D(0, 0));
    for (int i = 0; i < listP.size(); i++) {
      assertEquals(listP.get(i), listP2.get(i));
    }
    // Checks if two lists are rightfully equal.
    assertTrue(listP.equals(listP2));
    // Checks if a list is not the same as an empty list.
    assertFalse(list2P.equals(listP));
    // checks if two empty lists are the same.
    assertTrue(list2P.equals(list3P));
    // checks if two lists, which contain all the same elements, but one has one extra point.
    assertFalse(listP.equals(list4P));
    assertFalse(list4P.equals(listP));

  }

  @Test
  public void testFitLine() {
    //LIST 3
    list3.addPoint(10, 0);
    list3.addPoint(8, 2);
    list3.addPoint(6, 4);
    list3.addPoint(4, 6);
    list3.addPoint(2, 8);
    //LIST 6
    list6.addPoint(5, 0);
    list6.addPoint(10, 1);
    list6.addPoint(15, 2);
    list6.addPoint(20, 3);
    list6.addPoint(25, 4);
    list6.addPoint(30, 5);
    //LIST 2
    list2.addPoint(-1, -10);
    list2.addPoint(1, 0);
    list2.addPoint(2, 5);
    list2.addPoint(3, 10);
    list2.addPoint(4, 15);
    list2.addPoint(5, 20);
    emptyList.addPoint(5, 5);
    emptyList.addPoint(6, 5);
    emptyList.addPoint(-5, 5);

    // positive slope
    assertEquals(new Line(1.0, 0), list5.fitLine());
    // negative slope
    assertEquals(new Line(-1.0, 10), list3.fitLine());
    // non-integer slope, non-zero intercept
    assertEquals(new Line(.2, -1), list6.fitLine());
    // greater than 1 slope
    assertEquals(new Line(5, -5), list2.fitLine());
    // Slope 0
    assertEquals(new Line(0, 5), emptyList.fitLine());
    // Professor said to ignore the other case, where Slope is a vertical line.
    //TEST WOULD GO HERE OTHERWISE.

  }

  // Only one point: no line can be drawn.
  @Test(expected = IllegalArgumentException.class)
  public void testFitLineException() {
    emptyList.addPoint(1, 1);
    emptyList.fitLine();
  }

  // No points: no line can be drawn.
  @Test(expected = IllegalArgumentException.class)
  public void testFitLineException2() {
    emptyList.fitLine();
  }

  @Test
  public void testkmeans() throws Exception {
    // Check if size is equal"
    assertEquals(list4.seeListOfPoints().size(), list4.kmeans(2).size());
    boolean testTheIntegers = true;
    List<Integer> listInt = list4.kmeans(2);
    for (int i = 0; i < listInt.size(); i++) {
      if (listInt.get(i) == 0 || listInt.get(i) == 1) {
        System.out.println("True" + " " + listInt.get(i));
      } else {
        testTheIntegers = false;
      }
    }
    // Tests if the Integers are only either 0 or 1
    assertTrue(testTheIntegers);
    /*List<Integer> listInt1 = list4.kmeans(3);
    for (int i = 0; i < listInt.size(); i ++) {
      if (listInt1.get(i) == 0 || listInt1.get(i) == 1
              || listInt1.get(i) == 2) {
        //System.out.println("True");
      } else {
        testTheIntegers = false;
      }
    }
    //Tests if the Integers are either 0, 1, or 2.
    assertTrue(testTheIntegers);*/
    List<Point2D> listofCenters = new ArrayList<Point2D>();
    while (listofCenters.size() != 0) {
      listofCenters.remove(0);
    }
    List<Integer> listofCounters = new ArrayList<Integer>();
    while (listofCounters.size() != 0) {
      listofCounters.remove(0);
    }
    // COUNTER-LIST: ADDS THE AMOUNT OF POINTS BELONGING TO EACH CLUSTER IN A LIST OF COUNTERS
    for (int a = 0; a < 2; a++) {
      int count = 0;
      for (int i = 0; i < list4.seeListOfPoints().size(); i++) {
        if (listInt.get(i) == a) {
          count++;
        }
      }
      listofCounters.add(a, count);
    }
    for (int c = 0; c < 2; c++) {
      List<Point2D> tempList = new ArrayList<Point2D>();
      while (tempList.size() != 0) {
        tempList.remove(0);
      }
      double sumX = 0;
      double sumY = 0;
      for (int d = 0; d < listInt.size(); d++) {
        if (listInt.get(d) == c) {
          tempList.add(0, new Point2D(list4.seeListOfPoints().get(d).getX(),
                  list4.seeListOfPoints().get(d).getY()));
        }
      }
      for (Point2D term : tempList) {
        sumX = term.getX() + sumX;
        sumY = term.getY() + sumY;
      }
      listofCenters.add(0, new Point2D(sumX /
              listofCounters.get(c), sumY / listofCounters.get(c)));
    }
    List<Integer> compareList = new ArrayList<Integer>();
    while (compareList.size() != 0) {
      compareList.remove(0);
    }
    int size = listInt.size() - 1;
    for (int i = 0; i < listInt.size(); i++) {
      int place = size - i;
      double min = Double.POSITIVE_INFINITY;
      int minIndex = 0;
      for (int j = 0; j < listofCenters.size(); j++) {
        if (min > list4.seeListOfPoints().get(i).euclidianDistance(listofCenters.get(j))) {
          min = list4.seeListOfPoints().get(i).euclidianDistance(listofCenters.get(j));
          minIndex = j;
        }
      }
      compareList.add(i, minIndex);
      //System.out.println("listInt: " +
      // listInt.get(size - i) + " minIndex is: " + compareList.get(i));
    }

    assertEquals(listInt.get(9), compareList.get(0));
    assertEquals(listInt.get(8), compareList.get(1));
    assertEquals(listInt.get(7), compareList.get(2));
    assertEquals(listInt.get(6), compareList.get(3));
    assertEquals(listInt.get(5), compareList.get(4));
    assertEquals(listInt.get(4), compareList.get(5));
    assertEquals(listInt.get(3), compareList.get(6));
    assertEquals(listInt.get(2), compareList.get(7));
    assertEquals(listInt.get(1), compareList.get(8));
    assertEquals(listInt.get(0), compareList.get(9));

    boolean allSameGroup1 = true;
    boolean allSameGroup2 = true;
    boolean allSameGroup3 = true;
    List<Integer> kMeansTester = list7.kmeans(3);
    for (int term = 0; term < kMeansTester.size(); term++) {
      System.out.println("Point # " + term + " Cluster Number is: " + kMeansTester.get(term));
    }
    for (int d = 0; d < 115; d++) {
      int d1 = kMeansTester.get(d + 1);
      if (10 < d && d < 15) {
        if (kMeansTester.get(d) == d1) {
          //nothing happens
        } else {
          allSameGroup1 = false;
        }
      }
      if (d > 60 && d < 65) {
        if (kMeansTester.get(d) == d1) {
          //nothing happens, remains true
        } else {
          allSameGroup2 = false;
        }
      }
      if (d > 110 && d < 115) {
        if (kMeansTester.get(d) == d1) {
          //nothing happens, remains true
        } else {
          allSameGroup3 = false;
        }
      } else {
        //nothing
      }
    }
    // TESTS IF NEARBY POINTS ARE PLOTTED TO THE SAME CLUSTER
    assertTrue(allSameGroup1);
    assertTrue(allSameGroup2);
    assertTrue(allSameGroup3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionKmeans() throws Exception {
    list4.kmeans(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionKmeans1() throws Exception {
    list4.kmeans(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionKmeans2() throws Exception {
    list4.kmeans(11);
  }

}