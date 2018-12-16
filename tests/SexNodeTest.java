import org.junit.Assert;
import org.junit.Test;

public class SexNodeTest {
    @Test
    public void fillCommands() {
        var node = new SexNode();
        var nextNode = new StartNode();
        var map = QuizNode.sexOptions;
        node.fillCommands(nextNode, map);
        Assert.assertEquals(map.size() - 1, node.commands.size());
        for (var a : node.commands.keySet()){
            Assert.assertEquals(nextNode, node.commands.get(a).nextNode);
        }
    }
}
