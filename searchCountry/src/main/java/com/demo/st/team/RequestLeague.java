package com.demo.st.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestLeague {

    @JsonProperty("league_id")
    private String league_id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country_id")
    private int country_id;

    @Override
    public String toString() {
        return "\n League Name:" + name + " League_id:" + league_id + " Country_id:" + country_id + " \n";
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
