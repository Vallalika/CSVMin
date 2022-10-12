import edu.duke.DirectoryResource;
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
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-01-08.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRow = csvMin.coldestHourInFile(parser);
        double coldestTemp = Double.parseDouble(coldestRow.get("TemperatureF"));
        assertEquals(15.1,coldestTemp,0.00);
    }

//    @Test
    public void coldestHourInFileBogusDataTest() {
        // TODO: find relevant data file later
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-01-08.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRow = csvMin.coldestHourInFile(parser);
        double coldestTemp = Double.parseDouble(coldestRow.get("TemperatureF"));
        // TODO: change expected value when relevant file identified
        assertEquals(15.1,coldestTemp,0.00);
    }

    @Test
    public void fileWithColdestTemperatureTest(){
        // Use files 1 to 3 in 2014 folder for test to pass
        String coldestFile = fileWithColdestTemperature();
        assertEquals("weather-2014-01-03.csv",coldestFile);
    }
}