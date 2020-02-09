package by.beerfest.validator;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserDataValidatorTest {

    private UserDataValidator validator;

    @BeforeMethod
    public void setUp() {
        validator = new UserDataValidator();
    }

    @AfterMethod
    public void tearDown() {
        validator = null;
    }

    @DataProvider(name = "validEmailProvider")
    public Object[][] validEmailProvide() {
        return new Object[][]{
                {"test@test"},
                {"test@t"},
                {"t@test"}
        };
    }

    @DataProvider(name = "invalidEmailProvider")
    public Object[][] invalidEmailProvide() {
        return new Object[][]{
                {"test"},
                {"test.com"},
                {"gmail.com"},
                {"test@"},
                {"@test"},
        };
    }

    @DataProvider(name = "validPasswordProvider")
    public Object[][] validPasswordProvide() {
        return new Object[][]{
                {"1234567"},
                {"qwertyu"},
                {"qwe,@a.d"}
        };
    }

    @DataProvider(name = "invalidPasswordProvider")
    public Object[][] invalidPasswordProvide() {
        return new Object[][]{
                {"test"},
                {"pass"},
                {"qwerty"},
                {"1234"},
                {"@.,qw"},
        };
    }

    @DataProvider(name = "validPhoneNumberProvider")
    public Object[][] validPhoneNumberProvide() {
        return new Object[][]{
                {"+375291234567"},
                {"+37533 1234567"},
                {"+375449876543"}
        };
    }

    @DataProvider(name = "invalidPhoneNumberProvider")
    public Object[][] invalidPhoneNumberProvide() {
        return new Object[][]{
                {"+375111234567"},
                {"+11129 1324568"},
                {"+3752 1234567"},
                {"+37544 123456"},
        };
    }

    @DataProvider(name = "validAvatarProvider")
    public Object[][] validAvatarProvide() {
        return new Object[][]{
                {"q.png"},
                {"g.jpg"}
        };
    }

    @DataProvider(name = "invalidAvatarProvider")
    public Object[][] invalidAvatarProvide() {
        return new Object[][]{
                {"Q.txt"},
                {"file.docx"},
                {".png"},
                {".jpg"},
        };
    }

    @Test(dataProvider = "validEmailProvider")
    public void testEmailValidatePositive(String email) {
        Assert.assertTrue(validator.emailValidate(email));
    }

    @Test(dataProvider = "invalidEmailProvider")
    public void testEmailValidateNegative(String email) {
        Assert.assertFalse(validator.emailValidate(email));
    }

    @Test(dataProvider = "validPasswordProvider")
    public void testPasswordValidatePositive(String password) {
        Assert.assertTrue(validator.passwordValidate(password));
    }

    @Test(dataProvider = "invalidPasswordProvider")
    public void testPasswordValidateNegative(String password) {
        Assert.assertFalse(validator.passwordValidate(password));
    }

    @Test(dataProvider = "validPhoneNumberProvider")
    public void testPhoneNumberValidatePositive(String phoneNumber) {
        Assert.assertTrue(validator.phoneNumberValidate(phoneNumber));
    }

    @Test(dataProvider = "invalidPhoneNumberProvider")
    public void testPhoneNumberValidateNegative(String phoneNumber) {
        Assert.assertFalse(validator.phoneNumberValidate(phoneNumber));
    }

    @Test(dataProvider = "validAvatarProvider")
    public void testAvatarValidatePositive(String avatar) {
        Assert.assertTrue(validator.avatarValidate(avatar));
    }

    @Test(dataProvider = "invalidAvatarProvider")
    public void testAvatarValidateNegative(String avatar) {
        Assert.assertFalse(validator.avatarValidate(avatar));
    }
}