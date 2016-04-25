# -*- coding: utf_8 -*-

import re
import urllib
import datetime
import os
import MySQLdb


def savedb(data):
    sql="insert into event values(null,%s,%s,%s,%s)"
    conn=MySQLdb.connect(host="localhost",user="root",passwd="libowei",db="today",charset="utf8")
    cur=conn.cursor()
    cur.execute(sql,data)
    cur.close()
    conn.close()
    

    