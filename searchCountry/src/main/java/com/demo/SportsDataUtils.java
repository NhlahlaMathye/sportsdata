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

    public static void specific_league(int countriesId) {

        String specificLeagues = LEAGUES_URL + countriesId;
        String responseBodyString = SportsDataUtils.ApiRequest(specificLeagues);

        try {

            ObjectMapper mapLeagues = new ObjectMapper();
            RequestLeagueResponse leagueResponse = mapLeagues.readValue(responseBodyString, RequestLeagueResponse.class);

            for (Map.Entry<String, RequestLeague> responseMap : leagueResponse.getData().entrySet()) {

               String leagueName = responseMap.getValue().getName();
                String leagueID = responseMap.getValue().getLeague_id();
                System.out.println("League Name: " + leagueName + ", League ID: " + leagueID);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void specific_season(int seasonLeague)
    {
        String urlSeason = SEASON_URL + seasonLeague;
        String responseSeasonString = ApiRequest(urlSeason);

        try {
            ObjectMapper mapSeason = new ObjectMapper();
            RequestSeasonResponse checkResponse = mapSeason.readValue(responseSeasonString, RequestSeasonResponse.class);

            System.out.println(checkResponse);

        }catch (IOException e) {
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

    public static void specific_matches(int season, String date)
    {
        String matchUrl = MATCHES_URL + season + "&date_from="+date;
        String matchResponseBody = SportsDataUtils.ApiRequest(matchUrl);

        try{
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
        String scorerUrl = TOP_SCORER_URL + season;
        String scoreResBody = SportsDataUtils.ApiRequest(scorerUrl);

        try {
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
                        "" + name +
                        "" + team +
                        "\n Matches played: " + matchesPlayed +
                        "\n Minutes played: " + minutesPlayed +
                        "\n Substituted times: " + substituted +
                        "\n Goals scored: " + goals +
                        " Penalties taken: " + penalty
                        );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void specific_referees(int countryRefId)
    {
        String refUrl = REFEREE_URL + countryRefId;
        String refResBody = SportsDataUtils.ApiRequest(refUrl);

        try {
            ObjectMapper refMapping = new ObjectMapper();
            ResponseReferees referees = refMapping.readValue(refResBody, ResponseReferees.class);

            System.out.println(referees);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void specific_venue(int countryVenueId)
    {
        String venueUrl = VENUES_URL + countryVenueId;
        String venueResBody = SportsDataUtils.ApiRequest(venueUrl);

        try {
            ObjectMapper mapVenues = new ObjectMapper();
            ResponseVenues responseVenues = mapVenues.readValue(venueResBody, ResponseVenues.class);

            System.out.println(responseVenues);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stagesSeason(int seasonsId)
    {
        String stagesUrl = STAGES_URL + seasonsId;
        String stagesResponseBody = SportsDataUtils.ApiRequest(stagesUrl);

        try {
            ObjectMapper stageMap = new ObjectMapper();
            ResponseStages requestStage = stageMap.readValue(stagesResponseBody, ResponseStages.class);

            for (int ss = 0; ss < requestStage.getData().size(); ss++)
            {
                System.out.println(requestStage.getData().get(ss));

            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void specificLeagueSeason(String leaguesNames)
    {
        String leaguesSeason = LEAGUES_URL ;
        String responseBodySeason = SportsDataUtils.ApiRequest(leaguesSeason);

        try {
            ObjectMapper mapLeaguesS = new ObjectMapper();
            RequestSeasonLeague leagueSResponse = mapLeaguesS.readValue(responseBodySeason, RequestSeasonLeague.class);

            for (int oo = 0; oo < leagueSResponse.getData().size(); oo++)
            {
                String leaguesName = leagueSResponse.getData().get(oo).getName();
                int leaguesId = Integer.parseInt(leagueSResponse.getData().get(oo).getLeague_id());
                if(leaguesName.equalsIgnoreCase(leaguesNames))
                {
                    SportsDataUtils.specific_season(leaguesId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchCountryReferee(String countryRef){

        String refResponseBody = SportsDataUtils.ApiRequest(COUNTRY_URL);

        try{
            ObjectMapper refMap = new ObjectMapper();
            RequestAllCountries refCountries = refMap.readValue(refResponseBody, RequestAllCountries.class);

            for (int r = 0; r < refCountries.getData().size();r++)
            {
                String refCountry = refCountries.getData().get(r).getName();
                if(refCountry.equalsIgnoreCase(countryRef))
                {
                    int countryRefId = refCountries.getData().get(r).getCountry_id();
                    SportsDataUtils.specific_referees(countryRefId);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void searchVenuesCountry(String country)
    {
        String venueResBody = SportsDataUtils.ApiRequest(COUNTRY_URL);
        try {
            ObjectMapper venueMap = new ObjectMapper();
            RequestAllCountries requestAllCountries = venueMap.readValue(venueResBody, RequestAllCountries.class);

            for (int v = 0; v < requestAllCountries.getData().size();v++)
            {
                String venueCountry = requestAllCountries.getData().get(v).getName();
                if(venueCountry.equalsIgnoreCase(country))
                {
                    int countryID = requestAllCountries.getData().get(v).getCountry_id();
                    SportsDataUtils.specific_venue(countryID);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
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

    public static void bookmakers()
    {
        String urlBody = SportsDataUtils.ApiRequest(BOOKMAKER_URL);
        try {
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
        String urlRes = SportsDataUtils.ApiRequest(MARKETS_URL);
        try {
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
}
