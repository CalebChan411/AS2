# 无 Association 版本连线索引

全部 47 个项目类型均在同一张画布；额外的 RuntimeException 是 JDK 类型。PDF 只有一页。

图例：H=extends，R=implements，C=«create»，U=普通Dependency。连线交叉处白色断口不表示连接。类框中下划线为static，斜体为abstract。

## extends

| 编号 | 来源 | 目标 | 角色／说明 |
|---|---|---|---|
| H01 | AddDriverCommand | UndoableCommand | — |
| H02 | ChangeNameCommand | UndoableCommand | — |
| H03 | CommandExecutionException | RuntimeException | — |
| H04 | CreateFleetCommand | UndoableCommand | — |
| H05 | DeleteDriverCommand | UndoableCommand | — |
| H06 | DeliveryVanFleet | VehicleFleet | — |
| H07 | ListCommand | BaseCommand | — |
| H08 | ModifyRoleCommand | UndoableCommand | — |
| H09 | RedoCommand | BaseCommand | — |
| H10 | RefrigeratedTruckFleet | VehicleFleet | — |
| H11 | SetCurrentFleetCommand | BaseCommand | — |
| H12 | ShowAllFleetsCommand | BaseCommand | — |
| H13 | ShowFleetCommand | BaseCommand | — |
| H14 | UndoCommand | BaseCommand | — |
| H15 | UndoableCommand | BaseCommand | — |

## implements

| 编号 | 来源 | 目标 | 角色／说明 |
|---|---|---|---|
| R01 | AddDriverCommandFactory | CommandFactory | — |
| R02 | BaseCommand | Command | — |
| R03 | ChangeNameCommandFactory | CommandFactory | — |
| R04 | CreateFleetCommandFactory | CommandFactory | — |
| R05 | DeleteDriverCommandFactory | CommandFactory | — |
| R06 | DeliveryVanFleetFactory | FleetFactory | — |
| R07 | DriverRoleMemento | Memento | — |
| R08 | FleetNameMemento | Memento | — |
| R09 | ListCommandFactory | CommandFactory | — |
| R10 | ModifyRoleCommandFactory | CommandFactory | — |
| R11 | RedoCommandFactory | CommandFactory | — |
| R12 | RefrigeratedTruckFleetFactory | FleetFactory | — |
| R13 | SetCurrentFleetCommandFactory | CommandFactory | — |
| R14 | ShowAllFleetsCommandFactory | CommandFactory | — |
| R15 | ShowFleetCommandFactory | CommandFactory | — |
| R16 | UndoCommandFactory | CommandFactory | — |

## create Dependency

| 编号 | 来源 | 目标 | 角色／说明 |
|---|---|---|---|
| C01 | AddDriverCommand | CommandExecutionException | — |
| C02 | AddDriverCommandFactory | AddDriverCommand | — |
| C03 | AddDriverCommandFactory | CommandExecutionException | — |
| C04 | ChangeNameCommand | CommandExecutionException | — |
| C05 | ChangeNameCommand | FleetNameMemento | — |
| C06 | ChangeNameCommand | MementoCaretaker | — |
| C07 | ChangeNameCommandFactory | ChangeNameCommand | — |
| C08 | CommandInvoker | CommandHistory | — |
| C09 | CreateFleetCommand | CommandExecutionException | — |
| C10 | CreateFleetCommandFactory | CreateFleetCommand | — |
| C11 | DeleteDriverCommand | CommandExecutionException | — |
| C12 | DeleteDriverCommandFactory | DeleteDriverCommand | — |
| C13 | DeliveryVanFleetFactory | DeliveryVanFleet | — |
| C14 | DriverFactory | Driver | — |
| C15 | FleetManager | CommandExecutionException | — |
| C16 | ListCommandFactory | ListCommand | — |
| C37 | ModifyRoleCommand | CommandExecutionException | — |
| C38 | ModifyRoleCommand | DriverRoleMemento | — |
| C39 | ModifyRoleCommand | MementoCaretaker | — |
| C40 | ModifyRoleCommandFactory | CommandExecutionException | — |
| C41 | ModifyRoleCommandFactory | ModifyRoleCommand | — |
| C42 | RedoCommandFactory | RedoCommand | — |
| C43 | RefrigeratedTruckFleetFactory | RefrigeratedTruckFleet | — |
| C44 | SetCurrentFleetCommand | CommandExecutionException | — |
| C45 | SetCurrentFleetCommandFactory | SetCurrentFleetCommand | — |
| C46 | ShowAllFleetsCommandFactory | ShowAllFleetsCommand | — |
| C47 | ShowFleetCommand | CommandExecutionException | — |
| C48 | ShowFleetCommandFactory | ShowFleetCommand | — |
| C49 | UndoCommandFactory | UndoCommand | — |

## usage Dependency

| 编号 | 来源 | 目标 | 角色／说明 |
|---|---|---|---|
| U01 | AddDriverCommand | FleetUtil | — |
| U02 | AddDriverCommandFactory | Command | — |
| U03 | AddDriverCommandFactory | FleetFactory | — |
| U04 | AddDriverCommandFactory | VehicleFleet | — |
| U05 | ChangeNameCommandFactory | Command | — |
| U06 | CommandFactory | Command | — |
| U07 | CommandHistory | CommandExecutionException | — |
| U08 | CommandInvoker | Command | — |
| U09 | CommandInvoker | CommandExecutionException | — |
| U10 | CreateFleetCommandFactory | Command | — |
| U11 | DeleteDriverCommand | FleetUtil | — |
| U12 | DeleteDriverCommandFactory | Command | — |
| U13 | DeliveryVanFleetFactory | VehicleFleet | — |
| U14 | FleetFactory | VehicleFleet | — |
| U15 | FleetUtil | Driver | — |
| U16 | FleetUtil | VehicleFleet | — |
| U17 | ListCommandFactory | Command | — |
| U18 | Main | Command | — |
| U20 | Main | CommandFactory | — |
| U21 | Main | VehicleFleet | — |
| U22 | ModifyRoleCommand | FleetUtil | — |
| U23 | ModifyRoleCommandFactory | Command | — |
| U24 | ModifyRoleCommandFactory | FleetFactory | — |
| U25 | ModifyRoleCommandFactory | VehicleFleet | — |
| U26 | RedoCommandFactory | Command | — |
| U27 | RefrigeratedTruckFleetFactory | VehicleFleet | — |
| U28 | SetCurrentFleetCommand | VehicleFleet | — |
| U29 | SetCurrentFleetCommandFactory | Command | — |
| U30 | ShowAllFleetsCommand | FleetFactory | — |
| U31 | ShowAllFleetsCommand | VehicleFleet | — |
| U32 | ShowAllFleetsCommandFactory | Command | — |
| U33 | ShowFleetCommand | VehicleFleet | — |
| U34 | ShowFleetCommandFactory | Command | — |
| U35 | UndoCommandFactory | Command | — |
| U36 | MementoCaretaker | Memento | save(Memento) / restore() |
