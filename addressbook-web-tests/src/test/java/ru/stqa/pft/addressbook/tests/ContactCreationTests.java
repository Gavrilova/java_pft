package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

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
    File photo = new File("src/test/resources/Zello.png");
    ContactData contact = new ContactData().withFirstname("Maria").withMiddlename("Alexeevna")
            .withLastname("Gavrilova").withNickname("myNickname").withTitle("test4")
            .withAddress("Peregrine Falcon Dr.").withHomePhone("345-678 9012").withMobilePhone("123-456 7890")
            .withWorkPhone("234-567 8901").withFax("5647").withEmail2("gavrilova.irina@gmail.com")
            .withCompany("http://www.zello.com/").withPhoto(photo).withGroup("test1");
    app.contact().createContact(contact);
    app.goTo().home();
    assertThat(app.contact().count(), equalTo(beforeContact.size() + 1));
    Contacts afterContact = app.contact().all();
    assertThat(afterContact, equalTo(
            beforeContact.withAdded(contact.withId(afterContact.stream().mapToInt((c) -> c.getId()).max().getAsInt()))))
    ;
  }
  @Test (enabled = false)

  public void testCurrentDir() {
    File currentDir = new File (".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/Zello.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
