@echo off
setlocal

set "BASE_DIR=%~dp0"
set "MAVEN_VERSION=3.9.9"
set "MAVEN_DIR=%BASE_DIR%.mvn"
set "MAVEN_HOME=%MAVEN_DIR%\apache-maven-%MAVEN_VERSION%"
set "MAVEN_ZIP=%MAVEN_DIR%\apache-maven-%MAVEN_VERSION%-bin.zip"
set "MAVEN_URL=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/apache-maven-%MAVEN_VERSION%-bin.zip"

if not exist "%MAVEN_HOME%\bin\mvn.cmd" (
    powershell -NoProfile -ExecutionPolicy Bypass -Command ^
        "$ErrorActionPreference = 'Stop';" ^
        "$mavenDir = '%MAVEN_DIR%';" ^
        "$mavenHome = '%MAVEN_HOME%';" ^
        "$mavenZip = '%MAVEN_ZIP%';" ^
        "$mavenUrl = '%MAVEN_URL%';" ^
        "New-Item -ItemType Directory -Force -Path $mavenDir | Out-Null;" ^
        "if (-not (Test-Path -LiteralPath $mavenZip)) { Invoke-WebRequest -Uri $mavenUrl -OutFile $mavenZip; }" ^
        "Expand-Archive -LiteralPath $mavenZip -DestinationPath $mavenDir -Force;" ^
        "if (-not (Test-Path -LiteralPath (Join-Path $mavenHome 'bin\mvn.cmd'))) { throw 'Maven local nao foi instalado corretamente.' }"

    if errorlevel 1 (
        echo Falha ao baixar ou preparar o Maven local.
        exit /b 1
    )
)

call "%MAVEN_HOME%\bin\mvn.cmd" %*
