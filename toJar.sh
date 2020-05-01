#!/bin/bash
cd .
rm *.jar
javac *.java
jar cf AnimalCrossingGuide.jar *.class
jar cfe AnimalCrossingGuide.jar AnimalCrossingGuide *.class
