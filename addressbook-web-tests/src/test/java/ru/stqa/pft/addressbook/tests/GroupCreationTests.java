package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
      app.goTo().groupPage();
      Groups beforeGroup = app.group().all();
      GroupData group = new GroupData().withName("test2");
      app.group().create(group);
      assertThat(app.group().count(), equalTo(beforeGroup.size() + 1));
      Groups afterGroup = app.group().all();

      assertThat(afterGroup, equalTo(
              beforeGroup.withAdded(group.withId(afterGroup.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test
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
