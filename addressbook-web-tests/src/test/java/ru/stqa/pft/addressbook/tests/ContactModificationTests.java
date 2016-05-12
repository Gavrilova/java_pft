package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
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
      app.goTo().home();
      Contacts beforeContact1 = app.contact().all();
      app.goTo().addNew();
      ContactData contact1 = new ContactData().withFirstname("Irina")
              .withMiddlename("Aleksandrovna").withLastname("Gavrilova")
              .withNickname("myNickname").withTitle("test4").withAddress("Peregrine Falcon Dr.")
              .withHome("123-456 7890").withWork("234-567 8901").withMobile("345-678 9012")
              .withFax("1945").withEmail2("gavrilova.irina@gmail.com")
              .withHomepage("http://www.zello.com/").withGroup("test1");
      app.contact().createContact(contact1);
      app.goTo().home();
      Contacts afterContact1 = app.contact().all();
      assertThat(afterContact1.size(), equalTo(beforeContact1.size() + 1));
      assertThat(afterContact1, equalTo(
              beforeContact1.withAdded(contact1.withId(afterContact1.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().home();
    Contacts beforeContact = app.contact().all();
    ContactData modifiedContact = beforeContact.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname("Ira").withMiddlename("Aleksandrovna").withLastname("Gavrilova")
            .withNickname("editedNickname").withTitle("editedTEST")
            .withAddress("Peregrine Falcon Dr.").withCompany("Zello").withHome("123-456 1234")
            .withMobile("234-567 3457").withWork("345-678 0000").withFax("5647").withEmail2("gavrilova.irina@gmail.com")
            .withHomepage("zello");
    app.contact().modifyContact(contact);
    app.goTo().home();
    Contacts afterContact = app.contact().all();
    assertThat(beforeContact.size(), equalTo(afterContact.size()));
    assertThat(afterContact, equalTo(
            beforeContact.without(modifiedContact).withAdded(contact)));
  }
}
