package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();


    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> beforeGroup = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(beforeGroup.size()-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test5", "test6"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> afterGroup = app.getGroupHelper().getGroupList();
    Assert.assertEquals(afterGroup.size(), beforeGroup.size());
  }
}
