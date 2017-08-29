package tests;

import core.Browser;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.MyLogger;
import tests.git.GitHubPage;
import tests.git.GitLogInPage;

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
        GitLogInPage gitLogInPage = new GitLogInPage().logIn(baseUrl, userName, pass);
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
            Browser.takeScreenshot();
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Browser.kill();
    }

}
