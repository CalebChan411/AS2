# SDFMS report delivery

## Latest student submission review

The current student-uploaded Word report and source ZIP were reviewed against the assignment on 2026-11-13. Download the revised named files from `report/final-review/`; see `report/final-review/Final_Review_ZH.md` for verified results and remaining diagram notation changes. The named ZIP now includes the required run.bat and the test input/output records referenced by the report. The 47 Java sources are unchanged. Cover details and the student's portrait Part 2 diagram are already present in this reviewed report.

The earlier canonical report/template and combined bundles below are retained as historical artifacts; use the named files in `report/final-review/` for this final review.

---

Code: main commit `bf10becbdceb873e77c5c36b0c8d59d5cebaed4b` (47 Java files).

The report branch contains the same application source plus the report and submission artifacts. The report has been restructured to match the requested five Parts:

1. Assumptions Regarding the Problem Context — written.
2. Application Design with Class Diagram — one landscape page reserved for the student's final diagram.
3. Discussion of the Design Patterns — explanation, 12 inserted actual-source code screenshots, exact source ranges and annotations.
4. Test Plan and Test Cases — 25 self-designed cases plus the assignment sample, 33 genuine actual-result images.
5. Well-documented Source Code — every one of the 47 Java files, copied in full with readable monospaced formatting.

Appendix A supplies the separately required User Guide. The cover is centered horizontally and vertically; the Contents lists all five Part titles plus User Guide and References, with verified page numbers.

Files: `report/SDFMS_Report.docx`, `report/SDFMS_Report.pdf`, `report/Part3_Screenshot_Checklist_ZH.md`, `report/Submission_Checklist_ZH.txt`. `SDFMS_Submission.zip` combines source, run.bat, the report, checklists, evidence and a single-page diagram reference.

The report currently has 128 pages, mostly full source and per-case evidence. Current code was recompiled and all 26 scenario outputs were rerun and matched the validated evidence; all 166 independent output assertions remain satisfied. Figures are actual application output, not rendered expected answers.

Before submission: complete name/number/date, insert the Part 2 diagram, remove placeholder instructions, update Word fields/contents (Ctrl+A, F9), and export a new PDF. Part 3 is filled; cover personal details and the Part 2 diagram remain for student completion. Windows execution was not tested here; Linux compilation/execution were tested. Source files and run.bat must be submitted separately as well as listed in the report.


## Simplified diagram view

`report/diagram-no-association/` contains a single-page view with all Association lines omitted. Main has only three usage dependencies: CommandFactory, Command and VehicleFleet. The view retains snapshot creation by commands and interface use by MementoCaretaker; the Chinese explanation distinguishes creation from stored-reference relationships. The full application and report source remain the same.

Word download: https://github.com/CalebChan411/AS2/raw/refs/heads/report/report/SDFMS_Report.docx


## Part 3 code screenshot collection

`report/part3-code-screenshots/Part3_Code_Screenshots.docx` and its 13-page PDF contain Figures 3.1-3.12 in the original report order. The same 12 screenshots are already inserted into the 128-page main report. Part 2 remains the overall class-diagram position; no duplicate diagrams are required in Part 3. The images show verified actual Java source excerpts in a source-code viewer, with original filenames/line numbers, not runtime results or simulated IDE screenshots. Independent PNGs, the source mapping and provenance hashes are included.
