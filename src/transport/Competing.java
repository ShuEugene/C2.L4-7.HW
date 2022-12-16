package transport;

import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

import java.sql.Time;

public interface Competing {

    String NO_PARTICIPANTS = "\nВ соревнованиях пока что никто не участвует.";


    void pitStop();

    Time getBestLapTime();

    float getMaximumLapSpeed();

    void addCompetitionParticipant();

    static void showParticipants(Competing[] participants) {
        if (!DataService.isCorrect(participants)) {
            System.out.println(NO_PARTICIPANTS);
            return;
        }
        String participantsClass = participants.getClass().getSimpleName();
        String outputTitle;
        switch (participantsClass) {
            case "Competing[]":
                outputTitle = "\nУчастники соревнований:";
                break;
            case "Car[]":
                outputTitle = "\nУчаствующие в соревновании Легковые автомобили:";
                break;
            case "Truck[]":
                outputTitle = "\nУчаствующие в соревновании Грузовые автомобили:";
                break;
            case "Bus[]":
                outputTitle = "\nУчаствующие в соревновании Автобусы:";
                break;
            default:
                outputTitle = "\nУчастники соревнований неизвестного типа:";
        }
        System.out.println(outputTitle);
        TextService.printList(participants, TextService.PrintModes.NUMBERED_LIST_PM);

//        System.out.println(participants.getClass().getSimpleName());
    }
}
