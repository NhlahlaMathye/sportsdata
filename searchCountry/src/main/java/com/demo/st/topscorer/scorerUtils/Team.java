package com.demo.st.topscorer.scorerUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    private int team_id;
    private String team_name;

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
}
