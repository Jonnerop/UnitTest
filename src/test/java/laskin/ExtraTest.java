package laskin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ExtraTest extends AbstractParent {

    private static Laskin laskin = new Laskin();
    private final double DELTA = 0.001;

    @BeforeAll
    public static void testVirtaON() {
        System.out.println("@BeforeAll Virta ON (ennen ensimmäistä testiä)");
        laskin.virtaON();
    }

    @AfterAll
    public static void testVirtaOFF() {
        System.out.println("@AfterAll Virta OFF (kaikki testit ajettu).");
        laskin.virtaOFF();
        laskin = null;
    }

    @BeforeEach
    public void testNollaa() {
        System.out.println("  Nollaa laskin.");
        laskin.nollaa();
        assertEquals(0.0, laskin.annaTulos(), DELTA, "Nollaus ei onnistunut");
    }

    @ParameterizedTest(name = "Luvun {0} neliö on {1}")
    @CsvSource({ "2, 4", "4, 16", "5, 25" })
    public void testNelio(double luku, double expected) {
        laskin.nelio(luku);
        assertEquals(expected, laskin.annaTulos(), DELTA, "Neliöön korotus väärin");
    }

    @ParameterizedTest(name = "Luvun {0} neliöjuuri on {1}")
    @CsvSource({ "2, 1.414213562", "4, 2.0", "9, 3.0" })
    public void testNeliojuuri(double luku, double expected) {
        laskin.neliojuuri(luku);
        assertEquals(expected, laskin.annaTulos(), DELTA, "Neliöjuuri väärin");
    }

    @ParameterizedTest(name = "Testaa negatiivinen neliöjuuri: {0}")
    @CsvSource({ "-1", "-4", "-9" })
    @DisplayName("Testaa negatiivinen neliöjuuri")
    public void testNeliojuuriNegat(double luku) {
        assertThrows(IllegalArgumentException.class, () -> laskin.neliojuuri(luku),
                "Negatiivisen luvun neliöjuuren pitäisi heittää poikkeus");
    }
}
