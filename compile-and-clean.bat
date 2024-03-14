@echo off

REM Set the source directory and output directory
set SRC_DIR=src\rules
set OUT_DIR=out

REM Remove existing .class files in the output directory
del /Q %OUT_DIR%\*.class

REM Compile Java files to the output directory
javac -d %OUT_DIR% %SRC_DIR%\*.java

REM Check if compilation was successful
if %errorlevel% equ 0 (
    echo Compilation successful.
) else (
    echo Compilation failed.
    exit /b 1
)