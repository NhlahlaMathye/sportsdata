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
        catchInfo = false;
        do {
            System.out.println("Select number for the information you would like to receive." +
                    "\n" +
                    "\n 1. Countries" +
                    "\n 2. Teams " +
                    "\n 3. Leagues" +
                    "\n 4. Players" +
                    "\n 5. Rounds" +
                    "\n 6. Matches" +
                    "\n 7. Top Scorers" +
                    "\n 8. Bookmakers" +
                    "\n 9. Markets" +
                    "\n 10. Venues" +
                    "\n 11. Referees" +
                    "\n 12. Seasons" +
                    "\n" +
                    "\n Enter number zero(0) to exit the program.");
            int inputUser = sc.nextInt();
            switch (inputUser){
                case 1:
                    System.out.print("From which continent would you like to view countries: ");
                    String continentName = sc.nextLine();
                    continentName+=sc.nextLine();
                    SportsDataUtils.searchCountryByContinent(continentName);
                    continue;

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
                    }
                    continue;

                case 3:
                    System.out.print("Enter name of country for leagues you want to receive: ");
                    String leagueCountry = sc.nextLine();
                    leagueCountry+=sc.nextLine();
                    SportsDataUtils.searchLeagues(leagueCountry);
                    continue;

                case 4:
                    System.out.print("From which country would you like to view players : ");
                    String countryPlayer = sc.next();
                    SportsDataUtils.searchPlayers(countryPlayer);
                    continue;

                case 12:
                    System.out.print("From which league you want to view the seasons : ");
                    String leagueName = sc.nextLine();
                    leagueName += sc.nextLine();
                    SportsDataUtils.specificLeagueSeason(leagueName);

                    System.out.print("Would you also like to view, Select : " +
                            "\n 1. Matches" +
                            "\n 2. Stages" +
                            "\n" +
                            "\n To cancel, enter Zero(0)." +
                            "\n");
                    int viewStage = sc.nextInt();

                        if (viewStage == 2) {
                            System.out.print("Enter the season(season_id) you have just viewed in the seasons : ");
                            int season = sc.nextInt();
                            SportsDataUtils.stagesSeason(season);
                        } else if (viewStage == 1) {
                            System.out.print("For which season would you like to view matches (See season_id's above) ? : ");
                            int seasonM = sc.nextInt();
                            System.out.print("Write the date of the matches you want view? (YYYY-MM-DD) : ");
                            String dateMatch = sc.nextLine();
                            dateMatch += sc.nextLine();
                            SportsDataUtils.specific_matches(seasonM, dateMatch);

                        } else if(viewStage == 0) {
                            break;
                        }

                    continue;

                default:
                    break;
            }
            if (inputUser == 0)
            {
                break;
            }
        } while (!catchInfo);
    }
}
