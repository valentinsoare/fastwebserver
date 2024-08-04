package io.valentinsoare.fastwebserver.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;


@Getter
@Component
public class HomeEndPointMetrics {
    private final AtomicInteger http_server_requests;
    private final AtomicInteger http_server_requests_success;
    private final Timer http_server_response_time;

    @Autowired
    public HomeEndPointMetrics(MeterRegistry meterRegistry) {

        this.http_server_requests = new AtomicInteger(0);
        Gauge.builder("http_server_requests", http_server_requests, AtomicInteger::get )
                .description("Number of HTTP Requests coming into the server.")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);

        this.http_server_requests_success = new AtomicInteger(0);
        Gauge.builder("http_server_requests_success", http_server_requests_success, AtomicInteger::get)
                .description("Number of HTTP requests with success.")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);

        this.http_server_response_time = Timer.builder("http_server_response_time")
                .description("Timer for response time measurements.")
                .tags("environment", "production", "endpoint", "/")
                .register(meterRegistry);
    }

    public void incrementHttpRequests() {
        this.http_server_requests.getAndIncrement();
    }

    public void decrementHttpRequests() {
        this.http_server_requests.getAndDecrement();
    }

    public void incrementHttpRequestsWithSuccess() {
        this.http_server_requests_success.getAndIncrement();
    }

    public void decrementHttpRequestsWithSuccess() {
        this.http_server_requests_success.getAndDecrement();
    }


}

