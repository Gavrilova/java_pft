package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact(new ContactData("Ira", "Aleksandrovna", "Gavrilova", "test4", "myNickname", "Peregrine Falcon Dr.", null, "123-456 7890", "234-567 8901", "345-678 9012", "5647", "gavrilova.irina@gmail.com", "http://www.zello.com/", "test1"), true);
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptDeletion();
    app.getNavigationHelper().gotoHomePage();
  }

}
