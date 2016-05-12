package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by irinagavrilova on 5/12/16.
 */
public class ContactDataTests extends TestBase {

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
      ContactData contact1 = new ContactData().withFirstname("Ira").withMiddlename("Aleksandrovna")
              .withLastname("Gavrilova").withNickname("myNickname").withTitle("test4")
              .withAddress("Peregrine Falcon Dr.").withHomePhone("123-456 7890").withMobilePhone("234-567 8901")
              .withWorkPhone("345-678 9012").withFax("5647").withEmail2("gavrilova.irina@gmail.com")
              .withHomepage("http://www.zello.com/").withGroup("test1");
      app.contact().createContact(contact1);
      app.goTo().home();
      assertThat(app.contact().count(), equalTo(beforeContact1.size() + 1));
      Contacts afterContact1 = app.contact().all();
      assertThat(afterContact1, equalTo(
              beforeContact1.withAdded(contact1.withId(afterContact1.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  @Test
  public void testContactAddress() {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

  }

  @Test
  public void testContactEmails() {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }


  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals("")).map(ContactDataTests::cleaned).collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((e) -> !e.equals("")).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}