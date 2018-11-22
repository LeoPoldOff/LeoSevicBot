import java.util.HashMap;
import java.util.Map;

class RegionNode extends Node {
    private PersonInfo personInfo;

    RegionNode(PersonInfo pInfo) {
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    void fillCommands(INode node, Map<Integer,String> options){
        var action = new BotAction(this::regionChangeRespond, node);
        for (var a : options.keySet()){
            commands.put(a.toString(), action);
        }
    }

    private Response regionChangeRespond(String s) {
        personInfo.updateRegion(s);
        return new Response("Your region changed to " + s, 0);
    }
}
