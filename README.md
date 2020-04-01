Google Trends Analyzer
========

Two small programs whose aim is to collect Google Trends data and draw them in a synthetic graph !

With the actual configuration, data have been collected since 2017 for each word written in the file words.csv, but you can change this at the end of the csv-generator.py file !

Python: Data Mining
--------
```
 - csv-generator.py: Get all data and store them in the folder csv-google-trends-output
 - Package embedded: pytrends
 - Package that needs to be installed: pytz, pandas => pip3 install {package_name}
```

Java: Calculation and Drawing
--------
```
 - No package needed !
 - Maven needed ! => sudo apt install maven
 - Calculate weight of each key word written in the file words.csv
 - Draw graphs for each key word and group related to
```

Scripts for Linux Distributions
--------

### start.sh :
```
 - To run it: ./start.sh
 - Clean/remove olds data and graphs
 - Run python data miner
 - Run java program which draws graphs
```

### Add new key words :
```
You have to add them to the words.csv file:
 - word column is the key word that you want to search with Google Trends.
 - ratio is the weight/importance that you give to this word (0 up to 5).
 - related column is the big word that the current word is related to. Example: BTCUSD is related to Bitcoin
```

### Output Examples
<h1 align="center">
  <img src="https://i.imgur.com/4sKfg5j.png">
</h1>
<h1 align="center">
  <img src="https://i.imgur.com/kw6b0yC.png">
</h1>
