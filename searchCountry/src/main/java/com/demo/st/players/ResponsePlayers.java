package com.demo.st.players;

import com.demo.query.Query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePlayers {

    private Query query;
    private List<RequestPlayers> data;


    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<RequestPlayers> getData() {
        return data;
    }

    public void setData(List<RequestPlayers> data) {
        this.data = data;
    }
}
