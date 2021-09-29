package ch.welld.patternrecognition.Services;

import ch.welld.patternrecognition.Models.Point;
import ch.welld.patternrecognition.Models.Response;

import java.util.List;
import java.util.Set;

public interface SpaceService {

    /**
     * This method add in new point to the space.
     * <br/> It does not accept duplicates and return the appropriate message if a point had been added or declined.
     * @param newPoint Point to be added to the space.
     * @return Response to tell if it was added or not as well as the point that was acted upon.
     */
    Response addPoint(Point newPoint);


    /**
     * This method clears the space by deleting all points from the space.
     */
    void clearAll();


    /**
     * This method does not take in any input.
     * @return all the points currently in the space
     */
    Set<Point> getAllPoints();


    /**
     * This method get all lines which have at least N number of points in the space then returns the set of their points
     * @param numberOfPoints minimum number of points a line should have.
     * @return Set of points in the lines.
     */
    List<Set<Point>> getAllPointsFromLinesByNumberOfPoints(int numberOfPoints);
}
