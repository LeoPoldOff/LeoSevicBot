import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class DataWorker {
    private static final String dirPath = new File("").getAbsolutePath() + "/data";
    private static Logger log = Logger.getLogger(DataWorker.class.getName());

    boolean hasInfo(String chatId) {
        var dataFile = new File(dirPath + "/" + chatId + ".json");
        return dataFile.exists();
    }

    UserInfo readData(String chatId) {
        if (!hasInfo(chatId))
            return null;
        var info = new UserInfo();
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(dirPath + "/" + chatId + ".json"));

            info.sex = ((Number) object.get("sex")).intValue();
            info.birthDate = object.get("birthDate").toString();
            info.height = ((Number) object.get("height")).intValue();
            info.weight = ((Number) object.get("weight")).intValue();
            info.region = ((Number) object.get("region")).intValue();
            info.smokingRange = ((Number) object.get("smokingRange")).intValue();
            info.alcoholRange = ((Number) object.get("alcoholRange")).intValue();
            info.sportRange = ((Number) object.get("sportRange")).intValue();
            return info;
        } catch (IOException | org.json.simple.parser.ParseException e) {
            log.log(Level.SEVERE, "Exception: ", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    void writeData(UserInfo pInfo, String id) {
        var dataDir = new File(dirPath);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        JSONObject object = new JSONObject();
        object.put("sex", pInfo.sex);
        object.put("birthDate", pInfo.birthDate);
        object.put("height", pInfo.height);
        object.put("weight", pInfo.weight);
        object.put("region", pInfo.region);
        object.put("smokingRange", pInfo.smokingRange);
        object.put("alcoholRange", pInfo.alcoholRange);
        object.put("sportRange", pInfo.sportRange);

        try (FileWriter writer = new FileWriter(dirPath + "/" + id + ".json")){
            writer.write(object.toJSONString());
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }
}