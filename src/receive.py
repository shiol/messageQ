#!/usr/bin/env python
import pika

arquivo1 = open("apple.txt", "w")
arquivo2 = open("microsoft.txt", "w")

connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

channel.queue_declare(queue='apple')
channel.queue_declare(queue='microsoft')


def callback(ch, method, properties, body):
    if(method.routing_key == 'apple'):
        print('apple = %.4f' % float(body))
        arquivo1.write('%.4f\n' % float(body))
    elif (method.routing_key == 'microsoft'):
        print('ms = %.4f' % float(body))
        arquivo2.write('%.4f\n' % float(body))
    arquivo1.flush()
    arquivo2.flush()


channel.basic_consume(
    queue='apple', on_message_callback=callback, auto_ack=True)
channel.basic_consume(
    queue='microsoft', on_message_callback=callback, auto_ack=True)

channel.start_consuming()
