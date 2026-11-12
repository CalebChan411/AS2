/** Executes commands and delegates undo/redo history to CommandHistory. */
public class CommandInvoker {
    private CommandHistory history;

    public CommandInvoker() {
        this(new CommandHistory());
    }

    public CommandInvoker(CommandHistory history) {
        this.history = history;
    }

    public void execute(Command command) {
        if (command == null) return;
        try {
            command.execute();
            history.record(command);
        } catch (CommandExecutionException ex) {
            System.out.println(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void undo() {
        history.undo();
    }

    public void redo() {
        history.redo();
    }

    public void showUndoRedoList() {
        history.showUndoRedoList();
    }
}
