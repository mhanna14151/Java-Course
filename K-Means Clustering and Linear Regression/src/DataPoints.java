import java.util.List;

/**
 * An interface designed to contain the necessary methods for DataPoints.
 */
public interface DataPoints {

  /**
   * Adds a new Point2D to the list of datapoints.
   *
   * @param x the x value of the point.
   * @param y the y value of the point.
   */
  public void addPoint(double x, double y);

  /**
   * Returns the current list of points.
   *
   * @return the list of Point2D.
   */
  public List<Point2D> seeListOfPoints();

  /**
   * Calculates the kmeans clustering of a list of Point2Ds.
   *
   * @param k is the amount of clusters.
   * @return a list of integers, representing the associated cluster.
   * @throws Exception if no cluster could be found after 100 attempts.
   */
  public List<Integer> kmeans(int k) throws Exception;

  /**
   * Returns the best-fit line found through the process of linear regression.
   *
   * @return a Line representing the best-fit.
   */
  public Line fitLine() throws IllegalArgumentException;

}
