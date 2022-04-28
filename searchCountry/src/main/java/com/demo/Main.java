package com.demo;

import com.demo.st.Country;
import com.demo.st.country.allCountries;
import com.demo.st.country.countryResponse;
import com.demo.parsedata.data;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import com.demo.st.team.fetchLeagueResponse;

import org.json.JSONObject;


import java.io.IOException;

import java.util.Scanner;
import java.util.logging.Logger;

@SuppressWarnings("ConstantConditions")
public class Main {

    //Set the response body of api request
    private static ResponseBody responseBody;
    //Set the response of api request
    private static Response response;
    //LOGGER
    final static Logger logger = Logger.getLogger(Main.class.getSimpleName());
    private String name;

    //Main method
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        Boolean catch_Info = false;

        do {
            System.out.print("Do you want to search specific teams? (Y/N)");
            String repo = sc.next();
            if (repo.equalsIgnoreCase("N"))
            {
                String default_country = "England";
                searchCountry(default_country);
            }
            else if(repo.equalsIgnoreCase("Y"))
            {
                System.out.print("Enter country name of your choice : ");
                if(sc.hasNext()) {

                    String country_name = sc.next();
                    searchCountry(country_name);
                    catch_Info = true;
                }else{
                    System.out.println("Enter a valid country name");
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


    //This method requires id the printout data of a specific country
    public static void specific_country(int country_id){
        Scanner scb = new Scanner(System.in);
        String url = "https://app.sportdataapi.com/api/v1/soccer/teams/?apikey=c070c210-bbe4-11ec-a108-99c509a5d562&country_id="+country_id;
        ApiRequest(url);
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper countryMapper = new ObjectMapper();
            countryResponse leagues = countryMapper.readValue(responseBody.string(), new TypeReference<countryResponse>() {
            });

            System.out.println(leagues);

        } catch (IOException e) {
            logger.info("IOException message: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static String ApiRequest(String url){

        MediaType json_media =  MediaType.parse("application/json; charset=urf-8");

        RequestBody formBody = new FormBody.Builder()
                .add("country", "Germany")
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .get()
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
        return url;
    }

    public static void searchCountry(String country_name)
    {
        String countries_url = "https://app.sportdataapi.com/api/v1/soccer/countries?apikey=c070c210-bbe4-11ec-a108-99c509a5d562";
        ApiRequest(countries_url);

        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper countryMapper = new ObjectMapper();
            countryMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            allCountries countries = countryMapper.readValue(responseBody.string(), new TypeReference<allCountries>() {
            });

            for (int i = 0 ; i < countries.getData().size();i++)
            {
                String name_indent = countries.getData().get(i).getName();
                //System.out.println(countries.getData().get(i).getName());
                if(name_indent.equalsIgnoreCase(country_name)){
                    //System.out.println("Country found");
                    //System.out.println("Country ID:"+countries.getData().get(i).getCountry_id());
                    int count_id = (int) countries.getData().get(i).getCountry_id();
                    specific_country(count_id);
                }
            }

            } catch (JsonMappingException ex) {
            ex.printStackTrace();
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    public static void parseObj(JSONObject json, String key){
        String output = String.valueOf(json.get(key));
         System.out.println(""+ output);

    }
}
