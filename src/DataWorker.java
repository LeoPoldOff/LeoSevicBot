import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.Date;

class DataWorker{
    private static final String dirPath = new File("").getAbsolutePath() + "/data";
    private static final String dataFilePath = dirPath + "/user_data.json";

     boolean hasInfo(){
        var dataFile = new File(dataFilePath);
        return dataFile.exists();
    }

     PersonInfo readData(){
        var pInfo = new PersonInfo();
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(dataFilePath));

            pInfo.sex = ((Number)object.get("sex")).intValue();
            pInfo.birthDate = object.get("birthDate").toString();
            pInfo.height = ((Number) object.get("height")).intValue();
            pInfo.weight = ((Number) object.get("weight")).intValue();
            pInfo.region = ((Number) object.get("region")).intValue();
            pInfo.smokingRange = ((Number) object.get("smokingRange")).intValue();
            pInfo.alcoholRange = ((Number) object.get("alcoholRange")).intValue();
            pInfo.sportRange = ((Number) object.get("sportRange")).intValue();
        }
        catch (IOException | org.json.simple.parser.ParseException e){ System.out.println(e.getMessage()); }

        return pInfo;
    }

    @SuppressWarnings("unchecked")
    void writeData(PersonInfo pInfo){
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

        try{
            FileWriter writer = new FileWriter(dataFilePath);
            writer.write(object.toJSONString());
            writer.flush();
        } catch (IOException e) {System.out.println(e.getMessage());}
    }
}