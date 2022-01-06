import json

from config import CONFIG


class ConfigManager:
  def __init__(self) -> None:
    self.config = CONFIG

  def get_config(self, service:str, key:str) -> any:
    return self.config[service][key]


class ConfigInstance:
  INSTANCE = None

  def __init__(self, service:str) -> None:
      self._service = service

  def get_key(self, key:str) -> any:
    return ConfigInstance.get_instance().get_config(self._service, key)

  @classmethod
  def get_instance(cls) -> ConfigManager:
    if cls.INSTANCE is None: cls.INSTANCE = ConfigManager()
    return cls.INSTANCE

