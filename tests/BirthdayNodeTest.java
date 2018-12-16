import org.junit.*;

public class BirthdayNodeTest {
    @Test
    public void viewHelp() {
        var node = new BirthdayNode();
        var nextNode = new StartNode();
        node.fillCommands(nextNode);
        var response = node.viewHelp();
        Assert.assertEquals("Type your birthday (dd.mm.yyyy)", response.userRespond);
        Assert.assertSame(response.userRespond, node.getAnswer("help").sup.get().userRespond);
    }

    @Test
    public void getCommandList() {
        var node = new BirthdayNode();
        var nextNode = new StartNode();
        node.fillCommands(nextNode);
        Assert.assertEquals(0,node.getCommandList().size());
    }
}
