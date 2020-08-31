package execution;

import borrower.IBorrower;
import person.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Collector extends Person implements IWorker, ICollector {

    private ICollector.Violence violence;
    private  double seniority;
    private double salaryPerMission;
    private double sumOfBonuses;
    private final List<ICollectionMission> doneMissionsList = new ArrayList<>();
    private final List<ICollectionMission> openMissionsList = new ArrayList<>();
    private static final int MAX_OPEN_MISSIONS_OVERLOAD = 3;
    private static final double SENIORITY_COEFFICIENT_ADDED_FOR_EACH_MISSION = 0.01;

    public Collector() {
    }

    public Collector(int id, String name, String address, String phone, String email, Date dateOfBirth, Violence violence, double salaryPerMission) {
        super(id, name, address, phone, email, dateOfBirth);
        this.violence = violence;
        this.seniority = 1;
        this.salaryPerMission = salaryPerMission;
    }

    @Override
    public ICollector.Violence getViolence() {
        return this.violence;
    }

    @Override
    public boolean isFree() {
        return openMissionsList.size() < MAX_OPEN_MISSIONS_OVERLOAD;
    }

    @Override
    public void giveMission(ICollectionMission collectionMission) {
        collectionMission.setCollector(this);
        openMissionsList.add(collectionMission);
    }

    @Override
    public ICollectionMission getMission(IBorrower borrower) {
        for (ICollectionMission mission :
                openMissionsList) {
            if (mission.borrowerDetails() == borrower)
                return mission;
        }
        return null;
    }

    @Override
    public void closeMission(ICollectionMission doneMission, String collectionReport) {
        openMissionsList.remove(doneMission);
        doneMission.closeMission(collectionReport);
        doneMissionsList.add(doneMission);
        seniority += SENIORITY_COEFFICIENT_ADDED_FOR_EACH_MISSION;
    }

    @Override
    public void giveBonus(double bonus) {
        sumOfBonuses += bonus;
    }

    @Override
    public double calcSalary() {
        double salary = sumOfBonuses;
        salary += (doneMissionsList.size() * salaryPerMission);
        salary *= this.seniority;
        return salary;
    }

}
