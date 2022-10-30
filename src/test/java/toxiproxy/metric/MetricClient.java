package toxiproxy.metric;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricClient {


    public static Metrics getMetric(String toxiUrl) {
        String response = RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(toxiUrl).get("/metrics").body().asString();
        String[] splitLine = response.split("\n");

        Map<String, ArrayList<Metric>> metricMap = new HashMap<>();

        for (String line : splitLine) {
            if (line.startsWith(Metric.ToxiDirection.PROXY_RECIEVED.getLinePrefix()) || line.startsWith(Metric.ToxiDirection.PROXY_SENT.getLinePrefix())) {
                Metric metric = new Metric(line);
                if (!metricMap.containsKey(metric.getProxy())) {
                    metricMap.put(metric.getProxy(), new ArrayList<>(List.of(metric)));
                } else {
                    metricMap.get(metric.getProxy()).add(metric);
                }
            }
        }
        return new Metrics(metricMap);
    }


}
