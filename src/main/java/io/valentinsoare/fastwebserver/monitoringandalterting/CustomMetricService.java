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

        this.httpRequestsSuccess = new AtomicInteger();
        Gauge.builder("httpRequestsSuccess", httpRequestsSuccess, AtomicInteger::get)
                .description("Number of HTTP requests with success.")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);

        this.httpRequestsFailed = new AtomicInteger();
        Gauge.builder("httpRequestsFailed", httpRequestsFailed, AtomicInteger::get)
                .description("Number of HTTP with failure.")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);

        this.lastHttpRequestSuccess = new AtomicLong();
        Gauge.builder("lastHttpRequestSuccess", lastHttpRequestSuccess, AtomicLong::get)
                .description("Last Http Request Served with Success")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);

        this.lastHttpRequestFailed = new AtomicLong();
        Gauge.builder("lastHttpRequestFailed", lastHttpRequestFailed, AtomicLong::get)
                .description("Last Http Request failed.")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);

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
        /**
         Here we should implement something with tracing of any http request or
         answer with UUID to be able to track the request down the flow.
         */

        LocalDateTime time = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        long epochSecond = time.atZone(zoneId).toEpochSecond();

        lastHttpRequestSuccess.set(epochSecond);
    }

    public void setLastHttpRequestFailed() {
        /**
         Here we should implement something with tracing of any http request or
         answer with UUID to be able to track the request down the flow.
         */

        LocalDateTime time = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        long epochSecond = time.atZone(zoneId).toEpochSecond();

        lastHttpRequestFailed.set(epochSecond);
    }

    public void incrementHttpRequestsWithSuccess() {
        httpRequestsSuccess.getAndIncrement();
    }

    public void decrementHttpRequestsWithSuccess() {
        httpRequestsSuccess.getAndDecrement();
    }

    public void incrementHttpRequestsWithFailure() {
        httpRequestsFailed.getAndIncrement();
    }

    public void decrementHttpRequestsWithFailure() {
        httpRequestsFailed.getAndDecrement();
    }

    public Timer getResponseTimeSetter() {
        return responseTime;
    }
}
