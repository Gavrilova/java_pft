package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();

    if (!app.getContactHelper().isThereAContact()) {

      app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHelper().isThereAGroup()){
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact(new ContactData("Ira", "Aleksandrovna", "Gavrilova", "myNickname","test4",  "Peregrine Falcon Dr.", null, "123-456 7890", "234-567 8901", "345-678 9012", "5647", "gavrilova.irina@gmail.com", "http://www.zello.com/", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    int beforeContact = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContactToEditFromList();
    app.getContactHelper().fillContactForm(new ContactData("Ira", "Aleksandrovna", "Gavrilova", "editedNickname", "editedTEST",  "Peregrine Falcon Dr.", "Zello", "123-456 1234", "234-567 3457", "345-678 0000", "5647", "gavrilova.irina@gmail.com", "zello", null), false);
    app.getContactHelper().initContactModification();
    app.getNavigationHelper().gotoHomePage();
    int afterContact = app.getContactHelper().getContactCount();
    Assert.assertEquals(beforeContact, afterContact);
  }
}
