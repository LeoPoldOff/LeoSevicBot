import java.util.List;

interface INode {
    BotAction getAnswer(String s);

    Response unknown();

    Response viewHelp();

    List<String> getCommandList();
}