package stepdefs;

import eu.rekawek.toxiproxy.model.ToxicDirection;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import toxiproxy.metric.Metrics;

public class ToxiProxySteps {

    private final ToxiProxyHelper toxiProxyHelper = new ToxiProxyHelper();

    private Metrics initialMetrics;

    private Metrics latestMetrics;

    @Before()
    public void init() {
        toxiProxyHelper.reset();
    }

    @After()
    public void clean() {
        toxiProxyHelper.reset();
    }

    @SneakyThrows
    @Given("The SQL service timeouts after {int} millisecond")
    public void SQLServiceIsDown(int timeoutMillis) {
        this.initialMetrics = toxiProxyHelper.getMetric();
        toxiProxyHelper.getMySqlProxy().toxics().timeout("sql-timeout", ToxicDirection.DOWNSTREAM, timeoutMillis);
    }

    @SneakyThrows
    @Given("The SQL service timeouts after {int} millisecond upstream")
    public void SQLServiceIsDownUpstream(int timeoutMillis) {
        this.initialMetrics = toxiProxyHelper.getMetric();
        toxiProxyHelper.getMySqlProxy().toxics().timeout("sql-timeout", ToxicDirection.UPSTREAM, timeoutMillis);
    }

    @And("bytes have been received by proxy")
    public void bytesHaveBeenReceivedByProxy() {
        this.latestMetrics = toxiProxyHelper.getMetric();
        boolean forwardedBytesAreLessThanSent = latestMetrics.forwardedBytesAreLessThanSent("mysql", initialMetrics);
        Assertions.assertThat(forwardedBytesAreLessThanSent)
                .as("Bytes that have been sent from application to ToxiProxyServer are less than the Bytes forwarded from ToxiProxyServer")
                .isTrue();
    }

    @And("bytes have not been sent to database")
    public void bytesHaveNotBeenSentToDatabase() {
        boolean anyForwarded = latestMetrics.noBytesForwardedFromProxyServer("mysql", initialMetrics);
        Assertions.assertThat(anyForwarded)
                .as("NO Bytes have been forwarded from ToxiProxyServer to actual service")
                .isTrue();
    }

    @And("bytes have been sent to database")
    public void bytesHaveBeenSentToDatabase() {
        boolean anyForwarded = latestMetrics.noBytesForwardedFromProxyServer("mysql", initialMetrics);
        Assertions.assertThat(anyForwarded)
                .as("NO Bytes have been forwarded from ToxiProxyServer to actual service")
                .isFalse();
    }
}
