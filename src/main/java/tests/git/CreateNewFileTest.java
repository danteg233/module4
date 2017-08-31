package tests.git;

import core.Browser;
import model.git.page_objects.GitHubPage;
import model.git.page_objects.GitLogInPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.MyLogger;

public class CreateNewFileTest {
    private String baseUrl, userName, pass, repositoryName, fileName, fileContext, headerName, commitMessage;

    @BeforeClass
    @Parameters({"baseUrl", "userName", "pass", "repositoryName", "fileName", "fileContext", "headerName", "commitMessage"})
    public void setUp(String baseUrl, String userName, String pass, String repositoryName, String fileName, String fileContext, String headerName, String commitMessage){
        MyLogger.info("---CreateNewFileTest---");
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.pass = pass;
        this.repositoryName = repositoryName;
        this.fileName = fileName;
        this.fileContext = fileContext;
        this.headerName = headerName;
        this.commitMessage = commitMessage;
    }

    @Test
    public void logInTest(){
        Assert.assertTrue(new GitLogInPage().logIn(baseUrl, userName, pass).logInConfirm());
        MyLogger.info("---SUCCESSFULLY LOGGED IN---");
    }


    @Test(dependsOnMethods = "logInTest")
    public void createNewFileTest(){
        Assert.assertTrue(new GitHubPage().chooseRepository(repositoryName).createNewFile(fileName, fileContext, headerName, commitMessage).isFileCreated(fileName));
        MyLogger.info("---CREATED NEW FILE---");
    }

    @Test(dependsOnMethods = "createNewFileTest")
    public void logOutTest(){
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
