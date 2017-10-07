import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * A Class Designed to visually represent the fitLine and kMeans. It requires that a .txt data file
 * be streamed into it.
 */
public class DrawFitLineAndClusters {

  /**
   * The main function designed to output data.
   *
   * @param args reading the code.
   */
  public static void main(String[] args) throws Exception {
    ImagePlotter linePlotter = new ImagePlotter();
    ImagePlotter klusterPlotter = new ImagePlotter();
    linePlotter.setWidth(500);
    linePlotter.setHeight(500);
    linePlotter.setDimensions(-500, 500, -500, 500);
    klusterPlotter.setWidth(500);
    klusterPlotter.setHeight(500);
    klusterPlotter.setDimensions(-500, 500, -500, 500);
    Scanner sc = new Scanner(new FileInputStream("linedata-1.txt"));
    double token = sc.nextDouble();
    DataPointsImpl pointList1 = new DataPointsImpl();
    List<Point2D> pointList = new ArrayList<Point2D>();
    //System.out.println("List is: " + pointList);
    while (sc.hasNextDouble()) {
      double tokenTemp = token;
      token = sc.nextDouble();
      Point2D point1 = new Point2D(tokenTemp, token);
      //System.out.println(point1.getX() + " " + point1.getY());
      pointList.add(point1);
      if (sc.hasNextDouble()) {
        token = sc.nextDouble();
      }
    }
    for (int i = 0; i < pointList.size(); i++) {
      //System.out.println((int) pointList.get(i).getX() + " " + (int) pointList.get(i).getY());
      linePlotter.addPoint((int) pointList.get(i).getX(),
              (int) pointList.get(i).getY(), Color.BLACK);
    }
    for (int i = 0; i < pointList.size(); i++) {
      pointList1.addPoint(pointList.get(i).getX(), pointList.get(i).getY());
    }
    Line line1 = pointList1.fitLine();
    double y1 = -500 * line1.getM() + line1.getB();
    double y2 = 500 * line1.getM() + line1.getB();
    linePlotter.addLine(-500, (int) y1, 500, (int) y2);
    Scanner scC = new Scanner(new FileInputStream("clusterdata-2.txt"));
    double tokenCluster = scC.nextDouble();
    DataPointsImpl pointListD = new DataPointsImpl();
    List<Point2D> pointListC = new ArrayList<Point2D>();
    while (scC.hasNextDouble()) {
      double tokenTemp2 = tokenCluster;
      tokenCluster = scC.nextDouble();
      Point2D point2 = new Point2D(tokenTemp2, tokenCluster);
      //System.out.println(point2.getX() + " " + point2.getY());
      pointListC.add(0, point2); // remove index 0 if need be
      if (scC.hasNextDouble()) {
        tokenCluster = scC.nextDouble();
      }
    }
    for (int i = 0; i < pointListC.size(); i++) {
      pointListD.addPoint(pointListC.get(i).getX(), pointListC.get(i).getY());
    }
    List<Integer> listofInt1 = pointListD.kmeans(2);
    for (int i = 0; i < pointListC.size(); i++) {
      if (listofInt1.get(i) == 0) {
        klusterPlotter.addPoint(
                (int) pointListC.get(i).getX(), (int) pointListC.get(i).getY(), Color.red);
      }
      if (listofInt1.get(i) == 1) {
        klusterPlotter.addPoint(
                (int) pointListC.get(i).getX(), (int) pointListC.get(i).getY(), Color.green);
      }
      if (listofInt1.get(i) == 2) {
        klusterPlotter.addPoint(
                (int) pointListC.get(i).getX(), (int) pointListC.get(i).getY(), Color.blue);
      }
      if (listofInt1.get(i) == 3) {
        klusterPlotter.addPoint(
                (int) pointListC.get(i).getX(), (int) pointListC.get(i).getY(), Color.black);
      }
      if (listofInt1.get(i) == 4) {
        klusterPlotter.addPoint(
                (int) pointListC.get(i).getX(), (int) pointListC.get(i).getY(), Color.yellow);
      }
      if (listofInt1.get(i) == 5) {
        klusterPlotter.addPoint(
                (int) pointListC.get(i).getX(), (int) pointListC.get(i).getY(), Color.orange);
      }
    }
    try {
      linePlotter.write("This is the output line.png");
      klusterPlotter.write("This is the output kluster.png");
    } catch (IOException e) {
      //No action necessary.

    }
  }
}
