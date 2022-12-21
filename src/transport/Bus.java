package transport;

import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

import java.util.Arrays;

public class Bus extends Transport {

//    public Bus(String brand, String model, int productionYear, int speed) {
//        this(brand, model, null, null, productionYear, speed);
//    }
//
//    public Bus(String brand, String model, String color, String productionCountry, int productionYear, int speed) {
//        super(brand, model, color, productionCountry, productionYear, speed);
//        setFuelType();
//        if (defaultParametersNumber > 0) {
//            System.out.println("\nТранспортное средство «" + getBrand() + "» добавлено c " + defaultParametersNumber
//                    + " параметром(-ами) по умолчанию.");
//        } else {
//            System.out.println("\n" + this + "успешно добавлен.");
//        }
//    }

    private static Bus[] competitionParticipants;


    public Bus(String brand, String model, float engineVolume) {
        super(brand, model, engineVolume);
        addCompetitionParticipant();
        Transport.addCompetitionParticipant(this);
    }


    public final void addCompetitionParticipant() {
        competitionParticipants = getCompetitionParticipants();
        competitionParticipants = Arrays.copyOf(competitionParticipants, competitionParticipants.length + 1);
        competitionParticipants[competitionParticipants.length - 1] = this;
    }

//    public static void showCompetitionParticipants() {
//        if (DataService.isCorrect(competitionParticipants)) {
//            System.out.println("\nУчаствующие в соревновании Автобусы:");
//            TextService.printList(competitionParticipants, TextService.PrintModes.NUMBERED_LIST_PM);
//        } else {
//            System.out.println("\nАвтобусы не участвуют в соревнованиях.");
//        }
//    }


//    @Override
//    public void refill() {
//        refill(fuelType);
//    }
//
//    public void refill(String fuelType) {
//        if (!fuelType.equals("ДТ") && !fuelType.equals("бензин")) {
//            System.out.println("\nЗаправка данным видом топлива исключена.");
//        }
//        refuel(fuelType);
//    }
//
//    public String getFuelType() {
//        if (!fuelType.equals("ДТ") && !fuelType.equals("бензин")) {
//            return UNKNOWN_INFO;
//        }
//        return fuelType;
//    }
//
//    public void setFuelType() {
//        setFuelType("ДТ");
//    }
//
//    public void setFuelType(String fuelType) {
//        this.fuelType = fuelType.equals("бензин") ? fuelType : "ДТ";
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Автобус марки %s %s\n" +
//                        "(цвет: %s; страна-производитель: %s; год выпуска: %d, макс. скорость - %s)\n",
//                getBrand(), getModel(), getColor(), getProductionCountry(), getProductionYear(), getStrSpeed());
//    }

    public static Bus[] getCompetitionParticipants() {
        if (competitionParticipants == null) {
            competitionParticipants = new Bus[0];
        }
        return competitionParticipants;
    }

    public static void setCompetitionParticipants(Bus[] competitionParticipants) {
        if (DataService.isCorrect(competitionParticipants)) {
            Bus.competitionParticipants = competitionParticipants;
        }
    }

    @Override
    public String toString() {
        return String.format("Автобус марки «%s %s» (объём двигателя: %.1f л)",
                getBrand(), getModel(), getEngineVolume());
    }
}