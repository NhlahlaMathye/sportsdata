package com.demo.st.topscorer;

import com.demo.st.topscorer.scorerUtils.Goals;
import com.demo.st.topscorer.scorerUtils.Player;
import com.demo.st.topscorer.scorerUtils.Team;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestScorer {

    private int pos;
    private Player player;
    private Team team;
    private int league_id;
    private int season_id;
    private int matches_played;
    private int minutes_played;
    private int substituted_in;
    private Goals goals;
    private int penalties;

    @Override
    public String toString()
    {
        return "\n Position:" + pos + " " + player + " " + team + " Matches Played:" + matches_played
                + " Minutes Played:" + minutes_played + " Substituted:" + substituted_in + " " + goals + " Penalties:" + penalties;
    }

    public int getPos() {
        return pos;
    }


    public void setPos(int pos) {
        this.pos = pos;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getLeague_id() {
        return league_id;
    }

    public void setLeague_id(int league_id) {
        this.league_id = league_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getMatches_played() {
        return matches_played;
    }

    public void setMatches_played(int matches_played) {
        this.matches_played = matches_played;
    }

    public int getMinutes_played() {
        return minutes_played;
    }

    public void setMinutes_played(int minutes_played) {
        this.minutes_played = minutes_played;
    }

    public int getSubstituted_in() {
        return substituted_in;
    }

    public void setSubstituted_in(int substituted_in) {
        this.substituted_in = substituted_in;
    }

    public Goals getGoals() {
        return goals;
    }

    public void setGoals(Goals goals) {
        this.goals = goals;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }
}
