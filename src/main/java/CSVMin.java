import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;

public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRow = null;
        for (CSVRecord currentRow: parser) {
            if (coldestRow == null) {
                coldestRow = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestRow.get("TemperatureF"));
                if (currentTemp < coldestTemp && currentTemp != -9999) {
                    coldestRow = currentRow;
                }
            }
        }
        return coldestRow;
    }

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        String coldestFile = null;
        Double coldestTempInAllFiles = null;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord coldestRowInFile = coldestHourInFile(parser);
            Double coldestTempInFile = Double.parseDouble(coldestRowInFile.get("TemperatureF"));
            if (coldestFile == null) {
                coldestFile = f.getName();
                coldestTempInAllFiles = coldestTempInFile;
            }
            if (coldestTempInFile < coldestTempInAllFiles) {
                coldestFile = f.getName();
                coldestTempInAllFiles = coldestTempInFile;
            }
        }
        System.out.println(coldestFile);
        return coldestFile;
    }
}