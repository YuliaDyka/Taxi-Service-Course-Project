package ua.lviv.iot.Server.Services;

import ua.lviv.iot.Server.Models.Driver;
import ua.lviv.iot.Server.Models.TaxiEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TaxiService {
    //-------------- Statics properties and constants ------------
    public static final String CSV_FILES_PATH = "Data/";
    public static final String CSV_SEPARATOR = ";";
    public static final String HTML_TAB = "&emsp;&emsp;";
    // Static Map for store all services
    private static final Map<String, TaxiService> services = new HashMap<>();

    //-------------------- Member properties ----------------------
    // Map for store entities
    protected Map<Integer, TaxiEntity> data;
    protected String csvFileURL = "";
    protected String entityClassName = "";

    //--------------------  Constructor ---------------------------
    public TaxiService() {
        data = new HashMap<>();
        String serviceClassName = this.getClass().getName();
        if (!services.containsKey(serviceClassName)) {
            System.out.println("******* Add service: " + serviceClassName + " ***************");
            services.put(serviceClassName, this);
        }
    }

    //----------------- Public abstract methods -------------------
    public abstract String addEntity(Map<String, String> params) throws IOException;
    public abstract String toStringAll();

    //---------------------- Public methods -----------------------
    public TaxiService getService(String serviceClassName) {
        return services.get(serviceClassName);
    }
    public void add(final TaxiEntity entity) {
        entity.setId(getNextID());
        data.put(entity.getId(), entity);
    }

    public String toStringById(int id, String margin) {
        return toString(data.get(id), margin);
    }
    public String toStringById(int id) {
        return toStringById(id, "");
    }

    public TaxiEntity getById(int id) {
        return data.get(id);
    }

    public List<TaxiEntity> getAll() {
        return new ArrayList<TaxiEntity>(data.values());
    }

    public int readCSV() throws IOException {
        data.clear();
        int entitiesCount = 0;
        try (FileReader fileReader = new FileReader(csvFileURL);
            BufferedReader actualBR = new BufferedReader(fileReader)) {
            String currentLine = actualBR.readLine();
            while(currentLine != null)
            {
                if (entitiesCount > 0) {
                    TaxiEntity entity = parseLine(currentLine);
                    data.put(entity.getId(), entity);
                }
                currentLine = actualBR.readLine();
                entitiesCount++;
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entitiesCount;
    }

    public void writeToCSV() throws IOException {
        try (FileWriter fileWriter = new FileWriter(csvFileURL, false)){
            String header = buildHeaderLine();
            fileWriter.write(header);
            fileWriter.write("\r\n");
            for (Map.Entry<Integer, TaxiEntity> item : data.entrySet())
            {
                String line = buildDataLine(item.getValue());
                fileWriter.write(line);
                fileWriter.write("\r\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String removeById(int id) throws IOException {
        String result = "The " + entityClassName + " with id:" + id;
        result += data.remove(id) != null ? " have removed!" : " not found!!!";
        writeToCSV();
        return result;
    }

    //--------------- Protected abstract methods ------------------
    protected abstract String toString(TaxiEntity entity, String margin);
    protected abstract String buildDataLine(TaxiEntity entity);
    protected abstract TaxiEntity parseLine(String line);

    //--------------------- Protected methods ---------------------
    protected Integer getNextID() {
        return data.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
    }

    protected String buildHeaderLine()
    {
        return "ID" + CSV_SEPARATOR + "Name";
    }

    public boolean edit(int id, TaxiEntity entity) throws IOException {
        if(entity != null)
        {
            data.put(entity.getId(), entity);
            writeToCSV();
            return true;
        }
        return false;
    }

}
