package io.valentinsoare.fastwebserver.services;

import io.valentinsoare.fastwebserver.metrics.HomeEndPointMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics implements MetricServices {
    private final HomeEndPointMetrics homeEndPoint;

    @Autowired
    public CustomMetrics(HomeEndPointMetrics homeEndPoint) {
        this.homeEndPoint = homeEndPoint;
    }

    public HomeEndPointMetrics getHomeEndPointMetrics() {
        return homeEndPoint;
    }

    public CustomMetrics getCustomMetrics() {
        return this;
    }
}
