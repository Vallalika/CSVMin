import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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
}