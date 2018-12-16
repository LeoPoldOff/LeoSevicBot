import org.junit.Assert;
import org.junit.Test;

public class TreeTest {

    @Test
    public void calcAgeTest() {
        var uInfo = createUserInfo(1, "01.01.200" ,170, 70,
                             1,1,1,1);
        Assert.assertEquals(48, Tree.calcAge(uInfo));
    }

    private UserInfo createUserInfo(int sex, String bDay, int height, int weight,
                                    int region, int smokRng, int alcRng, int spRng){
        var info = new UserInfo();
        info.sex= sex;
        info.birthDate = bDay;
        info.height = height;
        info.weight = weight;
        info.region = region;
        info.smokingRange = smokRng;
        info.alcoholRange = alcRng;
        info.sportRange = spRng;

        return info;
    }
}
