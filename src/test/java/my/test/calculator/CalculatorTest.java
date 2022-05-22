package my.test.calculator;

import com.sun.org.glassfish.gmbal.Description;
import my.test.calculator.pages.BasePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

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
    @DisplayName("Positive Verifications test")
    @Description("Calculator should calculate input values to right factorials")
    @ValueSource(ints = {0, 1, 10, 99, 111})
    void calculatorPositiveVerifications(int value) {
        basePage.calculateNumbersFactorial(value);
        int actualValue = basePage.getIntResult();
        int expectedValue = getFactorial(value);
        Assertions.assertEquals(expectedValue, actualValue, "Result doesn't match. Calculator os positive values don't work");
    }

    @ParameterizedTest
    @DisplayName("Input Words Verifications test")
    @Description("Calculator should not calculate input words to factorials")
    @CsvFileSource(resources = "/CalculatorNegativeValues.csv", numLinesToSkip = 1)
    void calculatorWordsVerification(String value) {
        Assertions.assertTrue(basePage.calculateWordsFactorial(value), "Calculator doesn't show error that user should enter just integers");
    }

    @ParameterizedTest
    @DisplayName("Big Values Verification test")
    @Description("Calculator should not calculate big values to right factorials")
    @ValueSource(ints = {999})
    void calculatorBigValuesVerifications(int value) {
        basePage.calculateNumbersFactorial(value);
        String actualValue = basePage.getStringResult();
        Assertions.assertEquals("The factorial of " + value + " is: Infinity", actualValue, "Result doesn't match. Calculator os positive values don't work");
    }

    public static int getFactorial(int f) {
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }

    @AfterEach
    void cleanup() {
    }
}
