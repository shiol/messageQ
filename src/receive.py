#!/usr/bin/env python
import pika
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import time

connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

channel.queue_declare(queue='apple')

x = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
list1 = []
list2 = []

# ploting
fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)


def animate(i):
    ax.clear()
    if(len(list1) >= 10 & len(list2) >= 10):
        ax.plot(list1)
        ax.plot(list2)
        print(list1)
        print(list2)
        list1.clear()
        list2.clear()


def callback(ch, method, properties, body):
    if(method.routing_key == 'apple'):
        aux = float(body)
        list1.append(aux)
        print('apple = %f' % aux)
    elif (method.routing_key == 'microsoft'):
        aux = float(body)
        list2.append(aux)
        print('ms = %f' % aux)
    if(len(list1) >= 10 & len(list2) >= 10):
        plt.plot(list1)
        plt.plot(list2)
        plt.draw()
        plt.show()
        print(list1)
        print(list2)
        list1.clear()
        list2.clear()


channel.basic_consume(
    queue='apple', on_message_callback=callback, auto_ack=True)
channel.basic_consume(
    queue='microsoft', on_message_callback=callback, auto_ack=True)

# ani = animation.FuncAnimation(fig, animate, repeat=False)
# plt.show()

channel.start_consuming()
