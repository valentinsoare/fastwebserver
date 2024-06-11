package io.valentinsoare.fastwebserver.monitoringandalterting;

import io.micrometer.core.instrument.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/**
 * This class is responsible for managing custom metrics for the application.
 * It uses the Micrometer library to create and manage the metrics.
 */
@Component
public class CustomMetricService {
    private final Counter httpRequests;
    private final AtomicInteger httpRequestsInProgress;
    private final AtomicLong lastHttpRequestServed;
    private final Timer responseTime;


    /**
     * Constructor that sets up the metrics.
     *
     * @param meterRegistry The MeterRegistry instance used to register the metrics
     */
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

    /**
     * This method increments the total number of HTTP requests.
     */
    public void incrementHttpTotalRequests() {
        httpRequests.increment();
    }

    /**
     * This method increments the number of HTTP requests in progress.
     */
    public void incrementHttpRequestsInProgress() {
        httpRequestsInProgress.getAndIncrement();
    }

    /**
     * This method decrements the number of HTTP requests in progress.
     */
    public void decrementHttpRequestsInProgress() {
        httpRequestsInProgress.getAndDecrement();
    }

    /**
     * This method sets the time of the last HTTP request served.
     */
    public void setLastHttpRequestServed() {
        LocalDateTime time = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        long epochSecond = time.atZone(zoneId).toEpochSecond();

        lastHttpRequestServed.set(epochSecond);
    }

    /**
     * This method returns the Timer instance that measures the response time.
     *
     * @return The Timer instance
     */
    public Timer getResponseTimeSetter() {
        return responseTime;
    }
}
