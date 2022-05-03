package com.demo;


import com.demo.st.country.allCountries;
import com.demo.st.country.countryResponse;
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

    private static final String apiKey = "c070c210-bbe4-11ec-a108-99c509a5d562";

    //Main method
    public static void main(String[] args) throws IOException, InputMismatchException {

        Scanner sc = new Scanner(System.in);
        Boolean catch_Info;

        System.out.println("Welcome to the Sports Data App");
        do {
            System.out.println("Enter number for the information you would like to receive" +
                    "\n 1. Default Country Teams" +
                    "\n 2. Search Teams By Country Name" +
                    "" +
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
            else if (input_user == 0){
                break;
            }
            catch_Info = false;
        } while (!catch_Info );
    }

    //This method requires id the printout data of a specific country
    public static void specific_country(int country_id){

        String url = "https://app.sportdataapi.com/api/v1/soccer/teams/?apikey="+apiKey+"&country_id="+country_id;
        ApiRequest(url);
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper countryMapper = new ObjectMapper();
            countryResponse leagues = countryMapper.readValue(responseBody.string(), new TypeReference<countryResponse>() {
            });
                if (leagues.getData() == null){
                    System.out.println("Country not found");
                }else {
                    System.out.println(leagues);
                }
        } catch (IOException e) {
            logger.info("IOException message: " + e.getMessage());
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
                } catch (IOException e) {
                    logger.info("Request Exception : " + e.getMessage());
                    e.printStackTrace();
                }
                return null;
    }

    public static void searchCountry(String country_name)
    {
        String countries_url = "https://app.sportdataapi.com/api/v1/soccer/countries?apikey="+apiKey;
        ApiRequest(countries_url);
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code : " + response);
            ObjectMapper countryMapper = new ObjectMapper();
            countryMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            allCountries countries = countryMapper.readValue(responseBody.string(), new TypeReference<allCountries>() {
            });

            for (int i = 0; i < countries.getData().size(); i++) {
                String name_indent = countries.getData().get(i).getName();
                int count_id = countries.getData().get(i).getCountry_id();
                if (name_indent.equalsIgnoreCase(country_name)) {
                    specific_country(count_id);
                }else {
                    System.out.println("Country not Found");
                    break;
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
