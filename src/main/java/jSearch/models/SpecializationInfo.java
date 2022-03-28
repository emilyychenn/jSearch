package jSearch.models;

import java.util.UUID;

public class SpecializationInfo {
    UUID spec_id;
    String major;
    String minor;
    boolean is_honours;
    String degree_type;

    // constructor for all fields that cannot be NULL
    // TODO: add checks in models for not null
    // TODO: add methods in models to get functional dependencies (e.g. given major, is_honours, degree_type, get numCredits?)
    public SpecializationInfo(UUID spec_id, String major, boolean is_honours, String degree_type) {
        this.spec_id = spec_id;
        this.major = major;
        this.is_honours = is_honours;
        this.degree_type = degree_type;
    }

    public SpecializationInfo(UUID spec_id, String major, String minor, boolean is_honours, String degree_type) {
        this.spec_id = spec_id;
        this.major = major;
        this.minor = minor;
        this.is_honours = is_honours;
        this.degree_type = degree_type;
    }
}