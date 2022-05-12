package com.demo;
import com.demo.st.bets.ResponseBookmakers;
import com.demo.st.bets.ResponseMarkets;
import com.demo.st.country.CountriesByContinentResponse;
import com.demo.st.country.Country;
import com.demo.st.country.RequestAllCountries;
import com.demo.st.matches.RequestMatchResponse;
import com.demo.st.players.ResponsePlayers;
import com.demo.st.referees.ResponseReferees;
import com.demo.st.seasons.RequestSeasonLeague;
import com.demo.st.seasons.RequestSeasonResponse;
import com.demo.st.seasons.RequestStages;
import com.demo.st.seasons.ResponseStages;
import com.demo.st.team.RequestLeague;
import com.demo.st.team.RequestLeagueResponse;
import com.demo.st.team.RequestTeamsResponse;
import com.demo.st.topscorer.RequestScorerResponse;
import com.demo.st.venues.ResponseVenues;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.io.IOException;

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
    private static final String SEASON_URL = "/seasons?apikey=&league_id=";
    private static final String STAGES_URL = "/stages?apikey=&season_id=";
    private static final String MATCHES_URL = "/matches?apikey=&season_id=";
    private static final String REFEREE_URL = "/referees?apikey=&country_id=";
    private static final String VENUES_URL = "/venues?apikey=&country_id=";
    private static final String BOOKMAKER_URL = "/bookmakers?apikey=";
    private static final String MARKETS_URL = "/markets?apikey=";
    private static final String TOP_SCORER_URL = "/topscorers?apikey=&season_id=";

    final static Logger logger = Logger.getLogger(SportsDataUtils.class.getSimpleName());

    public static String apiRequest(String url){

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
            return null;
        }
    }

    public static void specificTeams(int countryTeamId){
        try {
            String responseBodyTeamString = SportsDataUtils.apiRequest(TEAMS_URL + countryTeamId);
            ObjectMapper teamMapper = new ObjectMapper();
            RequestTeamsResponse leagueTeams = teamMapper.readValue(responseBodyTeamString, RequestTeamsResponse.class);

            for(int t = 0; t < leagueTeams.getData().size(); t++)
            {
                String teamName = leagueTeams.getData().get(t).getName();
                System.out.println("No."+ t +"\n " +
                        " Team Name: " + teamName + " \n");
            }

        } catch (IOException e) {
            logger.info("IOException message: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void specificLeague(int countriesId) {

        try {
            String responseBodyString = SportsDataUtils.apiRequest(LEAGUES_URL + countriesId);
            ObjectMapper mapLeagues = new ObjectMapper();
            RequestLeagueResponse leagueResponse = mapLeagues.readValue(responseBodyString, RequestLeagueResponse.class);

            for (Map.Entry<String, RequestLeague> responseMap : leagueResponse.getData().entrySet()) {

               String leagueName = responseMap.getValue().getName();
                System.out.println("\n League Name: " + leagueName);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void specificSeason(int seasonLeague)
    {
        try {
            String responseSeasonString = apiRequest(SEASON_URL + seasonLeague);
            ObjectMapper mapSeason = new ObjectMapper();
            RequestSeasonResponse checkResponse = mapSeason.readValue(responseSeasonString, RequestSeasonResponse.class);

            for(int s = 0; s < checkResponse.getData().size();s++)
            {
                String seasonName = checkResponse.getData().get(s).getName();
                String startDate = checkResponse.getData().get(s).getStart_date();
                String endDate = checkResponse.getData().get(s).getEnd_date();
                int seasonId = checkResponse.getData().get(s).getSeason_id();

                System.out.println("No."+s+"" +
                        "\n Season Name: " + seasonName +
                        "\n Start Date: " + startDate +
                        "\n End Date: " + endDate +
                        "\n Season Id: " + seasonId +
                        "\n");
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void specificPlayer(int countryID){

            try {
                String responseBodyStr = SportsDataUtils.apiRequest(PLAYERS_URL + countryID);
                ObjectMapper mapPlayers = new ObjectMapper();
                mapPlayers.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                ResponsePlayers leaguePlayers = mapPlayers.readValue(responseBodyStr, ResponsePlayers.class);

                for(int p = 0; p < leaguePlayers.getData().size();p ++)
                {
                    String firstname = leaguePlayers.getData().get(p).getFirstname();
                    String lastname = leaguePlayers.getData().get(p).getLastname();
                    String birthdate = leaguePlayers.getData().get(p).getBirthday();
                    int age = leaguePlayers.getData().get(p).getAge();
                    int weight = leaguePlayers.getData().get(p).getWeight();
                    int height = leaguePlayers.getData().get(p).getHeight();

                    System.out.println("\n No." +p +" \n firstname:" + firstname+
                            "\n lastname:" + lastname+
                            "\n birthdate:" + birthdate+
                            "\n age:" + age+
                            "\n weight:" + weight+
                            "\n height:" + height
                            );
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void specificMatches(int season, String date)
    {

        try{
            String matchResponseBody = SportsDataUtils.apiRequest(MATCHES_URL + season + "&date_from="+date);
            ObjectMapper mapMatch = new ObjectMapper();
            RequestMatchResponse matchResponse = mapMatch.readValue(matchResponseBody, RequestMatchResponse.class);

            for (int mm = 0; mm < matchResponse.getData().size(); mm++)
            {
                String matchStatus = matchResponse.getData().get(mm).getStatus();
                String matchStart = matchResponse.getData().get(mm).getMatch_start();
                String homeTeam = String.valueOf(matchResponse.getData().get(mm).getHome_team());
                String awayTeam = String.valueOf(matchResponse.getData().get(mm).getAway_team());

                System.out.println("****" +
                        "\n Match Status:" +matchStatus +
                        "\n Match Starts:" +matchStart +
                        "\n\n Home Team:" + homeTeam+
                        "\n\n Away Team:" + awayTeam+
                        "\n****" );
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void topScorers(int season)
    {
        try {
            String scoreResBody = SportsDataUtils.apiRequest(TOP_SCORER_URL + season);
            ObjectMapper scoreMap = new ObjectMapper();
            RequestScorerResponse scorerResponse = scoreMap.readValue(scoreResBody, RequestScorerResponse.class);

            for (int s= 0; s < scorerResponse.getData().size(); s++)
            {
                int position = scorerResponse.getData().get(s).getPos();
                String name = String.valueOf(scorerResponse.getData().get(s).getPlayer());
                String team = String.valueOf(scorerResponse.getData().get(s).getTeam());
                int matchesPlayed = scorerResponse.getData().get(s).getMatches_played();
                int minutesPlayed = scorerResponse.getData().get(s).getMinutes_played();
                int substituted = scorerResponse.getData().get(s).getSubstituted_in();
                String goals = String.valueOf(scorerResponse.getData().get(s).getGoals());
                int penalty = scorerResponse.getData().get(s).getPenalties();

                System.out.println("" +
                        "\n Position: " + position +
                        "\n Player Name:" + name +
                        "\n Team:" + team +
                        "\n Matches played: " + matchesPlayed +
                        "\n Minutes played: " + minutesPlayed +
                        "\n Substituted times: " + substituted +
                        "\n Goals scored: " + goals +
                        " Penalties taken: " + penalty
                        );
            }

        } catch (IOException e) {
            System.out.println("Enter a valid season");
        }
    }

    public static void specificReferees(int countryRefId)
    {
        try {
            String refResBody = SportsDataUtils.apiRequest(REFEREE_URL + countryRefId);
            ObjectMapper refMapping = new ObjectMapper();
            ResponseReferees referees = refMapping.readValue(refResBody, ResponseReferees.class);
            
            for(int r = 0; r < referees.getData().size(); r++)
            {
                String name = referees.getData().get(r).getName();
                String birthdate = referees.getData().get(r).getBirthdate();
                String image = String.valueOf(referees.getData().get(r).getImg());
                System.out.println("No." + r +"" +
                        "\n Referee Name: " + name +
                        "\n Referee birthdate: " + birthdate +
                        "\n Referee Images: " + image +
                        "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void specificVenue(int countryVenueId)
    {
        try {
            String venueResBody = SportsDataUtils.apiRequest(VENUES_URL + countryVenueId);
            ObjectMapper mapVenues = new ObjectMapper();
            ResponseVenues responseVenues = mapVenues.readValue(venueResBody, ResponseVenues.class);

            for (int v = 0; v < responseVenues.getData().size();v++)
            {
                String name = responseVenues.getData().get(v).getName();
                int capacity = responseVenues.getData().get(v).getCapacity();
                String city = responseVenues.getData().get(v).getCity();

                System.out.println("No." + v + "" +
                        "\n Venue Name: " + name +
                        "\n Venue Capacity: " + capacity +
                        "\n City: " + city +
                        "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stagesSeason(int seasonsId)
    {
        try {
            String stagesResponseBody = SportsDataUtils.apiRequest(STAGES_URL + seasonsId);
            ObjectMapper stageMap = new ObjectMapper();
            ResponseStages requestStage = stageMap.readValue(stagesResponseBody, ResponseStages.class);
            RequestStages requestStages = null;

            for (RequestStages requestStages1 : requestStage.getData())
            {
                String name = requestStages1.getName();
                requestStages = requestStages1;
                System.out.println("\n Stage Name: " + name);
            }
            if (requestStages == null)
            {
                System.out.println("Could not retrieve stages");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void specificLeagueSeason(String leaguesNames)
    {
        try {
            String responseBodySeason = SportsDataUtils.apiRequest(LEAGUES_URL);
            ObjectMapper mapLeaguesS = new ObjectMapper();
            RequestSeasonLeague leagueSResponse = mapLeaguesS.readValue(responseBodySeason, RequestSeasonLeague.class);

            RequestLeague requestLeague = null;

            for (RequestLeague requestLeague1 : leagueSResponse.getData())
            {
                String leaguesName = requestLeague1.getName();
                int leaguesId = Integer.parseInt(requestLeague1.getLeague_id());
                if(leaguesName.equalsIgnoreCase(leaguesNames))
                {
                    requestLeague = requestLeague1;
                    SportsDataUtils.specificSeason(leaguesId);
                    break;
                }
            }
            if (requestLeague == null)
            {
                System.out.println("Could not find league : " + leaguesNames);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchCountryReferee(String countryRef){

        List<Country> allCountries = SportsDataUtils.countryRequest();
            Country userCountry = null;
            for (int r = 0; r < allCountries.size();r++)
            {
                String refCountry = allCountries.get(r).getName();
                if(refCountry.equalsIgnoreCase(countryRef))
                {
                    userCountry = allCountries.get(r);
                    int countryRefId = allCountries.get(r).getCountry_id();
                    SportsDataUtils.specificReferees(countryRefId);
                }
            }
            if (userCountry ==  null)
            {
                System.out.println("Could not find Country : " + countryRef);
            }
    }

    public static void searchVenuesCountry(String countryName)
    {
        List<Country> allCountries = SportsDataUtils.countryRequest();

        Country userCountry = null;

        for( Country country: allCountries) {
            String countryNames = country.getName();
            int countryId = country.getCountry_id();
            if (countryName.equalsIgnoreCase(countryNames))
            {
                userCountry = country;
                SportsDataUtils.specificVenue(countryId);
                break;
            }
        }
        if (userCountry == null)
        {
            System.out.println("Could not find Country : " + countryName);
        }
    }

    public static  void searchLeagues(String countryLeagueName)
    {
        List<Country> allCountries = SportsDataUtils.countryRequest();

             Country userCountry = null;
            for( Country country: allCountries) {
                String countryNames = country.getName();
                int countryId = country.getCountry_id();
                if (countryLeagueName.equalsIgnoreCase(countryNames))
                {
                    userCountry = country;
                    SportsDataUtils.specificLeague(countryId);
                    break;
                }
            }
            if (userCountry == null)
            {
                System.out.println("Could not find Country : " + countryLeagueName);
            }

    }

    public static void searchCountryByContinent(String continentName)
    {
        try {
            String responseBodyContinentString = SportsDataUtils.apiRequest(CONTINENT_URL +continentName);
            ObjectMapper continentMapper = new ObjectMapper();
            CountriesByContinentResponse continent = continentMapper.readValue(responseBodyContinentString, CountriesByContinentResponse.class);

            for (Map.Entry<String, Country> countryEntry : continent.getData().entrySet())
            {
                String countryName = countryEntry.getValue().getName();
                System.out.println("\n Country Name:" + countryName);
            }

        } catch (IOException e) {
            System.out.println("This is not a valid continent name: " + continentName);
        }

    }

    public static void searchCountry(String countryName)
    {
        List<Country> allCountries = SportsDataUtils.countryRequest();
            Country userCountry = null;

            for( Country country: allCountries) {
                String countryNames = country.getName();
                int countryIds = country.getCountry_id();
                if (countryName.equalsIgnoreCase(countryNames))
                {
                    userCountry = country;
                    SportsDataUtils.specificTeams(countryIds);
                    break;
                }

            }
            if (userCountry == null)
            {
                System.out.println("Could not find Country : " + countryName);
            }

        }

    public static void searchPlayers(String countryPlayerName) {

        List<Country> allCountries = SportsDataUtils.countryRequest();
                Country userCountry = null;

                 for(Country country: allCountries) {
                     String countryName = country.getName();
                     int countryId = country.getCountry_id();
                     if (countryPlayerName.equalsIgnoreCase(countryName))
                     {
                         userCountry = country;
                         specificPlayer(countryId);
                         break;
                     }
                     
                 }
                if (userCountry == null)
                {
                    System.out.println("Could not find Country : " + countryPlayerName);
                }
    }

    public static void bookmakers()
    {
        try {
            String urlBody = SportsDataUtils.apiRequest(BOOKMAKER_URL);
            ObjectMapper book = new ObjectMapper();
            ResponseBookmakers bookmakers = book.readValue(urlBody, ResponseBookmakers.class);
            for (int b = 0; b < bookmakers.getData().size();b++)
            {
                String name = bookmakers.getData().get(b).getName();
                String photo = String.valueOf(bookmakers.getData().get(b).getImg());
                System.out.println("\n Name: " + name + "\n Photo: " + photo);
            }
            

        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void markets()
    {
        try {
            String urlRes = SportsDataUtils.apiRequest(MARKETS_URL);
            ObjectMapper market = new ObjectMapper();
            ResponseMarkets responseMarkets = market.readValue(urlRes, ResponseMarkets.class);
            for (int m = 0; m < responseMarkets.getData().size();m++)
            {
                String name = responseMarkets.getData().get(m).getName();
                System.out.println("\n Name: " + name);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Country> countryRequest() {
        try {
            String countriesResBody = SportsDataUtils.apiRequest(COUNTRY_URL);
            ObjectMapper countriesMap = new ObjectMapper();
            RequestAllCountries allCountries = countriesMap.readValue(countriesResBody, RequestAllCountries.class);

            return allCountries.getData();

        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
