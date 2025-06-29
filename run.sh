#!/bin/bash
cp -r JeuPuissance4/vue/images class/JeuPuissance4/vue
javac @compile.list -d ./class

cd class && java JeuPuissance4.Controleur

cd ..

