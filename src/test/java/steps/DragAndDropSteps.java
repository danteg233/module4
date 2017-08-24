package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import tests.yandex.business_objects.DragDropPage;
import tests.yandex.business_objects.RestoreElementPage;
import tests.yandex.business_objects.YandexDiskPage;

public class DragAndDropSteps {
    private DragDropPage dragDropPage;
    private RestoreElementPage restoreElementPage;

    /*Try @BeforeClass here*/

    @Given("^I have YandexDisk page$")
    public void iHaveYandexDiskPage(){
    }

    @And("^I drag one item and move it to the trash$")
    public void iDragOneItemAndMoveItToTheTrash(){
        dragDropPage.dragAndDrop();
        Assert.assertTrue(dragDropPage.isProgressbarPresented(), "Couldn't delete a file");
    }

    @And("^I restore file back$")
    public void iRestoreFileBack(){
        restoreElementPage.restoreElement();
    }

    @Then("^I log out and confirm my log out$")
    public void iLogOutAndConfirmMyLogOut(){
        Assert.assertTrue(new YandexDiskPage().lofOff().isLoginConfirm(), "Couldn't log out!");
    }
}
