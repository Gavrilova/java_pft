package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
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
