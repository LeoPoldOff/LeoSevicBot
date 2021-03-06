import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightNode implements INode {
    private Map<String, BotAction> commands;

    WeightNode() {
        commands = new HashMap<>();
    }

    void fillCommands(INode node) {
        commands.put("", new BotAction(this::weightChangeRespond, node));
    }

    private Response weightChangeRespond(String s, User user) {
        user.update(s, this.getClass());
        return new Response("Your weight changed to " + s + " kg", 0);
    }

    public BotAction getAnswer(String s) {
        if (s.equals("help"))
            return new BotAction(this::viewHelp, this);
        BotAction command = commands.get("");
        if (command == null)
            return new BotAction(this::unknown, this);
        return command;
    }

    public Response viewHelp() {
        return new Response("Type your weight in kg", 0);
    }

    public List<String> getCommandList() {
        return new ArrayList<>();
    }

    public Response unknown() {
        return new Response("Unknown", 0);
    }
}
