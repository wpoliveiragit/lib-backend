package suport;

import java.util.concurrent.CountDownLatch;

public class CheckSuport {

	private CountDownLatch latch;

	public CheckSuport() {
		latch = new CountDownLatch(1);
	}

	public void finishing() {
		latch.countDown();
	}

	public void await() {
		try {
			latch.await();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
