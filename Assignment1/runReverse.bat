rem THIS BATCH FILE MUST BE INSERTED IN WS/CGI-BIN

@echo off

echo Content-type: text/plain

echo.

rem GET "GET REQUEST" PARAMETER

FOR  %%i IN (%QUERY_STRING%) DO set VAR1=%%i

rem RUN REVERSE

FOR /f %%i IN ('java " 'YOUR_PATH' \Reverse.java" %VAR1%') DO set VAR2=%%i

rem PARSE RESULT

set RESULT=%VAR2:+= %

set RESULT=%RESULT:02%= %

echo %RESULT%

rem pause