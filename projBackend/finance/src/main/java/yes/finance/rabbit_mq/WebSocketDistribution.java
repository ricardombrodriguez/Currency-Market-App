package yes.finance.rabbit_mq;

import org.json.JSONObject;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import yes.finance.SpringContext;

public class WebSocketDistribution implements Notificable {

  public WebSocketDistribution() {
    MessageQueue.getInstance().subscribe(this);
  }

  @Override
  public void notification(String input, MQChannels channel) {
    if (channel == MQChannels.Tickers) {
      JSONObject data = new JSONObject(input);
      SpringContext.getBean(SimpMessageSendingOperations.class).convertAndSend("/market/" + data.getString("symbol"), input);
    }
  }
  
}
