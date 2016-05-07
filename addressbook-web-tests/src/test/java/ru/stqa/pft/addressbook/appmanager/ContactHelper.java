package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
    click(By.id("content"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    click(By.xpath("//div[@id='content']//label[.='Last name:']"));
    type(By.name("lastname"), contactData.getLastname());
    click(By.xpath("//div[@id='content']//label[.='Nickname:']"));
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("homepage"), contactData.getHomepage());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {

    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void selectContactToEditFromList(int index) {
    String locatorToSelectContactToEdit = "//table[@id='maintable']/tbody/tr[" + (2 + index) + "]/td[8]/a/img";
    wd.findElement(By.xpath(locatorToSelectContactToEdit)).click();
  }

  public void initContactModification() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public void modifyContact(int index, ContactData contact) {
    selectContactToEditFromList(index);
    fillContactForm(contact, false);
    initContactModification();
  }

  public void deleteContact(int index) {
    selectContact(index);
    deleteSelectedContact();
    acceptDeletion();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstname, null, lastname, null, null, null, null, null, null, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
