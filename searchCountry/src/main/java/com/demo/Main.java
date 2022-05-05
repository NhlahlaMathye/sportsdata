package com.demo;


import com.demo.st.country.RequestAllCountries;
import com.demo.st.country.CountriesResponse;
import com.demo.st.players.ResponsePlayers;
import com.demo.st.team.RequestLeagueResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import org.json.JSONObject;
import java.io.IOException;
import java.util.InputMismatchException;
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

    private static final String apiKey = "eabdaa10-cb20-11ec-8eeb-536de17b6548";

    //Main method
    public static void main(String[] args) throws IOException, InputMismatchException {

        Scanner sc = new Scanner(System.in);
        Boolean catch_Info;

        System.out.println("\n Welcome to the Sports Data App \n");
        do {
            System.out.println("Select number for the information you would like to receive." +
                    "\n 1. Default Country Teams" +
                    "\n 2. Search Teams By Country Name" +
                    "\n 3. View Leagues" +
                    "\n 4. View Countries" +
                    "\n 5. View Players By Their Nationalities" +
                    "\n" +
                    "\n Enter number zero(0) to exit the program.");
            int input_user = sc.nextInt();
            if(input_user == 1)
            {
                String country_de = "South Africa";
                searchCountry(country_de);
                catch_Info = true;
            }
            else if(input_user == 2){
                System.out.print("From which country would you like to receive teams : ");
                String country = sc.next();
                searchCountry(country);
                catch_Info = true;
            }
            else if(input_user == 3)
            {
                System.out.println("Enter name of country for leagues you want to receive.");
                String league_co = sc.next();
                searchLeagues(league_co);
                catch_Info = true;
            }
            else if (input_user == 4){
                System.out.println("Here are all the available Countries.");
                search_countries();
                catch_Info = true;
            }
            else if(input_user == 5)
            {
                System.out.print("From which country would you like to view players : ");
                String country_play = sc.next();
                searchPlayers(country_play);
                catch_Info = true;
            }
            else if (input_user == 0){
                break;
            }
            catch_Info = false;
        } while (!catch_Info );
    }

    //This method requires id the printout data of a specific country
    public static void specific_country(int country_id){

        String url = "https://app.sportdataapi.com/api/v1/soccer/teams/?apikey="+apiKey+"&country_id="+country_id;
        String responseBodyString = ApiRequest(url);
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper countryMapper = new ObjectMapper();
            CountriesResponse leagues = countryMapper.readValue(responseBodyString, new TypeReference<CountriesResponse>() {
            });

            System.out.println(leagues);
        } catch (IOException e) {
            logger.info("IOException message: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void search_leagues()
    {
        String leagues = "https://app.sportdataapi.com/api/v1/soccer/leagues?apikey="+apiKey;
        String responseBodyString = ApiRequest(leagues);

        try {
            if(!response.isSuccessful()) throw new IOException("Error code : " + response);
            ObjectMapper mapLeagues = new ObjectMapper();
            mapLeagues.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestLeagueResponse liga_response = mapLeagues.readValue(responseBodyString, RequestLeagueResponse.class);

            System.out.println(liga_response.getData());

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void search_countries()
    {
        String country_url = "https://app.sportdataapi.com/api/v1/soccer/countries?apikey="+apiKey;
        String responseBodyString =  ApiRequest(country_url);
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper countryMapper = new ObjectMapper();
            countryMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestAllCountries countries = countryMapper.readValue(responseBodyString, new TypeReference<RequestAllCountries>() {
            });

            System.out.println(countries);
        }
        catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void specific_league(int country_id){

        String specific_leagues = "https://app.sportdataapi.com/api/v1/soccer/leagues?apikey="+apiKey+"&country_id="+country_id;
        String responseBodyString = ApiRequest(specific_leagues);

        try {
            if(!response.isSuccessful()) throw new IOException("Error code : " + response);
            ObjectMapper mapLeagues = new ObjectMapper();
            RequestLeagueResponse liga_response = mapLeagues.readValue(responseBodyString, RequestLeagueResponse.class);

            System.out.println(liga_response);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void specific_player(int coun_id){

        String play_url ="https://app.sportdataapi.com/api/v1/soccer/players?apikey=eabdaa10-cb20-11ec-8eeb-536de17b6548&country_id="+coun_id;
        String responseBodyStr = ApiRequest(play_url);

        try {
            if(!response.isSuccessful()) throw new IOException("Error code : " + response);
            ObjectMapper mapLeagues = new ObjectMapper();
            mapLeagues.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            ResponsePlayers liga_response = mapLeagues.readValue(responseBodyStr, ResponsePlayers.class);

            System.out.println(liga_response);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String ApiRequest(String url){

        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
                OkHttpClient client = new OkHttpClient();

                try {
                     response = client.newCall(request).execute();
                    if(!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
                    responseBody = client.newCall(request).execute().body();

                    String responseString = responseBody.string();

                    //logger.info("Api response" + responseString);

                    return responseString;

                } catch (IOException e) {
                    logger.info("Request Exception : " + e.getMessage());
                    e.printStackTrace();
                }
                return null;
    }

    public static  void searchLeagues(String country_name)
    {
        String league_url = "https://app.sportdataapi.com/api/v1/soccer/countries?apikey="+apiKey;

        final String responseBodyString = ApiRequest(league_url);

        try {

            if(!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper leagueMap = new ObjectMapper();
            leagueMap.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestAllCountries liga = leagueMap.readValue(responseBodyString, new TypeReference<RequestAllCountries>() {});

            for (int l = 0; l < liga.getData().size(); l++)
            {
                String name_in = liga.getData().get(l).getName();
                int country_id = liga.getData().get(l).getCountry_id();
                if(name_in.equalsIgnoreCase(country_name))
                {
                    specific_league(country_id);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchCountry(String country_name)
    {
        String countries_url = "https://app.sportdataapi.com/api/v1/soccer/countries?apikey="+apiKey;
        String responseBodyString =  ApiRequest(countries_url);
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper countryMapper = new ObjectMapper();
            countryMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestAllCountries countries = countryMapper.readValue(responseBodyString, new TypeReference<RequestAllCountries>() {
            });

            for (int i = 0; i < countries.getData().size(); i++) {
                String name_indent = countries.getData().get(i).getName();
                int count_id = countries.getData().get(i).getCountry_id();
                if (name_indent.equalsIgnoreCase(country_name)) {
                    specific_country(count_id);
                }
            }
        }
     catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void searchPlayers(String country_name)
    {
        String couies_url = "https://app.sportdataapi.com/api/v1/soccer/countries?apikey="+apiKey;
        String responseBodyString =  ApiRequest(couies_url);
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper countryMapper = new ObjectMapper();
            countryMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestAllCountries countryP = countryMapper.readValue(responseBodyString, new TypeReference<RequestAllCountries>() {
            });

            for (int i = 0; i < countryP.getData().size(); i++) {
                String name_indent = countryP.getData().get(i).getName();
                int count_id = countryP.getData().get(i).getCountry_id();
                if (name_indent.equalsIgnoreCase(country_name)) {
                    specific_player(count_id);
                }
            }
        }
        catch (JsonProcessingException ex) {
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
