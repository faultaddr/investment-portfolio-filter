#!/usr/bin/python
#-*-coding=utf-8-*-
from scipy.spatial import distance as spd
import numpy as np

# 老用户协同过滤
# 输入交易记录总表，K
# 输出每个用户前K个相似用户
def filter_o(id,history,K):
    temp = 1 - spd.cdist(np.array([history[id]]),history,'cosine') # cdist is dissimilarity
    temp[0][id] = 0
    re = np.argsort(temp[0])
    return re[0,:K]

# 新用户协同过滤
# 输入一个新用户属性，老用户全部属性，K
# 输出新用户前K个相似用户
def filter_n(new,users,K):
    temp = 1 - spd.cdist(np.array([new]),users,'cosine') # cdist is dissimilarity
    re = np.argsort(temp[0])
    return re[0,:K]
