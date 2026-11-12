# SDFMS final class-diagram audit

## Scope and evidence

The uploaded `AS_ClassDiagram.pdf` is a single A4 page containing the 44-type pre-refactor design. It was inspected as a rendered image and extracted text, and compared with all 47 final Java files. The uploaded PDF has not been edited. The files in `diagrams/` are newly generated, source-derived views of the updated model; they are not a Visual Paradigm project or a claim that the uploaded PDF is already corrected.

The 47 types comprise 40 concrete classes, 3 abstract classes (VehicleFleet, BaseCommand, UndoableCommand), and 4 interfaces (Command, CommandFactory, FleetFactory, Memento). Explicit members are listed below. Java-generated default constructors and inherited members are omitted from the member compartments; no inherited manager association is duplicated.

## Required changes to the uploaded diagram

1. Add CommandHistory, Memento and MementoCaretaker. Move both history stacks out of CommandInvoker into CommandHistory. Add Invoker.history and the injection constructor; retain the existing no-argument constructor.
2. Replace the two commands’ directly stored concrete mementos with caretaker : MementoCaretaker. Keep their «create» dependencies to concrete mementos. Add each command’s «create» dependency to MementoCaretaker.
3. Bind each concrete memento to its actual Driver or VehicleFleet reference, replace its constructor, change restore(target) to restore(), and add dashed hollow-triangle realization arrows toward Memento.
4. Replace the old solid CommandInvoker → Command history/execute line with dashed dependency execute(command). Add one CommandHistory → Command association covering undoStack and redoStack.
5. Add Main → CommandHistory «create»; no Main.history attribute. Add the missing private static final MENU_PROMPT attribute; static members may be underlined or annotated {static}.
6. Correct final int to int {readOnly} for all four role constants; final does not imply static. Add the missing : void result of FleetUtil.restoreDriverOrder and mark all FleetUtil methods static.
7. Replace CommandFactoryRegistry → CommandFactory getFactory() dependency with the stored-map association factories. Replace CreateFleetCommand → FleetFactoryRegistry dependency with association fleetRegistry; its uploaded label createDriver() is incorrect. AddDriverCommand → DriverFactory is also a stored-reference association.
8. Replace memento → target restore(target) dependencies with stored-target associations. Keep one line per class pair when several fields refer to that type.
9. Add remaining stored-reference associations from the checklist. Existing associations lack multiplicities; label both ends using the documented convention. Generalization points to the superclass; realization points to the interface; «create» points from creator to created type.

## Notation and multiplicity convention

`+` public, `-` private, `#` protected. Abstract classifiers are italic in generated diagrams; abstract operations carry {abstract}. Static operations/attributes carry {static}; final attributes carry {readOnly}. Interface operations are implicitly public and abstract.

Each association is navigable from the field owner (Source) to its referenced type (Target). The **target-end multiplicity** states how many target objects one source can retain. Write it beside the Target box. The **source-end multiplicity** states how many source objects can refer to the same target. Write it beside the Source box. A default 0..* inverse is conservative: the classes do not enforce exclusive ownership. These are valid-application/lifecycle multiplicities, not claims that every public constructor rejects null.

Required references supplied by normal application construction have target multiplicity 1. Captured targets that are unset before first execution use 0..1. A merged association uses the union of its fields, with per-field limits in its note. Each command constructs its own private caretaker, so a caretaker is linked to at most one command of either concrete kind; note this cross-type exclusivity. Do not add composition merely because a reference is a field.

For VehicleFleet → Driver, the conservative Java-reference model uses 0..* at both ends because the original public addDriver API does not enforce inverse exclusivity. In the intended console workflow, a Driver object belongs to at most one live fleet (0..1 at the fleet end; exactly 1 while assigned). A detached driver can remain in undo history. If retaining the assignment’s conceptual 1-to-* domain association, add this lifecycle note rather than claiming the Java API enforces exclusive ownership.

## Complete deduplicated stored-reference association checklist

The following 51 rows cover all direct project-type fields, including project types inside collection/map fields. They do not count temporary variables or duplicated inherited fields. Existing-line status is based on the uploaded rendered PDF; “ADD” means the association itself is missing even if a dependency or other relation exists.

| ID | Source → Target | Field role(s) | At Source | At Target | Action | Note |
|---|---|---|---|---|---|---|
| A01 | AddDriverCommand → Driver | driver | 0..* | 0..1 | ADD (missing stored-reference association) | — |
| A02 | AddDriverCommand → DriverFactory | driverFactory | 0..* | 1 | REPLACE dependency with association | — |
| A03 | AddDriverCommand → FleetFactory | fleetFactory | 0..* | 0..1 | ADD (missing stored-reference association) | — |
| A04 | AddDriverCommand → VehicleFleet | fleet | 0..* | 0..1 | ADD (missing stored-reference association) | — |
| A05 | AddDriverCommandFactory → ConsoleInput | input | 0..* | 1 | ADD (missing stored-reference association) | — |
| A06 | AddDriverCommandFactory → DriverFactory | driverFactory | 0..* | 1 | ADD (missing stored-reference association) | — |
| A07 | AddDriverCommandFactory → FleetManager | manager | 0..* | 1 | ADD (missing stored-reference association) | — |
| A08 | ChangeNameCommand → MementoCaretaker | caretaker | 0..1 | 1 | ADD (refactor) | — |
| A09 | ChangeNameCommand → VehicleFleet | fleet | 0..* | 0..1 | ADD (missing stored-reference association) | — |
| A10 | ChangeNameCommandFactory → ConsoleInput | input | 0..* | 1 | ADD (missing stored-reference association) | — |
| A11 | ChangeNameCommandFactory → FleetManager | manager | 0..* | 1 | ADD (missing stored-reference association) | — |
| A12 | CommandFactoryRegistry → CommandFactory | factories | 0..* | 0..* | REPLACE dependency with association | — |
| A13 | CommandHistory → Command | redoStack, undoStack | 0..* | 0..* | ADD (refactor) | Merge undoStack and redoStack. Each is 0..*; successful normal requests occupy one stack at a time. Listing is oldest to newest; undo/redo uses the top. |
| A14 | CommandInvoker → CommandHistory | history | 0..* | 1 | ADD (refactor) | — |
| A15 | CreateFleetCommand → FleetFactory | factory | 0..* | 0..1 | ADD (missing stored-reference association) | — |
| A16 | CreateFleetCommand → FleetFactoryRegistry | fleetRegistry | 0..* | 1 | REPLACE dependency with association | — |
| A17 | CreateFleetCommand → VehicleFleet | fleet, previousCurrentFleet | 0..* | 0..2 | ADD (missing stored-reference association) | Merge fleet (0..1 before execution) and previousCurrentFleet (0..1): at most two distinct fleet objects. |
| A18 | CreateFleetCommandFactory → ConsoleInput | input | 0..* | 1 | ADD (missing stored-reference association) | — |
| A19 | CreateFleetCommandFactory → FleetFactoryRegistry | fleetRegistry | 0..* | 1 | ADD (missing stored-reference association) | — |
| A20 | CreateFleetCommandFactory → FleetManager | manager | 0..* | 1 | ADD (missing stored-reference association) | — |
| A21 | DeleteDriverCommand → Driver | driver, originalDriverOrder | 0..* | 0..* | ADD (missing stored-reference association) | Merge driver (0..1) and originalDriverOrder (0..*). Snapshot preserves object identities and order, including the deleted driver. |
| A22 | DeleteDriverCommand → VehicleFleet | fleet | 0..* | 0..1 | ADD (missing stored-reference association) | — |
| A23 | DeleteDriverCommandFactory → ConsoleInput | input | 0..* | 1 | ADD (missing stored-reference association) | — |
| A24 | DeleteDriverCommandFactory → FleetManager | manager | 0..* | 1 | ADD (missing stored-reference association) | — |
| A25 | DriverRoleMemento → Driver | driver | 0..* | 1 | REPLACE dependency with association | Snapshot retains the actual target object; equal Driver IDs in distinct fleets do not alias. |
| A26 | FleetFactoryRegistry → FleetFactory | factories | 0..* | 0..* | KEEP / complete labels | — |
| A27 | FleetManager → FleetFactory | fleetFactories | 0..* | 0..* | KEEP / complete labels | fleetFactories map values; several fleets can share one registered factory. |
| A28 | FleetManager → VehicleFleet | currentFleet, fleetFactories, fleets | 0..* | 0..* | KEEP / complete labels | Merge fleets (0..*), fleetFactories keys (0..*), and currentFleet (0..1). currentFleet is null or belongs to fleets. The one-argument addFleet overload does not add a factory mapping. |
| A29 | FleetNameMemento → VehicleFleet | fleet | 0..* | 1 | REPLACE dependency with association | Snapshot retains the actual target fleet and its old name. |
| A30 | ListCommand → CommandInvoker | invoker | 0..* | 1 | ADD (missing stored-reference association) | — |
| A31 | ListCommandFactory → CommandInvoker | invoker | 0..* | 1 | KEEP / complete labels | — |
| A32 | MementoCaretaker → Memento | memento | 0..* | 0..1 | ADD (refactor) | Initially empty; save stores one snapshot reference. restore delegates without inspecting concrete state. |
| A33 | ModifyRoleCommand → Driver | driver | 0..* | 0..1 | ADD (missing stored-reference association) | — |
| A34 | ModifyRoleCommand → FleetFactory | fleetFactory | 0..* | 0..1 | ADD (missing stored-reference association) | — |
| A35 | ModifyRoleCommand → MementoCaretaker | caretaker | 0..1 | 1 | ADD (refactor) | — |
| A36 | ModifyRoleCommand → VehicleFleet | fleet | 0..* | 0..1 | ADD (missing stored-reference association) | — |
| A37 | ModifyRoleCommandFactory → ConsoleInput | input | 0..* | 1 | ADD (missing stored-reference association) | — |
| A38 | ModifyRoleCommandFactory → FleetManager | manager | 0..* | 1 | ADD (missing stored-reference association) | — |
| A39 | RedoCommand → CommandInvoker | invoker | 0..* | 1 | ADD (missing stored-reference association) | — |
| A40 | RedoCommandFactory → CommandInvoker | invoker | 0..* | 1 | KEEP / complete labels | — |
| A41 | SetCurrentFleetCommand → FleetManager | manager | 0..* | 1 | ADD (missing stored-reference association) | — |
| A42 | SetCurrentFleetCommandFactory → ConsoleInput | input | 0..* | 1 | ADD (missing stored-reference association) | — |
| A43 | SetCurrentFleetCommandFactory → FleetManager | manager | 0..* | 1 | ADD (missing stored-reference association) | — |
| A44 | ShowAllFleetsCommand → FleetManager | manager | 0..* | 1 | ADD (missing stored-reference association) | — |
| A45 | ShowAllFleetsCommandFactory → FleetManager | manager | 0..* | 1 | ADD (missing stored-reference association) | — |
| A46 | ShowFleetCommand → FleetManager | manager | 0..* | 1 | ADD (missing stored-reference association) | — |
| A47 | ShowFleetCommandFactory → FleetManager | manager | 0..* | 1 | ADD (missing stored-reference association) | — |
| A48 | UndoCommand → CommandInvoker | invoker | 0..* | 1 | ADD (missing stored-reference association) | — |
| A49 | UndoCommandFactory → CommandInvoker | invoker | 0..* | 1 | KEEP / complete labels | — |
| A50 | UndoableCommand → FleetManager | manager | 0..* | 1 | KEEP / complete labels | — |
| A51 | VehicleFleet → Driver | drivers | 0..* | 0..* | KEEP / complete labels | Original field type is raw AbstractList; runtime elements are Driver. Normal application adds a new Driver for each membership. Unmodified public API itself does not enforce inverse uniqueness. |

JDK-only stored reference: ConsoleInput → java.util.Scanner, role scanner, target 1, inverse 0..*. Collections (Stack, List, Map, AbstractList) are already shown as attribute types and their element relationships above; separate container boxes are optional and unnecessary. String/primitive values are attributes, not project associations.

## Inheritance and interface realization

| Source | Kind | Target | Diagram direction |
|---|---|---|---|
| AddDriverCommand | Generalization | UndoableCommand | Solid line, hollow triangle at UndoableCommand |
| AddDriverCommandFactory | Realization | CommandFactory | Dashed line, hollow triangle at CommandFactory |
| BaseCommand | Realization | Command | Dashed line, hollow triangle at Command |
| ChangeNameCommand | Generalization | UndoableCommand | Solid line, hollow triangle at UndoableCommand |
| ChangeNameCommandFactory | Realization | CommandFactory | Dashed line, hollow triangle at CommandFactory |
| CommandExecutionException | Generalization | RuntimeException | Solid line, hollow triangle at RuntimeException |
| CreateFleetCommand | Generalization | UndoableCommand | Solid line, hollow triangle at UndoableCommand |
| CreateFleetCommandFactory | Realization | CommandFactory | Dashed line, hollow triangle at CommandFactory |
| DeleteDriverCommand | Generalization | UndoableCommand | Solid line, hollow triangle at UndoableCommand |
| DeleteDriverCommandFactory | Realization | CommandFactory | Dashed line, hollow triangle at CommandFactory |
| DeliveryVanFleet | Generalization | VehicleFleet | Solid line, hollow triangle at VehicleFleet |
| DeliveryVanFleetFactory | Realization | FleetFactory | Dashed line, hollow triangle at FleetFactory |
| DriverRoleMemento | Realization | Memento | Dashed line, hollow triangle at Memento |
| FleetNameMemento | Realization | Memento | Dashed line, hollow triangle at Memento |
| ListCommand | Generalization | BaseCommand | Solid line, hollow triangle at BaseCommand |
| ListCommandFactory | Realization | CommandFactory | Dashed line, hollow triangle at CommandFactory |
| ModifyRoleCommand | Generalization | UndoableCommand | Solid line, hollow triangle at UndoableCommand |
| ModifyRoleCommandFactory | Realization | CommandFactory | Dashed line, hollow triangle at CommandFactory |
| RedoCommand | Generalization | BaseCommand | Solid line, hollow triangle at BaseCommand |
| RedoCommandFactory | Realization | CommandFactory | Dashed line, hollow triangle at CommandFactory |
| RefrigeratedTruckFleet | Generalization | VehicleFleet | Solid line, hollow triangle at VehicleFleet |
| RefrigeratedTruckFleetFactory | Realization | FleetFactory | Dashed line, hollow triangle at FleetFactory |
| SetCurrentFleetCommand | Generalization | BaseCommand | Solid line, hollow triangle at BaseCommand |
| SetCurrentFleetCommandFactory | Realization | CommandFactory | Dashed line, hollow triangle at CommandFactory |
| ShowAllFleetsCommand | Generalization | BaseCommand | Solid line, hollow triangle at BaseCommand |
| ShowAllFleetsCommandFactory | Realization | CommandFactory | Dashed line, hollow triangle at CommandFactory |
| ShowFleetCommand | Generalization | BaseCommand | Solid line, hollow triangle at BaseCommand |
| ShowFleetCommandFactory | Realization | CommandFactory | Dashed line, hollow triangle at CommandFactory |
| UndoCommand | Generalization | BaseCommand | Solid line, hollow triangle at BaseCommand |
| UndoCommandFactory | Realization | CommandFactory | Dashed line, hollow triangle at CommandFactory |
| UndoableCommand | Generalization | BaseCommand | Solid line, hollow triangle at BaseCommand |

The uploaded hierarchy already represents the command and fleet families. Retain those directions. Add the two new Memento realizations. RuntimeException is a JDK type, so it may be documented separately rather than crowding the application overview.

## Creation dependencies

Each row is an explicit `new` of a project type. Draw a dashed open arrow from creator to created type, labelled «create». A creation dependency can coexist with a field association; the “one line per pair” rule applies to associations, not to distinct UML relationship kinds.

| Creator | Created project types |
|---|---|
| AddDriverCommand | CommandExecutionException |
| AddDriverCommandFactory | AddDriverCommand, CommandExecutionException |
| ChangeNameCommand | CommandExecutionException, FleetNameMemento, MementoCaretaker |
| ChangeNameCommandFactory | ChangeNameCommand |
| CommandInvoker | CommandHistory |
| CreateFleetCommand | CommandExecutionException |
| CreateFleetCommandFactory | CreateFleetCommand |
| DeleteDriverCommand | CommandExecutionException |
| DeleteDriverCommandFactory | DeleteDriverCommand |
| DeliveryVanFleetFactory | DeliveryVanFleet |
| DriverFactory | Driver |
| FleetManager | CommandExecutionException |
| ListCommandFactory | ListCommand |
| Main | AddDriverCommandFactory, ChangeNameCommandFactory, CommandFactoryRegistry, CommandHistory, CommandInvoker, ConsoleInput, CreateFleetCommandFactory, DeleteDriverCommandFactory, DeliveryVanFleetFactory, DriverFactory, FleetFactoryRegistry, FleetManager, ListCommandFactory, ModifyRoleCommandFactory, RedoCommandFactory, RefrigeratedTruckFleetFactory, SetCurrentFleetCommandFactory, ShowAllFleetsCommandFactory, ShowFleetCommandFactory, UndoCommandFactory |
| ModifyRoleCommand | CommandExecutionException, DriverRoleMemento, MementoCaretaker |
| ModifyRoleCommandFactory | CommandExecutionException, ModifyRoleCommand |
| RedoCommandFactory | RedoCommand |
| RefrigeratedTruckFleetFactory | RefrigeratedTruckFleet |
| SetCurrentFleetCommand | CommandExecutionException |
| SetCurrentFleetCommandFactory | SetCurrentFleetCommand |
| ShowAllFleetsCommandFactory | ShowAllFleetsCommand |
| ShowFleetCommand | CommandExecutionException |
| ShowFleetCommandFactory | ShowFleetCommand |
| UndoCommandFactory | UndoCommand |

## Other significant dependencies

| Client | Supplier | Reason |
|---|---|---|
| Main | CommandFactory | Calls createCommand() on the factory returned by the registry; local variable only. |
| Main | Command | Local created request passed to the invoker. |
| CommandInvoker | Command | execute(Command); no stored command or stack field. |
| CommandFactory | Command | createCommand return type. |
| FleetFactory | VehicleFleet | createFleet return type. |
| FleetUtil | VehicleFleet, Driver | Static lookup and membership-order operations; no retained references. |
| AddDriverCommand, ModifyRoleCommand, DeleteDriverCommand | FleetUtil | Static lookup, validation, or restoration calls. |
| AddDriverCommandFactory, ModifyRoleCommandFactory | VehicleFleet, FleetFactory | Local current-fleet and factory lookups used to prepare input prompts. |
| SetCurrentFleetCommand, ShowFleetCommand, ShowAllFleetsCommand | VehicleFleet | Local receiver references only. |
| ShowAllFleetsCommand | FleetFactory | Local lookup for display text. |
| Main and classes which throw/catch CommandExecutionException | CommandExecutionException | Exception use; optional in crowded behavioural views. |

Returned types or parameter types already represented by a stored association need not be drawn again as redundant dependency lines. Constructors accepting shared objects do not imply «create». Main’s Scanner construction is a dependency to the JDK, separate from project types.

## Per-type member audit and exact final signature catalogue

All declarations below were extracted from the final source and checked against the uploaded member text. For unchanged types, “matches” means the declared names/types/visibility match; static/abstract notation and relationships still follow the rules above. Constructors have no return type. The diagram must not invent extra domain methods to support Memento.

### AddDriverCommand

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-driverFactory : DriverFactory
-fleetFactory : FleetFactory
-driverID : String
-driverName : String
-role : int
-fleet : VehicleFleet
-driver : Driver

+AddDriverCommand(manager : FleetManager, factory : DriverFactory, driverID : String, driverName : String, role : int)
+execute() : void
+undo() : void
+redo() : void
+displayName() : String
```

### AddDriverCommandFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-manager : FleetManager
-driverFactory : DriverFactory
-input : ConsoleInput

+AddDriverCommandFactory(manager : FleetManager, driverFactory : DriverFactory, input : ConsoleInput)
+createCommand() : Command
-parseDriverDetails(information : String) : String[]
```

### BaseCommand

Abstract class. Member signatures match; keep abstract class, abstract execute/displayName, and concrete undo/redo/isUndoable.

```text
+undo() : void
+redo() : void
+isUndoable() : boolean
+execute() : void {abstract}
+displayName() : String {abstract}
```

### ChangeNameCommand

Concrete class. REPLACE -memento : FleetNameMemento with -caretaker : MementoCaretaker; public operations unchanged.

```text
-newName : String
-fleet : VehicleFleet
-caretaker : MementoCaretaker

+ChangeNameCommand(manager : FleetManager, newName : String)
+execute() : void
+undo() : void
+redo() : void
+displayName() : String
```

### ChangeNameCommandFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-manager : FleetManager
-input : ConsoleInput

+ChangeNameCommandFactory(manager : FleetManager, input : ConsoleInput)
+createCommand() : Command
```

### Command

Interface. Existing declared member names, parameter/result types and visibility match the final source.

```text
+execute() : void
+undo() : void
+redo() : void
+displayName() : String
+isUndoable() : boolean
```

### CommandExecutionException

Concrete class. Constructor matches; extends external JDK RuntimeException. Add generalization to an external RuntimeException node if JDK inheritance is shown.

```text
+CommandExecutionException(message : String)
```

### CommandFactory

Interface. Existing declared member names, parameter/result types and visibility match the final source.

```text
+createCommand() : Command
```

### CommandFactoryRegistry

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-factories : Map<String, CommandFactory>

+CommandFactoryRegistry()
+registerFactory(code : String, factory : CommandFactory) : void
+getFactory(code : String) : CommandFactory
```

### CommandHistory

Concrete class. ADD new class with both Stack<Command> fields and constructor, record, undo, redo, showUndoRedoList.

```text
-undoStack : Stack<Command>
-redoStack : Stack<Command>

+CommandHistory()
+record(command : Command) : void
+undo() : void
+redo() : void
+showUndoRedoList() : void
```

### CommandInvoker

Concrete class. REPLACE undoStack/redoStack fields with history : CommandHistory; ADD CommandInvoker(history : CommandHistory). Keep no-argument constructor and execute/undo/redo/showUndoRedoList signatures.

```text
-history : CommandHistory

+CommandInvoker()
+CommandInvoker(history : CommandHistory)
+execute(command : Command) : void
+undo() : void
+redo() : void
+showUndoRedoList() : void
```

### ConsoleInput

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-scanner : Scanner
-interactive : boolean

+ConsoleInput(scanner : Scanner, interactive : boolean)
+readLine(prompt : String) : String
+readInt(prompt : String) : Integer
```

### CreateFleetCommand

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-fleetRegistry : FleetFactoryRegistry
-factory : FleetFactory
-type : String
-fleetID : String
-fleetName : String
-fleet : VehicleFleet
-previousCurrentFleet : VehicleFleet

+CreateFleetCommand(manager : FleetManager, registry : FleetFactoryRegistry, type : String, fleetID : String, fleetName : String)
+execute() : void
+undo() : void
+redo() : void
+displayName() : String
```

### CreateFleetCommandFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-manager : FleetManager
-fleetRegistry : FleetFactoryRegistry
-input : ConsoleInput

+CreateFleetCommandFactory(manager : FleetManager, fleetRegistry : FleetFactoryRegistry, input : ConsoleInput)
+createCommand() : Command
```

### DeleteDriverCommand

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-driverID : String
-fleet : VehicleFleet
-driver : Driver
-originalDriverOrder : List<Driver>

+DeleteDriverCommand(manager : FleetManager, driverID : String)
+execute() : void
+undo() : void
+redo() : void
+displayName() : String
```

### DeleteDriverCommandFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-manager : FleetManager
-input : ConsoleInput

+DeleteDriverCommandFactory(manager : FleetManager, input : ConsoleInput)
+createCommand() : Command
```

### DeliveryVanFleet

Concrete class. Correct both role constants from type final int to type int, {readOnly}; they are instance fields, not static. Values remain 1 and 2.

```text
-VAN_DRIVER_ROLE : int {readOnly}
-COURIER_DRIVER_ROLE : int {readOnly}

+DeliveryVanFleet(fleetID : String)
+updateDriverRole() : void
+showFleet() : void
```

### DeliveryVanFleetFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
+createFleet(fleetID : String) : VehicleFleet
+getType() : String
+getFleetTypeName() : String
+getDriverRoleName(role : int) : String
```

### Driver

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-driverID : String
-driverName : String
-role : int

+Driver(driverID : String)
+getDriverID() : String
+getRole() : int
+setRole(role : int) : void
+getName() : String
+setName(name : String) : void
```

### DriverFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
+createDriver(driverID : String, driverName : String) : Driver
```

### DriverRoleMemento

Concrete class. REPLACE driverID : String with driver : Driver; constructor now DriverRoleMemento(driver : Driver); restore now has no parameter; ADD realization of Memento. oldRole : int remains.

```text
-driver : Driver
-oldRole : int

+DriverRoleMemento(driver : Driver)
+restore() : void
```

### FleetFactory

Interface. Existing declared member names, parameter/result types and visibility match the final source.

```text
+createFleet(fleetID : String) : VehicleFleet
+getType() : String
+getFleetTypeName() : String
+getDriverRoleName(role : int) : String
```

### FleetFactoryRegistry

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-factories : Map<String, FleetFactory>

+FleetFactoryRegistry()
+registerFactory(factory : FleetFactory) : void
+getFactory(type : String) : FleetFactory
```

### FleetManager

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-fleets : List<VehicleFleet>
-currentFleet : VehicleFleet
-fleetFactories : Map<VehicleFleet, FleetFactory>

+FleetManager()
+addFleet(fleet : VehicleFleet) : void
+removeFleet(fleet : VehicleFleet) : void
+addFleet(fleet : VehicleFleet, factory : FleetFactory) : void
+getFleetFactory(fleet : VehicleFleet) : FleetFactory
+getFleets() : List<VehicleFleet>
+findFleet(fleetID : String) : VehicleFleet
+setCurrentFleet(fleet : VehicleFleet) : void
+getCurrentFleet() : VehicleFleet
+getFleetCount() : int
+hasFleet(fleetID : String) : boolean
```

### FleetNameMemento

Concrete class. REPLACE fleetID : String with fleet : VehicleFleet; constructor now FleetNameMemento(fleet : VehicleFleet); restore now has no parameter; ADD realization of Memento. oldName : String remains.

```text
-fleet : VehicleFleet
-oldName : String

+FleetNameMemento(fleet : VehicleFleet)
+restore() : void
```

### FleetUtil

Concrete class. ADD : void to restoreDriverOrder; mark all four operations static. No attributes or inherited manager.

```text
+findDriver(fleet : VehicleFleet, driverID : String) : Driver {static}
+indexOfDriver(fleet : VehicleFleet, driverID : String) : int {static}
+getAllDrivers(fleet : VehicleFleet) : List<Driver> {static}
+restoreDriverOrder(fleet : VehicleFleet, order : List<Driver>) : void {static}
```

### ListCommand

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-invoker : CommandInvoker

+ListCommand(invoker : CommandInvoker)
+execute() : void
+displayName() : String
```

### ListCommandFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-invoker : CommandInvoker

+ListCommandFactory(invoker : CommandInvoker)
+createCommand() : Command
```

### Main

Concrete class. ADD private static final MENU_PROMPT : String (UML type String, {readOnly}); mark interactive and all three operations static. history is a main-local variable, not a field.

```text
-interactive : boolean {static}
-MENU_PROMPT : String {static, readOnly}

+main(args : String[]) : void {static}
-printHeader(manager : FleetManager) : void {static}
-showMenu() : void {static}
```

### Memento

Interface. ADD interface with +restore() : void.

```text
+restore() : void
```

### MementoCaretaker

Concrete class. ADD class with -memento : Memento, constructor, save, hasMemento and restore.

```text
-memento : Memento

+MementoCaretaker()
+save(memento : Memento) : void
+hasMemento() : boolean
+restore() : void
```

### ModifyRoleCommand

Concrete class. REPLACE -memento : DriverRoleMemento with -caretaker : MementoCaretaker; public operations unchanged.

```text
-driverID : String
-newRole : int
-fleet : VehicleFleet
-driver : Driver
-caretaker : MementoCaretaker
-fleetFactory : FleetFactory

+ModifyRoleCommand(manager : FleetManager, driverID : String, newRole : int)
+execute() : void
+undo() : void
+redo() : void
+displayName() : String
```

### ModifyRoleCommandFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-manager : FleetManager
-input : ConsoleInput

+ModifyRoleCommandFactory(manager : FleetManager, input : ConsoleInput)
+createCommand() : Command
```

### RedoCommand

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-invoker : CommandInvoker

+RedoCommand(invoker : CommandInvoker)
+execute() : void
+displayName() : String
```

### RedoCommandFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-invoker : CommandInvoker

+RedoCommandFactory(invoker : CommandInvoker)
+createCommand() : Command
```

### RefrigeratedTruckFleet

Concrete class. Correct both role constants from type final int to type int, {readOnly}; they are instance fields, not static. Values remain 1 and 2.

```text
-REFRIGERATED_TRUCK_DRIVER_ROLE : int {readOnly}
-COLD_CHAIN_DELIVERY_DRIVER_ROLE : int {readOnly}

+RefrigeratedTruckFleet(fleetID : String)
+updateDriverRole() : void
+showFleet() : void
```

### RefrigeratedTruckFleetFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
+createFleet(fleetID : String) : VehicleFleet
+getType() : String
+getFleetTypeName() : String
+getDriverRoleName(role : int) : String
```

### SetCurrentFleetCommand

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-manager : FleetManager
-fleetID : String

+SetCurrentFleetCommand(manager : FleetManager, fleetID : String)
+execute() : void
+displayName() : String
```

### SetCurrentFleetCommandFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-manager : FleetManager
-input : ConsoleInput

+SetCurrentFleetCommandFactory(manager : FleetManager, input : ConsoleInput)
+createCommand() : Command
```

### ShowAllFleetsCommand

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-manager : FleetManager

+ShowAllFleetsCommand(manager : FleetManager)
+execute() : void
+displayName() : String
```

### ShowAllFleetsCommandFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-manager : FleetManager

+ShowAllFleetsCommandFactory(manager : FleetManager)
+createCommand() : Command
```

### ShowFleetCommand

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-manager : FleetManager

+ShowFleetCommand(manager : FleetManager)
+execute() : void
+displayName() : String
```

### ShowFleetCommandFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-manager : FleetManager

+ShowFleetCommandFactory(manager : FleetManager)
+createCommand() : Command
```

### UndoCommand

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-invoker : CommandInvoker

+UndoCommand(invoker : CommandInvoker)
+execute() : void
+displayName() : String
```

### UndoCommandFactory

Concrete class. Existing declared member names, parameter/result types and visibility match the final source.

```text
-invoker : CommandInvoker

+UndoCommandFactory(invoker : CommandInvoker)
+createCommand() : Command
```

### UndoableCommand

Abstract class. Member signatures match; keep abstract class, #manager protected, abstract undo/redo. isUndoable is concrete and returns true; {true} may be a note, not a type/modifier.

```text
#manager : FleetManager

+UndoableCommand(manager : FleetManager)
+isUndoable() : boolean
+undo() : void {abstract}
+redo() : void {abstract}
```

### VehicleFleet

Abstract class. Member names/types/visibility match. Keep abstract class and abstract updateDriverRole/showFleet. Preserve raw AbstractList in diagram because source is protected.

```text
-fleetID : String
-fleetName : String
-drivers : AbstractList

+VehicleFleet(fleetID : String)
+getFleetID() : String
+getName() : String
+setName(name : String) : void
+addDriver(driver : Driver) : void
+dropDriver(driver : Driver) : void
+getDrivers() : Iterator<Driver>
+updateDriverRole() : void {abstract}
+showFleet() : void {abstract}
```

## Generated diagram files and use in the report

All views reference the same 47-type source model. Repeating a class box across different views does not create duplicate UML classifiers. The complete association checklist is authoritative for the merged role/cardinality notes.

| File stem | Purpose |
|---|---|
| 00-all-types | Complete 47-type catalogue grouped by responsibility; members and relationships intentionally omitted. |
| 01-architecture | Main factory/invoker/history/memento collaboration overview. |
| 02-domain | Domain, fleet manager, fleet factories, DriverFactory and utility signatures. |
| 03-history-memento | Detailed history, caretaker, memento and target members with updated relationships. |
| 04-mutating-commands | Five mutation command member compartments and their abstract base; stored targets shown as members. |
| 05-query-history-commands | Six non-undoable commands and their stored receiver associations. |
| 06-command-factories | Five mutation factories and their created commands. |
| 07-command-factories | Six selection/display/history factories and created commands. |
| 08-startup-support | Main, input, registry, invoker and supporting classes. |

Compact report figures 09-summary-dispatch, 10-summary-memento and 11-summary-fleets provide selected collaboration summaries at a readable page size; their abbreviated signatures are explained by the full views. Final_Class_Diagram.pdf combines the nine catalogue/detail views 00–08. Each stem is delivered as SVG, PNG and PDF. Use SVG/PDF for zooming and standalone review, and embed selected views at readable size rather than shrinking the entire 47-class model to a single A4 page. `.dot` files retain the editable diagram description. The source catalogue supplies the exact members if a printed view is too small.

## Source provenance

The diagram model is generated from the final 47 Java files. The four protected classes retain the uploaded/remote baseline contents; no unavailable teacher-source archive is claimed as evidence. See the project validation record for compiler/runtime tests.
