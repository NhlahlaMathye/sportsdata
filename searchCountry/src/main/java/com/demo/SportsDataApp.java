package com.demo;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class SportsDataApp {
    //LOGGER
    final static Logger logger = Logger.getLogger(SportsDataApp.class.getSimpleName());
    static boolean catchInfo ;
    //Main method
    public static void main(String[] args) throws  InputMismatchException {

        Scanner sc = new Scanner(System.in);

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
            int inputUser = sc.nextInt();
            switch (inputUser){
                case 1:
                    System.out.print("From which continent would you like to view countries: ");
                    String continentName = sc.nextLine();
                    continentName+=sc.nextLine();
                    SportsDataUtils.searchCountryByContinent(continentName);
                    break;
                case 2:
                    System.out.println(" How would you like to view teams?" +
                            "\n" +
                            "\n 1. Default country" +
                            "\n 2. Search by country");
                    int checkView = sc.nextInt();
                    if (checkView == 1)
                    {
                        System.out.println("Teams are from South Africa");
                        String defaultCountry = "South Africa";
                        SportsDataUtils.searchCountry(defaultCountry);
                    }
                    else if(checkView == 2) {
                        System.out.print("From which country would you like to receive teams : ");
                        String country = sc.nextLine();
                        country+=sc.nextLine();
                        SportsDataUtils.searchCountry(country);
                        sc.next();
                    }
                    break;
                case 3:
                    System.out.print("Enter name of country for leagues you want to receive: ");
                    String leagueCountry = sc.nextLine();
                    leagueCountry+=sc.nextLine();
                    SportsDataUtils.searchLeagues(leagueCountry);
                    break;
                case 4:
                    System.out.print("From which country would you like to view players : ");
                    String countryPlayer = sc.next();
                    SportsDataUtils.searchPlayers(countryPlayer);
                    break;
                default:
                    break;
            }
            if (inputUser == 0)
            {
                break;
            }
            catchInfo = false;
        } while (!catchInfo);
    }
}
