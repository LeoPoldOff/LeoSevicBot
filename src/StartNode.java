import java.util.HashMap;

class StartNode extends Node {

    StartNode() {
        commands = new HashMap<>();
    }

    void fillCommands(QuizNode quiz, boolean exitFlag) {
        commands.put("my info", new BotAction(this::myInfo, this));
        commands.put("change info", new BotAction(this::changeInfo, quiz));
        commands.put("when i die?", new BotAction(this::whenIDie, this));
        if (exitFlag)
            commands.put("exit", new BotAction(this::exit, this));
    }

    private Response myInfo(String s, User user) {
        return new Response(user.showInfo(), 0);
    }

    private Response exit() {
        return new Response("Bye!", 1);
    }

    private Response whenIDie(String s, User user) {
        var age = Tree.calcAge(user.getInfo());
        var userRespond = String.format("You'll die at age %d", age);
        return new Response(userRespond, 0);
    }

    private Response changeInfo() {
        return new Response("What parameter you want to change?", 0);
    }
}
