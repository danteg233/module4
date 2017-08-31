package tests.git;

import model.git.page_objects.GitHubPage;
import org.testng.Assert;
import org.testng.annotations.*;
import reporting.MyLogger;

public class CreateRepositoryTestClass extends MainGitTestClass {

    @Test
    public void createRepositoryTest() throws Exception {
        Assert.assertTrue(new GitHubPage().createRepository(repositoryName).isRepositoryCreated(repositoryName));
        MyLogger.info("---CREATED NEW REPOSITORY---");
    }

}
