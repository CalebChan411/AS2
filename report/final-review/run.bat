@echo off
setlocal
cd /d "%~dp0"
javac -encoding UTF-8 *.java
if errorlevel 1 exit /b 1
java Main
exit /b %errorlevel%
