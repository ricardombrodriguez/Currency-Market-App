import json


class ConfigManager:
  def __init__(self, config_file='config.json') -> None:
      with open(config_file, 'r') as f:
        self.config = json.load(f)

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

