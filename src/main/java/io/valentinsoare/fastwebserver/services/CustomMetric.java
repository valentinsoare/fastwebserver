package io.valentinsoare.fastwebserver.services;

import io.valentinsoare.fastwebserver.metrics.HomeEndPointMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomMetric {
    private HomeEndPointMetrics homeEndPoint;

    @Autowired
    public CustomMetric(HomeEndPointMetrics homeEndPoint) {
        this.homeEndPoint = homeEndPoint;
    }

    public HomeEndPointMetrics getHomeEndPointMetrics() {
        return homeEndPoint;
    }
}
