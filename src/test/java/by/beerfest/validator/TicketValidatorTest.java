package by.beerfest.validator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TicketValidatorTest {

    private TicketValidator validator;

    @BeforeMethod
    public void setUp() {
        validator = new TicketValidator();
    }

    @AfterMethod
    public void tearDown() {
        validator = null;
    }

    @DataProvider(name = "validTicketNumberProvider")
    public Object[][] validTicketNumberProvide() {
        return new Object[][]{
                {"1"},
                {"10"},
                {"0"},
                {""}
        };
    }

    @DataProvider(name = "invalidTicketNumberProvider")
    public Object[][] invalidTicketNumberProvide() {
        return new Object[][]{
                {"one"},
                {"-1"}
        };
    }

    @Test(dataProvider = "validTicketNumberProvider")
    public void testValidateTicketNumberPositive(String ticketNumber) {
        Assert.assertTrue(validator.validateTicketNumber(ticketNumber));
    }

    @Test(dataProvider = "invalidTicketNumberProvider")
    public void testValidateTicketNumberNegative(String ticketNumber) {
        Assert.assertFalse(validator.validateTicketNumber(ticketNumber));
    }
}