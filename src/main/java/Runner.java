import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Runner {
    public static void main(String[] args) {
        CSVMin csvMin = new CSVMin();
        String result = csvMin.fileWithColdestTemperature();
    }
}
