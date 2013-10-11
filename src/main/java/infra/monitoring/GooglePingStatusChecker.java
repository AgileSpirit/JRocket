package infra.monitoring;

import java.net.InetAddress;

import net.sf.appstatus.core.check.ICheck;
import net.sf.appstatus.core.check.ICheckResult;
import net.sf.appstatus.core.check.impl.StatusResultImpl;

public class GooglePingStatusChecker implements ICheck {

    public ICheckResult checkStatus() {

        StatusResultImpl result = new StatusResultImpl();
        result.setProbeName(getName());

        try {
            InetAddress address = InetAddress.getByName("www.google.com");

            if (address.isReachable(2000)) {
                result.setDescription("Google Access ok");
                result.setCode(ICheckResult.OK);
            } else {
                throw new Exception("Ping timeout (2000ms)");
            }

        } catch (Exception e) {
            result.setCode(ICheckResult.ERROR);
            result.setDescription("Google ping failed");
            result.setResolutionSteps("Ping failed. This means that ICMP messages are blocked by this host. (This may not be an issue) "
                    + e.getMessage());
            result.setFatal(false);
        }

        return result;
    }

    public String getName() {
        return "Google Ping check";
    }

    @Override
    public String getGroup() {
        return "Group name";
    }

}