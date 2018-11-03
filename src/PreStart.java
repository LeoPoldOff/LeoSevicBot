import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PreStart extends Node{
    private List<BotAction> usedCommands;

    PreStart(){
        commands = new HashMap<>();
        usedCommands = new ArrayList<>();
    }

    public BotAction getAnswer(String s){
        BotAction command = commands.get(s);
        if (s.equals("help"))
            return new BotAction(this::viewHelp, this);
        else if (command == null || (s.equals("ok") && commands.size() - 1 != usedCommands.size()))
            return new BotAction(this::unknown, this);
        else if (usedCommands.contains(command))
            return new BotAction(this::alreadyUsed,this);
        usedCommands.add(command);
        return command;
    }

    public Response viewHelp() {
        if (usedCommands.size() == commands.size() - 1)
            return new Response("ok", 0);
        else {
            var help = new StringBuilder();
            for (var key : commands.keySet()) {
                if (!key.equals("help") && !key.equals("ok") && !usedCommands.contains(commands.get(key)))
                    help.append(key).append("\n");
            }
            return new Response(help.substring(0, help.length() - 1), 0);
        }
    }

    private Response alreadyUsed(){
        return new Response("This field already filled", 0);
    }
}
