package com.demo;

import com.demo.countryResponse;
import com.demo.data;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import com.demo.st.fetchLeagueResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Boolean catch_Info = true;

        do {
            System.out.print("Do you want to search specific teams? (Y/N) {L-for specific League} : ");
            String repo = sc.next();

            if (repo.equalsIgnoreCase("N"))
            {
                Default_Country();

            }else if(repo.equalsIgnoreCase("Y"))
            {
                System.out.print("Enter the country_id name of your choice : ");
                int count_id = Integer.parseInt(sc.next());
                specific_country(count_id);
            }
            else if(repo.equalsIgnoreCase("L")){
                System.out.print("Enter the league_id of your choice : ");
                int league = Integer.parseInt(sc.next());
                specific_league(league);
            }
            System.out.print("Do you want to search again Y/N : ");
            String asking = sc.next();
                if(asking.equalsIgnoreCase("N")){
                    catch_Info = false;
                    break;
                }else if (asking.equalsIgnoreCase("Y")){
                    catch_Info = true;
                }
        } while (catch_Info == true);

    }

    public static void Default_Country(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://app.sportdataapi.com/api/v1/soccer/teams?apikey=c070c210-bbe4-11ec-a108-99c509a5d562&country_id=47")
                .get()
                .addHeader("apikey", "c070c210-bbe4-11ec-a108-99c509a5d562")
                .addHeader("sportsdataapi","app.sportdataapi.com")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            Headers responseHeader = response.headers();
            for (int i = 0; i < responseHeader.size(); i++){
                System.out.println(responseHeader.name(i) + ":" + responseHeader.value(i));
            }

            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            countryResponse leagues = objectMapper.readValue(responseBody.string(), new TypeReference<countryResponse>() {
            });
            String inputJSON = response.body().string();
            JSONObject jsonObject = new JSONObject(inputJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            ArrayList<Object> list_data = new ArrayList<Object>();
            if(jsonArray != null){
                for(int i = 0; i<jsonArray.length();i++){

                    list_data.add(jsonArray.get(i));
                }
            }
            for (int i = 0; i < list_data.size(); i++){
                System.out.println("TEAM INFORMATION");
                System.out.println(list_data.get(i));
            }
            //System.out.println(newData.setGetKey(inputObject, "data") + "\n") ;
            System.out.println(leagues);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void specific_country(int id){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://app.sportdataapi.com/api/v1/soccer/teams?apikey=c070c210-bbe4-11ec-a108-99c509a5d562&country_id="+id)
                .get()
                .addHeader("apikey", "c070c210-bbe4-11ec-a108-99c509a5d562")
                .addHeader("sportsdataapi","app.sportdataapi.com")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            Headers responseHeader = response.headers();
            for (int i = 0; i < responseHeader.size(); i++){
                System.out.println(responseHeader.name(i) + ":" + responseHeader.value(i));
            }
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            countryResponse leagues = objectMapper.readValue(responseBody.string(), new TypeReference<countryResponse>() {
            });

            String inputJSON = response.body().string();
            JSONObject jsonObject = new JSONObject(inputJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            ArrayList<Object> list_data = new ArrayList<Object>();
            if(jsonArray != null){
                for(int i = 0; i<jsonArray.length();i++){

                    list_data.add(jsonArray.get(i));
                }
            }
            for (int i = 0; i < list_data.size(); i++){
                System.out.println("TEAM INFORMATION");
                System.out.println(list_data.get(i));
            }
//            data newData = new data();
//            newData.setGetKey(inputObject, "data");

            System.out.println(leagues);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void specific_league(int id){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://app.sportdataapi.com/api/v1/soccer/teams/"+id+"?apikey=c070c210-bbe4-11ec-a108-99c509a5d562")
                .get()
                .addHeader("apikey", "c070c210-bbe4-11ec-a108-99c509a5d562")
                .addHeader("sportsdataapi","app.sportdataapi.com")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            Headers responseHeader = response.headers();
            for (int x = 0; x < responseHeader.size(); x++){
                System.out.println(responseHeader.name(x) + ":" + responseHeader.value(x));
            }
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            fetchLeagueResponse leagues = objectMapper.readValue(responseBody.string(), new TypeReference<fetchLeagueResponse>() {
            });

            String inputJSON = response.body().string();
            JSONObject inputObject = new JSONObject(inputJSON);

            data newData = new data();
            newData.setGetKey(inputObject, "data");

            System.out.println(leagues);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void parseObj(JSONObject json, String key){
        String output = String.valueOf(json.get(key));
         System.out.println(""+ output);

    }
}
