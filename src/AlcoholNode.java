import java.util.HashMap;
import java.util.Map;

class AlcoholNode extends Node {

    AlcoholNode() {
        commands = new HashMap<>();
    }

    void fillCommands(INode node, Map<Integer, String> options) {
        var action = new BotAction(this::alcoholChangeRespond, node);
        for (var a : options.keySet()) {
            if (a != 0)
                commands.put(a.toString(), action);
        }
    }

    private Response alcoholChangeRespond(String s, User user) {
        user.update(s, this.getClass());
        return new Response("Your alcohol habit changed to \"" + s + "\"", 0);
    }
}
