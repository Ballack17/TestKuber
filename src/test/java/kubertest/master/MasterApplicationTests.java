package kubertest.master;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

@Order(1)
class MasterApplicationTests extends BaseTestClassWithPostgreSQL {

	@Test
	public static void main(String[] args) {
		SpringApplication.run(MasterApplication.class, args);
	}

}


