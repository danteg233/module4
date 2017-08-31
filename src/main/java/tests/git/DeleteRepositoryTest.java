package tests.git;

import core.Browser;
import model.git.page_objects.GitHubPage;
import model.git.page_objects.GitLogInPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.MyLogger;

public class DeleteRepositoryTest {
    private String baseUrl, repositoryName, userName, pass;

    @BeforeClass
    @Parameters({"baseUrl", "userName", "pass", "repositoryName"})
    public void setUp(String baseUrl, String userName, String pass, String repositoryName){
        MyLogger.info("---DeleteRepositoryTest---");
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.pass = pass;
        this.repositoryName = repositoryName;
    }

    @Test
    public void logInTest(){
        MyLogger.info("---LOGGING IN---");
        Assert.assertTrue(new GitLogInPage().logIn(baseUrl, userName, pass).logInConfirm());
        MyLogger.info("---SUCCESSFULLY LOGGED IN---");
    }

    @Test(dependsOnMethods = "logInTest")
    public void deleteRepositoryTest(){
        MyLogger.info("---DELETING REPOSITORY---");
        Assert.assertFalse(new GitHubPage().chooseRepository(repositoryName).deleteRepository(repositoryName).isRepositoryDeleted(repositoryName));
        MyLogger.info("---REPOSITORY DELETED---");
    }

    @Test(dependsOnMethods = "deleteRepositoryTest")
    public void logOutTest(){
        MyLogger.info("---LOGGING OUT---");
        Assert.assertTrue(new GitHubPage().logOff().isLogOutConfirmed());
        MyLogger.info("---LOGGED OUT---");
    }

    @AfterMethod
    public void takeScreenshotOnTestFailure(ITestResult iTestResult){
        if(iTestResult.getStatus() == ITestResult.FAILURE){
            Browser.takeScreenshot();
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Browser.kill();
    }
}
