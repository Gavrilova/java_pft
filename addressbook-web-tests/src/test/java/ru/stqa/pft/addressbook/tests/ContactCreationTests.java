package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod

  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {

      Groups beforeTest1 = app.group().all();
      GroupData groupTest1 = new GroupData().withName("test1");
      app.group().create(groupTest1);
      assertThat(app.group().count(), equalTo(beforeTest1.size() + 1));
      Groups afterTest1 = app.group().all();
      assertThat(afterTest1, equalTo(
              beforeTest1.withAdded(groupTest1.withId(afterTest1.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }
  }

  @Test

  public void testContactCreation() {
    app.goTo().home();
    Contacts beforeContact = app.contact().all();
    app.goTo().addNew();
    ContactData contact = new ContactData().withFirstname("Maria").withMiddlename("Alexeevna")
            .withLastname("Gavrilova").withNickname("myNickname").withTitle("test4")
            .withAddress("Peregrine Falcon Dr.").withHome("345-678 9012").withMobile("123-456 7890")
            .withWork("234-567 8901").withFax("5647").withEmail2("gavrilova.irina@gmail.com")
            .withCompany("http://www.zello.com/").withGroup("test1");
    app.contact().createContact(contact);
    app.goTo().home();
    Contacts afterContact = app.contact().all();
    assertThat(afterContact.size(), equalTo(beforeContact.size() + 1));
    assertThat(afterContact, equalTo(
            beforeContact.withAdded(contact.withId(afterContact.stream().mapToInt((c) -> c.getId()).max().getAsInt()))))
    ;
  }
}
