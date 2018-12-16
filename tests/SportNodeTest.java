import org.junit.Assert;
import org.junit.Test;

public class SportNodeTest {
    @Test
    public void fillCommands() {
        var node = new SportNode();
        var nextNode = new StartNode();
        var map = QuizNode.sportOptions;
        node.fillCommands(nextNode, map);
        Assert.assertEquals(map.size() - 1, node.commands.size());
        for (var a : node.commands.keySet()){
            Assert.assertEquals(nextNode, node.commands.get(a).nextNode);
        }
    }
}
