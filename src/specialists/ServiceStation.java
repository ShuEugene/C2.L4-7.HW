package specialists;

import java.util.*;

import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

import transport.Bus;
import transport.Transport;
import transport.Transport.TransportException;

import specialists.Mechanic.Repaired;
import specialists.Mechanic.RepairType;

import static specialists.Mechanic.RepairType.PROPERLY;


public class ServiceStation {

    public static class ServiceException extends Exception {
        public ServiceException() {
        }

        public ServiceException(String message) {
            super(message);
        }
    }

    private final String title;
    private Set<Mechanic> mechanics = new HashSet<>();
    private Deque<Repaired> repaired = new LinkedList<>();

    private Map<Transport, Mechanic> tasks = new HashMap<>();


    public ServiceStation(String title, Mechanic... mechanics) throws ServiceException {

        if (!DataService.isCorrect(title))
            throw new ServiceException("Название станции техобслуживания должно быть указано.");

        this.title = title;
        System.out.println("\nТолько что налажена работа новой станции ТО - " + getTitle() + ".");
        addMechanics(mechanics);
    }


    public final void showTasks() {
        String printTitle = "\nУ станции ТО " + getTitle() + " пока нет текущих задач.";

        if (DataService.isCorrect(tasks))
            printTitle = "\n" + getTitle() + " - Перечень задач по техобслуживанию:";

        System.out.println(printTitle);

        if (tasks.size() > 0)
            TextService.printList(getTasksList(), TextService.PrintModes.NUMBERED_LIST_PM);
    }

    public final void addTask(Transport transport, Mechanic mechanic) {
        try {
            if (transport == null)
                throw new ServiceException("Для добавления задачи техобслуживания следует указать транспортное средство," +
                        " над которым должны быть произведены работы.");
            if (mechanic == null)
                throw new ServiceException("Для добавления задачи техобслуживания следует указать механика," +
                        " который должен приступить к техобслуживанию " + transport.getTechnicalCard() + ".");

            if (!mechanic.getRepaired().contains(new Repaired(transport, transport.getRepairType())))
                mechanic.addRepaired(transport);

            getTasks().put(transport, mechanic);
            System.out.println("\n" + getTitle() + ": добавлена новая задача - " + mechanic + " добавил в очередь своих задач техобслуживание "
                    + transport.getTechnicalCard() + ".");

        } catch (ServiceException | Mechanic.MechanicException | TransportException e) {
            TextService.printException(e);
        }
    }

    public final void performDiagnostic(Repaired repaired) {
        try {
            if (repaired == null)
                throw new ServiceException("Транспортное средство никак не обозначено. Проведение диагностики исключено.");

            if (!this.getRepaired().contains(repaired))
                throw new ServiceException("Транспортное средство (" + repaired.getTechnicalCard()
                        + " пока ещё не обслуживается данной станцией ТО (" + this + ".\n"
                        + "Для проведения диагностики добавьте его в очередь обслуживания данной станции.");

            RepairType repairType = repaired.getRepairType();
            System.out.println("\nТранспортное средство (" + repaired.getTechnicalCard() + ") " +
                    (repairType == PROPERLY ? "исправно" : "неисправно; требуется " + repairType.getTitle()) + ".");

        } catch (ServiceException e) {
            TextService.printException(e);
        }
    }

    public final void addRepaired(Transport... transports) {
        if (!DataService.isCorrect(transports))
            return;

        for (Transport curTrasport :
                transports) {

            if (curTrasport != null && !(curTrasport instanceof Bus)) {
                if (curTrasport.getRepairType() == PROPERLY) {
                    System.out.println("Транспортное средство (" + curTrasport.getTechnicalCard()
                            + ") исправно и не требует обслуживания на станции ТО (" + this + ".");
                    continue;
                }

                try {
                    Repaired curRepaired = new Repaired(curTrasport, curTrasport.getRepairType());
                    if (!repaired.contains(curRepaired)) {
                        if (repaired.offer(curRepaired)) {
                            System.out.println("\n" + curTrasport.getTechnicalCard()
                                    + " теперь в очереди на обслуживание на станции ТО " + getTitle() + ".");
                            curTrasport.setServiceStation(this);

                        } else
                            System.out.println("\n" + curTrasport.getTechnicalCard()
                                    + " не удалось добавить в очередь на обслуживание на станции ТО " + getTitle() + ".");
                    } else
                        System.out.println("\n" + curTrasport.getTechnicalCard()
                                + " уже стоит в очереди на обслуживание на станции ТО " + getTitle() + ".");

                } catch (TransportException e) {
                    TextService.printException(e);
                }
            }
        }
    }

    public final void addMechanics(Mechanic... mechanics) {
        if (!DataService.isCorrect(mechanics))
            return;

        String resultMess;
        for (Mechanic current :
                mechanics) {
            resultMess = null;

            if (current != null)
                if (!getMechanics().contains(current))
                    try {
                        this.mechanics.add(current);
                        resultMess = "\n" + current + " теперь работает на этой станции ТО (" + getTitle() + ").";
                        current.setServiceStation(this);
                    } catch (Exception e) {
                        System.out.println(current + " не может работать на этой станции ТО: " + e.getMessage() + ".");
                    }

                else
                    resultMess = "\n" + current + " уже числиться на этой станции ТО (" + getTitle() + ").";

            if (resultMess != null)
                System.out.println(resultMess);
        }
    }


    public final String getTitle() {
        return "«" + title + "»";
    }

    public final Set<Mechanic> getMechanics() {
        if (mechanics == null)
            mechanics = new HashSet<>();

        return mechanics;
    }

    public final void setMechanics(Set<Mechanic> mechanics) {
        if (mechanics != null)
            this.mechanics = mechanics;
    }

    public final Deque<Repaired> getRepaired() {
        if (repaired == null)
            repaired = new LinkedList<>();

        return repaired;
    }

    public final void setRepaired(Deque<Repaired> repaired) {
        if (DataService.isCorrect(repaired))
            this.repaired = repaired;
    }

    private final String[] getTasksList() {
        if (!DataService.isCorrect(getTasks()))
            return new String[]{"Текущих задач нет."};

        String[] tasksList = new String[getTasks().size()];
        int index = 0;
        String transport, mechanic;

        for (Map.Entry<Transport, Mechanic> curTask :
                tasks.entrySet()) {
            if (curTask.getKey() != null && curTask.getValue() != null) {
                transport = curTask.getKey().getTechnicalCard();
                mechanic = curTask.getValue().toString();
                tasksList[index++] = "Ответственный за техобслуживание " + transport + " - " + mechanic;
            }
        }

        if (index > 0)
            return tasksList;
        else
            return new String[]{"Текущих задач нет."};
    }
    
    public final Map<Transport, Mechanic> getTasks() {
        if (tasks == null)
            tasks = new HashMap<>();
        return tasks;
    }

    public final void setTasks(Map<Transport, Mechanic> tasks) {
        if (tasks != null)
            this.tasks = tasks;
    }

    @Override
    public final String toString() {
        return getTitle();
    }
}