package com.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

@JsonIgnoreProperties(ignoreUnknown = true)

public class data {

    private String getKey;

    @Override
    public String toString()
    {
        return  getKey ;
    }

    public String setGetKey(JSONObject json, String getKey) {
        boolean exist = json.has(getKey);
        Iterator<?> keys;
        String nextKeys = null;

        if(!exist){
            keys = json.keys();
            while (keys.hasNext()){
                nextKeys = (String) keys.next();
                try {
                    if(json.get(nextKeys) instanceof JSONObject)
                    {
                        if(exist == false){
                            setGetKey(json.getJSONObject(nextKeys), getKey);
                        }
                    }else if(json.get(nextKeys) instanceof JSONArray)
                    {
                        JSONArray jsonArray = json.getJSONArray(nextKeys);
                        for (int i = 0; i < jsonArray.length(); i++){
                            String jsonString = json.get(String.valueOf(i)).toString();
                            JSONObject innerJson = new JSONObject(jsonString);

                            if(exist == false)
                            {
                                setGetKey(innerJson, getKey);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else
        {
            Main.parseObj(json,getKey);
        }
        return nextKeys;
    }

    public String getGetKey(){
        return getKey;
    }
}




