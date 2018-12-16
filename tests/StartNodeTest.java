import org.junit.Assert;
import org.junit.Test;

public class StartNodeTest {
    @Test
    public void fillCommands() {
        var node = new StartNode();
        var quizNode = new QuizNode();
        node.fillCommands(quizNode, false);
        Assert.assertEquals(3, node.commands.size());
        node.fillCommands(quizNode, true);
        Assert.assertEquals(4, node.commands.size());
    }
}
