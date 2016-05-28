package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by irinagavrilova on 5/27/16.
 */
public class AddContactToGroupTests extends TestBase {

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
    Contacts contactsAtAll = app.db().contacts();
    ContactData contact = contactsAtAll.iterator().next();
    Groups groupsAtAll = app.db().groups();
    GroupData assosiateGroup = app.db().groups().iterator().next();
    Groups groupsInContact = contact.getGroups();
    int before = groupsInContact.size();

    if (groupsInContact.size() == groupsAtAll.size()) {  //verify, that we have at least one group to add to contact:
      app.goTo().groupPage();
      assosiateGroup = new GroupData().withName("AssosiateGroupName");
      app.group().create(assosiateGroup);
    } else {                                                    //choose one NOT from contact.inGroups() list;
      for (GroupData groupAll : groupsAtAll) {
        for (GroupData groupContact : groupsInContact) {
          if (groupAll.getId() != groupContact.getId()) {        //int assosiateGroupID = groupAll.getId();
            assosiateGroup = groupAll;
            break;
          }
        }
      }
    }
    app.goTo().home();
    app.contact().addContactToGroup(contact, assosiateGroup);
    assertThat(contact.getGroups().size(), equalTo(before + 1)); //validate, that getGroups.size() list increased by 1;
    assertThat(contact.getGroups(), equalTo(groupsInContact.withAdded(assosiateGroup)));
  }
}



