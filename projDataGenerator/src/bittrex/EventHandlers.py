from base64 import b64decode
from typing import List
from zlib import decompress, MAX_WBITS
import json

from ..MQInstance import MQInstance


class EventHandlers:

  @classmethod
  async def on_tickers(cls, msg) -> None:
    cls._publish_array('Tickers', json.loads(cls._process_message(msg[0]))['deltas'])

  @classmethod
  def _publish_array(cls, channel: str, msgs) -> None:
    for msg in msgs: cls._publish(channel, json.dumps(msg))

  @classmethod
  def _publish_decoded(cls, channel: str, msg) -> None:
    cls._publish(channel, cls._process_message(msg[0]))

  @classmethod
  def _publish(cls, channel: str, body: str) -> None:
    MQInstance.get_instance().publish(channel, body)

  @classmethod
  def _process_message(cls, message: str) -> None:
    try:
      decompressed_msg = decompress(b64decode(message, validate=True), -MAX_WBITS)
    except SyntaxError:
      decompressed_msg = decompress(b64decode(message, validate=True))
    return decompressed_msg.decode()
