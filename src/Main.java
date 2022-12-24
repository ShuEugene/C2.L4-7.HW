import transport.*;
import transport.tecnicalSpecifications.*;

public class Main {

    public static void main(String[] args) {

        Car lada21099 = new Car("Лада", "21099", 1.5f, CarBody.SEDAN);
        Car ladaGranta = new Car("Лада", "Гранта", 1.6f, CarBody.SEDAN);
        Car ladaVesta = new Car("Лада", "Веста", 1.6f, CarBody.SEDAN);
        Car moskvich = new Car("Москвич", "2140", 1.5f);

        Truck kamaz = new Truck("КамАЗ", "43253", 4.5f, LoadCapacity.N2);
        Truck uralNext = new Truck("Урал", "Next", 6.7f, LoadCapacity.N3);
        Truck zil5301Bychok = new Truck("ЗИЛ", "5301 \"Бычок\"", 5f, LoadCapacity.N1);
        Truck kraz = new Truck("КрАЗ", "6505", 11f, LoadCapacity.N2);

        Bus ikarus = new Bus("Ikarus", "250", 10.7f, PassangerCapacity.MEDIUM);
        Bus liaz = new Bus("ЛиАз", "5292", 6.9f,PassangerCapacity.EXTRA_LARGE);
        Bus pazVector = new Bus("ПАЗ", "Вектор Next 7.6", 4.4f, PassangerCapacity.LARGE);
        Bus nefaz5299 = new Bus("НефАЗ", "5299 \"Городской\"", 11.8f, PassangerCapacity.SMALL);

//        Competing.showParticipants(Transport.getCompetitionParticipants());

        transport.Driver<Car> ivan = new transport.Driver<>("Иван", moskvich, 15);
        transport.Driver<Truck> stepan = new transport.Driver<>("Степан", kamaz, 10);
        transport.Driver<Bus> vasiliy = new transport.Driver<>("Василий", pazVector, 5);
        transport.Driver<Bus> fedor = new transport.Driver<>("Фёдор");
        kraz.setDriver(fedor);

//        vasiliy.started();
//        ivan.started();
//        stepan.started();
//
//        ivan.transportRefuel();
//
//        ivan.finished();
//        stepan.finished();
//        vasiliy.finished();

//        ladaVesta.printType();
//        moskvich.printType();
//        zil5301Bychok.printType();
//        ikarus.printType();

        Transport.diagnoseTheCompetitors();
    }
}