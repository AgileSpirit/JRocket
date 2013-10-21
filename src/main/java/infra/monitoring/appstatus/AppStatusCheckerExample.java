package infra.monitoring.appstatus;

import java.io.IOException;

import net.sf.appstatus.core.check.AbstractCheck;
import net.sf.appstatus.core.check.ICheckResult;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;

public class AppStatusCheckerExample extends AbstractCheck {

    public ICheckResult checkStatus() {
        ICheckResult result;

        try {
            HttpResponse response = Request.Get("http://www.google.com").execute().returnResponse();
            if (response == null || response.getStatusLine().getStatusCode() != 200) {
                throw new IOException("Could not reach URL");
            }
            result = createResult(OK);
            result.setDescription("Google Access ok");
        } catch (Exception e) {
            e.printStackTrace();
            result = createResult(WARN);
            result.setDescription("Google ping failed");
            result.setResolutionSteps("Ping failed. This means that ICMP messages are blocked by this host. (This may not be an issue)");
        }
        return result;
    }

    public String getGroup() {
        return "google";
    }

    public String getName() {
        return "Google Ping check";
    }
}
