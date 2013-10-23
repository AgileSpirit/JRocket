package infra.monitoring.metrics.healthchecks;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;

import com.codahale.metrics.health.HealthCheck;

/**
 * Example of an HTTP based Metrics <code>HealthChecker</code>
 */
public class RestResourcesHealthCheck extends HealthCheck {

    private final String pingUrl;
    
    public RestResourcesHealthCheck(String pingUrl) {
        this.pingUrl = pingUrl;
    }

    @Override
    protected Result check() throws Exception {
        
        HttpResponse response = Request.Get(pingUrl).execute().returnResponse();
        
        if (response == null || response.getStatusLine().getStatusCode() != 200) {
            return Result.unhealthy("REST resources are not available");
        }
        return Result.healthy("REST resources are available :-)");
    }
    
}
