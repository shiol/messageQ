#!/usr/bin/env python
import pika

connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

channel.queue_declare(queue='apple')
channel.queue_declare(queue='microsoft')

channel.basic_publish(exchange='', routing_key='apple', body='Hello World!')
print(" [x] Sent 'Hello World!'")
connection.close()
