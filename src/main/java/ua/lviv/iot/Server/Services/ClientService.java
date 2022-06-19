package ua.lviv.iot.Server.Services;

import org.springframework.stereotype.Service;
import ua.lviv.iot.Server.Models.Car;
import ua.lviv.iot.Server.Models.Client;
import ua.lviv.iot.Server.Models.Driver;
import ua.lviv.iot.Server.Models.TaxiEntity;

import java.io.IOException;
import java.util.Map;

@Service
public class ClientService  extends TaxiService {

    public ClientService() throws IOException {
        super();
        csvFileURL = CSV_FILES_PATH + "client.csv";
        entityClassName = Client.class.getSimpleName();
        int lineCount = readCSV();
        System.out.println("--------- Clients read lines: " + lineCount);
    }

    @Override
    public String toStringAll() {
        String result = "=============== ALL CLIENTS ===============<br>";
        for (Map.Entry<Integer, TaxiEntity> item : data.entrySet())
        {
            result += toString(item.getValue(), "");
        }
        return result;
    }

    @Override
    public String addEntity(Map<String, String> params) throws IOException {
        Client client = new Client();
        client.setId(getNextID());
        client.setName(params.getOrDefault("name", "Client #" + client.getId().toString()));
        client.setPhone(params.getOrDefault("phone", "Phone not specified!"));
        writeToCSV();

        return "***************** CLIENT ADDED *****************<br>" + toString(client, "");
    }

    @Override
    protected String toString(TaxiEntity entity, String margin) {
        String result = "<br>" + margin + "---------------<br>";
        if (entity != null) {
            Client client = (Client) entity;
            result += margin + "Client Id: " + client.getId().toString() + "<br>";
            result += margin + "Client Name: " + client.getName() + "<br>";
            result += margin + "Client Phone: " + client.getPhone() + "<br>";
        } else {
            result += margin + "!!! No Clients to print. Entity is null !!!" + "<br>";
        }
        result += margin + "---------------<br>";
        return result;
    }

    @Override
    protected String buildDataLine(TaxiEntity entity) {
        Client client = (Client) entity;
        return client.getId().toString() + CSV_SEPARATOR
                + client.getName() + CSV_SEPARATOR
                + client.getPhone() + CSV_SEPARATOR;
    }

    @Override
    protected String buildHeaderLine()
    {
        return super.buildHeaderLine()
                + CSV_SEPARATOR + "Phone";
    }

    @Override
    protected TaxiEntity parseLine(String line)
    {
        var properties = line.split(CSV_SEPARATOR);

        Client client  = new Client();
        client.setId(Integer.parseInt(properties[0]));
        client.setName(properties[1]);
        client.setPhone(properties[2]);

        return client;
    }
}
