package com.laioffer.job.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
    private String id;
    private String title;
    private String location;
    private String companyLogo;
    private String url;
    private String description;
    private Set<String> keywords;
    private boolean favorite;

    public Item() {
    }

    public Item(String id, String title, String location, String companyLogo, String url, String description, Set<String> keywords, boolean favorite) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.companyLogo = companyLogo;
        this.url = url;
        this.description = description;
        this.keywords = keywords;
        this.favorite = favorite;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("company_logo")
    public String getCompanyLogo() {
        return companyLogo;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("keywords")
    public Set<String> getKeywords() {
        return keywords;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("favorite")
    public boolean isFavorite() {
        return favorite;
    }

    public void setKeywords(Set<String> strings) {
        keywords = strings;
    }

    @JsonProperty("id")
    public String getId() {
        return this.id;
    }
}
