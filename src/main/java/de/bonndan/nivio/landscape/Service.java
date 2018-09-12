package de.bonndan.nivio.landscape;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

@Entity
public class Service implements LandscapeItem {

    @Id
    @NotNull
    @Pattern(regexp = LandscapeItem.IDENTIFIER_VALIDATION)
    private String identifier;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private Landscape landscape;

    @NotNull
    private String type;

    private String name;

    private String owner;

    private String team;

    private String contact;

    private String homepage;

    private String description;

    private String short_name;

    private String version;

    private String software;

    private String repository;

    @Column(name = "`group`")
    private String group;

    private String visibility;

    private String[] tags;

    private String network_zone;

    private String machine;

    private String scale;

    private String host_type;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "source")
    private Set<DataFlow> dataFlow = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "INFRASTRUCTURE",
            joinColumns = {@JoinColumn(name = "service_identifier")},
            inverseJoinColumns = {@JoinColumn(name = "infrastructure_identifier")})
    private Set<Service> providedBy = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "INFRASTRUCTURE",
            joinColumns = {@JoinColumn(name = "infrastructure_identifier")},
            inverseJoinColumns = {@JoinColumn(name = "service_identifier")})
    private Set<Service> provides = new HashSet<>();

    private String note;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier.toLowerCase();
    }

    public Landscape getLandscape() {
        return landscape;
    }

    public void setLandscape(Landscape landscape) {
        this.landscape = landscape;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getNetwork_zone() {
        return network_zone;
    }

    public void setNetwork_zone(String network_zone) {
        this.network_zone = network_zone;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getHost_type() {
        return host_type;
    }

    public void setHost_type(String host_type) {
        this.host_type = host_type;
    }

    public Set<DataFlow> getDataFlow() {
        return dataFlow;
    }

    public void setDataFlow(Set<DataFlow> outgoing) {
        this.dataFlow = outgoing;
    }

    public Set<Service> getProvidedBy() {
        return providedBy;
    }

    public void setProvidedBy(Set<Service> providedBy) {
        this.providedBy = providedBy;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    /**
     * Check if service provides any other.
     * <p>
     * Returns false if an infrastructure item has no "providedBy" relationship.
     */
    public boolean providesAny() {
        if (LandscapeItem.APPLICATION.equals(type)) {
            return true;
        }

        return !provides.isEmpty();
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public Set<Service> getProvides() {
        return provides;
    }

    public void setProvides(Set<Service> provides) {
        this.provides = provides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(identifier, service.identifier) &&
                Objects.equals(landscape, service.landscape);
    }

    @Override
    public int hashCode() {

        return Objects.hash(identifier, landscape);
    }

    @Override
    public String toString() {
        return identifier + " (" + type + ", group: " + group + ")";
    }
}