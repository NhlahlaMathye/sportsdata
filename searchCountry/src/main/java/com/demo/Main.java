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
            System.out.print("Do you want to search a specific Country ? (Y/N). If(N)default country will be England. : ");
            String repo = sc.next();
            if (repo.equalsIgnoreCase("N"))
            {
                String default_country = "England";
                searchCountry(default_country);

                //int teaam_id = specific_country();
            }
            else if(repo.equalsIgnoreCase("Y"))
            {
                System.out.print("Enter country name of your choice : ");
                if(sc.hasNext()) {
                    String country_name = sc.next();
                    searchCountry(country_name);
                    catch_Info = true;
                }else{
                    System.out.println("Enter The Team you wants");
                    catch_Info = false;
                    sc.next();
                }
            }

            System.out.print("Do you want to search again? (Y- To continue searching) / (N- To cancel)  : ");
            String asking = sc.next();
            if (asking.equalsIgnoreCase("Y")) {
                catch_Info = false;

            } else {
                catch_Info = true;
            }
        } while (catch_Info == false);

    }


    //This method requires id the printout data of a specific country
    public static int specific_country(int country_id){
        Scanner ut = new Scanner(System.in);
        String url = "https://app.sportdataapi.com/api/v1/soccer/teams/?apikey=c070c210-bbe4-11ec-a108-99c509a5d562&country_id="+country_id;
        ApiRequest(url);
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper countryMapper = new ObjectMapper();
            countryResponse leagues = countryMapper.readValue(responseBody.string(), new TypeReference<countryResponse>() {
            });

            System.out.println(leagues);

            System.out.print("Enter team name to search teams / (N) to cancel search: ");
                String team_name = ut.nextLine();
                for (int c = 0; c < leagues.getData().size(); c++) {
                    String team_indent = leagues.getData().get(c).getName();
                    //System.out.println(team_name.toString());
                    if (team_indent.equalsIgnoreCase(team_name)) {
                        //int team_id = (int) leagues.getData().get(c).getTeam_id();
                        System.out.println("Team Info::Tame_name:" + team_indent + " Short_code:" + leagues.getData().get(c).getShort_code()
                                + " Team_id:" + leagues.getData().get(c).getTeam_id());
                        break;
                    }
                }
        } catch (IOException e) {
            logger.info("IOException message: " + e.getMessage());
            e.printStackTrace();
        }
        return country_id;
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
                if(name_indent.equalsIgnoreCase(country_name)){
                    int count_id = (int) countries.getData().get(i).getCountry_id();
                    specific_country(count_id);
                    int result =specific_country(count_id);
                }
            }
        }
        catch (JsonMappingException ex) {
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
