import java.util.HashMap;

class SexNode extends Node {
    private PersonInfo personInfo;

    SexNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    void fillCommands(INode node){
        var action = new BotAction(this::sexChangeRespond, node);
        for (var a : QuizNode.sexOptions.keySet()){
            commands.put(a.toString(), action);
        }
    }

    private Response sexChangeRespond(String s){
        personInfo.updateSex(s);
        return new Response("Your sex changed to " + s, 0);
    }
}
