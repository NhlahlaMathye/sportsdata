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

    private static void mainSportData(int inputUser){
        Scanner sc = new Scanner(System.in);
            switch (inputUser){
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
                    String continentName = sc.nextLine();
                    SportsDataUtils.searchCountryByContinent(continentName);
                    break;

                case 2:
                    System.out.println(" How would you like to view teams?" +
                            "\n" +
                            "\n 1. Default country" +
                            "\n 2. Search by country" +
                            "\n " +
                            "\n press Zero(0) to exit menu" +
                            "\n");
                    int checkView = sc.nextInt();
                    if (checkView == 1)
                    {
                        System.out.println("Teams are from South Africa");
                        String defaultCountry = "South Africa";
                        SportsDataUtils.searchCountry(defaultCountry);
                        break;
                    }
                    else if(checkView == 2) {
                        System.out.print("From which country would you like to receive teams : ");
                        String country = sc.nextLine();
                        SportsDataUtils.searchCountry(country);
                        break;
                    }else if(checkView == 0){
                        break;
                    }
                    break;

                case 3:
                    System.out.print("Enter name of country for leagues you want to receive: ");
                    String leagueCountry = sc.nextLine();
                    SportsDataUtils.searchLeagues(leagueCountry);
                    break;

                case 4:
                    System.out.print("From which country would you like to view players : ");
                    String countryPlayer = sc.next();
                    SportsDataUtils.searchPlayers(countryPlayer);

                    break;

                case 5:
                    System.out.print("For which league would you like to view stages : ");
                    String stageLeague = sc.nextLine();
                    SportsDataUtils.specificLeagueSeason(stageLeague);
                    System.out.print("Enter the season for stages you want to view (See above Season_ID) : ");
                    int stageSeason = sc.nextInt();
                    SportsDataUtils.stagesSeason(stageSeason);
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
                    break;

                case 7:
                    System.out.print("For which league would you like to view top scorers : ");
                    String scoreLeague = sc.nextLine();
                    SportsDataUtils.specificLeagueSeason(scoreLeague);
                    System.out.print("Enter the season for top scorers (See above Season_ID) : ");
                    int scoreSeason = sc.nextInt();
                    SportsDataUtils.topScorers(scoreSeason);
                    break;

                case 8:
                    System.out.println("Here are the bookmakers below.");
                    SportsDataUtils.bookmakers();
                    break;

                case 9:
                    System.out.println("Here are the markets below.");
                    SportsDataUtils.markets();
                    break;

                case 10:
                    System.out.print("From which country would you like to view venues(Stadiums) : ");
                    String countryVenue = sc.nextLine();
                    SportsDataUtils.searchVenuesCountry(countryVenue);
                    break;

                case 11:
                    System.out.print("From which country referees would you like to view : ");
                    String countryRef = sc.nextLine();
                    SportsDataUtils.searchCountryReferee(countryRef);
                    break;

                case 12:
                    System.out.print("From which league you want to view the seasons : ");
                    String leagueName = sc.nextLine();
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
                        break;
                    } else if (viewStage == 1) {
                        System.out.print("For which season would you like to view matches (See season's above(Season_ID)) ? : ");
                        int seasonM = sc.nextInt();
                        System.out.print("Write the date of the matches you want view? (YYYY-MM-DD) (See dates above) : ");
                        String dateMatch = sc.nextLine();
                        SportsDataUtils.specificMatches(seasonM, dateMatch);
                        break;
                    } else if(viewStage == 3){
                        System.out.print("Enter the season you would like to view top scorers (See season's above(Season_ID)) : ");
                        int seasonS = sc.nextInt();
                        SportsDataUtils.topScorers(seasonS);
                        break;
                    } else if(viewStage == 0) {
                        break;
                    }
                    break;

                default:
                    break;
            }

    }

}
