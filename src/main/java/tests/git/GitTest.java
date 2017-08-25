package tests.git;

import core.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GitTest {
    private String baseUrl, repositoryName, userName, pass, fileName, fileContext, headerName, commitMessage;

    @BeforeClass
    @Parameters({"baseUrl", "userName", "pass", "repositoryName", "fileName", "fileContext", "headerName", "commitMessage"})
    public void setUp(String baseUrl, String userName, String pass, String repositoryName, String fileName, String fileContext, String headerName, String commitMessage){
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
        GitHubPage gitHubPage = new GitHubPage();
        gitHubPage.createRepository(repositoryName).createNewFile(fileName, fileContext, headerName, commitMessage);
        Assert.assertTrue(gitHubPage.isRepositoryCreated(repositoryName));
        gitHubPage.deleteRepository(repositoryName);
        Assert.assertFalse(gitHubPage.isRepositoryDeleted(repositoryName));
        gitHubPage.logOff();
        Assert.assertTrue(gitHubPage.isLogOutConfirmed());
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        WebDriverSingleton.kill();
    }

}
