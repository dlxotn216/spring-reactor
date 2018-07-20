package me.reactive.ch01.flux_mono_subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
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
public class SubscriberRunner implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		System.out.println("\nSubscriberRunner");
		
		Flux<Integer> flux
				= Flux.just(1, 2, 3, 4, 5, 6);
		
		flux.subscribe(new Subscriber<Integer>() {
			private Subscription subscription;
			
			@Override
			public void onSubscribe(Subscription s) {
				System.out.println("Subscriber.onSubscribe");
				this.subscription = s;
//				this.subscription.request(1);    //Publisher에 최초 1개 데이터 요청
				this.subscription.request(Integer.MAX_VALUE);    //Publisher에 최초 Max개 데이터 요청
			}
			
			@Override
			public void onNext(Integer integer) {
				System.out.println("Subscriber.onNext: " + integer);
//				this.subscription.request(1);	//Publisher에게 1개씩 요청
			}
			
			@Override
			public void onError(Throwable t) {
				System.out.println("Subscriber.onError -> " + t.getMessage());
			}
			
			@Override
			public void onComplete() {
				System.out.println("Subscriber.onComplete");
			}
		});
		
		System.out.println("\nSubscribe()");
		flux.subscribe();
		
		System.out.println("\nSubscribe through consumer");
		flux.subscribe(System.out::println);
		
		System.out.println("\nComsumer, ErrorConsumer, CompleteConsummer(Runnable)");
		flux.subscribe(
				integer -> {
					System.out.println("CurrentThread is " + Thread.currentThread().getName());	//main
					System.out.println(integer);
				},
				throwable -> System.out.println("Error " + throwable.getMessage()),
				() -> {
					System.out.println("CurrentThread is " + Thread.currentThread().getName());	//main
					System.out.println("Complete");
				});
	}
}
