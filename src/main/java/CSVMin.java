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

    public double averageTemperatureInFile(CSVParser parser) {
        Double average = null;
        int tempCount = 0;
        for (CSVRecord row: parser) {
            Double temp = Double.parseDouble(row.get("TemperatureF"));
            if (average == null) {
                average = temp;
                tempCount += 1;
            } else {
                if (temp != -9999) {
                    average += temp;
                    tempCount += 1;
                }
            }
        }
        return average / tempCount;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        Double averageTemp = 0.00;
        int tempCount = 0;
        for (CSVRecord row: parser) {
            String currentHumidityValueInStr = row.get("Humidity");
            if (!currentHumidityValueInStr.equals("N/A")) {
                int currentHumidityValue = Integer.parseInt(currentHumidityValueInStr);
                if (currentHumidityValue > value && (row.get("TemperatureF") != "-9999")) {
                    Double currentTemperature = Double.parseDouble(row.get("TemperatureF"));
                    averageTemp += currentTemperature;
                    tempCount += 1;
                }
            }
        }
        if (averageTemp == 0.00) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + averageTemp / tempCount);
        }
        return averageTemp / tempCount;
    }
}