package com.demo.st.referees;

import com.demo.query.Query;

import java.util.List;

public class ResponseReferees {

    private Query query;
    private List<RequestReferee> data;

    @Override
    public String toString()
    {
        return " " + data;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<RequestReferee> getData() {
        return data;
    }

    public void setData(List<RequestReferee> data) {
        this.data = data;
    }
}
