package tests.git;

import core.ScreenShoter;
import core.WebDriverSingleton;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import tests.git.page_objects.GitHubPage;
import tests.git.page_objects.GitLogInPage;
import reporting.MyLogger;

public class GitTest {
    private String baseUrl, repositoryName, userName, pass, fileName, fileContext, headerName, commitMessage;

    @BeforeClass
    @Parameters({"baseUrl", "userName", "pass", "repositoryName", "fileName", "fileContext", "headerName", "commitMessage"})
    public void setUp(String baseUrl, String userName, String pass, String repositoryName, String fileName, String fileContext, String headerName, String commitMessage){
        MyLogger.info("---GITHUB TEST---");
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
    public void test() throws Exception {
        WebDriverSingleton.getWebDriverInstance().get(baseUrl);
        GitLogInPage gitLogInPage = new GitLogInPage().logIn(userName,pass);
        Assert.assertTrue(gitLogInPage.logInConfirm());
        MyLogger.info("---SUCCESSFULLY LOGGED IN---");
        GitHubPage gitHubPage = new GitHubPage();
        gitHubPage.createRepository(repositoryName).createNewFile(fileName, fileContext, headerName, commitMessage);
        Assert.assertTrue(gitHubPage.isRepositoryCreated(repositoryName));
        MyLogger.info("---CREATED NEW REPOSITORY---");
        gitHubPage.deleteRepository(repositoryName);
        Assert.assertFalse(gitHubPage.isRepositoryDeleted(repositoryName));
        gitHubPage.logOff();
        Assert.assertTrue(gitHubPage.isLogOutConfirmed());
        MyLogger.info("---LOGGED OUT---");
    }

    @AfterMethod
    public void takeScreenshotOnTestFailure(ITestResult iTestResult){
        if(iTestResult.getStatus() == ITestResult.FAILURE){
            ScreenShoter.takeScreenshot();
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        WebDriverSingleton.kill();
    }

}
