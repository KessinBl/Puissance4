#!/bin/bash

javac @compile.list -d ./class

cd class && java JeuPuissance4.Controleur

cd ..

