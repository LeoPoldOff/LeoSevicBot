import java.util.HashMap;
import java.util.Map;

class RegionNode extends Node {

    RegionNode() {
        commands = new HashMap<>();
    }

    void fillCommands(INode node, Map<Integer, String> options) {
        var action = new BotAction(this::regionChangeRespond, node);
        for (var a : options.keySet()) {
            if (a != 0)
                commands.put(a.toString(), action);
        }
    }

    private Response regionChangeRespond(String s, User user) {
        user.update(s, this.getClass());
        return new Response("Your region changed to " + s, 0);
    }
}
