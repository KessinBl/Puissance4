@echo off
REM Copier le dossier images
xcopy /E /I /Y "JeuPuissance4\vue\images" "class\JeuPuissance4\vue"

REM Compiler les fichiers Java
javac @compile.list -d .\class

REM Se déplacer dans le dossier class et exécuter le programme
cd class
java JeuPuissance4.Controleur

REM Revenir au dossier précédent
cd ..
