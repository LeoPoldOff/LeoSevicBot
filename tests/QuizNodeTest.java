import org.junit.Assert;
import org.junit.Test;

public class QuizNodeTest {
    QuizNode node = new QuizNode();

    @Test
    public void fillCommands() {
        var startNode = new StartNode();
        var sexNode = new SexNode();
        var regionNode = new RegionNode();
        var smokingNode = new SmokingNode();
        var alcoholNode = new AlcoholNode();
        var sportNode = new SportNode();
        var heightNode = new HeightNode();
        var weightNode = new WeightNode();
        var birthdayNode = new BirthdayNode();
        node.fillCommands(startNode, sexNode, regionNode, smokingNode, alcoholNode, sportNode,
                          heightNode, weightNode, birthdayNode);
        Assert.assertTrue(node.commands.get("sex").hasSup);
        Assert.assertSame(SexNode.class, node.commands.get("sex").nextNode.getClass());
        Assert.assertTrue(node.commands.get("region").hasSup);
        Assert.assertSame(RegionNode.class, node.commands.get("region").nextNode.getClass());
        Assert.assertTrue(node.commands.get("smoking habit").hasSup);
        Assert.assertSame(SmokingNode.class, node.commands.get("smoking habit").nextNode.getClass());
        Assert.assertTrue(node.commands.get("alcohol habit").hasSup);
        Assert.assertSame(AlcoholNode.class, node.commands.get("alcohol habit").nextNode.getClass());
        Assert.assertTrue(node.commands.get("sport habit").hasSup);
        Assert.assertSame(SportNode.class, node.commands.get("sport habit").nextNode.getClass());
        Assert.assertTrue(node.commands.get("height").hasSup);
        Assert.assertSame(HeightNode.class, node.commands.get("height").nextNode.getClass());
        Assert.assertTrue(node.commands.get("weight").hasSup);
        Assert.assertSame(WeightNode.class, node.commands.get("weight").nextNode.getClass());
        Assert.assertTrue(node.commands.get("birthday").hasSup);
        Assert.assertSame(BirthdayNode.class, node.commands.get("birthday").nextNode.getClass());

    }

    @Test
    public void changeSex() {
        var respond = node.changeSex();
        Assert.assertEquals("What`s your sex?\n" +
                "1. Male\n" +
                "2. Female", respond.userRespond);
    }

    @Test
    public void changeHeight() {
        var respond = node.changeHeight();
        Assert.assertEquals("What`s your height measuring in centimeters?", respond.userRespond);
    }

    @Test
    public void changeWeight() {
        var respond = node.changeWeight();
        Assert.assertEquals("What`s your weight measuring in kilos?", respond.userRespond);
    }

    @Test
    public void changeBDay() {
        var respond = node.changeBDay();
        Assert.assertEquals("What`s your date of birth?(dd.mm.yyyy)", respond.userRespond);
    }

    @Test
    public void changeRegion() {
        var respond = node.changeRegion();
        Assert.assertEquals("In what region do you live?\n" +
                "1. Russia\n" +
                "2. Europe\n" +
                "3. Asia\n" +
                "4. Africa\n" +
                "5. America\n" +
                "6. Australia", respond.userRespond);
    }

    @Test
    public void changeSmokingRange() {
        var respond = node.changeSmokingRange();
        Assert.assertEquals("What`s your attitude to smoking?\n" +
                "1. One pack of cigarettes a day or more\n" +
                "2. One or two pack of cigarettes a week\n" +
                "3. One or two pack of cigarettes a month\n" +
                "4. I`m not smoking", respond.userRespond);
    }

    @Test
    public void changeAlcoholRange() {
        var respond = node.changeAlcoholRange();
        Assert.assertEquals("What`s your attitude to alcohol?\n" +
                "1. I`m drinking everyday\n" +
                "2. Usually I drink a few times a week\n" +
                "3. Usually I drink a few times a month\n" +
                "4. I`m not drinking", respond.userRespond);
    }

    @Test
    public void changeSportRange() {
        var respond = node.changeSportRange();
        Assert.assertEquals("What`s your attitude to sport?\n" +
                "1. I`m professional athlete\n" +
                "2. Sometimes I go to the gym\n" +
                "3. I don`t like sport", respond.userRespond);
    }
}
