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

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRow = null;
        for (CSVRecord currentRow : parser) {
            if (lowestHumidityRow == null && currentRow.get("Humidity") != "N/A") {
                lowestHumidityRow = currentRow;
            } else {
                if (!currentRow.get("Humidity").equals("N/A")) {
                    int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
                    int lowestHumidity = Integer.parseInt(lowestHumidityRow.get("Humidity"));
                    if (currentHumidity < lowestHumidity) {
                        lowestHumidityRow = currentRow;
                    }
                }
            }
        }
        return lowestHumidityRow;
    }

    public CSVRecord lowestHumidityInManyFiles() {

        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidityRow = null;
        Integer lowestHumidityValue = null;

        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();

            if (lowestHumidityRow == null) {
                lowestHumidityRow = lowestHumidityInFile(parser);
                lowestHumidityValue = Integer.parseInt(lowestHumidityRow.get("Humidity"));
            } else {
                CSVRecord currentFilesLowestRow = lowestHumidityInFile(parser);
                int currentFilesLowestH = Integer.parseInt(currentFilesLowestRow.get("Humidity"));
                if (currentFilesLowestH < lowestHumidityValue) {
                    lowestHumidityRow = currentFilesLowestRow;
                    lowestHumidityValue = currentFilesLowestH;
                }
            }
        }
        String lowestHumidityTime = lowestHumidityRow.get("DateUTC");
        System.out.print("Lowest humidity was " + lowestHumidityValue + " at " + lowestHumidityTime);

        return lowestHumidityRow;
    }

    public double averageTemperatureWithHighHumidityInFile(parser, value) {

    }

}