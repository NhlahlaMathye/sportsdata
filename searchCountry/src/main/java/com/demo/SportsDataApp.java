package com.demo;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class SportsDataApp {
    //LOGGER
    final static Logger logger = Logger.getLogger(SportsDataApp.class.getSimpleName());

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
                String continent_ = sc.nextLine();
                continent_+=sc.nextLine();
                SportsDataUtils.searchCountryByContinent(continent_);
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
                    SportsDataUtils.searchCountry(default_country);

                }
                else if(check_view == 2) {
                    System.out.print("From which country would you like to receive teams : ");
                    String country = sc.nextLine();
                    country+=sc.nextLine();
                    SportsDataUtils.searchCountry(country);
                }
            }
            else if(input_user == 3)
            {
                System.out.print("Enter name of country for leagues you want to receive: ");
                String league_co = sc.nextLine();
                league_co+=sc.nextLine();
                SportsDataUtils.searchLeagues(league_co);
            }
            else if(input_user == 4)
            {
                System.out.print("From which country would you like to view players : ");
                String country_play = sc.nextLine();
                country_play+=sc.nextLine();
                SportsDataUtils.searchPlayers(country_play);
            }
            else if (input_user == 0){
                break;
            }
            catch_Info = false;
        } while (!catch_Info );
    }

}
