package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> beforeGroup = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    List<GroupData> afterGroup = app.getGroupHelper().getGroupList();
    Assert.assertEquals(afterGroup.size(), beforeGroup.size() + 1);

  }

}
