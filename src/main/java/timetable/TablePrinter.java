package timetable;

import java.time.LocalDate;
import java.util.List;

public class TablePrinter {
    private static final String space = " ";

    public static void printTable(List<EventList> dateList) {
        System.out.printf("%10s", space);
        for (int i = 0; i < 2400; i += 100) {
            String time = String.format("%04d", i);
            System.out.printf("|%-10s", time);
        }
        System.out.println("|");

        for (int i = 0; i < 7; i++) {
            System.out.printf("%-10s", LocalDate.now().plusDays(i).getDayOfWeek().name());
            boolean skip = false;
            for (int dateListIndex = 0; dateListIndex < dateList.size() && !skip; dateListIndex++) {
                if (dateList.get(dateListIndex).dateTag.equals(LocalDate.now().plusDays(i))) {
                    for (int j = 0; j < 24; j++) {
                        boolean isFree = true;
                        for (int eventListIndex = 0; eventListIndex < dateList.get(dateListIndex).events.size();
                             eventListIndex++) {
                            Event current = dateList.get(dateListIndex).events.get(eventListIndex);
                            for (Duration period: current.periods) {
                                if (period.containTimeSlot(j * 100) && period.startDateTime
                                        .toLocalDate().equals(dateList.get(dateListIndex).dateTag)) {
                                    System.out.printf("|%-10s", current.name);
                                    isFree = false;
                                }
                            }
                        }
                        if (isFree) {
                            System.out.printf("|%-10s", space);
                        }
                    }
                    skip = true;
                }
            }
            for (int j = 0; j < 24 && !skip; j++) {
                System.out.printf("|%-10s", space);
            }
            System.out.println("|");
        }
    }

}
