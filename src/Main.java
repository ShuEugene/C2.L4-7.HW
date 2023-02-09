import auxiliaryLibrary.TextService;

import transport.Transport;
import transport.Transport.Category;
import transport.Transport.TransportException;
import transport.Car;
import transport.Truck;
import transport.Bus;

import transport.tecnicalSpecifications.CarBody;
import transport.tecnicalSpecifications.LoadCapacity;
import transport.tecnicalSpecifications.PassangerCapacity;

import specialists.Driver;

import specialists.Mechanic;
import specialists.Mechanic.MechanicException;
import specialists.Mechanic.Repaired;
import specialists.Mechanic.RepairType;

import specialists.ServiceStation;
import specialists.ServiceStation.ServiceException;

public class Main {
    public static void main(String[] args) {

        Car lada21099 = new Car("Лада", "21099", 1.5f, CarBody.SEDAN);
        Car moskvich = new Car("Москвич", "2140", 1.5f);
        Truck kamaz = new Truck("КамАЗ", "43253", 4.5f, LoadCapacity.N2);
        Bus pazVector = new Bus("ПАЗ", "Вектор Next 7.6", 4.4f, PassangerCapacity.LARGE);

        Driver<Car> ivan = new Driver<>("Иван", moskvich, 15);
        Driver<Truck> stepan = new Driver<>("Степан", kamaz, 10);
        Driver<Bus> vasiliy = new Driver<>("Василий", pazVector, 5);
        Driver<Car> eulampiy = new Driver<>("Евлампий", lada21099);

        lada21099.setRepairType(RepairType.REPAIR);
        moskvich.setRepairType(RepairType.SERVICE);
        kamaz.setRepairType(RepairType.REPAIR);
        pazVector.setRepairType(RepairType.REPAIR);

        Mechanic potapych;
        try {
            potapych = new Mechanic("Бывалый", "Семён Потапович", Category.DLC_B, Category.DLC_C, Category.DLC_D);
            potapych.addRepaired(lada21099, moskvich, kamaz, pazVector);
        } catch (MechanicException | TransportException e) {
            TextService.printException(e);
        }

        Mechanic trofimych, trofimych2;
        try {
            trofimych = new Mechanic("Ворчалкин", "Егор Трофимович", Category.DLC_B, Category.DLC_C, Category.DLC_D);
            trofimych2 = new Mechanic("Ворчалкин", "Егор Трофимович", Category.DLC_B, Category.DLC_C, Category.DLC_D);
            trofimych.addRepaired(kamaz);
            trofimych2.addRepaired(kamaz);
        } catch (MechanicException | TransportException e) {
            TextService.printException(e);
        }

        kamaz.showSpecialists();
    }
}