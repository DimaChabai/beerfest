package by.beerfest.validator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParticipantDataValidatorTest {

    private ParticipantDataValidator validator;

    @BeforeMethod
    public void setUp() {
        validator = new ParticipantDataValidator();
    }

    @AfterMethod
    public void tearDown() {
        validator = null;
    }

    @DataProvider(name = "validCompanyNameProvider")
    public Object[][] validCompanyNameProvide() {
        return new Object[][]{
                {"SomeCompanyName"},
                {"Test@.,!.>?"}
        };
    }

    @DataProvider(name = "invalidCompanyNameProvider")
    public Object[][] invalidCompanyNameProvide() {
        return new Object[][]{
                {"Company"},
                {"Test"}
        };
    }

    @Test(dataProvider = "validCompanyNameProvider")
    public void testCompanyNameValidatePositive(String companyName) {
        Assert.assertTrue(validator.companyNameValidate(companyName));
    }

    @Test(dataProvider = "invalidCompanyNameProvider")
    public void testCompanyNameValidateNegative(String companyName) {
        Assert.assertFalse(validator.companyNameValidate(companyName));
    }
}