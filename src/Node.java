import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract class Node implements INode {
    protected Map<String, BotAction> commands;

    public BotAction getAnswer(String s) {
        if (s.equals("help"))
            return new BotAction(this::viewHelp, this);
        var command = commands.get(s);
        if (command == null)
            return new BotAction(this::unknown, this);
        return command;
    }

    public Response viewHelp() {
        return new Response(String.join("\n", getCommandList()), 0);
    }

    public List<String> getCommandList() {
        var commandList = new ArrayList<String>();
        for (var key : commands.keySet()) {
            if (!key.equals("help"))
                commandList.add(key);
        }
        return commandList;
    }

    public Response unknown() {
        return new Response("Unknown", 0);
    }
}
