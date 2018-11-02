interface INode {
    BotAction getAnswer(String s);
    Response unknown();
    Response viewHelp();
}