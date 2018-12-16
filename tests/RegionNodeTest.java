import org.junit.*;


public class RegionNodeTest {

    @Test
    public void fillCommands() {
        var node = new RegionNode();
        var nextNode = new StartNode();
        var map = QuizNode.regionOptions;
        node.fillCommands(nextNode, map);
        Assert.assertEquals(map.size() - 1, node.commands.size());
        for (var a : node.commands.keySet()){
            Assert.assertEquals(nextNode, node.commands.get(a).nextNode);
        }
    }
}
