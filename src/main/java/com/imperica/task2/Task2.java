package com.imperica.task2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {

    private static List<City> cities = new ArrayList<>();


    public static void main(String[] args) {

        //Initialization variables
        int countOfTests = 0;
        int countOfCities = 0;
        int countOfPathToFound = 0;

        Scanner scanner = new Scanner(System.in);

        //Reading variables
        countOfTests = scanner.nextInt();
        countOfCities = scanner.nextInt();

        //After reading integer remain space that reading with scanner.nextLine()
        scanner.nextLine();

        //Reading cities
        for (int i = 0; i < countOfCities; i++) {
            //Initialization variables for build city class entity
            String cityName = "";
            int countOfNeighborCities = 0;
            List<Integer> citiesId = new ArrayList<>();
            List<Integer> transportCosts = new ArrayList<>();

            //Reading values
            cityName = scanner.nextLine();
            countOfNeighborCities = scanner.nextInt();

            for (int j = 0; j < countOfNeighborCities; j++) {
                int citiId = scanner.nextInt();
                int transportCost = scanner.nextInt();

                citiesId.add(citiId);
                transportCosts.add(transportCost);
            }

            //After reading integer remain space that reading with scanner.nextLine()
            scanner.nextLine();

            //Build city entity with reading values
            City city = City.builder()
                    .id(i + 1)
                    .name(cityName)
                    .citiesIds(citiesId)
                    .transportCosts(transportCosts)
                    .build();

            //System.out.println("city = " + city);

            cities.add(city);
        }

        countOfPathToFound = scanner.nextInt();

        //After reading integer remain space that reading with scanner.nextLine()
        scanner.nextLine();

        String[][] citiesCouples = new String[2][countOfPathToFound];

        //Reading cities couples for counting cost
        for (int i = 0; i < countOfPathToFound; i++) {
            String transportPaths = scanner.nextLine();
            String[] transportPath = transportPaths.split(" ");

            citiesCouples[0][i] = transportPath[0];
            citiesCouples[1][i] = transportPath[1];
        }

        List<List<Integer>> pathsTransportationCost = new ArrayList<>();

        //Find cost of transportation
        for (int i = 0; i < countOfTests; i++) {

            for (int j = 0; j < countOfPathToFound; j++) {
                //Assignment to variables of source city and destination city names values
                String sourceCityName = citiesCouples[0][j];
                String destinationCityName = citiesCouples[1][j];

                //Initialization and find in collection source city and destiny city entity
                City sourceCity = City.builder().build();
                City destinationCity = City.builder().build();

                for (City city : cities) {
                    if (city.getName().equals(sourceCityName)) {
                        sourceCity = city;
                    }

                    if (city.getName().equals(destinationCityName)) {
                        destinationCity = city;
                    }
                }

                //System.out.println("sourceCity = " + sourceCity);
                //System.out.println("destinationCity = " + destinationCity);


                //Counting of transportation cost
                List<Integer> transportationCosts = new ArrayList<>();
                countingOfTransportCoast(sourceCity, destinationCity, transportationCosts);

                //sorting collection of transportation cost
                transportationCosts.sort(null);
                pathsTransportationCost.add(transportationCosts);
            }
        }

        //Write results to console
        for (int i = 0; i < countOfPathToFound; i++) {
            for (int j = 0; j < countOfTests; j++) {
                if (pathsTransportationCost.get(i).size() > j) {
                    System.out.print(pathsTransportationCost.get(i).get(j) + " ");
                }
            }
            System.out.println("");
        }

    }

    //Method to counting of transport cost between source and destiny cities
    private static void countingOfTransportCoast(City sourceCity, City destinationCity, List<Integer> transportationCosts) {
        int sourceCityId = sourceCity.getId();
        int destinyCityId = destinationCity.getId();

        //If source city have destination city in neighbors cities add cost to resulting collection
        if (sourceCity.getCitiesIds().contains(destinyCityId)) {
            transportationCosts.add(sourceCity.getTransportCosts().get(
                    sourceCity.getCitiesIds().indexOf(destinyCityId)
            ));
        }

        //Find else roots to destination city
        List<Integer> sourceCityNeighborsCitiesId = sourceCity.getCitiesIds();
        List<Integer> sourceCityTransportCosts = sourceCity.getTransportCosts();

        for (int i = 0; i < sourceCityNeighborsCitiesId.size(); i++) {

            //Condition search the transport root threw all neighbors cities of source city without destiny city
            sourceCityNeighborsCitiesId.forEach(s -> {
                if (s != destinyCityId) {
                    City currentCity = cities.get(s);

                    //Initialization count for another root transportation with transport cost to current city
                    int costForAnotherRootTransportation = sourceCityTransportCosts.get(
                            sourceCityNeighborsCitiesId.indexOf(s)
                    );

                    //Separate method to counting transportation cost from source neighbor city to destination city
                    costForAnotherRootTransportation += findCostForAnotherRootTransportation(currentCity,
                            sourceCityId,
                            destinyCityId,
                            costForAnotherRootTransportation
                    );

                    //If cost transportation not zero add to resulting collection
                    if (costForAnotherRootTransportation != 0)
                        transportationCosts.add(costForAnotherRootTransportation);

                }
            });
        }
    }

    private static int findCostForAnotherRootTransportation(City currentCity,
                                                            int sourceCityId,
                                                            int destinyCityId,
                                                            int costForSameRootTransportation) {

        //If city have destination city in neighbor cities add cost to resulting collection
        if (currentCity.getCitiesIds().contains(destinyCityId)) {
            costForSameRootTransportation += currentCity.getTransportCosts().get(
                    currentCity.getCitiesIds().indexOf(destinyCityId)
            );
            return costForSameRootTransportation;
        } else {
            //If city don't have destination city in neighbors cities call method with next neighbor city
            int finalCostForSameRootTransportation = costForSameRootTransportation;
            currentCity.getCitiesIds().forEach(c -> {
                if (c != sourceCityId & c != destinyCityId) {
                    findCostForAnotherRootTransportation(cities.get(c - 1),
                            sourceCityId,
                            destinyCityId,
                            finalCostForSameRootTransportation
                    );
                }
            });
            return finalCostForSameRootTransportation;
        }
    }

    @Builder
    @Data
    @AllArgsConstructor
    static class City {
        private String name;
        private int id;
        private List<Integer> citiesIds;
        private List<Integer> transportCosts;

        public City() {
            this.citiesIds = new ArrayList<>();
            this.transportCosts = new ArrayList<>();
        }
    }

}
