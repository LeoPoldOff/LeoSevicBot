import java.util.function.Function;
import java.util.function.Supplier;

class BotAction {
    boolean hasFunc = false;
    Function<String, Response> func;
    boolean hasSup = false;
    Supplier<Response> sup;
    INode nextNode;

    public BotAction(Function<String, Response> f, INode next){
        hasFunc = true;
        func = f;
        nextNode = next;
    }

    public BotAction(Supplier<Response> s, INode next){
        hasSup = true;
        sup = s;
        nextNode = next;
    }
}
