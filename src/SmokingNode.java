import java.util.HashMap;
import java.util.Map;

class SmokingNode extends Node {

    SmokingNode() {
        commands = new HashMap<>();
    }

    void fillCommands(INode node, Map<Integer, String> options) {
        var action = new BotAction(this::smokeChangeRespond, node);
        for (var a : options.keySet()) {
            if (a != 0)
                commands.put(a.toString(), action);
        }
    }

    private Response smokeChangeRespond(String s, User user) {
        user.update(s, this.getClass());
        return new Response("Your smoking habit changed to \"" + s + "\"", 0);
    }
}
