package org.projectreactor.samples.aeron;

import reactor.aeron.Context;
import reactor.aeron.subscriber.AeronSubscriber;
import reactor.io.buffer.Buffer;
import reactor.rx.Streams;

/**
 * @author Anatoly Kadyshev
 */
public class AeronServer {

	public static void main(String[] args) {
		AeronSubscriber subscriber = AeronSubscriber.create(new Context().name("server"));

		Streams.range(1, 1000).map(i -> Buffer.wrap("" + i)).subscribe(subscriber);
	}

}
