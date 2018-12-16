import org.junit.Assert;
import org.junit.Test;

public class SmokingNodeTest {
    @Test
    public void fillCommands() {
        var node = new SmokingNode();
        var nextNode = new StartNode();
        var map = QuizNode.smokingOptions;
        node.fillCommands(nextNode, map);
        Assert.assertEquals(map.size() - 1, node.commands.size());
        for (var a : node.commands.keySet()){
            Assert.assertEquals(nextNode, node.commands.get(a).nextNode);
        }
    }
}
