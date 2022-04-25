package com.demo.st.team;

import com.demo.st.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.File;

@JsonIgnoreProperties(ignoreUnknown = true)
public class fetchLeagues {

    private String short_code;
    private int team_id;
    private File logo;
    private String name;
    private Country country;


    @Override
    public String toString() {
        return "{Team Name:" + name +"}"+ " {team_id :" + team_id + "} " +  " Short code : " + short_code + " logo: " + logo + " Country_Obj : " + country
                ;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getLeague_id() {
        return team_id;
    }

    public void setLeague_id(int league_id) {
        this.team_id = league_id;
    }

    public String getShort_code() {
        return short_code;
    }

    public void setShort_code(String short_code) {
        this.short_code = short_code;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public File getLogo() {
        return logo;
    }

    public void setLogo(File logo) {
        this.logo = logo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
