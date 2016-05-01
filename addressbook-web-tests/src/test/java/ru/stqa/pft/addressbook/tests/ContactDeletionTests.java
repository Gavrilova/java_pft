package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {
    boolean doWeCreateTest1Group = false;
    app.getNavigationHelper().gotoHomePage();

    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoGroupPage();
      if (!app.getGroupHelper().isThereAGroup()) {
        doWeCreateTest1Group = true;
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact(new ContactData("Ira", "Aleksandrovna", "Gavrilova", "test4", "myNickname", "Peregrine Falcon Dr.", null, "123-456 7890", "234-567 8901", "345-678 9012", "5647", "gavrilova.irina@gmail.com", "http://www.zello.com/", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptDeletion();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(before, after +1);
    if (doWeCreateTest1Group) {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().selectGroup();
      app.getGroupHelper().deleteSelectedGroups();
      app.getGroupHelper().returnToGroupPage();
    }
  }

}
