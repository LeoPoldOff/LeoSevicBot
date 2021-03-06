import org.junit.*;


public class HeightNodeTest {
    private HeightNode node = new HeightNode();

    @Before
    public void fillCommands(){
        var nextNode = new StartNode();
        node.fillCommands(nextNode);
    }

    @Test
    public void getAnswer() {
        var res = node.getAnswer("help");
        Assert.assertEquals("Type your height in sm", res.sup.get().userRespond);
        res = node.getAnswer("170");
        Assert.assertEquals( "Your height changed to 170 sm", res.func.apply("170",
                new User(new StartNode(), "id")).userRespond);

    }

    @Test
    public void getCommandList() {
        Assert.assertEquals(0, node.getCommandList().size());
    }
}
