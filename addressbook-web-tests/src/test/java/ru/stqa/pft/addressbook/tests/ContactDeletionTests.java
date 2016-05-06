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
public class ContactDeletionTests extends TestBase {

  @Test (enabled = false)

  public void testContactDeletion() {
    boolean doWeCreateTest1Group = false;
    app.goTo().gotoHomePage();

    if (!app.getContactHelper().isThereAContact()) {

      app.goTo().groupPage();

      if (app.group().list().size() == 0) {

        doWeCreateTest1Group = true;
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
      ContactData contact1 = new ContactData("Ira", "Aleksandrovna", "Gavrilova", "myNickname", "test4", "Peregrine Falcon Dr.", null, "123-456 7890", "234-567 8901", "345-678 9012", "5647", "gavrilova.irina@gmail.com", "http://www.zello.com/", "test1");
      app.getContactHelper().createContact(contact1);
      app.goTo().gotoHomePage();
      List<ContactData> afterContact1 = app.getContactHelper().getContactList();
      Assert.assertEquals(afterContact1.size(), beforeContact1.size() + 1);

      beforeContact1.add(contact1);
      Comparator<? super ContactData> byIdContact1 = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      beforeContact1.sort(byIdContact1);
      afterContact1.sort(byIdContact1);
      Assert.assertEquals(beforeContact1, afterContact1);
    }
    List<ContactData> beforeContact = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(beforeContact.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptDeletion();
    app.goTo().gotoHomePage();
    List<ContactData> afterContact = app.getContactHelper().getContactList();
    Assert.assertEquals(beforeContact.size(), afterContact.size() + 1);

    beforeContact.remove(beforeContact.size() - 1);
    Assert.assertEquals(beforeContact, afterContact);


    if (doWeCreateTest1Group) {
      app.goTo().groupPage();
      List<GroupData> beforeGroup = app.group().list();
      app.group().selectGroup(beforeGroup.size() - 1);
      app.group().deleteSelectedGroups();
      app.group().returnToGroupPage();
      List<GroupData> afterGroup = app.group().list();
      Assert.assertEquals(afterGroup.size(), beforeGroup.size() - 1);

      beforeGroup.remove(beforeGroup.size()-1);
      Assert.assertEquals(beforeGroup, afterGroup);
    }
  }

}
