import java.util.HashMap;

class AlcoholNode extends Node {
    private PersonInfo personInfo;

    AlcoholNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    void fillCommands(INode node){
        var action =  new BotAction(this::alcoholChangeRespond, node);
        for (var a : QuizNode.alcoholOptions.keySet()){
            commands.put(a.toString(), action);
        }
    }

    private Response alcoholChangeRespond(String s){
        personInfo.updateAlcoholRange(s);
        return new Response("Your alcohol habit changed to \"" + s + "\"", 0);
    }
}
