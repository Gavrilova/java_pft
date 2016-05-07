package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.goTo().groupPage();
      if (app.group().list().size() == 0) {
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
      app.goTo().home();
      List<ContactData> beforeContact1 = app.contact().list();
      app.goTo().addNew();
      ContactData contact1 = new ContactData().withFirstname("Irina")
              .withMiddlename("Aleksandrovna").withLastname("Gavrilova")
              .withNickname("myNickname").withTitle("test4").withAddress("Peregrine Falcon Dr.")
              .withHome("123-456 7890").withWork("234-567 8901").withMobile("345-678 9012")
              .withFax("1945").withEmail2("gavrilova.irina@gmail.com")
              .withHomepage("http://www.zello.com/").withGroup("test1");
      app.contact().createContact(contact1);
      app.goTo().home();
      List<ContactData> afterContact1 = app.contact().list();
      Assert.assertEquals(afterContact1.size(), beforeContact1.size() + 1);

      beforeContact1.add(contact1);
      Comparator<? super ContactData> byIdContact1 = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      beforeContact1.sort(byIdContact1);
      afterContact1.sort(byIdContact1);
      Assert.assertEquals(beforeContact1, afterContact1);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().home();
    List<ContactData> beforeContact = app.contact().list();
    int index = beforeContact.size() - 1;
    ContactData contact = new ContactData().withId(beforeContact.get(index).getId())
            .withFirstname("Ira").withMiddlename("Aleksandrovna").withLastname("Gavrilova")
            .withNickname("editedNickname").withTitle("editedTEST")
            .withAddress("Peregrine Falcon Dr.").withCompany("Zello").withHome("123-456 1234")
            .withMobile("234-567 3457").withWork("345-678 0000").withFax("5647").withEmail2("gavrilova.irina@gmail.com")
            .withHomepage("zello");
    app.contact().modifyContact(index, contact);
    app.goTo().home();
    List<ContactData> afterContact = app.contact().list();
    Assert.assertEquals(beforeContact.size(), afterContact.size());

    beforeContact.remove(index);
    beforeContact.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    beforeContact.sort(byId);
    afterContact.sort(byId);
    Assert.assertEquals(beforeContact, afterContact);
  }
}
