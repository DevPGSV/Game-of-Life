@echo off
setlocal enabledelayedexpansion enableextensions

for /f "tokens=*" %%A in ('where javac.exe') do (set javacPath=%%A)
if "%javacPath%"=="" echo javac.exe not found&exit /b 1
call :basepath jdk "!javacPath!"
if not exist "%jdk%/javac.exe" echo javac.exe not found&exit /b 1

if "%1"=="compile" call :compile&goto end
if "%1"=="runClass" call :runClass&goto end
if "%1"=="makeJar" call :makeJar&goto end
if "%1"=="runJar" call :runJar&goto end
if "%1"=="help" call :help&goto end
echo Invalid argument
goto end


:compile
(
    if not exist bin mkdir bin
    "%jdk%/javac.exe" -d bin esRuedaPGSV\GameOfLife\*.java && (echo Compiled successfully) || (echo Error on compilation)
    EXIT /B %ERRORLEVEL%
)

:runClass
(
    if not exist "bin\esRuedaPGSV\GameOfLife\Main.class" (
        echo "class files not found. Creating..."
        call :compile
        if not !ERRORLEVEL!==0 echo Could not compile. Exiting. &EXIT /B 1
    )
    pushd bin
    "%jdk%/java.exe" -cp . esRuedaPGSV.GameOfLife.Main
    popd
)

:makeJar
(
    if not exist "bin\esRuedaPGSV\GameOfLife\Main.class" (
        echo "class files not found. Creating..."
        call :compile
        if !ERRORLEVEL!==1 echo Could not compile. Exiting. &EXIT /B 1
    )
    "%jdk%/jar.exe" cmvf "MANIFEST.MF" "Game of Life.jar" -C bin/ .
    exit /b %ERRORLEVEL%
)

:runJar
(
    if not exist "Game of Life.jar" (
        echo "Game of Life.jar" not found. Creating...
        call :makeJar
        if not !ERRORLEVEL!==0 echo Could not make jar. Exiting. &EXIT /B 1
    )
    "%jdk%/java.exe" -jar "Game of Life.jar"
    exit /b
)

:basepath
(
   set "%~1=%~dp2"
   exit /b 0
)

:help

echo Usage: %~nx0 ^<options^>
echo Options:
echo   compile          Creates .class from .java files
echo   runClass         Runs from .class files
echo   makeJar          Creates a .jar from .class files (creates .class if they dont exist)
echo   runJar           Run .jar (creates it if it doesnt exists)
echo   help             Show help

exit /b 0

:end
exit /b