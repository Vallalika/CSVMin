import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class CSVMinTest {

    CSVMin csvMin;

    @Before
    public void before() {
        csvMin = new CSVMin();
    }

    @Test
    public void coldestHourInFileTest() {
        // works with "weather-2014-01-08.csv" in  folder2014
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-01-08.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRow = csvMin.coldestHourInFile(parser);
        double coldestTemp = Double.parseDouble(coldestRow.get("TemperatureF"));
        assertEquals(15.1,coldestTemp,0.00);
    }
}