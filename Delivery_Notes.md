# SDFMS delivery

Code repository: https://github.com/CalebChan411/AS2

- `main`: 47 application Java sources, commit `bf10becbdceb873e77c5c36b0c8d59d5cebaed4b`.
- `report`: the same code plus this report, final diagrams, evidence, Windows `run.bat` and the submission ZIP.
- `report/SDFMS_Report.docx`: editable English report; PDF is its rendered review copy.
- `report/Final_Class_Diagram.pdf`: final source-derived diagram catalogue; SVG/DOT detail views are in `report/diagrams/`.
- `report/Diagram_Audit.md`: all member signatures and 51 deduplicated association relationships, including endpoint multiplicities and drawing corrections.
- `SDFMS_Submission.zip`: 47 Java files, `run.bat`, Word/PDF report and final diagram. Extract the folder, open a Windows Command Prompt in it and run `run.bat`.

Validation: baseline and final compiled with OpenJDK 21.0.13 on Linux. 26/26 console scenarios passed (25 independently designed cases plus assignment sample), 166/166 independent output assertions; all baseline/final outputs byte-identical. State suite: 9 groups, 65 assertions passed. Every console scenario has actual fresh-run terminal screenshots. The four protected classes are byte-identical to both available baselines. Test inputs, raw outputs, capture timestamps and checksums are included in `report/evidence/`.

Before Moodle submission: fill student name, student number and submission date on the report cover, then regenerate its PDF if edited. Review the assignment declaration and final files personally. Windows execution has not been tested here; Linux compile/execution was tested. The uploaded Visual Paradigm PDF was audited, not edited; newly generated final diagrams and the manual correction checklist are supplied. The original teacher Java archive was not supplied, so byte preservation is certified against the uploaded ZIP and inspected AS baseline only.

No external credentials or application services are needed. All runtime data and history are held only in memory for the current session.
