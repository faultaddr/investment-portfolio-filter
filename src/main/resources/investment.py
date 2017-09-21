#!/usr/bin/python
# -*- coding:utf-8 -*-
def bag(items,money):
    """ 
    items[0] -> price\n
    itmes[1] -> rate\n
    return {index : num}, total
    """
    num = len(items[0])
    last = [-1] * (money+1)
    full = [0] * (money+1)
    for i in range(num):
        for j in range(items[0][i], money+1):
            if full[j]<full[j-items[0][i]]+items[0][i]*items[1][i]:
                full[j] = full[j-items[0][i]]+items[0][i]*items[1][i]
                last[j] = i
    result = money
    dic = {}
    for i in range(num):
            dic[i] = 0
    while money>0 and last[money]>=0:
        dic[last[money]] = dic[last[money]] + 1
        money = money - items[0][last[money]]
    return dic, full[result]*0.01
    
# usage
# if __name__ =='__main__':
#     dic, total = bag([[5,4,7,2,6],[12,3,10,3,6]],8)#金额，收益率初始化, 总金额