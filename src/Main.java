import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Fredrik on 2015-07-10.
 */
public class Main {

    public static void main(String[] args) {
        MqttClient client = null;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            client = new MqttClient("tcp://mqtt.phelicks.net:1883", "SnapshotSender111", persistence);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("cab");
            options.setPassword("sjuttongubbar".toCharArray());
            client.connect(options);
        //   client.publish("/World","Hej från java".getBytes(),0,false);
            //String toString = "carID:"+carID+";timestamp:"+timestamp+";fuel:"+fuel+";speed:"+speed+";distanceTraveled:"+distanceTraveled+";longitude:"+longitude+";latitude:"+latitude;

            while(true){
               BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
               String line =reader.readLine();
                String[]splittedLine=line.split(" ");
                String carSnapShotString ="carID:"+splittedLine[0]+";timestamp:"+System.currentTimeMillis()+";fuel:50"+";speed:50.5"+";distanceTraveled:150"+";longitude:"+splittedLine[1]+";latitude:"+splittedLine[2];
                System.out.println(carSnapShotString);
                client.publish("telemetry/snapshot",carSnapShotString.getBytes(),0,false);
            }

        } catch (MqttException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
