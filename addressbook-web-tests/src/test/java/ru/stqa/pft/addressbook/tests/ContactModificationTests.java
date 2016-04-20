package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContactToEditFromList();
    app.getContactHelper().fillContactForm(new ContactData("Ira", "Aleksandrovna", "Gavrilova", "editedTEST", "editedNickname", "Peregrine Falcon Dr.", "Zello", "123-456 7890", "234-567 8901", "345-678 9012", "5647", "gavrilova.irina@gmail.com", "zello"));
    app.getContactHelper().initContactModification();
    app.getNavigationHelper().gotoHomePage();
  }
}
