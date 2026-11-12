@echo off
cd /d "%~dp0"
javac *.java
if errorlevel 1 exit /b %errorlevel%
java Main
exit /b %errorlevel%
