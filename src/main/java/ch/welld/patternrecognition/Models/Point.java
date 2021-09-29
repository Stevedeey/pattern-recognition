package ch.welld.patternrecognition.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class defines a 'point' object which is defined by its position on the cartesian plane.
 * This is done using the x-coordinates (x) and y-coordinates (y).
 * @author Festus Audu
 */
@Data
@AllArgsConstructor
public class Point implements Comparable<Point>{

    private double x;

    private double y;


    @Override
    public int compareTo(Point anotherPoint) {
        if (this.x > anotherPoint.x || this.y > anotherPoint.y) return 1;
        if (this.x < anotherPoint.x || this.y < anotherPoint.y) return -1;
        return 0;
    }

}