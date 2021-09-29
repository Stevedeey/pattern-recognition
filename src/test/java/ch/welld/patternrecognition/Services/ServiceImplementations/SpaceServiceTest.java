package ch.welld.patternrecognition.Services.ServiceImplementations;

import ch.welld.patternrecognition.Models.Point;
import ch.welld.patternrecognition.Models.Response;
import ch.welld.patternrecognition.Services.SpaceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SpaceServiceTest {


    private final SpaceService spaceServiceUnderTest = new SpaceServiceImpl();

    @AfterEach
    void setUp(){
        spaceServiceUnderTest.clearAll();
    }


    @Test
    void testAddPoint() {

        final Point newPoint = new Point(0.0, 0.0);
        final Response expectedResult =
                new Response("Point has been added to space", new Point(0.0, 0.0));
        final Response result = spaceServiceUnderTest.addPoint(newPoint);
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testAddSamePointTwice() {

        final Point newPoint1 = new Point(1.0, 4.0);
        final Point newPoint2 = new Point(1.0, 4.0);
        final Response result1 = spaceServiceUnderTest.addPoint(newPoint1);
        final Response result2 = spaceServiceUnderTest.addPoint(newPoint2);

        assertThat(result1).isNotEqualTo(result2);
        assertEquals(1, spaceServiceUnderTest.getAllPoints().size());

    }


    @Test
    void testAddSeveralDifferentPoints() {

        final Point newPoint1 = new Point(1.0, 4.0);
        final Point newPoint2 = new Point(1.0, 1.0);
        final Point newPoint3 = new Point(3.0, 4.0);
        final Point newPoint4 = new Point(1.0, 2.0);

        spaceServiceUnderTest.addPoint(newPoint1);
        spaceServiceUnderTest.addPoint(newPoint2);
        spaceServiceUnderTest.addPoint(newPoint3);
        spaceServiceUnderTest.addPoint(newPoint4);

        assertEquals(4, spaceServiceUnderTest.getAllPoints().size());

    }


    @Test
    void testGetAllPoints() {

        final Point newPoint1 = new Point(1.0, 4.0);
        final Point newPoint2 = new Point(2.0, 4.0);
        final Point newPoint3 = new Point(0.0, 0.0);
        spaceServiceUnderTest.addPoint(newPoint1);
        spaceServiceUnderTest.addPoint(newPoint2);
        spaceServiceUnderTest.addPoint(newPoint3);

        final Set<Point> expectedResult = Set.of(
                new Point(1.0, 4.0),
                new Point(2.0,4.0),
                new Point(0.0,0.0));
        final Set<Point> result = spaceServiceUnderTest.getAllPoints();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testClearAll() {

        final Point newPoint1 = new Point(1.0, 4.0);
        final Point newPoint2 = new Point(2.0, 4.0);
        final Point newPoint3 = new Point(0.0, 0.0);
        spaceServiceUnderTest.addPoint(newPoint1);
        spaceServiceUnderTest.addPoint(newPoint2);
        spaceServiceUnderTest.addPoint(newPoint3);

        assertThat(spaceServiceUnderTest.getAllPoints().size()).isGreaterThan(0);

        spaceServiceUnderTest.clearAll();

        assertThat(spaceServiceUnderTest.getAllPoints().size()).isEqualTo(0);
    }

}
