package ch.welld.patternrecognition.Models;

import ch.welld.patternrecognition.Exceptions.SamePointException;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.TreeSet;

@Data
public class Line implements Comparable<Line>{

    private double constant;

    private double gradient;

    @EqualsAndHashCode.Exclude
    private Set<Point> points = new TreeSet<>();


    public Line(double constant, double gradient) {
        this.constant = constant;
        this.gradient = gradient;
    }


    /**
     * This is a constructor for creating a line with two points.
     * It uses the fundamental equation of a straight line: y = mx + c.
     * where m -> gradient  &   c -> constant (as defined in this Line class).
     * @param point1 first point.
     * @param point2 second point.
     */
    public Line(Point point1, Point point2) {

        if (point1.equals(point2)) {
            throw new SamePointException("Point1 and Point2 are the same: " +
                    "A line cannot be made of points in the same position" + point1 + "  " + point2);
        }


        /*
        To compute the equation of a straight line from a set of points,
        there's need to calculate its gradient which is given below:
              - gradient = (y1 - y2)/(x1-x2);
              where y1,y2,x1,x2 are the respective x and y coordinates of both points.
         */
        double y1 = point1.getY();
        double y2 = point2.getY();
        double x1 = point1.getX();
        double x2 = point2.getX();

        double lineGradient;
        double lineConstant;

        if (y1-y2 == 0.0) {                                     // For the case of a horizontal line
            lineGradient = 0.0;
            lineConstant = point1.getY();

        } else if (x1-x2 == 0.0) {                              // For the case of a vertical line
            lineGradient = Double.NaN;
            lineConstant = point1.getX();

        } else {
            lineGradient = (y1-y2) / (x1-x2);
            lineConstant = point1.getY() - lineGradient*point1.getX(); // c = -mx + y
        }

        gradient = lineGradient;
        constant = lineConstant;
        points.add(point1);
        points.add(point2);
    }


    @Override
    public int compareTo(Line anotherLine) {

        if (Double.compare(this.gradient, anotherLine.gradient) == 0) {
            return Double.compare(this.constant, anotherLine.constant);
        }
        return Double.compare(this.gradient, anotherLine.gradient);
    }
}