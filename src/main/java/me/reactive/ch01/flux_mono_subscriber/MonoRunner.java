package me.reactive.ch01.flux_mono_subscriber;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project reactive-demo
 * @since 2018-07-20
 */
@Service
public class MonoRunner implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		System.out.println("\nMonoRunner");
		//Mono
		Mono.just("Hello mono")
				.subscribe(System.out::println);
		
		System.out.println();
	}
}
