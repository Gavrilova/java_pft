package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact(new ContactData("Ira", "Aleksandrovna", "Gavrilova", "test4", "myNickname", "Peregrine Falcon Dr.", null, "123-456 7890", "234-567 8901", "345-678 9012", "5647", "gavrilova.irina@gmail.com", "http://www.zello.com/", "test1"), true);
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().selectContactToEditFromList();
    app.getContactHelper().fillContactForm(new ContactData("Ira", "Aleksandrovna", "Gavrilova", "editedTEST", "editedNickname", "Peregrine Falcon Dr.", "Zello", "123-456 1234", "234-567 3457", "345-678 0000", "5647", "gavrilova.irina@gmail.com", "zello", null), false);
    app.getContactHelper().initContactModification();
    app.getNavigationHelper().gotoHomePage();
  }
}
