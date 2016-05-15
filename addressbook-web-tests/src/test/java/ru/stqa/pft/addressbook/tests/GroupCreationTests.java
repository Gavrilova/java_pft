package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
    list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
    list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
    return list.iterator();
  }

  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups beforeGroup = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(beforeGroup.size() + 1));
    Groups afterGroup = app.group().all();
    assertThat(afterGroup, equalTo(
            beforeGroup.withAdded(group.withId(afterGroup.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testGroupBadCreation() {
    app.goTo().groupPage();
    Groups beforeGroup = app.group().all();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(beforeGroup.size()));
    Groups afterGroup = app.group().all();
    assertThat(afterGroup, equalTo(beforeGroup));
  }
}
