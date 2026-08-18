package Tests;

import base.Base;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataDriven;

import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests extends Base {

    @Test(dataProvider = "validLoginData")
    public void testValidLogin(String userName, String password){
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        dashBoardPage = loginPage.clickLogin();

        String header = dashBoardPage.getHeaderText();

        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("/dashboard/index"), "The URL is wrong");
        assertEquals(header, "Dashboard", "The header title is wrong");
    }

    @Test(dataProvider = "invalidLoginData")
    public void testInvalidLogin(String userName, String password){
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        dashBoardPage = loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();

        assertEquals(errorMessage, "Invalid credentials", "The message is wrong");
    }

    @Test(dataProvider = "loginWithEmptyFieldsData")
    public void testLoginWithEmptyFields(String userName, String password){
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        dashBoardPage = loginPage.clickLogin();

        List<String> messages = loginPage.getRequiredMessages();

        assertTrue(messages.stream().allMatch(msg -> msg.trim().equals("Required")));
    }

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() throws Exception{
        JSONObject object = DataDriven.getObject("validLogin");
        return new Object[][]{
                {object.getString("username"), object.getString("password")}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() throws Exception{
        JSONObject object = DataDriven.getObject("invalidLogin");
        return new Object[][]{
                {object.getString("username"), object.getString("password")}
        };
    }

    @DataProvider(name = "loginWithEmptyFieldsData")
    public Object[][] loginWithEmptyFields() throws Exception{
        JSONObject object = DataDriven.getObject("loginWithEmptyFields");
        return new Object[][]{
                {object.getString("username"), object.getString("password")}
        };
    }
}
