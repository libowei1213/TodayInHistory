# -*- coding: utf_8 -*-

import re
import urllib
import datetime
import os
from mysql import savedb

def getHtml(url):
    page = urllib.urlopen(url)
    html = page.read()
    return html
    

def getList():
    
    list = []
    date = datetime.date(2016,1,1)
    i = 1
    while i <= 366:
       date_str = date.strftime('%m月%d日')
       list.append(date_str)
       date += datetime.timedelta(days=1)
       i += 1
    return list
    
def processHtml(html):
    reg_list=[]
    reg_list.append('<[\s\S]+?>') #标签
    reg_list.append('\[[\s\S]+?]') #引用、来源请求 
    for reg in reg_list:
        html = re.compile(reg).sub('',html)    
    return html.split('\n');   
    
        
    
def saveFile(date,content):
    file = open('data/' + date + '.txt', 'a')
    file.write(content)
    file.close()
    
def main():


    reg_list=[]
    reg_list.append(r'<h2><[\s\S]+?>大事记[\s\S]+?</h2>\s+?<ul>([\s\S]+?)<h2>')
    reg_list.append(r'<h2><[\s\S]+?>出生[\s\S]+?</h2>\s+?<ul>([\s\S]+?)<h2>')
    reg_list.append(r'<h2><[\s\S]+?>逝世[\s\S]+?</h2>\s+?<ul>([\s\S]+?)<h2>')

    list = getList()
    for date in list:
        url="https://zh.wikipedia.org/zh-cn/%s"%date
        html=getHtml(url)
        
        
        year = ''
        i = 0
        for reg in reg_list:
            match=re.compile(reg).search(html)
            if match:
                text = match.group(1)
                for line in processHtml(text):
                    if line.strip()=='':
                        continue
                    match = re.compile(r'^前\d{1,4}年：|^\d{1,4}年：').search(line)
                    if match:
                        year = match.group()
                        line = line.replace(year,'',1)
                    if line.strip()=='':
                        continue
                    data=[]
                    data.append(i)
                    data.append(year.replace('：',''))
                    data.append(date)
                    data.append(line)
                    
                    savedb(data)
                    #print data
            i += 1        

                    

if __name__=="__main__":
    
    main()