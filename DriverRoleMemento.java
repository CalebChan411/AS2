/** Snapshots the role of the actual driver, including its object identity. */
public class DriverRoleMemento implements Memento {
    private Driver driver;
    private int oldRole;

    public DriverRoleMemento(Driver driver) {
        this.driver = driver;
        this.oldRole = driver.getRole();
    }

    public void restore() {
        driver.setRole(oldRole);
    }
}
