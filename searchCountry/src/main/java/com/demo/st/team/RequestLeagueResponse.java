package com.demo.st.team;
import com.demo.query.Query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestLeagueResponse {

    private Query query;
    HashMap<String, RequestLeague>  data = new HashMap<>();

    @Override
    public String toString() {
        return  "" + query + "" + data + "";
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public HashMap<String, RequestLeague> getData() {
        return data;
    }

    public void setData(HashMap<String, RequestLeague> data) {
        this.data = data;
    }
}
