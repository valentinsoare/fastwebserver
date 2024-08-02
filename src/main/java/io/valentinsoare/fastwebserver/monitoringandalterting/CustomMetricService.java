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
    private final AtomicInteger httpRequests;
    private final AtomicLong lastHttpRequestSuccess;
    private final AtomicLong lastHttpRequestFailed;
    private final AtomicInteger httpRequestsSuccess;
    private final AtomicInteger httpRequestsFailed;
    private final Timer responseTime;

    @Autowired
    public CustomMetricService(MeterRegistry meterRegistry) {

        this.httpRequests = new AtomicInteger();
        Gauge.builder("httpRequests", httpRequests, AtomicInteger::get )
                .description("Number of HTTP Requests coming into the server.")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);

        this.lastHttpRequestSuccess = new AtomicLong();
        Gauge.builder("lastHttpRequestSuccess", lastHttpRequestSuccess, AtomicLong::get)
                .description("Last Http Request Served")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);

        this.lastHttpRequestFailed = new AtomicLong();

        this.responseTime = Timer.builder("responseTime")
                .tags("environment", "production", "endpoint", "/")
                .description("How fast a Http request is served/completed.")
                .register(meterRegistry);
    }

    public void incrementHttpRequests() {
        httpRequests.getAndIncrement();
    }

    public void decrementHttpRequests() {
        httpRequests.getAndDecrement();
    }

    public void setLastHttpRequestSuccess() {
        LocalDateTime time = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        long epochSecond = time.atZone(zoneId).toEpochSecond();

        lastHttpRequestSuccess.set(epochSecond);
    }

    public Timer getResponseTimeSetter() {
        return responseTime;
    }
}
