/** Holds one command's snapshot without inspecting its saved state. */
public class MementoCaretaker {
    private Memento memento;

    public MementoCaretaker() {
        memento = null;
    }

    public void save(Memento memento) {
        this.memento = memento;
    }

    public boolean hasMemento() {
        return memento != null;
    }

    public void restore() {
        if (hasMemento()) memento.restore();
    }
}
