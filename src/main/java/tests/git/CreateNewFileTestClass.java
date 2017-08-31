package tests.git;

import model.git.page_objects.GitHubPage;
import org.testng.Assert;
import org.testng.annotations.*;
import reporting.MyLogger;

public class CreateNewFileTestClass extends MainGitTestClass {

    @Test
    public void createNewFileTest(){
        Assert.assertTrue(new GitHubPage().chooseRepository(repositoryName).createNewFile(fileName, fileContext, headerName, commitMessage).isFileCreated(fileName));
        MyLogger.info("---CREATED NEW FILE---");
    }

}
