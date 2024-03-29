@echo off

rem Compile Java files
javac -d out src\example\*.java

rem Check if compilation was successful
if %errorlevel% neq 0 (
    echo Compilation failed.
    exit /b %errorlevel%
)

rem Execute Driver class with arguments
java -cp out example.Driver src\example\inputfile.txt src\example\outputfile.txt

rem Delete .class files
del /q out\example\*.class

rem Prompt the user to press a key
echo. 
echo Press any key to continue...
pause >nul 