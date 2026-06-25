@echo off
REM Script per compilare ed eseguire CineMAX da Windows CMD

echo ===== CINEMAX - Avvio Progetto =====
echo.

REM Compilazione di tutti i file Java
echo Compilazione in corso...
javac -d bin src\cinemax\*.java src\cinemax\model\*.java src\cinemax\service\*.java src\cinemax\utils\*.java

if errorlevel 1 (
    echo Errore durante la compilazione!
    pause
    exit /b 1
)

echo Compilazione completata!
echo.

REM Esecuzione del programma
echo Avvio programma...
echo.
java -cp bin cinemax.CineMax

pause
