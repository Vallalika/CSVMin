import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Runner {
    public static void main(String[] args) {
        CSVMin csvMin = new CSVMin();
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = csvMin.lowestHumidityInFile(parser);
    }
}
