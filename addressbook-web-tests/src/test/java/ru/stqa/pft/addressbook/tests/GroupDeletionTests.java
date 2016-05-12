package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
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

  @Test
  public void testGroupDeletion() {

    Groups beforeGroup = app.group().all();
    GroupData deletedGroup = beforeGroup.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(beforeGroup.size() - 1));
    Groups afterGroup = app.group().all();
    assertThat(afterGroup, equalTo(beforeGroup.without(deletedGroup)));
  }
}
