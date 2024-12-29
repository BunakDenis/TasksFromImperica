package com.imperica.interview;

public class Task2 {

    public static String getOutDoorAndFloor(int countOfFloors, int countOfFlats, int searchingFlat) {

        StringBuilder result = new StringBuilder();
        String floorString = " поверх";
        int floor = 0;
        String outdoorString = " під'їзд ";
        int outDoor = 0;
        int reminderOfDivine = 0;

        int amountFlats = countOfFlats * countOfFloors;

        if (searchingFlat == 1) {
            return "1 поверх 1 під'їзд";
        }

        if (searchingFlat == amountFlats) {
            outDoor = 1;
        } else if (searchingFlat % amountFlats != 0){
            reminderOfDivine = (searchingFlat / amountFlats);
            outDoor = reminderOfDivine + 1;
        } else {
            outDoor = (searchingFlat / amountFlats);
            reminderOfDivine = outDoor - 1;
        }

        int countOfFlatsInBuilding = 0;
        int reminderFlatsInOutdoor = 0;

        if (searchingFlat > amountFlats) {
            countOfFlatsInBuilding = reminderOfDivine * amountFlats;

            reminderFlatsInOutdoor = searchingFlat - countOfFlatsInBuilding;

            reminderOfDivine = reminderFlatsInOutdoor / countOfFlats;

            if (reminderFlatsInOutdoor % countOfFlats != 0) {
                floor = reminderOfDivine + 1;
            } else {
                floor = reminderOfDivine;
            }

        } else {
            reminderOfDivine = amountFlats / searchingFlat;

            if (amountFlats % searchingFlat != 0) {
                floor = reminderOfDivine;
            } else {
                floor = reminderOfDivine++;
            }
        }

        result.append(outDoor);
        result.append(outdoorString);
        result.append(floor);
        result.append(floorString);

        return result.toString();

    }

}


