# Part 3 截图清单

报告中已留好12个图位并写好英文图下注释。截图须来自 main / bf10becbdceb873e77c5c36b0c8d59d5cebaed4b 的实际 Java 源码。使用IDE放大字体，保留文件名和方法名；不要把代码缩成整屏小字。多文件可并排或分成a/b图，放进同一图位。

Part 2只留整张class diagram位置，由你放最终图。Part 4实际运行截图已放入，无须用预期输出另做图片。

## Figure 3.1 — Command contract and reversible requests

截图位置：Command.java (lines 2-8) and UndoableCommand.java (lines 2-16). Show both declarations and the methods execute, undo, redo and isUndoable.

图下注释（报告已有）：The Command interface defines the operations understood by the invoker. UndoableCommand supplies the shared manager reference and identifies the five reversible requests. Concrete business commands inherit this contract, so dispatch and history do not need a branch for every operation.

## Figure 3.2 — Execute first, record only after success

截图位置：CommandInvoker.java, lines 13-23: execute(Command). Include command.execute(), history.record(command) and both catch blocks.

图下注释（报告已有）：The invoker first executes the request, then passes it to history. If execute throws an expected exception, record is not reached. A rejected operation therefore does not become an undo entry and does not clear redo.

## Figure 3.3 — Record and undo history

截图位置：CommandHistory.java, lines 14-38: record() and undo(). Keep peek, command.undo, pop and push readable.

图下注释（报告已有）：record accepts only successful undoable requests and clears redo when a new reversible action succeeds. undo peeks before restoration and moves the entry only after command.undo returns successfully. The same request object is retained.

## Figure 3.4 — Redo without recreating a request

截图位置：CommandHistory.java, lines 40-56: redo(). Show peek, command.redo, pop, push and exception handling.

图下注释（报告已有）：Redo calls redo on the stored request rather than executing a new factory request. Stack transfer happens only after success. A failed redo remains available for retry, and no fresh console input is requested.

## Figure 3.5 — Command factories and dispatch

截图位置：CommandFactory.java (complete); CreateFleetCommandFactory.java, lines 14-23; Main.java, lines 47-55. Use separate readable captures within this figure if needed.

图下注释（报告已有）：Main obtains a CommandFactory from the registry, requests a fresh Command and passes it to the invoker. The concrete factory collects parameters and creates the request; the request changes business state when execute is called. Eleven command/factory pairs use the same dispatch mechanism.

## Figure 3.6 — Fleet creation through a common factory interface

截图位置：FleetFactory.java (complete), DeliveryVanFleetFactory.java lines 2-5 and RefrigeratedTruckFleetFactory.java lines 2-5. Show the two concrete createFleet implementations.

图下注释（报告已有）：Both concrete factories implement FleetFactory and return VehicleFleet. The creation command works with the factory interface and common fleet abstraction, while concrete constructors remain inside the factories. The chosen description is Factory pattern; the design does not require a multi-product Abstract Factory family.

## Figure 3.7 — Driver creation from actual request data

截图位置：DriverFactory.java (complete) and AddDriverCommand.java, lines 20-30. Show createDriver(driverID, driverName), role assignment and execution.

图下注释（报告已有）：DriverFactory constructs a Driver from the supplied ID and sets the supplied name. AddDriverCommand invokes this factory during execution, assigns the requested role and validates through the fleet. Redo retains the same driver object instead of creating a duplicate.

## Figure 3.8 — Common Memento interface and caretaker

截图位置：Memento.java and MementoCaretaker.java, both complete. Show save, hasMemento and restore.

图下注释（报告已有）：The caretaker stores one Memento reference and restores through the interface. It does not inspect old values or choose concrete behavior using instanceof. Each modification command has its own caretaker; the global undo/redo sequence remains in CommandHistory.

## Figure 3.9 — Snapshots bound to the original objects

截图位置：DriverRoleMemento.java and FleetNameMemento.java, both complete. Keep target reference, old value, constructor and restore readable.

图下注释（报告已有）：The snapshots keep the actual Driver or VehicleFleet reference plus the old role or name. Getters capture the old value and existing setters restore it. Equal Driver IDs in different fleets do not redirect restoration. No methods or fields are added to the four protected domain classes.

## Figure 3.10 — Capture before modifying a driver role

截图位置：ModifyRoleCommand.java, lines 17-36: execute() and undo(). Show target lookup, save(new DriverRoleMemento(driver)), caretaker.restore and updateDriverRole.

图下注释（报告已有）：The command resolves the original target and saves its role before mutation. Undo restores through its caretaker and then invokes fleet role validation. Changing the selected fleet later does not change the object retained by the command or its memento.

## Figure 3.11 — Reapply the requested role with rollback

截图位置：ModifyRoleCommand.java, lines 38-50: redo(). Keep the identity check, previousRole, setRole and catch block visible.

图下注释（报告已有）：Redo uses the retained driver and requested new role, without replacing the original snapshot or reading input again. If role validation fails, the immediately preceding role is restored before an execution error is reported.

## Figure 3.12 — Fleet-name Memento in execute, undo and redo

截图位置：ChangeNameCommand.java, lines 13-31. Include snapshot capture, caretaker.restore and fleet.setName(newName).

图下注释（报告已有）：The name-change command captures the old name once before its first modification. Undo restores that name through the caretaker; redo reapplies newName to the original fleet. Fleet identity and driver membership are unchanged.

## 提交前补齐

- 封面姓名、学号和提交日期。
- Part 2最终class diagram。
- Part 3的12个实际代码截图；按图位说明保留可读字号。
- 插图后在Word中全选Ctrl+A，再按F9更新目录/页码，然后重新导出PDF。
- User Guide已放在Appendix A；run.bat和47个.java仍须另交。
- 当前PDF内空图框是按要求预留，补齐前不应作为最终可提交版本。
