package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoAddNewContactPage();
    app.fillContactForm(new ContactData("Irina", "Aleksandrovna", "Gavrilova", "test4", "myNickname", "Peregrine Falcon Dr.", "Zello", "123-456 7890", "234-567 8901", "345-678 9012", "5647", "gavrilova.irina@gmail.com", "http://www.zello.com/"));
    app.submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
  }

}
