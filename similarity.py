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
def filter_o(id,users,history,K):
    temp = spd.cdist(np.array([history[id]]),history,'cosine') # cdist is dissimilarity
    temp = temp*0.75 + 0.25*spd.cdist(np.array([users[id]]),users,'cosine')
    temp[0][id] = 1
    nonz = np.argwhere(temp[0]<=0.752)[:,0]
    re = np.argsort(temp[0])[:K]
    return [re,nonz]

# 新用户协同过滤
# 输入一个新用户属性，老用户全部属性，K
# 输出新用户前K个相似用户
def filter_n(new,users,K):
    temp = spd.cdist(np.array([new]),users,'cosine') # cdist is dissimilarity
    nonz = np.argwhere(temp[0]<=0.01)[:,0]
    re = np.argsort(temp[0])[:K]
    return [re,nonz]

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
    selected[selected.columns[11]].plot.hist(title='Income',color='springgreen',bins=15)
    plt.savefig('Income.jpg')
    plt.close()

def to_old(u_id,money,history,production):
    [Simi_users, Group] = filter_o(u_id,history,int(len(history)*0.15)) 
    buy_set = set(np.argwhere(history[u_id]==1)[:,0])
    unbuy_set = set()
    for user in Simi_users:
        for index in range(len(history[user])):
            if history[user][index]==1 and history[user][index] not in buy_set:
                unbuy_set.add(history[user][index])
    unbuy_index = list(unbuy_set)
    unbuy_rate = [production[i][1] for i in unbuy_index]
    unbuy_money = [production[i][3] for i in unbuy_index]
    sort_instance = bag(unbuy_money,unbuy_rate)
    index = sort_instance.find_which(money)
    return [[unbuy_index[i] for i in index], Group]
    

def to_new(new,money,users,history,production):
    [Simi_users, Group] = filter_n(new,users,int(len(users)*0.15))
    unbuy_set = set()
    for user in Simi_users:
        for index in range(len(history[user])):
            if history[user][index]==1:
                unbuy_set.add(history[user][index])
    unbuy_index = list(unbuy_set)
    unbuy_rate = [production[i][1] for i in unbuy_index]
    unbuy_money = [production[i][3] for i in unbuy_index]
    sort_instance = bag(unbuy_money,unbuy_rate)
    index = sort_instance.find_which(money)
    return [[unbuy_index[i] for i in index], Group]
