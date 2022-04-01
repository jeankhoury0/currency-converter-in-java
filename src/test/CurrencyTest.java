package test;


import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import currencyConverter.Currency;


public class CurrencyTest {
    /**
     * Testing currencyConverter.Currency.convert(Double, Double)
     * • Boundery -1.00
     * • Boundery 0.00
     * • Valid Value 10.00
     * • Boundery 10000.00
     * • Boundery 10001.00
     */

    double mockeEchangeValue = 1.00;

    @Test
    void convertShouldRejectNegativeValue(){
        String expectedOutcome = "It should not accept -1.00 as an amount";
        Class<Exception> exceptionClass = Exception.class;
        assertThrows(expectedOutcome, exceptionClass,
                () -> Currency.convert(-1.00, mockeEchangeValue));
    }
    
    @Test
    void convertShouldAccept0(){
        assertNotNull(Currency.convert(0.00, mockeEchangeValue));
    }
    
    @Test
    void convertShouldAccept10000(){
        assertNotNull(Currency.convert(10000.00, mockeEchangeValue));

    }
    
    @Test
    void convertShouldReject10001(){
        String expectedOutcome = "It should not accept 10001 as an amount";
        Class<Exception> exceptionClass = Exception.class;
        assertThrows(expectedOutcome, exceptionClass,
                () -> Currency.convert(10001.00, mockeEchangeValue));

    }
}