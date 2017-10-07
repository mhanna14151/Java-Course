import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


/**
 * A class designed to be used in conjunction with ImagePlotter, to create a visual representation
 * of linear regression and k-means clustering on fed in files of Data.
 */
/*public class ImagePlotting {
  public static void main(String[] args) throws FileNotFoundException {
    ImagePlotter plotter = new ImagePlotter();
    plotter.setWidth(1000);
    plotter.setHeight(1000);
    plotter.setDimensions(-500,500,-500,500);
    //File inputFile = new File("linedata-1.txt");
   // Scanner sc = new Scanner("linedata-1.txt");
    Scanner sc = new Scanner(new FileInputStream("linedata-1.txt"));
    double token = sc.nextDouble();
    List<Point2D> pointList = new ArrayList<Point2D>();
    System.out.println("List is: " + pointList);
    while (sc.hasNextDouble()) {
        //pointList.add(new Point2D(sc.nextDouble(), sc.nextDouble()));
        // I thought about just putting in token, in, but then it gets stuck in an infinite loop.
        // I thought since token = sc.nextDouble(), each time it's called, it should progress through
        // the document further?
        double tokenTemp = token;
        token = sc.nextDouble();
        Point2D point1 = new Point2D(tokenTemp, token);
        System.out.println(point1.getX() + " " + point1.getY());
        pointList.add(point1);
        if (sc.hasNextDouble()) {
          token = sc.nextDouble();
        }
    }
    System.out.println("List is: " + pointList);
    System.out.println("List size is: " + pointList.size());


    /*for (int i = 0; i < pointList.size(); i++) {
      System.out.println((int) pointList.get(i).getX() + " " + (int) pointList.get(i).getY());
      plotter.addPoint((int) pointList.get(i).getX(), (int) pointList.get(i).getY(), Color.black);
    }*/

    /*for (int x = -200; x < 200; x += 20) {
      for (int y = 0; y <= x; y += 20) {
        plotter.addPoint(100, 140, Color.BLACK);

      }
    }

    try {
      plotter.write("HopeFulsssal"); //Just need to ask nicely, right?
    } catch (IOException e) {
    }
  }
}*/