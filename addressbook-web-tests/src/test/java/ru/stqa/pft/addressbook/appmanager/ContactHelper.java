package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
    click(By.id("content"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    click(By.name("theform"));
    type(By.name("middlename"), contactData.getMiddlename());
    click(By.xpath("//div[@id='content']//label[.='Last name:']"));
    type(By.name("lastname"), contactData.getLastname());
    click(By.xpath("//div[@id='content']//label[.='Nickname:']"));
    type(By.name("nickname"), contactData.getNickname());
    click(By.name("theform"));
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    click(By.name("theform"));
    type(By.name("work"), contactData.getWork());
    click(By.name("theform"));
    type(By.name("fax"), contactData.getFax());
    click(By.name("theform"));
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("homepage"), contactData.getHomepage());
  }
  public void selectContact() {
    click(By.name("selected[]"));
  }
  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }
  public void acceptContactDeletion(){
    wd.switchTo().alert().accept();
  }
}
