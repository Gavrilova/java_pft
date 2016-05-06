package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactModificationTests extends TestBase {

  @Test (enabled = false)

  public void testContactModification() {
    app.goTo().gotoHomePage();

    if (!app.getContactHelper().isThereAContact()) {

      app.goTo().groupPage();
      if (app.group().list().size() == 0){
        List<GroupData> beforeTest1 = app.group().list();
        GroupData groupTest1 = new GroupData().withName("test1");
        app.group().create(groupTest1);
        List<GroupData> afterTest1 = app.group().list();
        Assert.assertEquals(afterTest1.size(), beforeTest1.size() + 1);

        beforeTest1.add(groupTest1);
        Comparator<? super GroupData> byIdTest1 = (gT1, gT2) -> Integer.compare(gT1.getId(), gT2.getId());
        beforeTest1.sort(byIdTest1);
        afterTest1.sort(byIdTest1);
        Assert.assertEquals(beforeTest1, afterTest1);

      }
      app.goTo().gotoHomePage();
      List<ContactData> beforeContact1 = app.getContactHelper().getContactList();
      app.goTo().gotoAddNewContactPage();
      ContactData contact1 = new ContactData("Irina", "Aleksandrovna", "Gavrilova", "myNickname","test4",  "Peregrine Falcon Dr.", null, "123-456 7890", "234-567 8901", "345-678 9012", "5647", "gavrilova.irina@gmail.com", "http://www.zello.com/", "test1");
      app.getContactHelper().createContact(contact1);
      app.goTo().gotoHomePage();
      List<ContactData> afterContact1 = app.getContactHelper().getContactList();
      Assert.assertEquals(afterContact1.size(), beforeContact1.size() + 1);

      beforeContact1.add(contact1);
      Comparator<? super ContactData> byIdContact1 = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      beforeContact1.sort(byIdContact1);
      afterContact1.sort(byIdContact1);
      Assert.assertEquals(beforeContact1, afterContact1);

      app.goTo().gotoHomePage();
    }
    List<ContactData> beforeContact = app.getContactHelper().getContactList();
    app.getContactHelper().selectContactToEditFromList(beforeContact.size()-1);
    ContactData contact = new ContactData(beforeContact.get(beforeContact.size()-1).getId(), "Ira", "Aleksandrovna", "Gavrilova", "editedNickname", "editedTEST",  "Peregrine Falcon Dr.", "Zello", "123-456 1234", "234-567 3457", "345-678 0000", "5647", "gavrilova.irina@gmail.com", "zello", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().initContactModification();
    app.goTo().gotoHomePage();
    List<ContactData> afterContact = app.getContactHelper().getContactList();
    Assert.assertEquals(beforeContact.size(), afterContact.size());

    beforeContact.remove(beforeContact.size()-1);
    beforeContact.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    beforeContact.sort(byId);
    afterContact.sort(byId);
    Assert.assertEquals(beforeContact, afterContact);

  }
}
