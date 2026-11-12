# Main 与 Memento 连线说明（配合无 Association 版本）

源码基准：AS2 main bf10bec。

## Word 下载

报告文件已经保存到 GitHub report 分支：
https://github.com/CalebChan411/AS2/blob/report/report/SDFMS_Report.docx

直接下载：
https://github.com/CalebChan411/AS2/raw/refs/heads/report/report/SDFMS_Report.docx

## Main 的三条依赖

本张精简图只画 Main → CommandFactory、Main → Command、Main → VehicleFleet 的普通使用 Dependency。省略 Main 在启动时逐个 new 共享对象和具体工厂的创建依赖。Main 不保存这些项目对象为成员字段，因此不用为它们画 Association。

## Command 为什么又连 Caretaker、又连具体 Memento？

两种连接分别表达创建、使用或持有，不能只看两个框之间是否已有线。

实际角色修改代码：

```java
caretaker.save(new DriverRoleMemento(driver));
```

这里 new DriverRoleMemento(driver) 是在 ModifyRoleCommand 内执行，所以 Command → DriverRoleMemento 的 «create» Dependency 正确。随后 save 把这个快照交给 Caretaker 保存。

```java
caretaker.restore();
```

undo 时 Command 调用 Caretaker；Caretaker 再通过 Memento 接口调用具体快照的 restore()。DriverRoleMemento 恢复原 Driver 的 role；FleetNameMemento 恢复原 VehicleFleet 的 name。

| 连线 | 无 Association 版本的表示 | 原因 |
|---|---|---|
| ModifyRoleCommand → MementoCaretaker | «create» Dependency，注明 use save/restore | 构造命令时 new caretaker，执行和撤销时调用它 |
| ChangeNameCommand → MementoCaretaker | 同上 | 每个命令有自己的 caretaker |
| ModifyRoleCommand → DriverRoleMemento | «create» Dependency，注明 pass to caretaker | 首次修改前在 Command 中 new 快照 |
| ChangeNameCommand → FleetNameMemento | 同上 | 在 Command 中 new 名称快照 |
| MementoCaretaker → Memento | 普通 Dependency：save(Memento) / restore() | Caretaker 只使用通用接口 |
| DriverRoleMemento → Memento | implements / Realization | 虚线空心三角指接口 |
| FleetNameMemento → Memento | implements / Realization | 虚线空心三角指接口 |

完整图下一步补 Association 时：

- Command → MementoCaretaker：caretaker 字段，目标端 1。
- MementoCaretaker → Memento：memento 字段，目标端 0..1。
- DriverRoleMemento → Driver：driver 字段，目标端 1。
- FleetNameMemento → VehicleFleet：fleet 字段，目标端 1。

当前两个 Command 没有保存具体 Memento 的字段，因此不画 Command → 具体 Memento 的 Association；保留的是 «create» Dependency。Caretaker 自己没有 new 具体快照，所以不画 Caretaker «create» DriverRoleMemento / FleetNameMemento。

## 本版图的范围

本图保留全部47个项目类型和一个JDK RuntimeException框，只有继承、实现、创建依赖和使用依赖，全部Association暂时省略。Main仅画指定的3条使用依赖。其余95条关系均有编号。部分只靠字段持有关系连接的类在本版没有对应线路，完整图绘制Association时再补；这个版本用于分步检查关系类型。
