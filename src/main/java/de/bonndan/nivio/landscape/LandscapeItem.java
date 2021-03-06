package de.bonndan.nivio.landscape;

import java.util.Set;

public interface LandscapeItem {

    String LAYER_INFRASTRUCTURE = "infrastructure";
    String LAYER_APPLICATION = "application";
    String LAYER_INGRESS = "ingress";

    String IDENTIFIER_VALIDATION = "^[a-z0-9\\.\\:_-]{3,256}$";

    String STATUS_KEY_LIFECYCLE = "lifecycle";
    String STATUS_KEY_SECURITY = "security";
    String STATUS_KEY_STABILITY = "stability";
    String STATUS_KEY_BUSINESS_CAPABILITY = "business_capability";

    String STATUS_GREEN = "green";
    String STATUS_YELLOW = "yellow";
    String STATUS_ORANGE = "orange";
    String STATUS_RED = "red";

    /**
     * Returns the landscape-wide unique identifier of a server or application.
     *
     */
    String getIdentifier();

    /**
     * @return the type (ingress, service, infrastructure)
     */
    String getLayer();

    /**
     * @return the type (service, database, queue, loadbalancer...)
     */
    String getType();

    String getName();
    String getShort_name();

    String getGroup();

    String getSoftware();

    String getVersion();

    String getHomepage();

    String getRepository();

    String getContact();

    String getTeam();

    String getVisibility();

    String getMachine();

    String getScale();

    String getHost_type();

    Set<String> getNetworks();

    String getDescription();

    String[] getTags();

    String getNote();

    String getOwner();

    Set<InterfaceItem> getInterfaces();

    Set<DataFlowItem> getDataFlow();
}
