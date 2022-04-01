package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.Test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;

/**
 * Ny comprehension is:
 * - If it is not accepted it should throw an exception. We dont know the
 * exception so we just want an exception
 * -
 * 
 * What make no sense:
 * - It make no sense to have convertion use the full Name and not the Short
 * name,
 */
public class MainWindowTest {

    @Test
    void shouldNotAcceptValueLessThan0() {
        String expectedOutcome = "Should return an error if value to convert is less than 0";
        Class<Exception> exceptionClass = Exception.class;
        assertThrows(expectedOutcome, exceptionClass,
                () -> MainWindow.convert("US Dollar", "Euro", Currency.init(), -1.00));
    }

    @Test
    void shouldNotAcceptValueMoreThan10000() {
        String expectedOutcome = "Should return an error if value to convert is more than 1000.00";
        Class<Exception> exceptionClass = Exception.class;
        assertThrows(expectedOutcome,
                exceptionClass,
                () -> MainWindow.convert("US Dollar", "Euro", Currency.init(), 10001.00));
    }

    @Test
    void shouldAcceptAValidValue() {
        String expectedOutcome = "Should accept a 10 as a value";
        double response = MainWindow.convert("US Dollar", "Euro", Currency.init(), 999.00);
        assertTrue(expectedOutcome, response != 0);
    }

    @Test
    void shouldAccept0() {
        String expectedOutcome = "Should accept a 0 as a value";
        double response = MainWindow.convert("US Dollar", "Euro", Currency.init(), 0.00);
        assertTrue(expectedOutcome, response == 0);
    }

    @Test
    void shouldAcceptn10000() {
        String expectedOutcome = "Should accept 10000 as a value";
        double response = MainWindow.convert("US Dollar", "Euro", Currency.init(), 10000.00);
        assertTrue(expectedOutcome, response != 0);
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
    void shouldOnlySupportSpecificationCurrency() {
        // YEN should not be accepted
        double defaultCurrencyValue = 10.00;

        String expectedOutcome = "Should return an error if converting YEN";
        Class<Exception> exceptionClass = Exception.class;
        assertThrows(expectedOutcome, exceptionClass,
                () -> MainWindow.convert("Japanese Yen", "Japanese Yen", Currency.init(), defaultCurrencyValue));
    }

    @Test
    void shouldSupportDifferentCurrencyConversion() {
        double defaultCurrencyValue = 10.00;

        double USDtoUSD = MainWindow.convert("US Dollar", "Euro", Currency.init(), defaultCurrencyValue);
        assertNotNull("should support USD to EUR", USDtoUSD);

    }

    @Test
    void shouldSupportSameCurrencyConversion() {
        double USDtoUSD = MainWindow.convert("US Dollar", "US Dollar", Currency.init(), 10.00);
        assertNotNull("should support USD to USD conversion", USDtoUSD);

    }

}
