package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    List<GroupData> beforeGroup = app.group().list();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    List<GroupData> afterGroup = app.group().list();
    Assert.assertEquals(afterGroup.size(), beforeGroup.size() + 1);

    beforeGroup.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeGroup.sort(byId);
    afterGroup.sort(byId);
    Assert.assertEquals(beforeGroup, afterGroup);

  }
}
