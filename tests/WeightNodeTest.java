import org.junit.*;

public class WeightNodeTest {
    private WeightNode node = new WeightNode();

    @Before
    public void fillCommands(){
        var nextNode = new StartNode();
        node.fillCommands(nextNode);
    }

    @Test
    public void getAnswer() {
        var res = node.getAnswer("help");
        Assert.assertEquals("Type your weight in kg", res.sup.get().userRespond);
        res = node.getAnswer("70");
        Assert.assertEquals( "Your weight changed to 70 kg", res.func.apply("70",
                new User(new StartNode(), "id")).userRespond);

    }

    @Test
    public void getCommandList() {
        Assert.assertEquals(0, node.getCommandList().size());
    }
}
