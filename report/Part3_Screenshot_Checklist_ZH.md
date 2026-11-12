# Part 3 代码截图对应表

12张图已按原报告Figure3.1–3.12顺序生成，并已填入完整报告Part3。截图内容为当前真实Java源码，包含原文件名和行号；来源是源码查看页面，不是伪装的IDE或运行结果截图。

Part2放完整class diagram；Part3不必重复贴整张类图，可选局部模式图；Part4才是运行结果截图。

| 报告图号 | 对应源码范围 | 内容 |
|---|---|---|
| 3.1 | Command.java 2-8; UndoableCommand.java 2-16 | Command contract and reversible requests |
| 3.2 | CommandInvoker.java 13-23 | Execute first, record only after success |
| 3.3 | CommandHistory.java 14-38 | Record and undo history |
| 3.4 | CommandHistory.java 40-56 | Redo without recreating a request |
| 3.5 | CommandFactory.java 1-4; CreateFleetCommandFactory.java 14-23; Main.java 47-55 | Command factories and dispatch |
| 3.6 | FleetFactory.java 1-7; DeliveryVanFleetFactory.java 2-5; RefrigeratedTruckFleetFactory.java 2-5 | Fleet creation through a common factory interface |
| 3.7 | DriverFactory.java 1-8; AddDriverCommand.java 20-30 | Driver creation from actual request data |
| 3.8 | Memento.java 1-4; MementoCaretaker.java 1-20 | Common Memento interface and caretaker |
| 3.9 | DriverRoleMemento.java 1-14; FleetNameMemento.java 1-14 | Snapshots bound to the original objects |
| 3.10 | ModifyRoleCommand.java 17-36 | Capture before modifying a driver role |
| 3.11 | ModifyRoleCommand.java 38-50 | Reapply the requested role with rollback |
| 3.12 | ChangeNameCommand.java 13-31 | Fleet-name Memento in execute, undo and redo |

完整报告现在只需补封面个人资料和Part2最终class diagram；插图后Ctrl+A、F9更新目录，再导出PDF。截图单独Word用于查看/复制，完整报告Word中已插入同样12张图。
