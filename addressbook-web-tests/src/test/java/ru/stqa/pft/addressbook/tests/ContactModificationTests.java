package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups afterTest1 = app.group().all();
        assertThat(afterTest1.size(), equalTo(beforeTest1.size() + 1));
        assertThat(afterTest1, equalTo(
                beforeTest1.withAdded(groupTest1.withId(afterTest1.stream().mapToInt((gT1) -> gT1.getId()).max().getAsInt()))));

      }
      app.goTo().home();
      Set<ContactData> beforeContact1 = app.contact().all();
      app.goTo().addNew();
      ContactData contact1 = new ContactData().withFirstname("Irina")
              .withMiddlename("Aleksandrovna").withLastname("Gavrilova")
              .withNickname("myNickname").withTitle("test4").withAddress("Peregrine Falcon Dr.")
              .withHome("123-456 7890").withWork("234-567 8901").withMobile("345-678 9012")
              .withFax("1945").withEmail2("gavrilova.irina@gmail.com")
              .withHomepage("http://www.zello.com/").withGroup("test1");
      app.contact().createContact(contact1);
      app.goTo().home();
      Set<ContactData> afterContact1 = app.contact().all();
      assertEquals(afterContact1.size(), beforeContact1.size() + 1);
      contact1.withId(afterContact1.stream().mapToInt((c1) -> c1.getId()).max().getAsInt());
      beforeContact1.add(contact1);
      assertEquals(beforeContact1, afterContact1);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().home();
    Set<ContactData> beforeContact = app.contact().all();
    ContactData modifiedContact = beforeContact.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname("Ira").withMiddlename("Aleksandrovna").withLastname("Gavrilova")
            .withNickname("editedNickname").withTitle("editedTEST")
            .withAddress("Peregrine Falcon Dr.").withCompany("Zello").withHome("123-456 1234")
            .withMobile("234-567 3457").withWork("345-678 0000").withFax("5647").withEmail2("gavrilova.irina@gmail.com")
            .withHomepage("zello");
    app.contact().modifyContact(contact);
    app.goTo().home();
    Set<ContactData> afterContact = app.contact().all();
    assertEquals(beforeContact.size(), afterContact.size());

    beforeContact.remove(modifiedContact);
    beforeContact.add(contact);
    assertEquals(beforeContact, afterContact);
  }
}
