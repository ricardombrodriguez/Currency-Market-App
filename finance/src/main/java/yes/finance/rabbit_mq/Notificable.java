package yes.finance.rabbit_mq;

public interface Notificable {
    
    public void notification(String input, MQChannels channel);

}
