package com.demo;

import com.demo.st.country.countryResponse;
import com.demo.parsedata.data;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import com.demo.st.team.fetchLeagueResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    //Set the response body of api request
    private static ResponseBody responseBody;

    //Set the response of api request
    private static Response response;

    //LOGGER
    final static Logger logger = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Boolean catch_Info = false;

        do {
            System.out.print("Do you want to search specific teams? (Y/N) {L-for specific League} : ");
            String repo = sc.next();
            if (repo.equalsIgnoreCase("N"))
            {
                Default_Country();
            }
            else if(repo.equalsIgnoreCase("Y"))
            {
                System.out.print("Enter ID of country name of your choice : ");
                if(sc.hasNextInt())
                {
                    int count_id = sc.nextInt();
                    specific_country(count_id);
                    catch_Info = true;
                }else{
                    System.out.println("Enter a valid country ID");
                    catch_Info = false;
                    sc.next();
                }
            }
           else if(repo.equalsIgnoreCase("L")){
                System.out.print("Enter ID of the team of your choice : ");
                if(sc.hasNextInt()){

                int league = sc.nextInt();
                specific_league(league);
                catch_Info = true;
            }
                else{
                    System.out.println("Enter a valid team ID");
                    catch_Info = false;
                    sc.next();
                }
           }
            System.out.print("Do you want to search again? (Y/N) : ");
            String asking = sc.next();
            if (asking.equalsIgnoreCase("Y")) {
                catch_Info = false;

            } else {
                catch_Info = true;
            }
        } while (catch_Info == false);

    }

    public static void Default_Country(){

        String url = "https://app.sportdataapi.com/api/v1/soccer/teams?apikey=c070c210-bbe4-11ec-a108-99c509a5d562&country_id=47";
        ApiRequest(url);

        try {
            ObjectMapper defaultMapper = new ObjectMapper();
            countryResponse leagues = defaultMapper.readValue(responseBody.string(), new TypeReference<countryResponse>() {
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

        String url = "https://app.sportdataapi.com/api/v1/soccer/teams?apikey=c070c210-bbe4-11ec-a108-99c509a5d562&country_id="+id;
        ApiRequest(url);

        try {

            if(!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper countryMapper = new ObjectMapper();
            countryResponse leagues = countryMapper.readValue(responseBody.string(), new TypeReference<countryResponse>() {
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

            System.out.println(leagues);

        } catch (IOException e) {
            logger.info("IOException message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void specific_league(int id){

        String url = "https://app.sportdataapi.com/api/v1/soccer/teams/"+id+"?apikey=c070c210-bbe4-11ec-a108-99c509a5d562";
        ApiRequest(url);

        try {
            ObjectMapper mapper = new ObjectMapper();
            fetchLeagueResponse leagueResponse = mapper.readValue(responseBody.string(), new TypeReference<fetchLeagueResponse>() {});

            String inputJSON = response.body().string();
            JSONObject inputOBJ = new JSONObject(inputJSON);
            data newData = new data();
            System.out.println(newData.setGetKey(inputOBJ, "data"));
            System.out.println(leagueResponse);

        }  catch (IOException e) {
            logger.info("IOException message : " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static String ApiRequest(String url){

        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("apikey", "c070c210-bbe4-11ec-a108-99c509a5d562")
                .addHeader("sportsdataapi","app.sportdataapi.com")
                .build();

                OkHttpClient client = new OkHttpClient();

                try {

                     response = client.newCall(request).execute();
                    if(!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
                    responseBody = client.newCall(request).execute().body();

                } catch (IOException e) {
                    logger.info("Request Exception : " + e.getMessage());
                    e.printStackTrace();
                }
        return null;
    }

    public static void parseObj(JSONObject json, String key){
        String output = String.valueOf(json.get(key));
         System.out.println(""+ output);

    }
}
