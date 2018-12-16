import org.junit.*;

public class UserInfoTest {
    @Test
    public void userInfoTest(){
        var info = new UserInfo();
        info.updateSex("1");
        Assert.assertEquals(1, info.sex);
        info.updateAlcoholRange("1");
        Assert.assertEquals(1, info.alcoholRange);
        info.updateHeight("170");
        Assert.assertEquals(170, info.height);
        info.updateRegion("1");
        Assert.assertEquals(1, info.region);
        info.updateSmokingRange("1");
        Assert.assertEquals(1, info.smokingRange);
        info.updateSportRange("1");
        Assert.assertEquals(1, info.sportRange);
        info.updateWeight("70");
        Assert.assertEquals(70, info.weight);
    }
}
