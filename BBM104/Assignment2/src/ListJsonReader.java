import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class ListJsonReader{

    public static ChanceCard[] chanceArray = new ChanceCard[6];
    public static CommunityCard[] communityArray = new CommunityCard[11];
    int chanceLocation = 0;
    int communityLocation = 0;

    public ListJsonReader(){
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("list.json")){
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray chanceList = (JSONArray) jsonfile.get("chanceList");
            for(Object i:chanceList){
                chanceArray[chanceLocation] = new ChanceCard(((String)((JSONObject)i).get("item")));
                chanceLocation += 1;
				 
            }
            JSONArray communityChestList = (JSONArray) jsonfile.get("communityChestList");
            for(Object i:communityChestList){
				communityArray[communityLocation] = new CommunityCard(((String)((JSONObject)i).get("item")));
                communityLocation += 1;

            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
     }
     //You can add function if you want
}

