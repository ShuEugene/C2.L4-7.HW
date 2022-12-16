package transport;

import auxiliaryLibrary.DataService;

import java.sql.Time;
import java.util.Arrays;

public abstract class Transport implements Competing {

    protected static final String DEFAULT_STRING = "default";
    protected static final String UNKNOWN_INFO = "<Информация не указана>";

//    protected static final String FULL_FUEL_TANK = "\nТС не нуждается в заправке.";
//    protected static final String REFILL_FUEL_TANK = "\nТопливный бак дозаправлен <типТоплива> на <частьБака>%.";
//    protected static final String SPEED_MEASUREMENT_UNIT = "км/ч";

    private final String brand, model;
    protected float engineVolume;

    private Time bestLapTime;
    private float maxLapSpeed;

    private static Competing[] competitionParticipants;

//    private String color;

//    private final String productionCountry;
//    private final int productionYear;
//
//    private int speed;
//
//    protected String fuelType;
//    private float fuelPercentage;
//
//    protected byte defaultParametersNumber = 0;


//    protected Transport(String brand, String model, String color, String productionCountry, int productionYear) {
//        this(brand, model, color, productionCountry, productionYear, 0);
//    }
//
//    protected Transport(String brand, String model, float engineVolume/*,String color, String productionCountry, int productionYear, int speed*/) {
//        defaultParametersNumber = 0;
//        this.brand = getCorrect(brand);
//        this.model = getCorrect(model);
//        this.color = getCorrectColor(color);
//        this.productionCountry = getCorrect(productionCountry);
//        this.productionYear = getCorrectProductionYear(productionYear);
//        setSpeed(speed);
//    }

    protected Transport(String brand, String model, float engineVolume) {
        this.brand = getCorrect(brand);
        this.model = getCorrect(model);
        setEngineVolume(engineVolume);
    }

//    protected abstract void refill();
//
//    protected void refuel(String fuelType) {
//        if (!fuelType.equals(this.fuelType)) {
//            if (fuelType.equals("бензин")) {
//                fuelType += "ом";
//            }
//            System.out.println(REFILL_FUEL_TANK.replace("<типТоплива>", fuelType).
//                    replace("<частьБака>", "100").
//                    replace(" дозаправлен ", " перезаправлен "));
//            return;
//        }
//        if (getFuelPercentage() >= 100) {
//            System.out.println(FULL_FUEL_TANK);
//        } else {
//            if (fuelType.equals("бензин")) {
//                fuelType += "ом";
//            }
//            System.out.println(REFILL_FUEL_TANK.replace("<типТоплива>", fuelType).
//                    replace("<частьБака>", Float.toString(100f - getFuelPercentage())));
//        }
//    }

    protected static void addCompetitionParticipant(Transport transport) {
        competitionParticipants = getCompetitionParticipants();
        competitionParticipants = Arrays.copyOf(competitionParticipants, competitionParticipants.length + 1);
        competitionParticipants[competitionParticipants.length - 1] = transport;
    }


    protected void start() {
        System.out.println("\nНачал движение.");
    }

    protected void finish() {
        System.out.println("\nОстановился.");
    }


    @Override
    public void pitStop() {
        System.out.println("\nЗаехал в ПитСтоп.");
    }


//    protected final String getCorrect(String parameter) {
//        if (!DataService.isCorrect(parameter)) {
//            parameter = DEFAULT_STRING;
//            ++defaultParametersNumber;
//        }
//        return parameter;
//    }

    protected final String getCorrect(String parameter) {
        if (!DataService.isCorrect(parameter)) {
            parameter = DEFAULT_STRING;
        }
        return parameter;
    }

    protected final String getBrand() {
        return getCorrect(brand);
    }

    protected final String getModel() {
        return getCorrect(model);
    }

    protected final String getStrEngineVolume() {
        if (engineVolume <= 0) {
            return UNKNOWN_INFO;
        }
        return engineVolume + " л";
    }

    protected final float getEngineVolume() {
        if (engineVolume <= 0) {
            return 0;
        }
        return engineVolume;
    }

    protected final void setEngineVolume(float engineVolume) {
        this.engineVolume = Math.abs(engineVolume);
    }

    //    protected String getProductionCountry() {
//        return getCorrect(productionCountry);
//    }
//
//    protected int getCorrectProductionYear(int parameter) {
//        if (parameter <= 0) {
//            parameter = 2_000;
//            ++defaultParametersNumber;
//        }
//        return parameter;
//    }
//
//    protected int getProductionYear() {
//        return getCorrectProductionYear(productionYear);
//    }
//
//    protected String getCorrectColor(String color) {
//        if (!DataService.isCorrect(color)) {
//            color = "белый";
//            ++defaultParametersNumber;
//        }
//        return color;
//    }
//
//    protected String getColor() {
//        return getCorrectColor(color);
//    }
//
//    protected void setColor(String color) {
//        if (DataService.isCorrect(color)) {
//            this.color = color;
//        } else this.color = getCorrectColor(color);
//    }
//
//    protected int getSpeed() {
//        return speed;
//    }
//
//    protected String getStrSpeed() {
//        return speed != 0 ? Integer.toString(speed) + " км/ч" : "не указана";
//    }
//
//    protected void setSpeed(int speed) {
//        if (speed != 0) {
//            this.speed = Math.abs(speed);
//        } else ++defaultParametersNumber;
//    }
//
//    protected String getStrFuelPercentage() {
//        if (fuelPercentage != 0) {
//            return String.format("%.2f%%", getFuelPercentage());
//        } else return UNKNOWN_INFO;
//    }
//
//    protected float getFuelPercentage() {
//        return Math.abs(fuelPercentage);
//    }
//
//    public void setFuelPercentage(float fuelPercentage) {
//        if (fuelPercentage != 0) {
//            if (Math.abs(fuelPercentage) > 100) {
//                this.fuelPercentage = 100;
//            } else this.fuelPercentage = Math.abs(fuelPercentage);
//        }
//    }
//
//    protected abstract String getFuelType();
//
//    protected abstract void setFuelType(String fuelType);
//
//    @Override
//    public String toString() {
//        return String.format("Автомобиль марки %s %s\n" +
//                        "(цвет: %s; тип кузова: %s; количество мест: %d; страна-производитель: %s; год выпуска: %d;\n" +
//                        "объём двигателя: %.1f л; тип коробки передач: %s; покрышки: %s; регистрационный номер: \"%s\")\n",
//                getBrand(), getModel(), getColor(), body, seatsNumber, getProductionCountry(), getProductionYear(),
//                engineVolume, transmission, getTiresType(), regNumber);
//    }

    @Override
    public Time getBestLapTime() {
        if (bestLapTime == null) {
            return null;
        }
        return bestLapTime;
    }

    public void setBestLapTime(Time bestLapTime) {
        if (bestLapTime != null) {
            this.bestLapTime = bestLapTime;
        }
    }

    @Override
    public float getMaximumLapSpeed() {
        if (maxLapSpeed <= 0) {
            return 0;
        }
        return maxLapSpeed;
    }

    public void setMaxLapSpeed(float maxLapSpeed) {
        if (maxLapSpeed > 0) {
            this.maxLapSpeed = maxLapSpeed;
        }
    }

    public static Competing[] getCompetitionParticipants() {
        if (competitionParticipants == null) {
            competitionParticipants = new Competing[0];
        }
        return competitionParticipants;
    }

    public static void setCompetitionParticipants(Competing[] participants) {
        if (DataService.isCorrect(participants)) {
            Transport.competitionParticipants = participants;
        }
    }
}