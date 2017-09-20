#!/usr/bin/python
# -*- coding:utf-8 -*-
from similarity import *
import numpy as np
import sys

history = pd.read_csv(u'交易数据.csv')
history = history.as_matrix()
users = pd.read_csv(u'用户属性.csv')
us = users.as_matrix()
production = pd.read_csv(u'产品.csv')
production = production.as_matrix()

if __name__ =='__main__':
    money = int(sys.argv[2])
    if sys.argv[1]=='new':
        new = np.loadtxt(u'new_u.txt')
        index, total, Group = to_new(new,money,us,history,production)
        with open(u'new.txt','w') as f:
            f.write(str(total)+'\n')
            for i in index:
                f.write(str(i[0])+' '+str(i[1])+'\n')
    else:
        u_id = sys.argv[3]
        index, total, Group = to_old(u_id,money,history,production)
        with open(u'old.txt','w') as f:
            f.write(str(total)+'\n')
            for i in index:
                f.write(str(i[0])+' '+str(i[1])+'\n')
    picture(Group, users)