import java.util.HashMap;
import java.util.Map;

class SportNode extends Node {
    private PersonInfo personInfo;

    SportNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    void fillCommands(INode node, Map<Integer,String> options){
        var action =  new BotAction(this::sportChangeRespond, node);
        for (var a : options.keySet()){
            commands.put(a.toString(), action);
        }
    }

    private Response sportChangeRespond(String s){
        personInfo.updateSportRange(s);
        return new Response("Your sport habit changed to \"" + s + "\"", 0);
    }
}
