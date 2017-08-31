package tests.git;

import model.git.page_objects.GitHubPage;
import org.testng.Assert;
import org.testng.annotations.*;
import reporting.MyLogger;

public class DeleteRepositoryTestClass extends MainGitTestClass {

    @Test
    public void deleteRepositoryTest(){
        MyLogger.info("---DELETING REPOSITORY---");
        Assert.assertFalse(new GitHubPage().chooseRepository(repositoryName).deleteRepository(repositoryName).isRepositoryDeleted(repositoryName));
        MyLogger.info("---REPOSITORY DELETED---");
    }

}
