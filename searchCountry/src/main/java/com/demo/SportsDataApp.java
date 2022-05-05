package com.demo;

import com.demo.st.country.CountriesByContinentResponse;
import com.demo.st.country.Country;
import com.demo.st.country.RequestAllCountries;
import com.demo.st.team.RequestTeamsResponse;
import com.demo.st.players.ResponsePlayers;
import com.demo.st.team.RequestLeague;
import com.demo.st.team.RequestLeagueResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;


public class SportsDataApp {

    //Set the response body of api request
    private static ResponseBody responseBody;
    //Set the response of api request
    private static Response response;
    //LOGGER
    final static Logger logger = Logger.getLogger(SportsDataApp.class.getSimpleName());
    private static final String API_KEY = "4d6265e0-cc82-11ec-b961-03b703adda4e";
    private static final String BASE_URL = "https://app.sportdataapi.com/api/v1/soccer";

    //Main method
    public static void main(String[] args) throws  InputMismatchException {

        Scanner sc = new Scanner(System.in);
        Boolean catch_Info;

        System.out.println("\n Welcome to the Sports Data App \n");
        do {
            System.out.println("Select number for the information you would like to receive." +
                    "\n" +
                    "\n 1. Countries" +
                    "\n 2. Teams " +
                    "\n 3. Leagues" +
                    "\n 4. Players" +
                    "\n" +
                    "\n Enter number zero(0) to exit the program.");
            int input_user = sc.nextInt();
            if(input_user == 1)
            {
                System.out.print("From which continent would you like to view countries: ");
                String continent_ = sc.next();
                searchCountryByContinent(continent_);
                catch_Info = true;
            }
            else if(input_user == 2){
                System.out.println(" How would you like to view teams?" +
                        "\n" +
                        "\n 1. Default country" +
                        "\n 2. Search by country");
                int check_view = sc.nextInt();
                if (check_view == 1)
                {
                    System.out.println("Teams are from South Africa");
                    String default_country = "South Africa";
                    searchCountry(default_country);
                    catch_Info = true;

                }
                else if(check_view == 2) {
                    System.out.print("From which country would you like to receive teams : ");
                    String country = sc.next();
                    searchCountry(country);
                    catch_Info = true;
                }
            }
            else if(input_user == 3)
            {
                System.out.print("Enter name of country for leagues you want to receive: ");
                String league_co = sc.next();
                searchLeagues(league_co);
                catch_Info = true;
            }
            else if(input_user == 4)
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
    public static void specific_teams(int country_id){

        String url = "/teams/?apikey=&country_id="+country_id;
        String responseBodyString = ApiRequest(url);
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
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
        String responseBodyString = ApiRequest(specific_leagues);

        try {
            if(!response.isSuccessful()) throw new IOException("Error code : " + response);
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
        String responseBodyStr = ApiRequest(play_url);

        try {
            if(!response.isSuccessful()) throw new IOException("Error code : " + response);
            ObjectMapper mapLeagues = new ObjectMapper();
            mapLeagues.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            ResponsePlayers league_response = mapLeagues.readValue(responseBodyStr, ResponsePlayers.class);

            System.out.println(league_response);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String ApiRequest(String url){

        final Request request = new Request.Builder()
                .url(BASE_URL + url)
                .addHeader("apikey","4d6265e0-cc82-11ec-b961-03b703adda4e")
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

    public static  void searchLeagues(String country_name)
    {
        String league_url = "/countries?apikey=";
        final String responseBodyString = ApiRequest(league_url);

        try {

            if(!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
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
        String responseBodyString = ApiRequest(countryContinent);

        try {
            if(!response.isSuccessful()) throw new IOException("Unexpected status code: " + response);
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
        String responseBodyString =  ApiRequest(countries_url);
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper countryMapper = new ObjectMapper();
            countryMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            RequestAllCountries countries = countryMapper.readValue(responseBodyString, RequestAllCountries.class);

            for (int i = 0; i < countries.getData().size(); i++) {
                String name_indent = countries.getData().get(i).getName();
                int count_id = countries.getData().get(i).getCountry_id();
                if (name_indent.equalsIgnoreCase(country_name)) {
                    SportsDataApp.specific_teams(count_id);
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
        String responseBodyString =  ApiRequest(countries_url);
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
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
