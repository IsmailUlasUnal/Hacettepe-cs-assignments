import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;



public class PropertyJsonReader {
     public static Square[] squares = new Square[40];
     /*
     I am creating a squares array for making map of this game
      */

	 
     public PropertyJsonReader(){
         JSONParser processor = new JSONParser();
         try (Reader file = new FileReader("property.json")){

             JSONObject jsonfile = (JSONObject) processor.parse(file);
             JSONArray Land = (JSONArray) jsonfile.get("1");
             for(Object i:Land){

                 squares[Integer.parseInt((String)((JSONObject)i).get("id")) - 1] =
                         new Propertie((String)((JSONObject)i).get("name"), Integer.parseInt((String)((JSONObject)i).get("cost")), new RentByCost());
				        // this is Land
                 
             }
             JSONArray RailRoad = (JSONArray) jsonfile.get("2");
             for(Object i:RailRoad){

                 squares[Integer.parseInt((String)((JSONObject)i).get("id")) - 1] =
                         new Propertie((String)((JSONObject)i).get("name"), Integer.parseInt((String)((JSONObject)i).get("cost")), new RentByQuantity());
                        // this is Railroad

             }
			 
             JSONArray Company = (JSONArray) jsonfile.get("3");
             for(Object i:Company){

                 squares[Integer.parseInt((String)((JSONObject)i).get("id")) - 1] =
                         new Propertie((String)((JSONObject)i).get("name"), Integer.parseInt((String)((JSONObject)i).get("cost")), new RentByDice());
                        // this is Company

             }

             /*
             bottom part is manuel adding to the map
              */
             PropertyJsonReader.squares[2] = new CommunityCardReader();
             PropertyJsonReader.squares[17] = new CommunityCardReader();
             PropertyJsonReader.squares[33] = new CommunityCardReader();

             PropertyJsonReader.squares[7] = new ChanceCardReader();
             PropertyJsonReader.squares[22] = new ChanceCardReader();
             PropertyJsonReader.squares[36] = new ChanceCardReader();

             PropertyJsonReader.squares[0] = new GO();
             PropertyJsonReader.squares[4] = new IncomeTax();
             PropertyJsonReader.squares[10] = new Jail();
             PropertyJsonReader.squares[20] = new FreeParking();
             PropertyJsonReader.squares[30] = new GoToJail();
             PropertyJsonReader.squares[38] = new IncomeTax();

         } catch (IOException e){
             e.printStackTrace();
         } catch (ParseException e){
             e.printStackTrace();
         }

     }
}