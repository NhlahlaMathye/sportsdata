package com.demo.st.matches;

import com.demo.st.team.RequestTeams;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestMatch {

  private int match_id;
  private int status_code;
  private String status;
  private String match_start;
  private int league_id;
  private int season_id;
  private RequestTeams home_team;
  private RequestTeams away_team;

  @Override
  public String toString()
  {
    return "\n " + status + "\n " + match_start + "\n Home team:" + home_team + "\n Away team:" + away_team;
  }

  public int getMatch_id() {
    return match_id;
  }

  public void setMatch_id(int match_id) {
    this.match_id = match_id;
  }

  public int getStatus_code() {
    return status_code;
  }

  public void setStatus_code(int status_code) {
    this.status_code = status_code;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMatch_start() {
    return match_start;
  }

  public void setMatch_start(String match_start) {
    this.match_start = match_start;
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

  public RequestTeams getHome_team() {
    return home_team;
  }

  public void setHome_team(RequestTeams home_team) {
    this.home_team = home_team;
  }

  public RequestTeams getAway_team() {
    return away_team;
  }

  public void setAway_team(RequestTeams away_team) {
    this.away_team = away_team;
  }
}
