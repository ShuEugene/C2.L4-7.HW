package transport;

import auxiliaryLibrary.DataService;
import transport.tecnicalSpecifications.LoadCapacity;

import java.util.Arrays;

public class Truck extends Transport implements Diagnosed {

    private static Truck[] competitionParticipants;

    public static Truck[] getCompetitionParticipants() {
        if (competitionParticipants == null) {
            competitionParticipants = new Truck[0];
        }
        return competitionParticipants;
    }


//    private float tripCost;
//    private float tripDuration;
//
//    private String startStation, endStation;
//
//    private int numberOfWagons;
//
//    public Truck(String brand, String model, int productionYear, int speed,
//                 String startStation, String endStation, float tripCost, int numberOfWagons) {
//        this(brand, model, null, null, productionYear, speed, startStation, endStation, tripCost, numberOfWagons);
//    }
//
//    public Truck(String brand, String model, String color, String productionCountry, int productionYear, int speed,
//                 String startStation, String endStation, float tripCost, int numberOfWagons) {
//        super(brand, model, color, productionCountry, productionYear, speed);
//        setStartStation(startStation);
//        setEndStation(endStation);
//        setTripCost(tripCost);
//        setNumberOfWagons(numberOfWagons);
//        setFuelType();
//        if (defaultParametersNumber > 0) {
//            System.out.println("\nТранспортное средство «" + getBrand() + "» добавлено c " + defaultParametersNumber
//                    + " параметром(-ами) по умолчанию.");
//        } else {
//            System.out.println("\n" + this + "успешно добавлен.");
//        }
//    }

    private LoadCapacity type;

    public Truck(String brand, String model, float engineVolume) {
        this(brand, model, engineVolume, null);
    }

    public Truck(String brand, String model, float engineVolume, LoadCapacity type) {
        super(brand, model, engineVolume);
        this.type = type;
        addCompetitionParticipant();
        Transport.addCompetitionParticipant(this);
    }


    @Override
    public boolean performDiagnostics() {
        Driver<Transport> driver = getDriver();
        if (driver == null) {
            throw new NullPointerException("За " + getTitle() + " не закреплён водитель.");
        }
        if (driver.getDriverLicenseCategory() == Driver.DriveLicCategories.DLC_N) {
            throw new NullPointerException("У водителя (" + driver.getName() + ") отсутствуют водительские права.");
        }
        return true;
    }

    @Override
    public void printType() {
        if (type == null) {
            System.out.println("\nДанных по " + getTitle() + " недостаточно.");
            return;
        }
        System.out.println("\nТип " + getTitle() + " - «" + getType().name() + "» (" + type + ").");
    }

    public final void addCompetitionParticipant() {
        competitionParticipants = getCompetitionParticipants();
        competitionParticipants = Arrays.copyOf(competitionParticipants, competitionParticipants.length + 1);
        competitionParticipants[competitionParticipants.length - 1] = this;
    }

//    public static void showCompetitionParticipants() {
//        if (DataService.isCorrect(participatingBuses)) {
//            System.out.println("\nУчаствующие в соревновании Грузовые автомобили:");
//            TextService.printList(participatingBuses, TextService.PrintModes.NUMBERED_LIST_PM);
//        } else {
//            System.out.println("\nГрузовые автомобили не участвуют в соревнованиях.");
//        }
//    }


    //    @Override
//    public void refill() {
//        refill("ДТ");
//    }
//
//    public void refill(String fuelType) {
//        if (getFuelPercentage() >= 100) {
//            System.out.println(FULL_FUEL_TANK);
//        } else {
//            System.out.println(REFILL_FUEL_TANK.replace("<типТоплива>", fuelType).
//                    replace("<частьБака>", Float.toString(100f - getFuelPercentage())));
//        }
//    }
//
//    public String getStrTripCost() {
//        return tripCost != 0 ? String.format("%.2f руб.", getTripCost()) : Transport.UNKNOWN_INFO;
//    }
//
//    public float getTripCost() {
//        return Math.abs(tripCost);
//    }
//
//    public void setTripCost(float tripCost) {
//        this.tripCost = Math.abs(tripCost);
//    }
//
//    public String getStrTripDuration() {
//        return tripDuration != 0 ? String.format("%.1f ч.", getTripDuration()) : Transport.UNKNOWN_INFO;
//    }
//
//    public float getTripDuration() {
//        return Math.abs(tripDuration);
//    }
//
//    public void setTripDuration(float tripDuration) {
//        this.tripDuration = Math.abs(tripDuration);
//    }
//
//    public String getStartStation() {
//        if (DataService.isCorrect(startStation)) {
//            return startStation;
//        } else {
//            return Transport.UNKNOWN_INFO;
//        }
//    }
//
//    public void setStartStation(String startStation) {
//        if (DataService.isCorrect(startStation)) {
//            this.startStation = startStation;
//        }
//    }
//
//    public String getEndStation() {
//        if (DataService.isCorrect(endStation)) {
//            return endStation;
//        } else {
//            return Transport.UNKNOWN_INFO;
//        }
//    }
//
//    public void setEndStation(String endStation) {
//        if (DataService.isCorrect(endStation)) {
//            this.endStation = endStation;
//        }
//    }
//
//    public int getNumberOfWagons() {
//        return Math.abs(numberOfWagons);
//    }
//
//    public void setNumberOfWagons(int numberOfWagons) {
//        if (numberOfWagons != 0) {
//            this.numberOfWagons = Math.abs(numberOfWagons);
//        } else {
//            ++defaultParametersNumber;
//        }
//    }
//
//    public String getFuelType() {
//        if (!fuelType.equals("ДТ")) {
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
//        if (fuelType.equals("ДТ")) {
//            this.fuelType = fuelType;
//        }
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Поезд марки %s %s\n" +
//                        "(цвет: %s; страна-производитель: %s; год выпуска: %d),\n" +
//                        "идущий c %d вагоном(-ами) по маршруту «%s - %s» со скоростью %s\n" +
//                        "(стоимость поездки - %s)\n",
//                getBrand(), getModel(), getColor(), getProductionCountry(), getProductionYear(),
//                getNumberOfWagons(), getStartStation(), getEndStation(), getStrSpeed(), getStrTripCost());
//    }


    LoadCapacity getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Грузовой автомобиль марки «%s %s» (объём двигателя: %.1f л)",
                getBrand(), getModel(), getEngineVolume());
    }
}