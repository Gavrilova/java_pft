package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    int beforeGroup = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup(beforeGroup-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    int afterGroup = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(afterGroup, beforeGroup - 1);
  }

}
