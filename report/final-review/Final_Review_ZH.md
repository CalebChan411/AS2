# 最终提交检查

检查对象：用户最终上传的同名 ZIP、Word，以及 Word 中保存的完整 class diagram。按原始 assignment 说明核对。原始附件未覆盖。

**结论：代码与报告主体检查通过；原 ZIP 缺少必需的 run.bat，已经在修正版中补齐。Diagram 仍有下列记法细节，应在原画图文件中修正后替换 Word Part 2 的图片。不能把当前图片描述为“已经逐项完全无误”。**

## 已核实

| Assignment 要求 | 检查结果 |
| --- | --- |
| 封面：programme title/code、module title/code、姓名、学号 | 已填写，Chan Hei Lam / 250406422，信息居中 |
| 12 项系统功能及指定命令代码 | 26 个独立输入场景重新执行通过，166 项输出检查通过 |
| 11 项非退出功能使用 Command | 对应 Command / Factory 均存在并用于 Main dispatch |
| Factory 创建 Command、VehicleFleet、Driver | 已按实际源码核对 |
| 五类修改支持 undo / redo | 场景测试和状态检查通过 |
| Modify-role / change-name 使用 Memento | 两类具体 Memento、接口、Caretaker 和 Command 调用存在 |
| 四个保留类的属性和操作 | 四份源文件与最初上传的 AS archive 内容相同 |
| OCP | 通过 FleetFactory / VehicleFleet / Registry 扩展；固定菜单和两角色输入的扩展限制已在 3.4 如实说明 |
| Report 五个 Part | 全部存在；Part 5 的 47 份源码与 ZIP 逐项一致 |
| 每个 test case 有实际结果截图 | 26 个场景均有截图；33 张运行截图与原始执行截图逐字节相同；本次运行输出也与记录一致 |
| User Guide | Appendix A 已包含，属于 assignment 提交要求 |
| Windows run.bat | 原 ZIP 缺失，修正版已放在 ZIP 根目录；语句对应已验证的编译/运行操作，未在 Windows 主机执行 |
| 目录、页码、版面 | 导出核对 128 页，目录页码对应；Part 2 保持纵向；没有内容超出页面边界 |

文件名与封面 programme/module/student number/name 一致。现有 assignment 原文没有要求必须恰好提交三个独立文件；run.bat 可以和 Java 源码一起放入 ZIP。

## 已完成的修正

1. 修正版 ZIP 加入 `run.bat`，保留原始 47 份 Java 文件的全部字节。
2. 补入 Part 4 提到的实际测试输入、输出、断言结果和源文件 SHA-256，位于 ZIP 的 `evidence/`。
3. 修正 Part 3 仍称截图“由学生插入、位置留空”的旧文字。
4. 将 Part 2 图片放回纵向页面的文字范围，移除过宽的占位表格。全部 46 张嵌入图片的原始字节保留，未修改图中文字或连线。

## Diagram 尚需收尾

1. **Static 标记：**给 Main 的 `interactive`、`MENU_PROMPT`、`main(...)`、`printHeader(...)`、`showMenu()` 加下划线；给 FleetUtil 的四个方法加下划线。Main 的 `MENU_PROMPT` 还可标 `{readOnly}`。四个 fleet role 常量在代码中是实例 `final` 字段，不能因此当作 static 加下划线。
2. **只读常量的类型：**DeliveryVanFleet 的 `VAN_DRIVER_ROLE` / `COURIER_DRIVER_ROLE`，RefrigeratedTruckFleet 的 `REFRIGERATED_TRUCK_DRIVER_ROLE` / `COLD_CHAIN_DELIVERY_DRIVER_ROLE`，将 `final int` 规范地写成 `int` 并附 `{readOnly}`，保留原来的 private 可见性及值 1 / 2。
3. **返回类型：**FleetUtil 的 `restoreDriverOrder(fleet : VehicleFleet, order : List<Driver>)` 补 `: void`。
4. **Implements 箭头：**使用虚线和空心三角，三角指向 interface；图片中部分实现箭头有蓝色填充，建议统一去掉填充。Extends 使用实线和空心三角，方向保持不变。

47 个 class/interface 的主要继承、实现连接没有发现明显方向错误。Main 的主要 dependency 已有；FleetManager 和 FleetFactoryRegistry 不需要互连。此前选择省略的部分 association 不要求为凑连线重新加入。

这张图放进 A4 后成员文字很小，普通比例打印时难以阅读。保留可放大的完整原图；在原软件完成以上修改后重新导出清晰 PDF/PNG，再替换 Word 中的图。当前修正版 Word 保存的是用户原图，未自动改动这些 UML 细节。

## 下载内容

- 同名 `.zip`：47 个原始 Java 文件 + `run.bat` + 实际测试记录。
- 同名 `.docx`：修正版报告，姓名学号已保留，仍需替换修正后的 diagram 图片。
- 同名 `.pdf`：对应报告的版面核对预览，不是额外的强制提交项。
- `Original_ClassDiagram.png`：从用户 Word 中完整提取的原始高清图，供核对；UML 标注未自动修改。

测试能够核实已执行的场景和当前文档一致性，不能等同于对所有可能输入、教师评分或 Windows 环境的绝对保证。
