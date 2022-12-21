import auxiliaryLibrary.DataService;
import transport.*;

public class Driver<TC extends Transport & Competing> {

    private enum DriveLicCategories {DLC_B, DLC_C, DLC_D;}

    private String name;
    private DriveLicCategories driverLicenseCategory;
    private int drivingExperience;
    private TC transport;

//    public Driver(String name, TC driverLicense) {
//        this(name, driverLicense, 1);
//    }
//
//    public Driver(String name, TC driverLicense, int drivingExperience) {
//        setName(name);
//        setDriverLicense(driverLicense);
//        setDrivingExperience(drivingExperience);
//    }

    public Driver(String name) {
        this(name, null, 0);
    }

    public Driver(String name, TC transport) {
        this(name, transport, 1);
    }

    public Driver(String name, TC transport, int drivingExperience) {
        setName(name);
        setDriverLicenseCategory(transport);
        getOnTheTransport(transport);
        setDrivingExperience(drivingExperience);
    }


//    public void start(TC transport) {
//        System.out.printf("\nСтартовал на %s!\n", transport);
//    }
//
//    public void finish(TC transport) {
//        System.out.println("\nПриехали..");
//    }

    public void started() {
        if (transport != null) {
            getTransport().started();
            System.out.println(getName() + " стартовал.");
        }
    }

    public void finished() {
        if (transport != null) {
            getTransport().stopped();
            System.out.println(getName() + " финишировал.");
        }
    }

    public void pitStop() {
        if (transport != null) {
            System.out.print("\n" + getName() + " отправил свой " + transport.getTransportTypeTitle() + " в пит-стоп.");
            transport.pitStop();
        }
    }

    public void transportRefuel() {
        if (transport != null) {
            pitStop();
            System.out.println(getName() + " заправил свой " + transport.getTransportTypeTitle() + " и продолжил соревнование.");
        }
    }


    public String getName() {
        if (!DataService.isCorrect(name)) {
            return "<не известно>";
        }
        return name;
    }

    public void setName(String name) {
        if (DataService.isCorrect(name)) {
            this.name = name;
        }
    }

    public DriveLicCategories getDriverLicenseCategory() {
        return driverLicenseCategory;
    }

    public void setDriverLicenseCategory() {
        setDriverLicenseCategory(transport);
    }

    public void setDriverLicenseCategory(TC transport) {
        if (transport != null) {
            driverLicenseCategory = getTransportCategory(transport);
        }
    }

    public void setDriverLicenseCategory(DriveLicCategories licenseCategory) {
        driverLicenseCategory = licenseCategory;
    }

    public int getDrivingExperience() {
        if (drivingExperience < 0) {
            return 0;
        }
        return drivingExperience;
    }

    public void setDrivingExperience(int drivingExperience) {
        if (drivingExperience > 0) {
            this.drivingExperience = drivingExperience;
        }
    }

    public String getTitleOfTransport() {
        if (transport == null) {
            return "\nСредство соревнований ещё не выбрано.";
        }
        return transport.toString();
    }

    private DriveLicCategories getTransportCategory() {
        return getTransportCategory(transport);
    }

    private DriveLicCategories getTransportCategory(TC transport) {
        if (transport == null) {
            return null;
        }
        switch (transport.getClass().getSimpleName()) {
            case "Car":
                return DriveLicCategories.DLC_B;
            case "Truck":
                return DriveLicCategories.DLC_C;
            case "Bus":
                return DriveLicCategories.DLC_D;
            default:
                return null;
        }
    }

    public TC getTransport() {
        return transport;
    }

    public void getOnTheTransport(TC transport) {
        if (transport != null) {
            DriveLicCategories licCategories = getTransportCategory(transport);
//            if (licCategories != getDriverLicenseCategory()) {
//                System.out.println("\nКатегории водительских прав исключают вождение данного вида транспорта.");
//                this.transport = null;
//                return;
//            }
            this.transport = transport;
            String transportType;
            switch (licCategories) {
                case DLC_B:
                    transportType = "легковом автомобиле";
                    break;
                case DLC_C:
                    transportType = "грузовом автомобиле";
                    break;
                case DLC_D:
                    transportType = "автобусе";
                    break;
                default:
                    transportType = "<неизвестном транспорте>";
            }
            System.out.println("\n" + getName() + " соревнуется на "
                    + transportType + " " + transport.getTechnicalCard() + ".");
        }
    }
}