@echo off

REM Set the source directory and output directory
set SRC_DIR=src
set OUT_DIR=out

REM Remove existing .class files in the output directory
del /Q %OUT_DIR%\*.class

REM Initialize a variable to track compilation errors
set "ERRORS="

REM Compile Java files within src\recs directory
for /r %SRC_DIR%\recs %%G in (*.java) do (
    javac -d %OUT_DIR% "%%G"
    if not errorlevel 0 (
        echo Compilation failed for %%G.
        set ERRORS=!ERRORS! %%G
    )
)

REM Compile Java files within src\rules directory
for /r %SRC_DIR%\rules %%G in (*.java) do (
    javac -d %OUT_DIR% "%%G"
    if not errorlevel 0 (
        echo Compilation failed for %%G.
        set ERRORS=!ERRORS! %%G
    )
)

REM Check if there were any compilation errors
if "%ERRORS%"=="" (
    echo Compilation successful.
) else (
    echo Compilation failed for the following files:%ERRORS%
)

PAUSE