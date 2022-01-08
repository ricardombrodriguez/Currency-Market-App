package yes.finance.rabbit_mq;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {
  private final MQChannels channel;
  private final String message;

  public MessageEvent(Object source, MQChannels channel, String message) {
    super(source);
    
    this.channel = channel;
    this.message = message;
  }

  public MQChannels getChannel() {
    return this.channel;
  }

  public String getMessage() {
    return this.message;
  }
  
}
