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

    if (app.db().groups().size()==0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {

    Groups beforeGroup = app.db().groups();
    GroupData modifiedGroup = beforeGroup.iterator().next();
    GroupData group = new GroupData().
            withId(modifiedGroup.getId()).withName("test1").withHeader("test5").withFooter("test6");
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(beforeGroup.size()));
    Groups afterGroup = app.db().groups();
    assertThat(afterGroup, equalTo(beforeGroup.without(modifiedGroup).withAdded(group)));

  }
}
