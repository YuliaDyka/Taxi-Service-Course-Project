package ua.lviv.iot.Server.Services;

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
            services.put(serviceClassName, this);
        }
    }

    //----------------- Public abstract methods -------------------

    /**
     * Add an entity using GET request.
     * @param params
     * @return String
     * @throws IOException
     */
    public abstract String addEntity(Map<String, String> params) throws IOException;

    /**
     * Returns a formatted string of all entities.
     * @return String
     */
    public abstract String toStringAll();

    //---------------------- Public methods -----------------------

    /**
     * set FileURL csv.
     * @param url
     */
    public void setCsvFileURL(final String url) {
        csvFileURL = url;
    }

    /**
     * get service by service class name.
     * @param serviceClassName
     * @return TaxiService
     */
    public TaxiService getService(final String serviceClassName) {
        return services.get(serviceClassName);
    }

    /**
     * Add an entity using GET request.
     * @param entity
     * @throws IOException
     */
    public void add(final TaxiEntity entity) throws IOException {
        entity.setId(getNextID());
        data.put(entity.getId(), entity);
        writeToCSV();
    }

    /**
     * Returns a formatted string of entity by id.
     * @param id - id of entity and margin
     * @return String to show in browser
     */
    public String toStringById(final int id, final String margin) {
        return toString(data.get(id), margin);
    }

    /**
     * Returns a formatted string of entity by id.
     * @param id
     * @return String
     */
    public String toStringById(final int id) {
        return toStringById(id, "");
    }

    /**
     * Returns entity by id.
     * @param id
     * @return TaxiEntity
     */
    public TaxiEntity getById(final int id) {
        return data.get(id);
    }

    /**
     * Returns all entities.
     * @return List<TaxiEntity>
     */
    public List<TaxiEntity> getAll() {
        return new ArrayList<TaxiEntity>(data.values());
    }

    /**
     * Read entities from CSV file.
     * @throws IOException
     */
    public void readCSV() throws IOException {
        data.clear();
        int entitiesCount = 0;
        try (FileReader fileReader = new FileReader(csvFileURL);
            BufferedReader actualBR = new BufferedReader(fileReader)) {
            String currentLine = actualBR.readLine();
            while (currentLine != null) {
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
    }

    /**
     * Write to entities to CSV file.
     * @throws IOException
     */
    public void writeToCSV() throws IOException {
        try (FileWriter fileWriter = new FileWriter(csvFileURL, false)) {
            String header = buildHeaderLine();
            fileWriter.write(header);
            fileWriter.write("\r\n");
            for (Map.Entry<Integer, TaxiEntity> item : data.entrySet()) {
                String line = buildDataLine(item.getValue());
                fileWriter.write(line);
                fileWriter.write("\r\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * remove all.
     * @throws IOException
     */
    public void removeAll() throws IOException {
        data.clear();
        writeToCSV();
    }

    /**
     * Remove by id System.out.println.
     * @param id
     * @return Returns a formatted string
     * @throws IOException
     */
    public String removeByIdString(final int id) throws IOException {
        String result = "The " + entityClassName + " with id:" + id;
        result += data.remove(id) != null ? " have removed!" : " not found!!!";
        writeToCSV();
        return result;
    }

    /**
     * Remove by id entity.
     * @param id
     * @return TaxiEntity
     * @throws IOException
     */
    public TaxiEntity removeById(final int id) throws IOException {
        var result = data.remove(id);
        if (result != null) {
            writeToCSV();
        }
        return result;
    }

    //--------------- Protected abstract methods ------------------
    protected abstract String toString(TaxiEntity entity, String margin);
    protected abstract String buildDataLine(TaxiEntity entity);
    protected abstract TaxiEntity parseLine(String line);

    //--------------------- Protected methods ---------------------

    /**
     * Get next id.
     * @return Integer
     */
    public Integer getNextID() {
        return data.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
    }

    protected String buildHeaderLine() {
        return "ID" + CSV_SEPARATOR + "Name";
    }

    /**
     * Edit entity by id.
     * @param id
     * @param entity
     * @return boolean
     * @throws IOException
     */
    public boolean edit(final int id, final TaxiEntity entity) throws IOException {
        if (entity != null) {
            data.put(entity.getId(), entity);
            writeToCSV();
            return true;
        }
        return false;
    }
}
