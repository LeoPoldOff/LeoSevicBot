import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class BotAction {
    boolean hasFunc = false;
    BiFunction<String, User, Response> func;
    boolean hasSup = false;
    Supplier<Response> sup;
    INode nextNode;

    BotAction(BiFunction<String, User, Response> f, INode next) {
        hasFunc = true;
        func = f;
        nextNode = next;
    }

    BotAction(Supplier<Response> s, INode next) {
        hasSup = true;
        sup = s;
        nextNode = next;
    }
}
