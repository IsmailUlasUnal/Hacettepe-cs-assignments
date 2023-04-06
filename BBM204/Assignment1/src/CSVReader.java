import java.io.BufferedReader;
import java.io.FileReader;

public class CSVReader {
    public int[] readInteger(String strFile, int size) {
        try {
            int[] arr = new int[size];
            int index = 0;

            BufferedReader br = new BufferedReader(new FileReader(strFile));
            String line = br.readLine();

            while ((line = br.readLine()) != null && index < size) {
                String[] cols = line.split(",");
                arr[index++] = Integer.parseInt(cols[6]);
            }
            return arr;

        } catch (Exception err) {
            System.out.println("File not found (probably)");
        }
        return null;
    }
}
