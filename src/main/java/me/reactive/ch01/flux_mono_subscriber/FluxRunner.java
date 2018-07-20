package me.reactive.ch01.flux_mono_subscriber;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project reactive-demo
 * @since 2018-07-20
 */
@Service
public class FluxRunner implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		System.out.println("\nFluxRunner");
		
		//Flux
		Flux.just(1,2,3)
				.doOnNext(integer -> System.out.println("doOnNext -> "+ integer))
				.subscribe(integer -> System.out.println("Received -> "+ integer));
		
		Flux<Integer> flux = Flux.just(10, 11, 12)
				.doOnNext(integer -> System.out.println("doOnNext -> " + integer));
		
		System.out.println("\nFlux 시퀀스 생성");
		flux.subscribe(integer -> System.out.println("Received -> "+ integer));
		//구독 시에 doOnNext에 전달한 Lambda가 실행 됨
		/*
			Flux 시퀀스 생성
			doOnNext -> 10
			Received -> 10
			doOnNext -> 11
			Received -> 11
			doOnNext -> 12
			Received -> 12
		 */
		
		/*
			Cold Stream (Cold Sequence)
			- 구독을 하지 않으면 데이터를 생성하지 않음.
			- 구독을 하면 그 시점에 데이터를 새롭게 발생시킴.
			
			Hot Stream (Hot Sequence)
			- 구독 여부와 상관 없이 데이터를 발생시킴.
			
		 */
	}
}
