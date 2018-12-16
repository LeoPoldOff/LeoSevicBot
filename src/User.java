import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

class User {
    String id;
    private UserInfo info = new UserInfo();
    private INode currentNode;
    private Map<Class, Consumer<String>> updateMap = new HashMap<>();

    {
        updateMap.put(AlcoholNode.class, info::updateAlcoholRange);
        updateMap.put(BirthdayNode.class, info::updateBirthday);
        updateMap.put(HeightNode.class, info::updateHeight);
        updateMap.put(RegionNode.class, info::updateRegion);
        updateMap.put(SexNode.class, info::updateSex);
        updateMap.put(SmokingNode.class, info::updateSmokingRange);
        updateMap.put(SportNode.class, info::updateSportRange);
        updateMap.put(WeightNode.class, info::updateWeight);
    }

    User(INode currentNode, String chatId) {
        id = chatId;
        this.currentNode = currentNode;
    }

    User(INode currentNode, String chatId, UserInfo info) {
        id = chatId;
        this.info = info;
        this.currentNode = currentNode;
    }

    void update(String s, Class nodeClass) {
        updateMap.get(nodeClass).accept(s);
    }

    BotAction getAnswerFromCurrentNode(String command) {
        return currentNode.getAnswer(command);
    }

    void moveToNextNode(INode nextNode) {
        currentNode = nextNode;
    }

    List<String> getCommandList() {
        return currentNode.getCommandList();
    }

    String showInfo() {
        return info.showInfo();
    }

    UserInfo getInfo() {
        return new UserInfo(info);
    }
}
