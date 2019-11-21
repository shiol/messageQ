import matplotlib.pyplot as plt
import matplotlib.animation as animation
import time

import csv
arquivo1 = open("apple.txt", "r")
arquivo2 = open("microsoft.txt", "r")
for row in arquivo1:
    print(row)
for row in arquivo2:
    print(row)

list1 = []
list2 = []

fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)


def animate(i):
    data1 = arquivo1.read()
    data2 = arquivo2.read()
    dataArray1 = data1.split('\n')
    dataArray2 = data2.split('\n')
    for eachLine in dataArray1:
        if len(eachLine) > 1:
            list1.append(float(eachLine))
    for eachLine in dataArray2:
        if len(eachLine) > 1:
            list2.append(float(eachLine))
    ax.clear()
    ax.plot(list1)
    ax.plot(list2)


ani = animation.FuncAnimation(fig, animate, interval=1000)
plt.show()
