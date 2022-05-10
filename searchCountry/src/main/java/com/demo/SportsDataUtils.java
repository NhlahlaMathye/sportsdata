package com.demo;
import com.demo.st.country.CountriesByContinentResponse;
import com.demo.st.country.Country;
import com.demo.st.country.RequestAllCountries;
import com.demo.st.players.RequestPlayers;
import com.demo.st.players.ResponsePlayers;
import com.demo.st.team.RequestLeague;
import com.demo.st.team.RequestLeagueResponse;
import com.demo.st.team.RequestTeamsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public static void specific_teams(int countryTeamId){

        String urlTeams = TEAMS_URL + countryTeamId;
        String responseBodyTeamString = SportsDataUtils.ApiRequest(urlTeams);

        try {
            ObjectMapper teamMapper = new ObjectMapper();
            RequestTeamsResponse leagueTeams = teamMapper.readValue(responseBodyTeamString, RequestTeamsResponse.class);

            System.out.println(leagueTeams);
        } catch (IOException e) {
            logger.info("IOException message: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void specific_league(int countriesId){

        String specificLeagues = LEAGUES_URL + countriesId;
        String responseBodyString = SportsDataUtils.ApiRequest(specificLeagues);

        try {

            ObjectMapper mapLeagues = new ObjectMapper();
            RequestLeagueResponse leagueResponse = mapLeagues.readValue(responseBodyString, RequestLeagueResponse.class);

            for (Map.Entry<String, RequestLeague> responseMap : leagueResponse.getData().entrySet()){

                String leagueName = responseMap.getValue().getName();
                String leagueID = responseMap.getValue().getLeague_id();
                System.out.println("League Name: " + leagueName + ", League ID: " + leagueID);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void specific_player(int countryID){

        String playerUrl =PLAYERS_URL + countryID;
        String responseBodyStr = SportsDataUtils.ApiRequest(playerUrl);

        if(!responseBodyStr.isEmpty()) {
            try {
                ObjectMapper mapPlayers = new ObjectMapper();
                mapPlayers.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                ResponsePlayers leaguePlayers = mapPlayers.readValue(responseBodyStr, ResponsePlayers.class);

                    System.out.println(leaguePlayers);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("No records were found");
        }
    }

    public static  void searchLeagues(String countryLeagueName)
    {
        final String responseBodyLeagueString = SportsDataUtils.ApiRequest(COUNTRY_URL);

        try {

            ObjectMapper leagueMap = new ObjectMapper();
            leagueMap.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestAllCountries allCountries = leagueMap.readValue(responseBodyLeagueString, RequestAllCountries.class );

            for (int l = 0; l < allCountries.getData().size(); l++)
            {
                String nameInLeague = allCountries.getData().get(l).getName();
                int countryId = allCountries.getData().get(l).getCountry_id();
                if(nameInLeague.equalsIgnoreCase(countryLeagueName))
                {
                    SportsDataUtils.specific_league(countryId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchCountrySeasons(String seasonCountryN){

        String responseBodySeason = SportsDataUtils.ApiRequest(COUNTRY_URL);

        try {
                ObjectMapper seasonCountry = new ObjectMapper();
                seasonCountry.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                RequestAllCountries allSeasons = seasonCountry.readValue(responseBodySeason, RequestAllCountries.class);

                for (int o = 0; o < allSeasons.getData().size(); o++)
                {

                }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void searchCountryByContinent(String continentName)
    {
        String countryContinentUrl = CONTINENT_URL +continentName;
        String responseBodyContinentString = SportsDataUtils.ApiRequest(countryContinentUrl);

        try {
            ObjectMapper continentMapper = new ObjectMapper();
            CountriesByContinentResponse continent = continentMapper.readValue(responseBodyContinentString, CountriesByContinentResponse.class);

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

    public static void searchCountry(String countryName)
    {
        String responseBodyCountryString =  SportsDataUtils.ApiRequest(COUNTRY_URL);
        try {
            ObjectMapper countryMapper = new ObjectMapper();
            RequestAllCountries countries = countryMapper.readValue(responseBodyCountryString, RequestAllCountries.class);

            for (int i = 0; i < countries.getData().size(); i++) {
                String nameIndentCountry = countries.getData().get(i).getName();
                int countryIds = countries.getData().get(i).getCountry_id();
                if (nameIndentCountry.equalsIgnoreCase(countryName)) {
                    SportsDataUtils.specific_teams(countryIds);
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void searchPlayers(String countryPlayerName) {

        String responseBodyPlayerString = SportsDataUtils.ApiRequest(COUNTRY_URL);

            try {
                ObjectMapper playerMapper = new ObjectMapper();
                playerMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                RequestAllCountries countryPlayers = playerMapper.readValue(responseBodyPlayerString, RequestAllCountries.class);

                if(!countryPlayers.getData().isEmpty()) {
                    for (int i = 0; i < countryPlayers.getData().size(); i++) {
                        String nameCountry = countryPlayers.getData().get(i).getName();
                        int countryId = countryPlayers.getData().get(i).getCountry_id();
                        //logger.info("It comes here");
                        if (nameCountry.equalsIgnoreCase(countryPlayerName)) {
                            //logger.info("It gets here");
                            SportsDataUtils.specific_player(countryId);
                        }
                    }
                }else {
                    logger.info("No data for the country");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
