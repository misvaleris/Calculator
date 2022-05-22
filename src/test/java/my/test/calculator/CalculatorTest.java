package my.test.calculator;

import com.sun.org.glassfish.gmbal.Description;
import my.test.calculator.pages.BasePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CalculatorTest {

    private BasePage basePage;

    @BeforeEach
    void setup() {
        basePage = new BasePage();
    }

    @Test
    @DisplayName("Calculator page opened test")
    @Description("Calculator page should be opened")
    void isPageOpened() {
        Assertions.assertTrue(basePage.isCalculatorPageOpened(), "Calculator page is not opened");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CalculatorPositiveValue.csv", numLinesToSkip = 1)
    void calculatorPositiveVerifications(String value) {
        Assertions.assertEquals("The factorial of 1 is: 1", basePage.calculateFactorial(value),"Result doesn't match. Factorial of 1 doesn't work");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CalculatorNegativeValue.csv", numLinesToSkip = 1)
    void calculatorNegativeVerifications(String value, String result) {
    }


    @AfterEach
    void cleanup() {
    }
}
