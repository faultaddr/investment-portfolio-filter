#!/usr/bin/python
#-*-coding=utf-8-*-
import scipy.spatial.distance as spd
import numpy as np

# 老用户协同过滤
# 输入交易记录总表，K
# 输出每个用户前K个相似用户
def filter_h(history,K):
	temp = spd.pdist(history,'cosine')
	temp[range(len(temp)),range(len(temp))] = 0
	re = np.argsort(temp)
	return np.array(re[:,0:K])

# 新用户协同过滤
# 输入一个新用户属性，老用户全部属性，K
# 输出新用户前K个相似用户
def filter_n(new,users,K):
	temp = spd.cdist(new,users,'cosine')
	temp[0] = 0
	re = np.argsort()
	return np.array(re[:,0:K])
