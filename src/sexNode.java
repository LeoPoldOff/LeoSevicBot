import java.util.HashMap;
import java.util.Map;

class SexNode extends Node {

    SexNode() {
        commands = new HashMap<>();
    }

    void fillCommands(INode node, Map<Integer, String> options) {
        var action = new BotAction(this::sexChangeRespond, node);
        for (var a : options.keySet()) {
            if (a != 0)
                commands.put(a.toString(), action);
        }
    }

    private Response sexChangeRespond(String s, User user) {
        user.update(s, this.getClass());
        return new Response("Your sex changed to " + s, 0);
    }
}
