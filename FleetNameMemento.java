/** Restores the original name through the unchanged fleet's public API. */
public class FleetNameMemento implements Memento {
    private VehicleFleet fleet;
    private String oldName;

    public FleetNameMemento(VehicleFleet fleet) {
        this.fleet = fleet;
        this.oldName = fleet.getName();
    }

    public void restore() {
        fleet.setName(oldName);
    }
}
