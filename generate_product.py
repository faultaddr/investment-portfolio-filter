#!/usr/bin/python
# -*- coding:utf-8 -*-
from BeautifulSoup import BeautifulSoup as bs
import urllib2
import os
import threading
import time
import numpy as np
import random
import sys  # 把默认编码设置为中文

reload(sys)
sys.setdefaultencoding('utf8')

score = {'AAA': 5, 'AA': 4, 'A': 3, 'BBB': 2, 'BB': 1}


# todo: 改写成爬虫类
def getHtml(url):
    heads = {'Host': 'www.avmoo.net', 'Connection': 'keep-alive', 'Cache-Control': 'max-age=0',
             'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
             'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36',
             'Accept-Language': 'zh-CN,zh;q=0.8', 'Referer': 'http://www.itu8.com',
             'User-Agent': 'Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.14) Gecko/20110221 Ubuntu/10.10 (maverick) Firefox/3.6.14'}
    opener = urllib2.build_opener(urllib2.HTTPCookieProcessor())
    urllib2.install_opener(opener)
    req = urllib2.Request(url)
    opener.addheaders = heads.items()
    try:
        html = opener.open(req).read()
    except IOError:
        time.sleep(10)
        html = opener.open(req).read()
    finally:
        return html


# get actress' urls
def get(html):
    s = bs(html)
    items = []
    for j in range(8):
        i = s.body.contents[1].contents[2].contents[2].contents[1].contents[0].contents[4].tbody.contents[j]
        moneys = i.contents[4].contents[0].contents[1].span.text[6:].split(',')
        money = ''
        for num in range(len(moneys)):
            money = money + moneys[num]
        if u'月' in i.contents[3].span.text:
            items.append(
                [score[i.contents[1].strong.text], i.contents[2].strong.text, int(i.contents[3].strong.text) * 30,
                 money])
        items.append([score[i.contents[1].strong.text], i.contents[2].strong.text, i.contents[3].strong.text, money])
    print items
    return items


if __name__ == '__main__':
    re = []
    num = input('number: \n')
    for x in range(num - 1):
        url = "https://www.touzhijia.com/platform/bids?page=%s&limit=8&rate=0&period=0&level=0&sort=0&platId=0" % (
        x + 1)
        h = getHtml(url)
        re.extend(get(h))
        print "page:%s" % (x + 1)
    print 'all done!!'
    np.save('data', np.array(re))
