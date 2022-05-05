package com.demo.st.team;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestLeagueData {

        private int object;
        private RequestLeague data;

    @Override
    public String toString()
    {
        return " League data : " + data + " \n ";
    }

    public int getObject() {
        return object;
    }

    public void setObject(int object) {
        this.object = object;
    }

    public RequestLeague  getData() {
        return data;
    }

    public void setData(RequestLeague data) {
        this.data = data;
    }
}
