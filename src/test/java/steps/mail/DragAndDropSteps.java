package steps.mail;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import model.yandex.page_objects.YandexDiskPage;

public class DragAndDropSteps {
    private YandexDiskPage yandexDiskPage;

    @Given("^I have YandexDisk main page$")
    public void iHaveYandexDiskMainPage(){
        yandexDiskPage = new YandexDiskPage();
    }

    @And("^I drag one item and move it to the trash$")
    public void iDragOneItemAndMoveItToTheTrash(){
        yandexDiskPage.dragAndDrop();
        Assert.assertTrue(yandexDiskPage.isProgressbarPresented(), "Couldn't delete a file");
    }

    @And("^I restore file back$")
    public void iRestoreFileBack(){
        yandexDiskPage.restoreElement();
    }

    @Then("^I log out and confirm my log out$")
    public void iLogOutAndConfirmMyLogOut(){
        Assert.assertTrue(yandexDiskPage.lofOff().isLogOutConfirm(), "Couldn't log out!");
    }



}
