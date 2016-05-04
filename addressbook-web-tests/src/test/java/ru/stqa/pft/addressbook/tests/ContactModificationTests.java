package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

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
      app.getContactHelper().createContact(new ContactData("Irina", "Aleksandrovna", "Gavrilova", "myNickname","test4",  "Peregrine Falcon Dr.", null, "123-456 7890", "234-567 8901", "345-678 9012", "5647", "gavrilova.irina@gmail.com", "http://www.zello.com/", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    List<ContactData> beforeContact = app.getContactHelper().getContactList();
    app.getContactHelper().selectContactToEditFromList(beforeContact.size()-1);
    ContactData contact = new ContactData(beforeContact.get(beforeContact.size()-1).getId(), "Ira", "Aleksandrovna", "Gavrilova", "editedNickname", "editedTEST",  "Peregrine Falcon Dr.", "Zello", "123-456 1234", "234-567 3457", "345-678 0000", "5647", "gavrilova.irina@gmail.com", "zello", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().initContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> afterContact = app.getContactHelper().getContactList();
    Assert.assertEquals(beforeContact.size(), afterContact.size());

    beforeContact.remove(beforeContact.size()-1);
    beforeContact.add(contact);
    Assert.assertEquals(new HashSet<Object>(beforeContact), new HashSet<Object>(afterContact));

  }
}
