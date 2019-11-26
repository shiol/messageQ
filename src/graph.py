#!/usr/bin/env python
# coding: utf-8


import matplotlib.pyplot as plt
import matplotlib.animation as animation
from itertools import count
import time
import random
import csv
from datetime import datetime

arquivo1 = open("apple.txt", "r")
arquivo2 = open("microsoft.txt", "r")

listDate = []
list1 = []
list2 = []

plt.style.use('fivethirtyeight')

fig, ax = plt.subplots()

def animate(i):
    data1 = arquivo1.read()
    data2 = arquivo2.read()
    dataArray1 = data1.split('\n')
    dataArray2 = data2.split('\n')
    
    for eachLine in dataArray1 or dataArray2:
        if len(eachLine) > 1:
            now = datetime.now()
            current_time = now.strftime("%H:%M:%S")
            listDate.append(current_time)
    
    for eachLine in dataArray1:
        if len(eachLine) > 1:
            list1.append(float(eachLine))
            
    for eachLine in dataArray2:
        if len(eachLine) > 1:
            list2.append(float(eachLine))
    ax.clear()
    
    ax.plot(listDate,list1, lw=3, label = 'Apple')
    ax.plot(listDate,list2, lw=3, label = 'Microsoft')
    
    plt.xticks(listDate, listDate, rotation='30')
    plt.tick_params(axis='both', which='major', labelsize=15)
    
    plt.legend()
    plt.tight_layout()
    
ani = animation.FuncAnimation(fig, animate, interval=1000)
plt.show()




