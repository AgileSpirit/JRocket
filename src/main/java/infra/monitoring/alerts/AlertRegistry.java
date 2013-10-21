package infra.monitoring.alerts;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AlertRegistry {

    private final ConcurrentMap<String, Alert> alerts;
    
    public AlertRegistry() {
        alerts = new ConcurrentHashMap<String, Alert>();
    }
    
    public Alert register(Alert alert) {
        String name = alert.getName();
        final Alert existing = alerts.putIfAbsent(name, alert);
        if (existing != null) {
            throw new IllegalArgumentException("An alert named " + name + " already exists");
        }
        return alert;
    }
    
    public boolean remove(String name) {
        final Alert alert = alerts.remove(name);
        if (alert != null) {
            return true;
        }
        return false;
    }

    public Map<String, Alert> getAlerts() {
        return Collections.unmodifiableMap(alerts);
    }

}
