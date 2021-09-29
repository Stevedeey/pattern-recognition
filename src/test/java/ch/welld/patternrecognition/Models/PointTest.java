package ch.welld.patternrecognition.Models;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PointTest {

    private Point pointUnderTest1;
    private Point pointUnderTest2;
    private Point pointUnderTest3;
    private Point pointUnderTest4;

    @BeforeEach
    void setUp() {
        pointUnderTest1 = new Point(0.0, 0.0);
        pointUnderTest2 = new Point(1.0, 1.0);
        pointUnderTest3 = new Point(2.0, 0.0);
        pointUnderTest4 = new Point(0.0, -2.0);
    }

    /**
     *This test uses the EqualsVerifier library to verify that the Point class
     * follows the equals() and hashCode() contracts
    */
    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple().forClass(Point.class).verify();
    }

    @Test
    void testCompareTo() {

        final int result1 = pointUnderTest1.compareTo(new Point(0.0, 0.0));
        final int result2 = pointUnderTest2.compareTo(new Point(3.0, 1.0));
        final int result3 = pointUnderTest3.compareTo(new Point(0.0, 2.0));
        final int result4 = pointUnderTest4.compareTo(new Point(0.0, -2.0));

        assertThat(result1).isEqualTo(0);
        assertThat(result2).isEqualTo(-1);
        assertThat(result3).isEqualTo(1);
        assertThat(result4).isEqualTo(0);

    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {

        assertThatThrownBy(
                () -> {
                    pointUnderTest1.compareTo(null);
                }).isInstanceOf(NullPointerException.class);

    }


    @Test
    void testEquals() {

        final boolean result1 = pointUnderTest1.equals(new Point(0.0, 0));
        final boolean result2 = pointUnderTest2.equals(new Point(0.0, 2.0));
        final boolean result3 = pointUnderTest3.equals(new Point(0.0, 2.0));
        final boolean result4 = pointUnderTest4.equals(new Point(0.0, -2));

        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
        assertThat(result4).isTrue();

    }


    @Test
    void testToString() {

        assertThat(pointUnderTest1.toString()).isEqualTo("Point(x=0.0, y=0.0)");
        assertThat(pointUnderTest2.toString()).isEqualTo("Point(x=1.0, y=1.0)");
        assertThat(pointUnderTest3.toString()).isEqualTo("Point(x=2.0, y=0.0)");
        assertThat(pointUnderTest4.toString()).isEqualTo("Point(x=0.0, y=-2.0)");

    }

}
