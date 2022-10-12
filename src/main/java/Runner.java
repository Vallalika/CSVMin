import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Runner {
    public static void main(String[] args) {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-01-08.csv");
        CSVParser parser = fr.getCSVParser();
        CSVMin csvMin = new CSVMin();
        CSVRecord coldestRow = csvMin.coldestHourInFile(parser);
        double coldestTemp = Double.parseDouble(coldestRow.get("TemperatureF"));
    }
}
