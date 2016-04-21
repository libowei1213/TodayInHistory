# -*- coding: utf_8 -*-

import re
import urllib
import time

def getHtml(url):
    page = urllib.urlopen(url)
    html = page.read()
    return html
    

def processStr(str):
    return re.compile('<[\s\S]+?>').sub('',str)
    
    
def main():    
    date_str=time.strftime("%m‘¬%d»’",time.localtime())
    base_url="https://zh.wikipedia.org/zh-cn/%s"%date_str
    
    html=getHtml(base_url)
    
    reg=r'</h2>\s+?<ul>([\s\S]+?)<h2>'
    html_list=re.findall(re.compile(reg),html)
    
    for html_str in html_list:
        print processStr(html_str)
    
    

if __name__=="__main__":
    
    main()