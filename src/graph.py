import matplotlib.pyplot as plt
import matplotlib.animation as animation
import time

# x axis values
x = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
# corresponding y axis values
list1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
list2 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)


def animate(i):
    list1[:] = [x + 1 for x in list1]
    list2[:] = [x + 1 for x in list2]
    ax.clear()
    ax.plot(x, list1)
    ax.plot(x, list2)


ani = animation.FuncAnimation(fig, animate, interval=1000)
plt.show()

# fig.title('stock values')
# fig.xlabel('time')
# fig.ylabel('value')
