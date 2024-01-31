@echo off

REM Set the output directory
set OUT_DIR=out

REM Execute all .class files in the output directory
for %%f in (%OUT_DIR%\*.class) do (
    java -cp %OUT_DIR% %%~nf
)

REM Pause to keep the console window open
pause