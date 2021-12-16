from typing import List
import requests
import json

from ..ConfigInstance import ConfigInstance
from ..MQInstance import MQInstance


class RestAPI:
  def __init__(self) -> None:
      self._config_manager = ConfigInstance('bittrex_rest')

      for endpoint in self._config_manager.get_key('loaded'):
        self._publish_endpoint(endpoint)

  def _publish_endpoint(self, endpoint: str) -> any:
    RestAPI._publish_array(endpoint.capitalize(), self._get_data(endpoint))

  def _get_data(self, endpoint: str) -> any:
    return requests.get(self._build_url(endpoint)).json()

  def _build_url(self, endpoint: str) -> str:
    return self._config_manager.get_key('url') + endpoint

  @classmethod
  def _publish_array(cls, channel: str, data: List) -> None:
    for msg in data: cls._publish(channel, json.dumps(msg))

  @classmethod
  def _publish(cls, channel: str, body: str) -> None:
    MQInstance.get_instance().publish(channel, body)
