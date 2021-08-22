package com.example.EPAMProjectWork.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.EventsPage;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Data {

    public static void main(String args) {

        Calendar cal = Calendar.getInstance();
        Long currentDate = cal.getTime().getTime();

        //для примера разбираем строку
        String dateForCheck = "1 Feb - 23 May 2025";
//         String dateForCheck = (eventsPage.dateOnCard.getText());
//         String dateForCheck = (driver.findElement(By.xpath("//div[@class = 'evnt-events-column cell-3']")).getText());


        // получаем куски между пробелами в массив строк
        // или через сабстрингу делать dateForCheck.length() <= 4 ? dateForCheck : dateForCheck.substring(dateForCheck.length() - 4);

        String[] parts = dateForCheck.split(" ");


        String year = parts[5];
        String dayStart = parts[0];
        String dayEnd = parts[3];
        String monthStart = parts[1];
        String monthEnd = parts[4];

        System.out.println(year);
        System.out.println(dayStart);
        System.out.println(dayEnd);
        System.out.println(monthStart);
        System.out.println(monthEnd);


        Calendar start = new GregorianCalendar(Integer.parseInt(year),
                castMonthToInt(monthStart), Integer.parseInt(dayStart));
        Long startDate = start.getTime().getTime();

        Calendar end = new GregorianCalendar(Integer.parseInt(year),
                castMonthToInt(monthEnd), Integer.parseInt(dayEnd));
        Long endDate = end.getTime().getTime();


        System.out.println(startDate);
        System.out.println(endDate);

        System.out.println(currentDate);

        //ну и проверки на больше меньше и тд
        System.out.println(currentDate >= endDate);
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public static Integer castMonthToInt(String month) {
        Integer currentMonth = 0;
        switch (month) {
            case ("Jan"):
                currentMonth = 0;
                break;
            case ("Feb"):
                currentMonth = 1;
                break;
            case ("Mar"):
                currentMonth = 2;
                break;
            case ("Apr"):
                currentMonth = 3;
                break;
            case ("May"):
                currentMonth = 4;
                break;
            case ("Jun"):
                currentMonth = 5;
                break;
            case ("Jul"):
                currentMonth = 6;
                break;
            case ("Aug"):
                currentMonth = 7;
                break;
            case ("Sep"):
                currentMonth = 8;
                break;
            case ("Oct"):
                currentMonth = 9;
                break;
            case ("Nov"):
                currentMonth = 10;
                break;
            case ("Dec"):
                currentMonth = 11;
                break;
        }
        return currentMonth;
    }


}
