import os
import gzip
import datetime
import re
import multiprocessing
import time
import logging

class LogAnalyzerWorker():
    def doWork(self,filenames):
        print filenames
        for file in filenames:
            print datetime.datetime.now(),file
            try:
                g=gzip.open(file,"r")
                f=g.readlines()
                g.close()
            except:
                print "cannot process",file
                logging.exception("Cannot process gz file")
                continue
            for line in f:
                tokens=line.split(" ")
                for token in tokens:
                    if token=="sprit":
                        pass
                    

class LogAnalyzerMaster():
    def __init__(self,inputfolder):
        self.inputfolder=inputfolder
        self.thread_num=16
        self.get_file_list()

    def get_file_list(self):
        files=[]
        for inputfolder in self.inputfolder:
            inpfiles=os.listdir(inputfolder)
            for file in inpfiles:
                files.append(os.path.join(inputfolder,file))
        self.filelist=files
        
    def get_partitioned_filelist(self):
        filesperprocess=len(self.filelist)/self.thread_num
        if filesperprocess==0:
            filesperprocess=1
        chunks=[]
        iterator=0
        while iterator<len(self.filelist):
            chunks.append(self.filelist[iterator:iterator+filesperprocess])
            iterator+=filesperprocess
        return chunks
    
    def create_process(self):
        print datetime.datetime.now()
        filelists=self.get_partitioned_filelist()
        print filelists
        processes=[]
        for filelist in filelists:
            slave=LogAnalyzerWorker()
            p=multiprocessing.Process(target=slave.doWork,args=(filelist,))
            processes.append(p)
            p.start()
        for prc in processes:
            prc.join()
        for prc in processes:
            prc.terminate()
        print datetime.datetime.now()

filename = [];
l=LogAnalyzerMaster(filename)
l.create_process()

