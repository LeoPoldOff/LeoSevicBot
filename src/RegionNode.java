import java.util.HashMap;

class RegionNode extends Node {
    private PersonInfo personInfo;

    RegionNode(PersonInfo pInfo) {
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    void fillCommands(INode node){
        var action = new BotAction(this::regionChangeRespond, node);
        for (var a : QuizNode.regionOptions.keySet()){
            commands.put(a.toString(), action);
        }
    }

    private Response regionChangeRespond(String s) {
        personInfo.updateRegion(s);
        return new Response("Your region changed to " + s, 0);
    }
}
