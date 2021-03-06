package com.demo.st.team;

import com.demo.st.country.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.File;


@JsonIgnoreProperties(ignoreUnknown = true)

public class RequestTeams {

    private String name;
    private int team_id;
    private String short_code;
    private String common_name;
    private File logo;
    private Country country;


    @Override
    public String toString()
    {
        return "" + name +  " ";
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
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
