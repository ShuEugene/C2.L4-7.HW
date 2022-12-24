package transport;

import java.util.Arrays;

import transport.tecnicalSpecifications.CarBody;

public class Car extends Transport implements Diagnosed {

////    private final String body;
////    private final byte seatsNumber;
////
////    private String transmission;
////
////    private String regNumber;
////
////    private boolean useOfWinterTires;
////
////    private Key key;
////    private Insurance insurance;
////
////    public class Key {
////        private final boolean remoteEngineStart;
////        private final boolean keylessAccess;
////
////        public Key(boolean remoteEngineStart, boolean keylessAccess) {
////            this.remoteEngineStart = remoteEngineStart;
////            this.keylessAccess = keylessAccess;
////        }
////
////        public Key() {
////            this(false, false);
////        }
////
////        @Override
////        public String toString() {
////            String remoteEngineStart = this.isRemoteEngineStart() ? "есть" : "нет";
////            String keylessAccess = this.isKeylessAccess() ? "есть" : "нет";
////            return String.format("опции ключа зажигания: удалённый запуск двигателя - %s, безключевой доступ - %s",
////                    remoteEngineStart, keylessAccess);
////        }
////
////        public boolean isRemoteEngineStart() {
////            return remoteEngineStart;
////        }
////
////        public boolean isKeylessAccess() {
////            return keylessAccess;
////        }
////    }
////
////    public class Insurance {
////        private final LocalDate validityPeriod;
////        private final float cost;
////        private final String regNumber;
////
////        public Insurance(LocalDate validityPeriod, float cost, String regNumber) {
////            this.validityPeriod = validityPeriod;
////            this.cost = Math.abs(cost);
////            this.regNumber = regNumber;
////        }
////
////        public boolean periodIsValid() {
////            if (!validityPeriod.isAfter(LocalDate.now())) {
////                System.out.println("\nСтраховка просрочена.");
////                return false;
////            }
////            return true;
////        }
////
////        public boolean regNumberIsCorrect() {
////            return DataService.isCorrect(regNumber) && regNumber.length() == 9;
////        }
////
////        @Override
////        public String toString() {
////            return Car.this.insuranceIsValid() ? String.format("№%s действительна до %s (оплаченная стоимость - %.2f руб.)",
////                    regNumber, validityPeriod, cost) : "отсутствует";
////        }
////
////        public LocalDate getValidityPeriod() {
////            return validityPeriod;
////        }
////
////        public String getStrValidityPeriod() {
////            if (validityPeriod == null) {
////                return Transport.UNKNOWN_INFO;
////            }
////            return validityPeriod.toString();
////        }
////
////        public float getCost() {
////            return cost;
////        }
////
////        public String getRegNumber() {
////            if (!DataService.isCorrect(regNumber)) {
////                return UNKNOWN_INFO;
////            }
////            return regNumber;
////        }
////    }
//
//    public Car(String brand, String model, String color, String productionCountry, int productionYear, String regNumber,
//               String body, float engineVolume, byte seatsNumber, String transmission, boolean useOfWinterTires) {
//        super(brand, model, color, productionCountry, productionYear);
//        setRegNumber(regNumber);
//        this.body = getCorrect(body);
//        this.engineVolume = getCorrectEngineVolume(engineVolume);
//        this.seatsNumber = getCorrectSeatsNumber(seatsNumber);
//        this.transmission = getCorrect(transmission);
//        this.useOfWinterTires = useOfWinterTires;
//        setFuelType();
//        if (defaultParametersNumber > 0) {
//            System.out.println("\nТранспортное средство «" + getBrand() + "» добавлено c " + defaultParametersNumber
//                    + " параметром(-ами) по умолчанию.");
//        } else {
//            System.out.println("\n" + this + "успешно добавлен.");
//        }
//    }

    private static Car[] competitionParticipants;

    public static Car[] getCompetitionParticipants() {
        if (competitionParticipants == null) {
            competitionParticipants = new Car[0];
        }
        return competitionParticipants;
    }


    private CarBody type;


    public Car(String brand, String model, float engineVolume) {
        this(brand, model, engineVolume, null);
    }

    public Car(String brand, String model, float engineVolume, CarBody type) {
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


//    @Override
//    public void refill() {
//        refill(fuelType);
//    }
//
//    public void refill(String fuelType) {
//        if (!fuelType.equals("ДТ") && !fuelType.equals("бензин") && !fuelType.equals("ЭЭ")) {
//            System.out.println("\nЗаправка данным видом топлива исключена.");
//        }
//        refuel(fuelType);
//    }
//
//    public boolean insuranceIsValid() {
//        return insurance.periodIsValid() && insurance.regNumberIsCorrect();
//    }

//    public String getBody() {
//        return getCorrect(body);
//    }
//
//    public byte getSeatsNumber() {
//        return getCorrectSeatsNumber(seatsNumber);
//    }
//
//    float getCorrectEngineVolume(float parameter) {
//        if (parameter <= 0) {
//            parameter = 1.5f;
//            ++defaultParametersNumber;
//        }
//        return parameter;
//    }
//
//    public float getEngineVolume() {
//        return getCorrectEngineVolume(engineVolume);
//    }
//
//    public void setEngineVolume(float engineVolume) {
//        if (engineVolume > 0) {
//            this.engineVolume = engineVolume;
//        } else this.engineVolume = getCorrectEngineVolume(engineVolume);
//    }
//
//    public String getTransmission() {
//        return getCorrect(transmission);
//    }
//
//    public void setTransmission(String transmission) {
//        if (DataService.isCorrect(transmission)) {
//            this.transmission = transmission;
//        } else this.transmission = getCorrect(transmission);
//    }
//
//    boolean isCorrectRegNumber(String regNumber) {
//        if (!DataService.isCorrect(regNumber)) return false;
//        if (regNumber.length() != 9) return false;
//        String rnPart = regNumber.substring(0, 1);
//        if (!DataService.isLetters(rnPart)) return false;
//        rnPart = regNumber.substring(1, 4);
//        if (!rnPart.matches("\\d+")) return false;
//        rnPart = regNumber.substring(4, 6);
//        if (!DataService.isLetters(rnPart)) return false;
//        rnPart = regNumber.substring(6);
//        return rnPart.matches("\\d+");
//    }
//
//    public String getRegNumber() {
//        if (!isCorrectRegNumber(regNumber)) {
//            regNumber = UNKNOWN_INFO;
//            ++defaultParametersNumber;
//        }
//        return regNumber;
//    }
//
//    public void setRegNumber(String regNumber) {
//        if (isCorrectRegNumber(regNumber)) {
//            this.regNumber = regNumber;
//        } else this.regNumber = getRegNumber();
//    }
//
//    public boolean areWinterTiresUsed() {
//        return useOfWinterTires;
//    }
//
//    public void setWinterTiresUsed(boolean areUsed) {
//        this.useOfWinterTires = areUsed;
//    }
//
//    String getTiresType() {
//        return areWinterTiresUsed() ? "зимние" : "летние";
//    }


//    byte getCorrectSeatsNumber(byte parameter) {
//        if (parameter <= 0) {
//            parameter = 2;
//            ++defaultParametersNumber;
//        }
//        return parameter;
//    }
//
//    public Key getKey() {
//        return key;
//    }
//
//    public void setKey(Key key) {
//        this.key = key;
//    }
//
//    public Insurance getInsurance() {
//        return insurance;
//    }
//
//    public void setInsurance(Insurance insurance) {
//        if (insurance != null) {
//            this.insurance = insurance;
//        }
//    }
//
//    public String getFuelType() {
//        if (!fuelType.equals("ДТ") && !fuelType.equals("бензин") && !fuelType.equals("ЭЭ")) {
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
//        switch (fuelType) {
//            case "бензин":
//            case "ЭЭ":
//                this.fuelType = fuelType;
//                break;
//            default:
//                this.fuelType = "ДТ";
//        }
//    }

    CarBody getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Легковой автомобиль марки «%s %s» (объём двигателя: %.1f л)",
                getBrand(), getModel(), getEngineVolume());
    }
}