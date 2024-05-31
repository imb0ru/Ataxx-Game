@echo off
chcp 65001
call .\gradlew.bat build
java -jar build\libs\ataxx-all.jar
