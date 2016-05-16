package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
    }.getType()); //List<ContactData>.class
    return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
  }

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

  @Test(dataProvider = "validContactsFromJson")

  public void testContactCreation(ContactData contact) {
    app.goTo().home();
    Contacts beforeContact = app.contact().all();
    app.goTo().addNew();
    File photo = new File("src/test/resources/Zello.png");
    app.contact().createContact(contact.withPhoto(photo));
    app.goTo().home();
    assertThat(app.contact().count(), equalTo(beforeContact.size() + 1));
    Contacts afterContact = app.contact().all();
    assertThat(afterContact, equalTo(
            beforeContact.withAdded(contact.withId(afterContact.stream().mapToInt((c) -> c.getId()).max().getAsInt()))))
    ;
  }

  @Test(enabled = false)

  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/Zello.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
