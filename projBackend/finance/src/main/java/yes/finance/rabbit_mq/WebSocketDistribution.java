package yes.finance.rabbit_mq;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import yes.finance.events.MessageEvent;

@Component
public class WebSocketDistribution implements ApplicationListener<MessageEvent> {

  @Autowired
  private SimpMessageSendingOperations sendingOperations;

  @Override
  public void onApplicationEvent(MessageEvent event) {
    String input = event.getMessage();
    MQChannels channel = event.getChannel();

    if (channel == MQChannels.Tickers) {
      JSONObject data = new JSONObject(input);
      sendingOperations.convertAndSend("/market/" + data.getString("symbol"), input);
    }
  }
  
}
