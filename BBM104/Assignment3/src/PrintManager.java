import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

public class PrintManager {
    /*
    this class has methods for writing grid and writing wanted message etc.
     */

    public static void printGrid(Writer monitoringWriter) {
        //It write grid to a txt file
        Iterator<List<Jewel>> iterPrinter = GridManager.grid.iterator();

        try {
            monitoringWriter.write("\n");
            while (iterPrinter.hasNext()) {
                for (Jewel jewel : iterPrinter.next()) {
                    if (jewel == null) {
                        monitoringWriter.write(" ");
                    } else {
                        monitoringWriter.write(jewel.getName());
                    }
                    monitoringWriter.write(" ");
                }
                monitoringWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printMessage(Writer monitoringWriter,String message) {
        // it will write a message to a txt file
        try {
            monitoringWriter.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
