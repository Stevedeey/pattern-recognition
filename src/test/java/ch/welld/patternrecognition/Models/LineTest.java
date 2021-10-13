package ch.welld.patternrecognition.Models;

import ch.welld.patternrecognition.Exceptions.SamePointException;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LineTest {

    private Line lineUnderTest1;

    @BeforeEach
    void setUp() {
        lineUnderTest1 = new Line(0.0, 0.0);
    }

    /**
     *This test uses the EqualsVerifier library to verify that the Line class
     * follows the equals() and hashCode() contracts
     */
    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple().forClass(Line.class).suppress(Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
    }


    @Test
    void testEquals() {
        assertThat(lineUnderTest1.equals(new Line(0.0, 0.0))).isTrue();
    }


    @Test
    void testHashCode() {

        final int result = lineUnderTest1.hashCode();
        final int result2 = new Line(0,0).hashCode();
        assertThat(result).isEqualTo(result2);

    }


    @Test
    void testCompareTo() {

        assertThat(lineUnderTest1.compareTo(new Line(0.0, 0.0))).isEqualTo(0);
        assertThat(lineUnderTest1.compareTo(new Line(2.0,0.0))).isEqualTo(-1);
        assertThat(lineUnderTest1.compareTo(new Line(-2.0,0.0))).isEqualTo(1);
    }


    @Test
    void testCompareTo_ThrowsNullPointerException() {

        assertThatThrownBy(
                () -> lineUnderTest1.compareTo(null)
        ).isInstanceOf(NullPointerException.class);
    }


    @Test
    void testToString() {

        assertThat(lineUnderTest1.toString()).isEqualTo("Line(constant=0.0, gradient=0.0, points=[])");
        assertThat(new Line(new Point(0,2), new Point(2,3)).toString())
                .isEqualTo("Line(constant=2.0, gradient=0.5, points=[Point(x=0.0, y=2.0), Point(x=2.0, y=3.0)])");
    }


    @Test
    void testLineConstructorWithTwoPoints() {
            assertThat(new Line(new Point(2,2), new Point(1,2)))
                    .isInstanceOf(lineUnderTest1.getClass());
    }


    @Test
    void testLineWithSamePointsThrowsSamePointException() {

        assertThatThrownBy(
                () -> new Line(new Point(2,2), new Point(2,2)))
                .isInstanceOf(SamePointException.class);
    }

}