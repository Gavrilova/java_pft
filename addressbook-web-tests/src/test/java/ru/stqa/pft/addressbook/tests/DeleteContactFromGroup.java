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
 * Created by irinagavrilova on 5/28/16.
 */
public class DeleteContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {

      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        Groups beforeTest1 = app.db().groups();
        GroupData groupTest1 = new GroupData().withName("test1");
        app.group().create(groupTest1);
        assertThat(app.db().groups().size(), equalTo(beforeTest1.size() + 1));
        Groups afterTest1 = app.db().groups();
        assertThat(afterTest1, equalTo(
                beforeTest1.withAdded(groupTest1.withId(afterTest1.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

      }
      app.goTo().home();
      Contacts beforeContact1 = app.db().contacts();
      app.goTo().addNew();
      ContactData contact1 = new ContactData().withFirstname("Irina").withMiddlename("Aleksandrovna")
              .withLastname("Gavrilova").withAddress("Peregrine Falcon Dr.")
              .withHomePhone("123-456 7890").withEmail2("gavrilova.irina@gmail.com");
      app.contact().createContact(contact1);
      app.goTo().home();
      assertThat(app.db().contacts().size(), equalTo(beforeContact1.size() + 1));
      Contacts afterContact1 = app.db().contacts();
      assertThat(afterContact1, equalTo(
              beforeContact1.withAdded(contact1.withId(afterContact1.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
  }

  @Test
  public void addContactToGroupTest() {

    Groups groupsAtAll = app.db().groups();
    GroupData assosiateGroup = groupsAtAll.iterator().next();
    for (GroupData groupData : groupsAtAll) { //find group with at least one assosiate contact;
      if (groupData.getContacts().size() > 0) {
        assosiateGroup = groupData;
        break;
      } else { //add any contact to group;
        app.goTo().home();
        app.contact().addContactToGroup(app.db().contacts().iterator().next(), assosiateGroup);
        app.goTo().home();
      }
    }
    Contacts beforeDeleting = assosiateGroup.getContacts();
    int before = assosiateGroup.getContacts().size();
    ContactData assosiateContact = assosiateGroup.getContacts().iterator().next();
    app.goTo().groupPage();
    app.contact().deleteContactFromGroup(assosiateGroup, assosiateContact);
    app.goTo().home();
    assertThat(assosiateGroup.getContacts().size(), equalTo(before - 1));//validate, that getContacts list decreased by 1;
    assertThat(assosiateGroup.getContacts(), equalTo(beforeDeleting.without(assosiateContact)));
  }
}

