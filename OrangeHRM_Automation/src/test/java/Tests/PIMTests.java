package Tests;

import base.Base;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataDriven;

import static org.testng.Assert.assertEquals;

public class PIMTests extends Base {
    @Test(dataProvider = "searchForAnEmployeeData")
    public void testSearchForAnEmployee(String employeeName){
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        pimPage = loginPage.clickLogin().clickPIM();

        pimPage.searchForEmployee(employeeName);
        pimPage.clickSearch();

        String searchedEmployeeName = pimPage.getSearchedEmployeeName();

        assertEquals(searchedEmployeeName, "Peter Mac", "The employee name is wrong");
    }

    @Test(dataProvider = "SearchForNonExistingEmployeeData")
    public void testSearchForAnonExistingEmployee(String employeeName){
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        pimPage = loginPage.clickLogin().clickPIM();

        pimPage.searchForEmployee(employeeName);
        pimPage.clickSearch();

        String noRecordMessage = pimPage.getNoRecordsMessage();

        assertEquals(noRecordMessage, "No Records Found", "Wrong message");

    }

    @DataProvider(name = "searchForAnEmployeeData")
    public Object[][] searchForAnEmployeeData() throws Exception{
        JSONObject object = DataDriven.getObject("searchForAnEmployee");
        return new Object[][]{
                {object.getString("employeeName")}
        };
    }

    @DataProvider(name = "SearchForNonExistingEmployeeData")
    public Object[][] SearchForNonExistingEmployeeData() throws Exception{
        JSONObject object = DataDriven.getObject("SearchForNonExistingEmployee");
        return new Object[][]{
                {object.getString("employeeName")}
        };
    }

}
