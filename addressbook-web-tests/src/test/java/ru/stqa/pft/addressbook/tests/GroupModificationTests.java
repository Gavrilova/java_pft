package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      Groups beforeTest1 = app.group().all();
      GroupData groupTest1 = new GroupData().withName("test1");
      app.group().create(groupTest1);
      Groups afterTest1 = app.group().all();
      assertThat(afterTest1.size(), equalTo(beforeTest1.size() + 1));
      assertThat(afterTest1, equalTo(
              beforeTest1.withAdded(groupTest1.withId(afterTest1.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }
  }

  @Test
  public void testGroupModification() {

    Groups beforeGroup = app.group().all();
    GroupData modifiedGroup = beforeGroup.iterator().next();
    GroupData group = new GroupData().
            withId(modifiedGroup.getId()).withName("test1").withHeader("test5").withFooter("test6");
    app.group().modify(group);
    Groups afterGroup = app.group().all();
    assertThat(afterGroup.size(), equalTo(beforeGroup.size()));
    assertThat(afterGroup, equalTo(beforeGroup.without(modifiedGroup).withAdded(group)));

  }
}
