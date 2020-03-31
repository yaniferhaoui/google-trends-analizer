import sys
import csv
import datetime

from pytrends.request import TrendReq

class GeneratorCSV(object):

    def __init__(self, CSVMain, firstDate):
        self.CSVMain = CSVMain
        self.listCSVFile = []
        self.firstDate = firstDate

    def getDatas(self, indexWord):
        now = datetime.datetime.now()
        pytrends = TrendReq(hl="en-US", tz=360)
        kw_list = [self.listCSVFile[indexWord]]
        pytrends.build_payload(kw_list, cat=0, timeframe=self.firstDate+" "+str(now.year)+"-"+str(now.month)+"-"+str(now.day), geo="", gprop="")
        return pytrends.interest_over_time()

    def getListCSVFile(self):
        with open("./CSV/"+self.CSVMain+".csv") as csv_file:
            csv_reader = csv.reader(csv_file, delimiter=',')
            line_count = 0
            for row in csv_reader:
                if line_count == 0:
                    print("Column names are "+str(row[0])+", "+str(row[1])+", "+str(row[2]))
                    line_count += 1
                else:
                    print(str(row[0])+" "+str(row[1])+" "+str(row[2]))
                    self.listCSVFile.append(str(row[0]))
                    line_count += 1
            print("Number of rows : "+str(line_count-1))

    def generateCSVFile(self):
        for i in range(len(self.listCSVFile)):
            rw = self.getDatas(i)
            with open("./CSV/csv-google-trends-output/"+str(self.listCSVFile[i])+".csv", "w") as csvfile:
                filewriter = csv.writer(csvfile, delimiter=',',
                                        quotechar='|', quoting=csv.QUOTE_MINIMAL)
                filewriter.writerow(["date", "interest"])
                for index, row in rw.iterrows():
                    print(index, row[0])
                    filewriter.writerow([str(index), str(row[0])])

obj = GeneratorCSV("words", "2017-01-01")
obj.getListCSVFile()
obj.generateCSVFile()
