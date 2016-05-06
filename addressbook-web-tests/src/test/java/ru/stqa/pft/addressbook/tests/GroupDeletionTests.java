package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      List<GroupData> beforeTest1 = app.group().list();
      GroupData groupTest1 = new GroupData().withName("test1");
      app.group().create(groupTest1);
      List<GroupData> afterTest1 = app.group().list();
      Assert.assertEquals(afterTest1.size(), beforeTest1.size() + 1);

      beforeTest1.add(groupTest1);
      Comparator<? super GroupData> byIdTest1 = (gT1, gT2) -> Integer.compare(gT1.getId(), gT2.getId());;
      beforeTest1.sort(byIdTest1);
      afterTest1.sort(byIdTest1);
      Assert.assertEquals(beforeTest1, afterTest1);
    }
  }

  @Test
  public void testGroupDeletion() {

    List<GroupData> beforeGroup = app.group().list();
    int index = beforeGroup.size()-1;
    app.group().delete(index);
    List<GroupData> afterGroup = app.group().list();
    Assert.assertEquals(afterGroup.size(), index);

    beforeGroup.remove(index);
    Assert.assertEquals(beforeGroup, afterGroup);
  }
}
