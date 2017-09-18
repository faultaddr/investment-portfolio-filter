#!/usr/bin/python
# -*- coding:utf-8 -*-
class bag():
    def __init__(self,quota,rate):
        self.quota = quota
        self.rate = rate

    def knapsack(self, full_quota):#quota rate存数组
        result = [[0 for i in range(full_quota+1)] for i in range(len(self.rate)+1)]#结果集
        count = len(self.quota)#产品个数
        for n in range(1,count+1):#n当前最大产品个数
            for quota in range(0,full_quota+1):
                if self.quota[n-1]<=quota:#第n个背包的重量为quota[n-1]判断是否小于允许容量
                    if result[n-1][quota]<(result[n-1][quota-self.quota[n-1]]+self.rate[n-1]): #如果当前产品在相同金额下价值更高
                        result[n][quota]=result[n-1][quota-self.quota[n-1]]+self.rate[n-1]
                    else:
                        result[n][quota]=result[n-1][quota]
                else:
                    result[n][quota] =result[n-1][quota]
        #for perrow in result:
        #    print perrow
        return result

    def find_which(self,full_quota):
        result = self.knapsack(full_quota)
        i= len(result)-1
        j = len(result[i])-1
        select = []
        while i >=1:
            while j>=1:
                if result[i-1][j]!=result[i][j]:#说明当前行的东西拿了
                    select.append(i)
                    #print '产品'+ str(i)
                    j = j -self.quota[i-1]
                    i = i - 1
                    break
                else:
                    i = i -1
        return select

# usage
# if __name__ =='__main__':
#     sort_instance = bag([2,2,6,5,4,1,2,7,5,7,4],[6,3,5,4,6,1,4,7,3,6,1])#金额，收益率初始化
#     sort_instance.find_which(30)#定义用户拥有的总钱数