# Teaching-material and assignment review

## Scope and evidence

The five lecture PDFs (OCP, Adapter, Factory, Command, Memento), all Java source files in the supplied Lab 4, Lab 4a, Lab 5, Lab 6 and Lab 6 Part 2B answer ZIPs, the assignment DOCX text and its embedded domain-class diagram, and the handoff text were inspected. Extracted lecture text and Lab source are under `task-work/materials`, outside application checkouts. All three Lab question DOC files were converted with antiword and their complete extracted text was read. Embedded pictures in those legacy DOC files are not rendered by antiword; source examples and surrounding requirements were used for this pattern review.

This is a document/source review, not an execution result. The assignment and Lab instructions are reference requirements; they do not themselves authorize extra uploads or unrelated changes.

## Approved refactor: compatible with supplied teaching

1. **Memento interface and parameterless restore.** Lab 6 Part 2B `Memento.java` declares `public interface Memento { public void restore(); }`. `AccountMemento` and `CustomerMemento` implement that interface, bind the original object in their constructors and restore through the stored reference. `Caretaker.undo()` casts to `Memento` and calls `restore()` without `instanceof`. This directly supports the approved common interface and object-bound concrete snapshots.
2. **Preserve domain classes and use their accessors.** Memento lecture pp. 19–20 saves a `Shape` reference and values from `getX()`, `getY()`, `getWidth()` and `getHeight()`, then restores through `setLocation()` and `setSize()`. Therefore reading `Driver.getRole()` / `VehicleFleet.getName()` and restoring through their existing setters is supported by the supplied material; adding memento methods to protected domain classes is unnecessary. The earlier conceptual slide about originator-managed state must be read together with this concrete example.
3. **Separate history from per-operation snapshot storage.** Command lecture pp. 15–19 demonstrates request objects saved to a history stack and undo delegated to those requests. Lab 5 Q3 has separate command and redo stacks and reuses the original request object on redo. A `CommandHistory` owning two `Stack<Command>` fields, with `CommandInvoker` delegating history operations, preserves this structure. A per-command `MementoCaretaker` holding one snapshot is an adaptation combining Command and Memento. It is not a claim that Lab 6 itself uses one snapshot or the exact class name `MementoCaretaker`.
4. **Successful operations only.** Lab examples illustrate patterns with limited error handling. The approved design records only successful undoable execution, clears redo only after a successful new mutation, and moves history entries only after undo/redo succeeds. These are compatible correctness refinements and preserve the existing application's behavior. Do not copy Lab 5's eager factory push or pre-execution redo clearing, which could record failed actions.
5. **Snapshot timing and redo.** Lab 6 Part I and II save before mutation. Save the old role/name once before the first successful change; undo restores it; redo reapplies the captured new value without new input, factories or another snapshot. The TextEditor lecture pp. 22–26 instead demonstrates a history of complete post-edit states, whose top is the current state. Its pop-then-restore-new-top algorithm is not appropriate for the application's history of commands. Explain the chosen model rather than mixing these algorithms.
6. **No deep copy or extra pattern needed.** The role is an `int` and the name is an immutable `String`; copying these values plus retaining the original object reference suffices. Although Lab 6 Part 2B clones a Hashtable for a different collection example, this application needs no serialization, reflection or complex copying. Adapter is taught but is neither required nor implemented by this refactor.

## Factory / OCP explanation for the report

- Lab 5 Q3 declares `CommandFactory.createCommand(): Command`, registers one factory per command code in a map, and creates a fresh request on each selection. Preserve the eleven existing command/factory pairs.
- Lab 5 `ShapeFactory` implementations create different concrete shapes; its `PointFactory` creates a non-polymorphic product. These support the application's fleet-factory interface and simple `DriverFactory` respectively.
- The Factory lecture pp. 11–15 and 48 uses a broad delegated-creation meaning of Abstract Factory, while pp. 62–65 distinguishes families of related products. Calling the design **Factory pattern** and explaining its concrete roles is accurate without overclaiming a family of multiple product types.
- OCP lecture p. 15 explicitly says "No program can be 100% closed" and calls for strategic closure. Registering a new fleet factory in `Main` is consistent with leaving business commands, the invoker and history unchanged. Do not claim zero edits anywhere are required.

## Formal assignment requirements confirmed

- Eleven non-exit functions use Command; input codes are `c s a m d sf sa cn u r l x`.
- Factory or Abstract Factory creates Command, VehicleFleet and Driver objects.
- Create/add/modify/delete/rename support undo and redo; modify-role and rename specifically use Memento.
- A report must include assumptions, design with a class diagram, explanation of applied patterns, test plan/cases and documented source. Submission additionally requires a user guide and Windows `run.bat` compiling/running the Java program.
- Every report test case requires a real result screen capture. Expected-output illustrations, old grader scores and aggregate passes are insufficient.
- The sample is reference UI; the assignment explicitly permits a different UI while keeping input commands. The handoff additionally requests byte-for-byte output preservation relative to the actual code baseline, so compare identical run modes.
- The cover needs programme/module titles and codes, student name and student number. Unconfirmed identity fields must remain placeholders.
- Due Friday 13 November 2026, at or before 16:30 Hong Kong time.

## Original-class provenance limitation

The DOCX archive contains `word/media/image1.png` (the four-class UML) and **no embedded original Java or ZIP**. No `SDFMS_Java_Prototype_StrictOriginalClasses.zip` is among the provided uploads. Thus hashes can establish preservation against the supplied AS ZIP and inspected remote baseline, but cannot prove equality with unavailable teacher-provided source. Do not describe the latest ZIP as independently authenticated teacher originals.

The embedded UML requires the listed fields and public operations of VehicleFleet, DeliveryVanFleet, RefrigeratedTruckFleet and Driver; `VehicleFleet` and its `updateDriverRole` / `showFleet` operations are abstract. It depicts a one-fleet to many-driver aggregation. The approved work should not change any bytes of those four files.

## Lab question-sheet cross-check

Lab 4 asks for the distinction between Factory Method and Abstract Factory. Its examples use reflection, but that is an example mechanism, not a requirement to add reflection to SDFMS. Lab 5 explicitly combines commands, factories and a Stack with reverse-order undo, including restoration of a deleted object at its original list index. Lab 6 Part I A asks for Stack instead of Vector; Part I B adds redo and explicitly does not require Command/Factory for that exercise. Part II demonstrates restoring individual account state and customer collections in reverse order, then redo. These standalone exercises do not override the SDFMS assignment's explicit requirement to combine all three patterns. The proposed sole command-history stacks plus per-command snapshot holder implement that combined workflow without a second global history.

There is a contextual wording inconsistency in the Lab 6 question sheet: Part I A says not to use a package; Part I B says to move Caretaker into Memento's package. This applies to those exercises and does not require changing the default-package SDFMS application. Some supplied answer ZIPs are older variants (Vector history, undo only) rather than a full implementation of every revised question. The final report should cite concrete mechanisms actually shown, not claim its architecture is identical to all answer files.
