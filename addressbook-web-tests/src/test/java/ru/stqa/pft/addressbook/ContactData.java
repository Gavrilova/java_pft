package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String address;
  private final String company;
  private final String home;
  private final String mobile;
  private final String work;
  private final String fax;
  private final String email2;
  private final String homepage;

  public ContactData(String firstname, String middlename, String lastname, String nickname, String title, String address, String company, String home, String mobile, String work, String fax, String email2, String homepage) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.address = address;
    this.company = company;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.fax = fax;
    this.email2 = email2;
    this.homepage = homepage;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getAddress() {
    return address;
  }

  public String getCompany() {
    return company;
  }

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail2() {
    return email2;
  }

  public String getHomepage() {
    return homepage;
  }
}
