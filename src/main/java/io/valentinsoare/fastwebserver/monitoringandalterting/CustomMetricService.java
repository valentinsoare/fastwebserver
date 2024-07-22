package io.valentinsoare.fastwebserver.monitoringandalterting;

import io.micrometer.core.instrument.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CustomMetricService {
    private final Counter httpRequests;
    private final AtomicInteger httpRequestsInProgress;
    private final AtomicLong lastHttpRequestServed;
    private final Timer responseTime;

    @Autowired
    public CustomMetricService(MeterRegistry meterRegistry) {
        this.httpRequests = Counter.builder("httpRequests")
                .description("Http Total Requests coming into the server.")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);

        this.httpRequestsInProgress = new AtomicInteger();
        Gauge.builder("httpRequestsInProgress", httpRequestsInProgress, AtomicInteger::get)
                .description("Number of HTTP requests in progress.")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);

        this.lastHttpRequestServed = new AtomicLong();
        Gauge.builder("lastHttpRequestServed", lastHttpRequestServed, AtomicLong::get)
                .description("Last Http Request Served")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);

        this.responseTime = Timer.builder("responseTime")
                .tags("environment", "production", "endpoint", "/")
                .description("How fast a Http request is served/completed.")
                .register(meterRegistry);
    }

    public void incrementHttpTotalRequests() {
        httpRequests.increment();
    }

    public void incrementHttpRequestsInProgress() {
        httpRequestsInProgress.getAndIncrement();
    }

    public void decrementHttpRequestsInProgress() {
        httpRequestsInProgress.getAndDecrement();
    }

    public void setLastHttpRequestServed() {
        LocalDateTime time = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        long epochSecond = time.atZone(zoneId).toEpochSecond();

        lastHttpRequestServed.set(epochSecond);
    }

    public Timer getResponseTimeSetter() {
        return responseTime;
    }
}
