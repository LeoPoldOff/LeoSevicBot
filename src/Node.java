import java.util.Map;

abstract class Node implements INode {
    Map<String,BotAction> commands;

    public BotAction getAnswer(String s){
        BotAction command;
        if (s.equals("0"))
            command = null;
        else
            command = commands.get(s);
        if (s.equals("help")) {
            return new BotAction(this::viewHelp, this);
        }
        else if (command == null)
            return new BotAction(this::unknown, this);
        return command;
    }

    public Response viewHelp() {
        var help = new StringBuilder();
        for (var key : commands.keySet()) {
            if (!key.equals("help"))
                help.append(key).append("\n");
        }
        return new Response(help.substring(0, help.length() - 1), 0);
    }

    public Response unknown(){
        return new Response ("Unknown", 0);
    }

    public Response silentRespond(){
        return new Response("",0);
    }
}
