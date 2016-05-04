package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> beforeGroup = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test11", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> afterGroup = app.getGroupHelper().getGroupList();
    Assert.assertEquals(afterGroup.size(), beforeGroup.size() + 1);
    group.setId(afterGroup.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    beforeGroup.add(group);
    Assert.assertEquals(new HashSet<Object>(beforeGroup), new HashSet<Object>(afterGroup));

  }
}
