package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){

      List<GroupData> beforeTest1 = app.getGroupHelper().getGroupList();
      GroupData groupTest1 = new GroupData("test1", null, null);
      app.getGroupHelper().createGroup(groupTest1);
      List<GroupData> afterTest1 = app.getGroupHelper().getGroupList();
      Assert.assertEquals(afterTest1.size(), beforeTest1.size() + 1);

      beforeTest1.add(groupTest1);
      Comparator<? super GroupData> byIdTest1 = (gT1, gT2) -> Integer.compare(gT1.getId(), gT2.getId());
      beforeTest1.sort(byIdTest1);
      afterTest1.sort(byIdTest1);
      Assert.assertEquals(beforeTest1, afterTest1);
      app.getNavigationHelper().gotoGroupPage();

    }
    List<GroupData> beforeGroup = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(beforeGroup.size()-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> afterGroup = app.getGroupHelper().getGroupList();
    Assert.assertEquals(afterGroup.size(), beforeGroup.size() - 1);

    beforeGroup.remove(beforeGroup.size()-1);
    Assert.assertEquals(beforeGroup, afterGroup);
  }

}
