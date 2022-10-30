package stepdefs;

import eu.rekawek.toxiproxy.Proxy;
import eu.rekawek.toxiproxy.ToxiproxyClient;
import lombok.Getter;
import toxiproxy.metric.MetricClient;
import toxiproxy.metric.Metrics;

import java.io.IOException;

public class ToxiProxyHelper {


    private final ToxiproxyClient client;
    @Getter
    private final Proxy mySqlProxy;

    public ToxiProxyHelper() {
        this.client = new ToxiproxyClient(); //defaults url to localhost:8474, but can be overriden
        this.mySqlProxy = createProxy("mysql", "localhost:3306", "mysql:3306");
    }

    public Metrics getMetric() {
        return MetricClient.getMetric("http://localhost:8474");
    }


    public void reset() {
        try {
            client.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Proxy createProxy(String name, String listenURl, String upStreamURL) {
        try {
            return client.createProxy(name, listenURl, upStreamURL);
        } catch (IOException e) {
            try {
                return client.getProxy(name);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
