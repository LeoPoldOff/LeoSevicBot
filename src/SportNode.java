import java.util.HashMap;
import java.util.Map;

class SportNode extends Node {

    SportNode() {
        commands = new HashMap<>();
    }

    void fillCommands(INode node, Map<Integer, String> options) {
        var action = new BotAction(this::sportChangeRespond, node);
        for (var a : options.keySet()) {
            if (a != 0)
                commands.put(a.toString(), action);
        }
    }

    private Response sportChangeRespond(String s, User user) {
        user.update(s, this.getClass());
        return new Response("Your sport habit changed to \"" + s + "\"", 0);
    }
}
