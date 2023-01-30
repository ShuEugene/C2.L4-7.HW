package transport;

import specialists.Mechanic.RepairType;

public interface Diagnosed {

    RepairType performDiagnostics() throws Transport.DriverException;
}
