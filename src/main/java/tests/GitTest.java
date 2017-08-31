package tests;

import core.Browser;
import model.git.page_objects.NewFilePage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.MyLogger;
import model.git.page_objects.GitHubPage;
import model.git.page_objects.GitLogInPage;

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
        Assert.assertTrue(new GitLogInPage().logIn(baseUrl, userName, pass).logInConfirm());
        MyLogger.info("---SUCCESSFULLY LOGGED IN---");
        Assert.assertTrue(new GitHubPage().createRepository(repositoryName).isRepositoryCreated(repositoryName));
        MyLogger.info("---CREATED NEW REPOSITORY---");
        Assert.assertTrue(new NewFilePage().createNewFile(fileName, fileContext, headerName, commitMessage).isFileCreated(fileName));
        MyLogger.info("---CREATED NEW FILE---");
        Assert.assertFalse(new GitHubPage().deleteRepository(repositoryName).isRepositoryDeleted(repositoryName));
        MyLogger.info("---REPOSITORY ---");
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
