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
    final static Logger logger = Logger.getLogger(SportsDataUtils.class.getSimpleName());

    public static String ApiRequest(String url, Response response, ResponseBody responseBody){

        final Request request = new Request.Builder()
                .url(BASE_URL + url)
                .addHeader("apikey",API_KEY)
                .get()
                .build();
        OkHttpClient client = new OkHttpClient();

        try {
             response = client.newCall(request).execute();
            if(!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
             responseBody = client.newCall(request).execute().body();

            //logger.info("Api response" + responseString);

            return responseBody.string();

        } catch (IOException e) {
            logger.info("Request Exception : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void specific_teams(int country_id){

        String url = "/teams/?apikey=&country_id="+country_id;
        String responseBodyString = SportsDataUtils.ApiRequest(url, null, null);

        try {
            ObjectMapper countryMapper = new ObjectMapper();
            RequestTeamsResponse leagues = countryMapper.readValue(responseBodyString, RequestTeamsResponse.class);

            System.out.println(leagues);
        } catch (IOException e) {
            logger.info("IOException message: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void specific_league(int country_id){

        String specific_leagues = "/leagues?apikey=&country_id="+country_id;
        String responseBodyString = SportsDataUtils.ApiRequest(specific_leagues, null, null);

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

        String play_url ="/players?apikey=&country_id="+coun_id;
        String responseBodyStr = SportsDataUtils.ApiRequest(play_url, null, null);

        try {
            ObjectMapper mapLeagues = new ObjectMapper();
            mapLeagues.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            ResponsePlayers league_response = mapLeagues.readValue(responseBodyStr, ResponsePlayers.class);

            System.out.println(league_response);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static  void searchLeagues(String country_name)
    {
        String league_url = "/countries?apikey=";
        final String responseBodyString = SportsDataUtils.ApiRequest(league_url, null, null);

        try {

            ObjectMapper leagueMap = new ObjectMapper();
            leagueMap.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestAllCountries allCountries = leagueMap.readValue(responseBodyString, RequestAllCountries.class );

            for (int l = 0; l < allCountries.getData().size(); l++)
            {
                String name_in = allCountries.getData().get(l).getName();
                int country_id = allCountries.getData().get(l).getCountry_id();
                if(name_in.equalsIgnoreCase(country_name))
                {
                    specific_league(country_id);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchCountryByContinent(String continent_name)
    {
        String countryContinent = "/countries?apikey=&continent="+continent_name;
        String responseBodyString = SportsDataUtils.ApiRequest(countryContinent, null, null);

        try {
            ObjectMapper continentMapper = new ObjectMapper();
            CountriesByContinentResponse continent = continentMapper.readValue(responseBodyString, CountriesByContinentResponse.class);

            //logger.info("Response"  + continent);
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
        String countries_url = "/countries?apikey=";
        String responseBodyString =  SportsDataUtils.ApiRequest(countries_url, null, null);
        try {
            ObjectMapper countryMapper = new ObjectMapper();
            countryMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestAllCountries countries = countryMapper.readValue(responseBodyString, RequestAllCountries.class);

            for (int i = 0; i < countries.getData().size(); i++) {
                String name_indent = countries.getData().get(i).getName();
                int count_id = countries.getData().get(i).getCountry_id();
                if (name_indent.equalsIgnoreCase(country_name)) {
                    specific_teams(count_id);
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void searchPlayers(String country_name)
    {
        String countries_url = "/countries?apikey=";
        String responseBodyString =  SportsDataUtils.ApiRequest(countries_url, null, null);
        try {
            ObjectMapper countryMapper = new ObjectMapper();
            countryMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestAllCountries countryP = countryMapper.readValue(responseBodyString, RequestAllCountries.class);

            for (int i = 0; i < countryP.getData().size(); i++) {
                String name_indent = countryP.getData().get(i).getName();
                int count_id = countryP.getData().get(i).getCountry_id();
                if (name_indent.equalsIgnoreCase(country_name)) {
                    specific_player(count_id);
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
