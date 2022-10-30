package toxiproxy.metric;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Metric {

    private String direction;
    private String listener;
    private String proxy;
    private String upstream;
    private int bytes;
    private ToxiDirection toxiDirection;


    public enum ToxiDirection {
        PROXY_RECIEVED("toxiproxy_proxy_received_bytes_total"),
        PROXY_SENT("toxiproxy_proxy_sent_bytes_total");

        @Getter
        private final String linePrefix;

        ToxiDirection(String linePrefix) {
            this.linePrefix = linePrefix;

        }
    }

    private void populate(String line) {
        if (line.startsWith(ToxiDirection.PROXY_RECIEVED.getLinePrefix())) {
            this.toxiDirection = ToxiDirection.PROXY_RECIEVED;
        } else if (line.startsWith(ToxiDirection.PROXY_SENT.getLinePrefix())) {
            this.toxiDirection = ToxiDirection.PROXY_SENT;
        } else {
            throw new IllegalArgumentException(String.format("Start of string not as expected, string =%s", line));
        }
        this.direction = line.split("direction=\"")[1].split("\"")[0];
        this.listener = line.split("listener=\"")[1].split("\"")[0];
        this.proxy = line.split("proxy=\"")[1].split("\"")[0];
        this.upstream = line.split("upstream=\"")[1].split("\"")[0];
        this.bytes = Integer.parseInt(line.split(" ")[1]);
    }

    public Metric(String line) {
        populate(line);
    }


}
