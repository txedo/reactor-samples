package org.projectreactor.samples.aeron;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.aeron.Context;
import reactor.aeron.publisher.AeronPublisher;
import reactor.io.buffer.Buffer;

/**
 * @author Anatoly Kadyshev
 */
public class AeronClient {

	public static void main(String[] args) {
		AeronPublisher publisher = AeronPublisher.create(new Context().name("publisher").autoCancel(true));

		publisher.subscribe(new Subscriber<Buffer>() {

			private Subscription subscription;

			int counter = 0;

			@Override
			public void onSubscribe(Subscription s) {
				subscription = s;
				subscription.request(1);
			}

			@Override
			public void onNext(Buffer buffer) {
				System.out.println("received: " + buffer.asString());
				subscription.request(1);

				if(++counter == 10) {
					subscription.cancel();
				}
			}

			@Override
			public void onError(Throwable t) {
			}

			@Override
			public void onComplete() {

			}
		});
	}

}
