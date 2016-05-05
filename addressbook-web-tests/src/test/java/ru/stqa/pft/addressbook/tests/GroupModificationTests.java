package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();


    if (!app.getGroupHelper().isThereAGroup()) {

      List<GroupData> beforeTest1 = app.getGroupHelper().getGroupList();
      GroupData groupTest1 = new GroupData("test1", null, null);
      app.getGroupHelper().createGroup(groupTest1);
      List<GroupData> afterTest1 = app.getGroupHelper().getGroupList();
      Assert.assertEquals(afterTest1.size(), beforeTest1.size() + 1);

      beforeTest1.add(groupTest1);
      Comparator<? super GroupData> byIdTest1 = (gT1, gT2) -> Integer.compare(gT1.getId(), gT2.getId());;
      beforeTest1.sort(byIdTest1);
      afterTest1.sort(byIdTest1);
      Assert.assertEquals(beforeTest1, afterTest1);


    }
    List<GroupData> beforeGroup = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(beforeGroup.size()-1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(beforeGroup.get(beforeGroup.size()-1).getId(), "test1", "test5", "test6");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> afterGroup = app.getGroupHelper().getGroupList();
    Assert.assertEquals(afterGroup.size(), beforeGroup.size());

    beforeGroup.remove(beforeGroup.size()-1);
    beforeGroup.add(group);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeGroup.sort(byId);
    afterGroup.sort(byId);
    Assert.assertEquals(beforeGroup, afterGroup);


  }
}
