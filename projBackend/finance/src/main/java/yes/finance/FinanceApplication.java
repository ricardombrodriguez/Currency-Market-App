package yes.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import yes.finance.rabbit_mq.DatabasePersistance;
import yes.finance.rabbit_mq.WebSocketDistribution;

@SpringBootApplication
public class FinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
		
		new DatabasePersistance();
		new WebSocketDistribution();
	}

}
