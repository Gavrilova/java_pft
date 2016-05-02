package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getNavigationHelper().gotoHomePage();

    List<ContactData> beforeContact = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoAddNewContactPage();
    app.getContactHelper().createContact(new ContactData("Ira", "Aleksandrovna", "Gavrilova", "myNickname", "test4",  "Peregrine Falcon Dr.", null, "123-456 7890", "234-567 8901", "345-678 9012", "5647", "gavrilova.irina@gmail.com", "http://www.zello.com/", "test1"));
    app.getNavigationHelper().gotoHomePage();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> afterContact = app.getContactHelper().getContactList();
    Assert.assertEquals(afterContact.size(), beforeContact.size() + 1);
  }

}
