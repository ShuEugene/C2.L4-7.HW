import transport.*;

public class Main {

    public static void main(String[] args) {

        Car lada21099 = new Car("Лада", "21099", 1.5f);
        Car ladaGranta = new Car("Лада", "Гранта", 1.6f);
        Car ladaVesta = new Car("Лада", "Веста", 1.6f);
        Car moskvich = new Car("Москвич", "2140", 1.5f);

        Truck kamaz = new Truck("КамАЗ", "43253", 4.5f);
        Truck uralNext = new Truck("Урал", "Next", 6.7f);
        Truck zil5301Bychok = new Truck("ЗИЛ", "5301 \"Бычок\"", 5f);
        Truck kraz = new Truck("КрАЗ", "6505", 11f);

        Bus ikarus = new Bus("Ikarus", "250", 10.7f);
        Bus liaz = new Bus("ЛиАз", "5292", 6.9f);
        Bus pazVector = new Bus("ПАЗ", "Вектор Next 7.6", 4.4f);
        Bus nefaz5299 = new Bus("НефАЗ", "5299 \"Городской\"", 11.8f);

        Competing.showParticipants(Transport.getCompetitionParticipants());
    }
}