package test;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import currencyConverter.MainWindow;
import currencyConverter.Currency;

public class MainWindowTest {

    // Black box testing for currencyConverter.MainWindow.convert(String, String,
    // Currency, Double)

    private String currency1;
    private String currency2;
    private Double amount;
    private static ArrayList<Currency> currencies;
    private Class<Exception> exceptionClass = Exception.class;

    @BeforeEach
    public void init() {
        currency1 = "US Dollar";
        currency2 = "British Pound";
        amount = 1.0d;
    }

    @BeforeAll
    public static void setup() {
        currencies = Currency.init();
    }

    @Test
    public void shouldNotConvertInvalidCurrency1() {
        String falseCurrenty = "Dogecoins";
        String expectedOutcome = "Should return an error if currency 1 is invalid";

        assertThrows(exceptionClass,
                () -> MainWindow.convert(falseCurrenty, currency2, currencies, amount),
                expectedOutcome);
    }

    @Test
    public void shouldNotConvertInvalidCurrency2() {
        String falseCurrenty = "Dogecoins";
        String expectedOutcome = "Should return an error if currency 2 is invalid";
        assertThrows(exceptionClass,
                () -> MainWindow.convert(currency1, falseCurrenty, currencies,
                        amount),
                expectedOutcome);
    }

    @Test
    public void shouldConvertValidCurrency() {
        assertTrue(MainWindow.convert(currency1, currency2, currencies, amount) instanceof Double);
    }

    @Test
    void shouldSupportSpecificationCurrency() {
        // USD, CAD, GBP, EUR, CHF, INR, AUD
        double defaultCurrencyValue = 10.00;

        double USDtoUSD = MainWindow.convert("US Dollar", "US Dollar", Currency.init(), defaultCurrencyValue);
        double CADtoCAD = MainWindow.convert("Canadian Dollar", "Canadian Dollar", Currency.init(),
                defaultCurrencyValue);
        double EURtoEUR = MainWindow.convert("Euro", "Euro", Currency.init(), defaultCurrencyValue);
        double GBPtoGBP = MainWindow.convert("British Pound", "British Pound", Currency.init(), defaultCurrencyValue);
        double INRtoINR = MainWindow.convert("Indian Rupee", "Indian Rupee", Currency.init(), defaultCurrencyValue);
        double AUDtoAUD = MainWindow.convert("Australian Dollar", "Australian Dollar", Currency.init(),
                defaultCurrencyValue);

        assertNotNull("should support USD", USDtoUSD);
        assertNotNull("should support CAD", CADtoCAD);
        assertNotNull("should support EUR", EURtoEUR);
        assertNotNull("should support GBP", GBPtoGBP);
        assertNotNull("should support INR", INRtoINR);
        assertNotNull("should support AUD", AUDtoAUD);

    }

    @Test
    public void shouldNotConvertAmountLessThan0() {
        Double inferiorAmount = -1.0d;
        String expectedOutcome = "Should return an error if amount is inferior to 0";
        assertThrows(exceptionClass,
                () -> MainWindow.convert(currency1, currency2, currencies,
                        inferiorAmount),
                expectedOutcome);
    }

    @Test
    public void shouldConvert0() {
        Double inferiorAmount = 0.0d;
        assertTrue(MainWindow.convert(currency1, currency2, currencies,
                inferiorAmount) instanceof Double);
    }

    @Test
    public void shouldConvert100() {
        Double middleAmount = 100.0d;
        assertTrue(MainWindow.convert(currency1, currency2, currencies,
                middleAmount) instanceof Double);
    }

    @Test
    public void shouldConvert10000() {
        Double superiorAmount = 10000.0d;
        assertTrue(MainWindow.convert(currency1, currency2, currencies,
                superiorAmount) instanceof Double);
    }

    @Test
    public void ShouldNotConvertSuperiorTo10001() {
        Double superiorAmount = 10001.0d;
        String expectedOutcome = "Should return an error if amount is superior to 10001";
        assertThrows(exceptionClass,
                () -> MainWindow.convert(currency1, currency2, currencies,
                        superiorAmount),
                expectedOutcome);
    }

    // * White box testing for currencyConverter.MainWindow.convert(String, String,
    // Currency, Double)

    // critere des arcs du graphe de flot de controle & coverage
    public void testInvalidCurrency2Name() {
        String falseCurrency2Name = "Doge";
        String expectedOutcome = "Should return an error if currency 2 is invalid";
        assertThrows(exceptionClass,
                () -> MainWindow.convert(currency1,
                        falseCurrency2Name, currencies,
                        amount),
                expectedOutcome);
    }

    @Test
    public void testAllValidCurrencyName() {
        assertTrue(MainWindow.convert(currency1, currency2, currencies, amount) instanceof Double);
    }

    @Test
    public void testInvalidCurrency1Name() {
        String falseCurrency1Name = "Doge";
        String expectedOutcome = "Should return an error if currency 1 is invalid";
        assertThrows(exceptionClass,
                () -> MainWindow.convert(
                        falseCurrency1Name,
                        currency2, currencies,
                        amount),
                expectedOutcome);
    }

    @Test
    // chemins inds, est-ce que currencies peut etre vide? si non, utilise chemin 2
    public void testPath136() {
        ArrayList<Currency> emptyCurrencies = new ArrayList<Currency>();
        String expectedOutcome = "Should return an error if currencies Array is empty";
        assertThrows(exceptionClass,
                () -> MainWindow.convert(currency1, currency2, emptyCurrencies, amount),
                expectedOutcome);
    }

    @Test
    public void testPath1367910() {
        String sameFirstCurrency = "US Dollar";
        assertTrue(MainWindow.convert(sameFirstCurrency, sameFirstCurrency, currencies, amount) instanceof Double);
    }

    @Test
    public void testPath1236781011() {
        String falseCurrency = "Doge";
        String expectedOutcome = "Should return an error if currency 1 is invalid";
        assertThrows(exceptionClass,
                () -> MainWindow.convert(falseCurrency, currency2, currencies, amount),
                expectedOutcome);
    }

    /**
     ** Coverage of conditions
     * We cover each arc of the graph for the flow of control of P so we dont need
     * to verify all the values for the complex conditions
     * Also, note that its impossible here to have empty currncies (Chemins ind)
     *
     * * coverage of o-paths
     * skip loop, 1 loop, 2 loop, n loop, n-1 loop, n+1 loop, m middle loop
     */

    @Test
    public void test1Iteration() {
        String tempCurrency = currencies.get(0).getName();
        assertTrue(MainWindow.convert(tempCurrency, tempCurrency, currencies, amount) instanceof Double);
    }

    @Test
    public void test2Iteration() {
        String tempCurrency = currencies.get(1).getName();
        assertTrue(MainWindow.convert(tempCurrency, tempCurrency, currencies, amount) instanceof Double);
    }

    @Test
    public void testmIteration() {
        // generate random m < n
        String tempCurrency = currencies.get(3).getName();
        assertTrue(MainWindow.convert(tempCurrency, tempCurrency, currencies, amount) instanceof Double);
    }

    @Test
    public void testLastIteration() {
        String tempCurrency = currencies.get(currencies.size() - 1).getName();
        assertTrue(MainWindow.convert(tempCurrency, tempCurrency, currencies, amount) instanceof Double);
    }

    @Test
    public void testSecondtoLastIteration() {
        String tempCurrency = currencies.get(currencies.size() - 2).getName();
        assertTrue(MainWindow.convert(tempCurrency, tempCurrency, currencies, amount) instanceof Double);
    }

    // ici on a boucle concatenees independantes, donc 2 fois test bouble simple

}
