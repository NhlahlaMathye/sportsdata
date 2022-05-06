package com.demo;
import com.demo.st.country.CountriesByContinentResponse;
import com.demo.st.country.Country;
import com.demo.st.country.RequestAllCountries;
import com.demo.st.players.ResponsePlayers;
import com.demo.st.team.RequestLeague;
import com.demo.st.team.RequestLeagueResponse;
import com.demo.st.team.RequestTeamsResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class SportsDataUtils {

    private static final String BASE_URL = "https://app.sportdataapi.com/api/v1/soccer";
    private static final String API_KEY = "4d6265e0-cc82-11ec-b961-03b703adda4e";
    private static final String COUNTRY_URL = "/countries?apikey=";
    private static final String PLAYERS_URL = "/players?apikey=&country_id=";
    private static final String LEAGUES_URL = "/leagues?apikey=&country_id=";
    private static final String TEAMS_URL = "/teams/?apikey=&country_id=" ;
    private static final String CONTINENT_URL = "/countries?apikey=&continent=";

    final static Logger logger = Logger.getLogger(SportsDataUtils.class.getSimpleName());

    public static String ApiRequest(String url){

        final Request request = new Request.Builder()
                .url(BASE_URL + url)
                .addHeader("apikey",API_KEY)
                .get()
                .build();
        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()){

                return "request was not successful, Unexpected code: " + response;
            }
            ResponseBody responseBody = client.newCall(request).execute().body();

            //logger.info("Api response" + responseString);

            return responseBody.string();

        } catch (IOException e) {
            logger.info("Request Exception : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void specific_teams(int country_id){

        String urlTeams = TEAMS_URL + country_id;
        String responseBodyString = SportsDataUtils.ApiRequest(urlTeams);

        try {
            ObjectMapper countryMapper = new ObjectMapper();
            RequestTeamsResponse league_teams = countryMapper.readValue(responseBodyString, RequestTeamsResponse.class);

            System.out.println(league_teams);
        } catch (IOException e) {
            logger.info("IOException message: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void specific_league(int country_id){

        String specificLeagues = LEAGUES_URL + country_id;
        String responseBodyString = SportsDataUtils.ApiRequest(specificLeagues);

        try {
            ObjectMapper mapLeagues = new ObjectMapper();
            RequestLeagueResponse league_response = mapLeagues.readValue(responseBodyString, RequestLeagueResponse.class);

            for (Map.Entry<String, RequestLeague> responseMap : league_response.getData().entrySet()){

                String leagueName = responseMap.getValue().getName();
                String leagueID = responseMap.getValue().getLeague_id();

                System.out.println("League Name: " + leagueName + ", League ID: " + leagueID);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void specific_player(int coun_id){

        String playerUrl =PLAYERS_URL + coun_id;
        String responseBodyStr = SportsDataUtils.ApiRequest(playerUrl);

        try {
            ObjectMapper mapLeagues = new ObjectMapper();
            mapLeagues.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            ResponsePlayers leaguePlayers = mapLeagues.readValue(responseBodyStr, ResponsePlayers.class);

            if(responseBodyStr != null)
            {
                System.out.println(leaguePlayers);
            }else{
                System.out.println("No records found");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static  void searchLeagues(String country_name)
    {
        String leagueUrlSearch = COUNTRY_URL;
        final String responseBodyString = SportsDataUtils.ApiRequest(leagueUrlSearch);

        try {

            ObjectMapper leagueMap = new ObjectMapper();
            leagueMap.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestAllCountries allCountries = leagueMap.readValue(responseBodyString, RequestAllCountries.class );

            for (int l = 0; l < allCountries.getData().size(); l++)
            {
                String nameIn = allCountries.getData().get(l).getName();
                int countryId = allCountries.getData().get(l).getCountry_id();
                if(nameIn.equalsIgnoreCase(country_name))
                {
                    SportsDataUtils.specific_league(countryId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchCountryByContinent(String continent_name)
    {
        String countryContinentUrl = CONTINENT_URL +continent_name;
        String responseBodyString = SportsDataUtils.ApiRequest(countryContinentUrl);

        try {
            ObjectMapper continentMapper = new ObjectMapper();
            CountriesByContinentResponse continent = continentMapper.readValue(responseBodyString, CountriesByContinentResponse.class);

            for (Map.Entry<String, Country> countryEntry : continent.getData().entrySet())
            {
                String countryName = countryEntry.getValue().getName();
                String countryID = String.valueOf(countryEntry.getValue().getCountry_id());
                String countryCode = countryEntry.getValue().getCountry_code();

                System.out.println("\n Country Name:" + countryName + ", Country ID:" + countryID + ", Country Code:"+countryCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void searchCountry(String country_name)
    {
        String countriesUrl = COUNTRY_URL;
        String responseBodyString =  SportsDataUtils.ApiRequest(countriesUrl);
        try {
            ObjectMapper countryMapper = new ObjectMapper();
            RequestAllCountries countries = countryMapper.readValue(responseBodyString, RequestAllCountries.class);

            for (int i = 0; i < countries.getData().size(); i++) {
                String nameIndent = countries.getData().get(i).getName();
                int countryId = countries.getData().get(i).getCountry_id();
                if (nameIndent.equalsIgnoreCase(country_name)) {
                    SportsDataUtils.specific_teams(countryId);
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String searchPlayers(String country_name) {

        String countriesPlayersUrl = COUNTRY_URL;
        String responseBodyString = SportsDataUtils.ApiRequest(countriesPlayersUrl);

        String message = null;
        try {

            ObjectMapper countryMapper = new ObjectMapper();
            countryMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestAllCountries countryPlayers = countryMapper.readValue(responseBodyString, RequestAllCountries.class);

            if (countryPlayers != null) {
                for (int i = 0; i < countryPlayers.getData().size(); i++) {
                    String nameIndent = countryPlayers.getData().get(i).getName();
                    if (nameIndent.equalsIgnoreCase(country_name)) {
                        int countryId = countryPlayers.getData().get(i).getCountry_id();
                        SportsDataUtils.specific_player(countryId);
                    }
                }
            } else {
                message = "No records were found";
                System.out.println(message);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return country_name;
    }

}
