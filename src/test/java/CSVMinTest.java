import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.junit.Before;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;


public class CSVMinTest {

    CSVMin csvMin;

    @Before
    public void before() {
        csvMin = new CSVMin();
    }

    @Test
    public void coldestHourInFileTest() {
        FileResource fr = new FileResource("weather-2014-01-08.csv");
        CSVParser parser = fr.getCSVParser();
        assertEquals(15.9,csvMin.coldestHourInFile(parser).getRecord("TemperatureF"),0.00);
    }
}