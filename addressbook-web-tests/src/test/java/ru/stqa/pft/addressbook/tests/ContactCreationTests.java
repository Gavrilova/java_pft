package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @BeforeMethod

  public void ensurePrecondition() {
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
  }

  @Test

  public void testContactCreation() {
    app.goTo().home();
    List<ContactData> beforeContact = app.contact().list();
    app.goTo().addNew();
    ContactData contact = new ContactData().withFirstname("Maria").withMiddlename("Alexeevna")
            .withLastname( "Gavrilova").withNickname("myNickname").withTitle("test4")
            .withAddress("Peregrine Falcon Dr.").withHome("345-678 9012").withMobile("123-456 7890")
            .withWork("234-567 8901").withFax("5647").withEmail2("gavrilova.irina@gmail.com")
            .withCompany("http://www.zello.com/").withGroup("test1");
    app.contact().createContact(contact);
    app.goTo().home();
    List<ContactData> afterContact = app.contact().list();
    Assert.assertEquals(afterContact.size(), beforeContact.size() + 1);

    beforeContact.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    beforeContact.sort(byId);
    afterContact.sort(byId);
    Assert.assertEquals(beforeContact, afterContact);
  }
}
