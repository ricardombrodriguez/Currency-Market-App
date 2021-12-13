#!/usr/bin/python
# -*- coding: utf-8 -*-
#
# Last tested 2020/09/24 on Python 3.8.5
#
# Note: This file is intended solely for testing purposes and may only be used 
#   as an example to debug and compare with your code. The 3rd party libraries 
#   used in this example may not be suitable for your production use cases.
#   You should always independently verify the security and suitability of any 
#   3rd party library used in your code.

from signalr_aio import Connection # https://github.com/slazarov/python-signalr-client
import asyncio
from BittrexEventHandlers import BittrexEventHandlers

from ConfigInstance import ConfigInstance

URL = 'https://socket-v3.bittrex.com/signalr'

HUB = None
LOCK = asyncio.Lock()
INVOCATION_EVENT = None
INVOCATION_RESPONSE = None

config_manager = ConfigInstance('bittrex')

async def main():
  await connect()
  await subscribe()
  await asyncio.Event().wait()

async def connect():
  global HUB
  connection = Connection(URL)
  HUB = connection.register_hub('c3')
  connection.received += on_message
  connection.error += on_error
  connection.start()
  print('Connected')

async def subscribe():
  channels = []
  for event, handler in config_manager.get_key('events').items():
    HUB.client.on(event, getattr(BittrexEventHandlers, handler))
    channels.append(event)
  
  response = await invoke('Subscribe', channels)
  for i in range(len(channels)):
    if response[i]['Success']:
      print('Subscription to "' + channels[i] + '" successful')
    else:
      print('Subscription to "' + channels[i] + '" failed: ' + response[i]['ErrorCode'])
  
async def invoke(method, *args):
  async with LOCK:
    global INVOCATION_EVENT
    INVOCATION_EVENT = asyncio.Event()
    HUB.server.invoke(method, *args)
    await INVOCATION_EVENT.wait()
    return INVOCATION_RESPONSE

async def on_message(**msg):
  global INVOCATION_RESPONSE
  if 'R' in msg:
    INVOCATION_RESPONSE = msg['R']
    INVOCATION_EVENT.set()

async def on_error(msg):
  print(msg)

if __name__ == "__main__":
    asyncio.run(main())
