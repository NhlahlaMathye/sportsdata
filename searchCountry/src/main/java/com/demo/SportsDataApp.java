package com.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SportsDataApp {
    //LOGGER
    //final static Logger logger = Logger.getLogger(SportsDataApp.class.getSimpleName());
    static boolean catchInfo ;
    static int programTrack = 0;
    //Main method
    public static void main(String[] args) throws  InputMismatchException {

        System.out.println("\n Welcome to the Sports Data App \n");
        catchInfo = false;

        while (programTrack != 99)
        {
            SportsDataApp.mainSportData(programTrack);
        }
    }

    private static void mainSportData(int inputUser) throws InputMismatchException {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            switch (inputUser) {
                case 0:
                    System.out.println("Select number for the information you would like to receive." +
                            "\n" +
                            "\n 1. Countries" +
                            "\n 2. Teams " +
                            "\n 3. Leagues" +
                            "\n 4. Players" +
                            "\n 5. Stages" +
                            "\n 6. Matches" +
                            "\n 7. Top Scorers" +
                            "\n 8. Bookmakers" +
                            "\n 9. Markets" +
                            "\n 10. Venues" +
                            "\n 11. Referees" +
                            "\n 12. Seasons" +
                            "\n" +
                            "\n Enter number (99) to exit the program.");
                    programTrack = sc.nextInt();
                    break;
                case 1:
                    System.out.print("From which continent would you like to view countries: ");
                    String continentName = sc.next();
                    SportsDataUtils.searchCountryByContinent(continentName);
                    programTrack = 0;
                    break;

                case 2:
                    System.out.println(" How would you like to view teams? select character." +
                            "\n" +
                            "\n 1. for Default country " +
                            "\n 2. for Searching by country" +
                            "\n " +
                            "\n press Zero(0) to exit menu" +
                            "\n");
                    int checkView = sc.nextInt();
                    if (checkView == 1) {
                        System.out.println("Teams are from South Africa");
                        String defaultCountry = "South Africa";
                        SportsDataUtils.searchCountry(defaultCountry);
                        programTrack = 2;
                    }  else if (checkView == 2) {
                        System.out.print("From which country would you like to receive teams : ");
                        String country = sc.next();
                        SportsDataUtils.searchCountry(country);
                        programTrack = 2;

                    } else if (checkView == 0) {
                        programTrack = 0;
                        break;
                    }
                    break;

                case 3:
                    System.out.println(" How would you like to view leagues? select character." +
                            "\n" +
                            "\n 1. for Default country " +
                            "\n 2. for Searching by country" +
                            "\n " +
                            "\n press Zero(0) to exit menu" +
                            "\n");
                    int checkLeagues = sc.nextInt();
                    if(checkLeagues == 1)
                    {
                        System.out.println("Leagues are from South Africa");
                        String defaultLCountry = "South Africa";
                        SportsDataUtils.searchLeagues(defaultLCountry);
                        programTrack = 3;
                    }
                    else if(checkLeagues == 2) {
                        System.out.print("Enter name of country for leagues you want to receive: ");
                        String leagueCountry = sc.next();
                        SportsDataUtils.searchLeagues(leagueCountry);
                        programTrack = 3;
                    }
                    else if (checkLeagues == 0) {
                        programTrack = 0;
                        break;
                    }
                    break;

                case 4:
                    System.out.println(" How would you like to view players? select character." +
                            "\n" +
                            "\n 1. for Default country " +
                            "\n 2. for Searching by country" +
                            "\n " +
                            "\n press Zero(0) to exit menu" +
                            "\n");
                    int checkPlayers = sc.nextInt();
                    if(checkPlayers == 1) {
                        System.out.println("Players are from South Africa");
                        String defaultPCountry = "South Africa";
                        SportsDataUtils.searchPlayers(defaultPCountry);
                        programTrack = 4;
                    }
                    else if(checkPlayers == 2) {
                        System.out.print("From which country would you like to view players : ");
                        String countryPlayer = sc.next();
                        SportsDataUtils.searchPlayers(countryPlayer);
                        programTrack = 4;
                    }
                    else if(checkPlayers == 0) {
                        programTrack = 0;
                        break;
                    }
                    break;

                case 5:
                    System.out.print("For which league would you like to view stages : ");
                    String stageLeague = sc.nextLine();
                    SportsDataUtils.specificLeagueSeason(stageLeague);
                    System.out.print("Enter the season for stages you want to view (See above Season_ID) : ");
                    int stageSeason = sc.nextInt();
                    SportsDataUtils.stagesSeason(stageSeason);
                    programTrack = 0;
                    break;

                case 6:
                    System.out.print("For which league would you like to view matches : ");
                    String matchLeague = sc.nextLine();
                    SportsDataUtils.specificLeagueSeason(matchLeague);
                    System.out.print("Enter the season for matches you want to view (See above Season_ID) : ");
                    int matchSeason = sc.nextInt();
                    System.out.print("Enter date of the matches you want to view (See dates above) : ");
                    String matchDate = sc.nextLine();
                    SportsDataUtils.specificMatches(matchSeason, matchDate);
                    programTrack = 0;
                    break;

                case 7:
                    System.out.print("For which league would you like to view top scorers : ");
                    String scoreLeague = sc.nextLine();
                    SportsDataUtils.specificLeagueSeason(scoreLeague);
                    System.out.print("Enter the season for top scorers (See above Season_ID) : ");
                    int scoreSeason = sc.nextInt();
                    SportsDataUtils.topScorers(scoreSeason);
                    programTrack=0;
                    break;

                case 8:
                    System.out.println("Here are the bookmakers below.");
                    SportsDataUtils.bookmakers();
                    programTrack = 0;
                    break;

                case 9:
                    System.out.println("Here are the markets below.");
                    SportsDataUtils.markets();
                    programTrack = 0;
                    break;

                case 10:
                    System.out.println(" How would you like to view venues? select number." +
                            "\n 1. for Default country " +
                            "\n 2. for Searching by country" +
                            "\n" +
                            "\n Enter zero(0) to exit menu"
                            );
                    int checkVenues = Integer.parseInt(sc.next());
                    if(checkVenues == 1) {
                        System.out.println("Venues are from South Africa");
                        String defaultVCountry = "South Africa";
                        SportsDataUtils.searchVenuesCountry(defaultVCountry);
                        programTrack = 10;

                    }
                     if(checkVenues == 2) {
                        System.out.print("From which country would you like to view venues(Stadiums) :");
                        String countryVenue = sc.next();
                        SportsDataUtils.searchVenuesCountry(countryVenue);
                        programTrack = 10;
                    }
                     if(checkVenues == 0) {
                        programTrack = 0;
                        break;
                    }

                    break;

                case 11:
                    System.out.println(" How would you like to view referees? select character." +
                            "\n" +
                            "\n 1. for Default country " +
                            "\n 2. for Searching by country" +
                            "\n " +
                            "\n press Zero(0) to exit menu" +
                            "\n");
                    int checkReferees = sc.nextInt();
                    if(checkReferees == 1){
                        System.out.println("Referees are from South Africa");
                        String defaultRCountry = "South Africa";
                        SportsDataUtils.searchCountryReferee(defaultRCountry);
                        programTrack = 11;
                    }
                   else if(checkReferees == 2) {
                        System.out.print("From which country referees would you like to view : ");
                        String countryRef = sc.next();
                        SportsDataUtils.searchCountryReferee(countryRef);
                        programTrack = 11;
                    }
                    else if(checkReferees == 0) {
                        programTrack = 0;
                        break;
                    }
                    break;

                case 12:
                    System.out.print("From which league you want to view the seasons : ");
                    String leagueName = sc.next();
                    SportsDataUtils.specificLeagueSeason(leagueName);

                    System.out.print("Would you also like to view, Select : " +
                            "\n 1. Matches" +
                            "\n 2. Stages" +
                            "\n 3. Top Scorers" +
                            "\n" +
                            "\n To cancel, enter Zero(0)." +
                            "\n");
                    int viewStage = sc.nextInt();
                    if (viewStage == 2) {
                        System.out.print("Enter the season for the stages (See season's above(Season_ID)) : ");
                        int season = sc.nextInt();
                        SportsDataUtils.stagesSeason(season);
                        programTrack = 12;
                    } else if (viewStage == 1) {
                        System.out.print("For which season would you like to view matches (See season's above(Season_ID)) ? : ");
                        int seasonM = sc.nextInt();
                        System.out.print("Write the date of the matches you want view? (YYYY-MM-DD) (See dates above) : ");
                        String dateMatch = sc.next();
                        SportsDataUtils.specificMatches(seasonM, dateMatch);
                        programTrack = 12;
                    } else if (viewStage == 3) {
                        System.out.print("Enter the season you would like to view top scorers (See season's above(Season_ID)) : ");
                        int seasonS = sc.nextInt();
                        SportsDataUtils.topScorers(seasonS);
                        programTrack = 12;
                    } else if (viewStage == 0) {
                        programTrack = 0;
                        break;
                    }
                default:
                    break;
            }
        }catch(InputMismatchException e){
            System.out.println("Please enter valid input!");
        }
    }

}
