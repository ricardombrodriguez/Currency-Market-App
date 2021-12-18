import pika

from .ConfigInstance import ConfigInstance


class MQInterface:
  def publish(self, queue:str, body:str): pass


class RabbitMQ(MQInterface):
  SERVICE_NAME = 'rabbitmq'

  def __init__(self) -> None:
    self._config_manager = ConfigInstance(self.SERVICE_NAME)

    connection_params = {
        'host': self._config_manager.get_key('host'),
        'port': self._config_manager.get_key('port')
    }
    self._connection = pika.BlockingConnection(pika.ConnectionParameters(**connection_params))

    self._queues = []
    
  def publish(self, queue: str, body: str):
    channel = self._connection.channel()

    if queue not in self._queues:
      channel.queue_declare(queue)
      self._queues.append(queue)

    channel.basic_publish('', queue, body)
    channel.close()


class MQInstance:
  DEFAULT_IMPLEMENTATION = RabbitMQ
  INSTANCE = None

  @classmethod
  def get_instance(cls) -> MQInterface:
    if cls.INSTANCE is None: cls.INSTANCE = cls.DEFAULT_IMPLEMENTATION()
    return cls.INSTANCE
