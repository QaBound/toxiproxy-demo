package toxiproxy.metric;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Map;

public class Metrics {

    private final Map<String, ArrayList<Metric>> metrics;

    public Metrics(Map<String, ArrayList<Metric>> metrics) {
        this.metrics = metrics;
    }

    public Metric getMetric(String proxyName,
                            Metric.ToxiDirection toxiDirection,
                            StreamDirection streamDirection) {
        var metricList = metrics.get(proxyName);
        if (metricList == null) {
            return null;
        }
        return metricList.stream()
                .filter(m -> m.getToxiDirection() == toxiDirection
                        && m.getDirection().equals(streamDirection.getDirection()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(""));
    }

    private int getDifference(Metrics initialMetrics,
                              String proxyName,
                              Metric.ToxiDirection toxiDirection,
                              StreamDirection streamDirection) {
        Metric initMetricSentByApp = initialMetrics.
                getMetric(proxyName, toxiDirection, streamDirection);
        Metric finalMetricSentByApp = this.
                getMetric(proxyName, toxiDirection, streamDirection);

        int initBytesSent;
        if (initMetricSentByApp == null) {
            initBytesSent = 0;
        } else {
            initBytesSent = initMetricSentByApp.getBytes();
        }

        return finalMetricSentByApp.getBytes() - initBytesSent;

    }

    public boolean forwardedBytesAreLessThanSent(String proxyName, Metrics initialMetrics) {
        int bytesSentToServer = getDifference(initialMetrics, proxyName, Metric.ToxiDirection.PROXY_RECIEVED, StreamDirection.DOWN);
        int bytesForwardedFromServer = getDifference(initialMetrics, proxyName, Metric.ToxiDirection.PROXY_SENT, StreamDirection.UP);
        return bytesSentToServer > bytesForwardedFromServer;
    }

    public boolean noBytesForwardedFromProxyServer(String proxyName, Metrics initialMetrics) {
        int bytesForwardedFromServer = getDifference(initialMetrics, proxyName, Metric.ToxiDirection.PROXY_SENT, StreamDirection.UP);
        return bytesForwardedFromServer == 0;
    }

    public enum StreamDirection {
        UP("upstream"),
        DOWN("downstream");

        @Getter
        private final String direction;

        StreamDirection(String direction) {
            this.direction = direction;
        }
    }

}
