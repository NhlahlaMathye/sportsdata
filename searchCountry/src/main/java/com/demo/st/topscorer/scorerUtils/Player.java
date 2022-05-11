package com.demo.st.topscorer.scorerUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

    private int player_id;
    private String player_name;


    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }
}
