package by.beerfest.command;

import by.beerfest.controller.SessionRequestContent;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CommandProviderTest {

    public static final String COMMAND = "command";
    @Mock
    private SessionRequestContent content;

    @AfterMethod
    public void tearDown() {
        content = null;
    }

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DataProvider(name = "correctCommandProvider")
    private Object[][] correctCommandProvide() {
        return new Object[][]{
                {"TO_proFIle", CommandType.TO_PROFILE.getCurrentCommand()},
                {"chAngE_lOcalE", CommandType.CHANGE_LOCALE.getCurrentCommand()},
                {"LogIN", CommandType.LOGIN.getCurrentCommand()},
                {"Test", CommandType.MAIN.getCurrentCommand()},
        };
    }

    @Test(dataProvider = "correctCommandProvider")
    public void testDefineCommand(String commandName, Command command) {
        Mockito.when(content.getRequestAttribute(COMMAND)).thenReturn(commandName);
        Assert.assertEquals(command, CommandProvider.defineCommand(content));
    }
}