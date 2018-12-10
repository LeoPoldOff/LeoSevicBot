import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class BotTest {

    @Test
    public void unknownRespond() {
        var bot = new Bot();
        var response = bot.respond("Unknown command", "id");
        Assert.assertEquals("Unknown", response.userRespond);
    }

    @Test
    public void helpOnPreStart() {
        var bot = new Bot();
        var response = bot.respond("help", "id");
        var commandList = response.userRespond.split("\n");
        Assert.assertEquals(bot.currentCommandList("id").size(), commandList.length);
    }

    @Test
    public void wrongBDayFormat() {
        var bot = new Bot();
        bot.respond("my birthday", "id");
        var a = bot.respond("911199", "id");
        Assert.assertEquals("Wrong date format", a.userRespond);
    }

    @Test
    public void fieldAlreadyFilled() {
        var bot = new Bot();
        bot.respond("my sex", "id");
        bot.respond("1", "id");
        var a = bot.respond("my sex", "id");
        Assert.assertEquals("This field already filled", a.userRespond);
    }

    @Test
    public void checkInfo() {
        deleteFile("id2");
        var bot = new Bot();
        bot.respond("my birthday", "id2");
        bot.respond("10.12.1999", "id2");
        bot.respond("my sex", "id2");
        bot.respond("1", "id2");
        bot.respond("my region", "id2");
        bot.respond("1", "id2");
        bot.respond("my sex", "id2");
        bot.respond("my sport habit", "id2");
        bot.respond("1", "id2");
        bot.respond("my alcohol habit", "id2");
        bot.respond("1", "id2");
        bot.respond("my smoking habit", "id2");
        bot.respond("1", "id2");
        bot.respond("my weight", "id2");
        bot.respond("60", "id2");
        bot.respond("my height", "id2");
        bot.respond("170", "id2");
        bot.respond("ok", "id2");
        var a = bot.respond("my info", "id2");
        var expected = "Sex: Male\n" +
                "Date of birth: 10.12.1999\n" +
                "Region: Russia\n" +
                "Weight: 60 kg\n" +
                "Height: 170 sm\n" +
                "Attitude to smoking: One pack of cigarettes a day or more\n" +
                "Attitude to alcohol: I`m drinking everyday\n" +
                "Attitude to sport: I`m professional athlete";
        Assert.assertEquals(expected, a.userRespond);

        var expectedJSON = "{\"sportRange\":1,\"sex\":1,\"alcoholRange\":1,\"weight\":60,\"region\":1," +
                "\"smokingRange\":1,\"birthDate\":\"10.12.1999\",\"height\":170}";
        var filePath = new File("").getAbsolutePath() + "/data/id2.json";
        var actualJSON = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            actualJSON = br.readLine();
        } catch (java.io.IOException e) {
            Assert.fail();
        }
        Assert.assertEquals(expectedJSON, actualJSON);
    }

    private void deleteFile (String chatId){
        var filePath = new File("").getAbsolutePath() + "/data/" + chatId + ".json";
        var file = new File(filePath);
        file.delete();
    }
}
