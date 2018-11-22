import java.util.HashMap;
import java.util.Map;

class SmokingNode extends Node {
    private PersonInfo personInfo;

    SmokingNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    void fillCommands(INode node, Map<Integer,String> options){
        var action =  new BotAction(this::smokeChangeRespond, node);
        for (var a : options.keySet()){
            commands.put(a.toString(), action);
        }
    }

    private Response smokeChangeRespond(String s){
        personInfo.updateSmokingRange(s);
        return new Response("Your smoking habit changed to \"" + s + "\"", 0);
    }
}
