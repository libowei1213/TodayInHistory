# -*- coding: utf_8 -*-

import re
import urllib
import datetime
import os

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
    for reg in reg_list:
        html = re.compile(reg).sub('',html)    
    return html.split('\n');   
        
    
def saveFile(date,content):
    file = open('data/' + date + '.txt', 'a')
    file.write(content)
    file.close()
    
def main():

    list = getList()
    for str in list:
        url="https://zh.wikipedia.org/zh-cn/%s"%str
        html=getHtml(url)
        reg=r'</h2>\s+?<ul>([\s\S]+?)<h2>'
        html_list=re.findall(re.compile(reg),html)
        year=''
        for line in processHtml(html_list[0]):
            if line.strip()=='':
                continue
            match = re.compile(r'^[前]?\d{1,4}年：').match(line)
            if match:
                year = match.group()
            else:
                line = year + line
            line = line.replace(year,'',1)
            if line.strip()=='':
                continue
            line = year + line + '\n'
            saveFile(str,line)
        print str
    

if __name__=="__main__":
    
    main()