package com.demo.st;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.File;

@JsonIgnoreProperties(ignoreUnknown = true)
public class fetchLeagues {

    private String short_code;
    private int team_id;
    private String Tname;
    private File logo;
    private int country_id;
    private String name;
    private String country_code;
    private String continent;


    @Override
    public String toString() {
        return "{Team Name:" + name +"}"+ " {team_id :" + team_id + "}" + " {Country_ID:" + country_id + "}" + "Short code :" + short_code +
                " country_code: " + country_code + " country_name: " + Tname + " logo: " + logo + " continent: " + continent;
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

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public File getLogo() {
        return logo;
    }

    public void setLogo(File logo) {
        this.logo = logo;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
