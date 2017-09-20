#!/usr/bin/python
#-*-coding=utf-8-*-
import numpy as np
from investment import bag
from scipy.spatial import distance as spd
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib
matplotlib.style.use('ggplot')

# 老用户协同过滤
# 输入交易记录总表，K
# 输出每个用户前K个相似用户
def filter_o(u_id,users,history,K):
    temp = spd.cdist(np.array([history[u_id]]),history,'cosine') # cdist is dissimilarity
    temp[0][u_id] = 1
    re = np.argsort(temp[0])[:K]
    temp = temp*0.75 + 0.25*spd.cdist(np.array([users[u_id]]),users,'cosine')
    temp[0][u_id] = 1
    nonz = np.argsort(temp[0])[:K]
    return re, nonz

# 新用户协同过滤
# 输入一个新用户属性，老用户全部属性，K
# 输出新用户前K个相似用户
def filter_n(new,users,K):
    temp = spd.cdist(np.array([new]),users,'cosine') # cdist is dissimilarity
    re = np.argsort(temp[0])[:K]
    return re, re

def picture(index, users):
    selected = users.iloc[index]
    # Gender
    selected[selected.columns[0]].value_counts().plot.pie(label='Gender',title='Gender')
    plt.axis('equal')
    plt.savefig('Gender.jpg')
    plt.close()
    # Family Members
    selected[selected.columns[3]].plot.hist(title='Family Members',color='deepskyblue')
    plt.savefig('FamilyMembers.jpg')
    plt.close()
    # Age
    selected[selected.columns[1]].plot.hist(title='Age', color='mediumpurple')
    plt.savefig('Age.jpg')
    plt.close()
    # WorkTime
    selected[selected.columns[7]].plot.hist(title='Work Time')
    plt.savefig('WorkTime.jpg')
    plt.close()
    # Income
    selected[selected.columns[11]].plot.hist(title='Income',color='gold',bins=15)
    plt.savefig('Income.jpg')
    plt.close()

def to_old(u_id,money,users,history,production):
    Simi_users, Group = filter_o(u_id,users,history,int(len(history)*0.15)) 
    buy_set = set(np.argwhere(history[u_id]==1)[:,0])
    unbuy_set = set()
    for user in Simi_users:
        for index in range(len(history[user])):
            if history[user][index]==1 and index not in buy_set:
                unbuy_set.add(index)
    unbuy_index = list(unbuy_set)
    unbuy_rate = [production[i][1] for i in unbuy_index]
    unbuy_price = [int(production[i][3]) for i in unbuy_index]
    dic, total = bag([unbuy_price,unbuy_rate],money)
    return [[unbuy_index[i], dic[i]] for i in dic.keys()], total, Group
    
def to_new(new,money,users,history,production):
    Simi_users, Group = filter_n(new,users,int(len(users)*0.15))
    unbuy_set = set()
    for user in Simi_users:
        for index in range(len(history[user])):
            if history[user][index]==1:
                unbuy_set.add(index)
    unbuy_index = list(unbuy_set)
    unbuy_rate = [production[i][1] for i in unbuy_index]
    unbuy_price = [int(production[i][3]) for i in unbuy_index]
    dic, total = bag([unbuy_price,unbuy_rate],money)
    return [[unbuy_index[i], dic[i]] for i in dic.keys()], total, Group
