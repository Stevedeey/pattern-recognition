package ch.welld.patternrecognition.Services.ServiceImplementations;

import ch.welld.patternrecognition.Models.Line;
import ch.welld.patternrecognition.Models.Point;
import ch.welld.patternrecognition.Models.Response;
import ch.welld.patternrecognition.Services.SpaceService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpaceServiceImpl implements SpaceService {

    private static Map<Line, Set<Point>> linesAndPoints = new HashMap<>();
    private static Set<Point> pointsSet = new TreeSet<>();

    /**
     * This method add in new point to the space.
     * <br/> It does not accept duplicates and return the appropriate message if a point had been added or declined.
     * @param newPoint Point to be added to the space.
     * @return Response to tell if it was added or not as well as the point that was acted upon.
     */
    @Override
    public Response addPoint(Point newPoint) {

        if(pointsSet.isEmpty()) {
            pointsSet.add(newPoint);
            return new Response("Point has been added to space", newPoint);
        }

        if(pointsSet.contains(newPoint)){
            return new Response("Point had been previously added to space", newPoint);
        }

        for (Point point : pointsSet){
            Line line = new Line(point, newPoint);

            Set<Point> set;
            if(!linesAndPoints.containsKey(line)){
                set = new TreeSet<>();
            }else{
                set = linesAndPoints.get(line);
            }
            set.add(point);
            set.add(newPoint);
            linesAndPoints.put(line, set);
        }

        pointsSet.add(newPoint);

        return new Response("Point has been added to space", newPoint);
    }


    /**
     * This method does not take in any input.
     * @return all the points currently in the space
     */
    @Override
    public Set<Point> getAllPoints() {
        return new HashSet<>(pointsSet);
    }


    /**
     * This method clears the space by deleting all points from the space.
     */
    @Override
    public void clearAll() {
        pointsSet.clear();
        linesAndPoints.clear();
    }


    /**
     * This method get all lines which have at least N number of points in the space then returns the set of their points
     * @param numberOfPoints minimum number of points a line should have.
     * @return Set of points in the lines.
     */
    @Override
    public List<Set<Point>> getAllPointsFromLinesByNumberOfPoints(int numberOfPoints) {
        return linesAndPoints.values().stream()
                .filter(n -> n.size() >= numberOfPoints)
                .collect(Collectors.toList());
    }
}