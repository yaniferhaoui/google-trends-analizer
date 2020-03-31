rm CSV/csv-google-trends-output/*
python3 py/csv-generator.py
rm -f -r Graphs
mkdir Graphs
mkdir Graphs/related
mkdir Graphs/word
mvn compile
mvn exec:java
