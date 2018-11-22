import java.util.HashMap;
import java.util.Map;

class SexNode extends Node {
    private PersonInfo personInfo;

    SexNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    void fillCommands(INode node, Map<Integer,String> options){
        var action = new BotAction(this::sexChangeRespond, node);
        for (var a : options.keySet()){
            commands.put(a.toString(), action);
        }
    }

    private Response sexChangeRespond(String s){
        personInfo.updateSex(s);
        return new Response("Your sex changed to " + s, 0);
    }
}
