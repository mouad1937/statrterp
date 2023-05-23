package com.mikaila.demochucknorrisapi;
import java.util.Date;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;


@SpringBootApplication
@EnableScheduling
public class DemoChucknorrisApiApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoChucknorrisApiApplication.class);
@Bean
public RestTemplate restTemplate(RestTemplateBuilder builder){

	return builder.build();
}

@Bean
public CommandLineRunner run (RestTemplate restTemplate) throws Exception{
	return args -> {
		Joke joke = restTemplate.getForObject(
"https://api.chucknorris.io/jokes/random", Joke.class
		);
		log.info(joke.toString());
	};

}
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
    log.info("the time is now ",dateFormat.format(new Date()) );
}
	public static void main(String[] args) {
		SpringApplication.run(DemoChucknorrisApiApplication.class, args);
	}

}
