package pcollections;

import java.util.LinkedList;
import java.util.Queue;

import junit.framework.Assert;

import org.junit.Test;

import pcollections.LinkedQueue;
import pcollections.PersistentQueue;

public class LinkedQueueTest extends CollectionTest<String, PersistentQueue<String>, Queue<String>> {
  Queue<String> queue = new LinkedList<String>();

  @Test
  public void offerTest() {
    offer(random());
    assertEquivalent();
  }

  @Test
  public void offerOfferTest() {
    offer(random());
    offer(random());
    assertEquivalent();
  }

  @Test
  public void offerOfferPopTest() {
    offer(random());
    offer(random());
    pop();
    assertEquivalent();
  }

  @Test
  public void randomTest() {
    for (int i = 0; i < 100; i++) {
      if (RAND.nextBoolean() && queue.size() > 0) {
        offer(random());
      } else {
        pop();
      }
    }

    assertEquivalent();
  }

  @Test
  public void pushAndEmpty() {
    int total = 100;
    for (int i = 0; i < total; i++) {
      offer(random());
    }

    for (int i = 0; i < total; i++) {
      pop();
    }

    assertEquivalent();
  }

  protected void pop() {
    pCollection = pCollection.pop();
    queue.poll();
    checkpoint();
  }

  protected void offer(String value) {
    queue.offer(value);
    pCollection = pCollection.offer(value);
    checkpoint();
  }

  @Override
  protected PersistentQueue<String> empty() {
    return LinkedQueue.empty();
  }

  @Override
  protected Queue<String> getCollection() {
    return queue;
  }

  @Override
  protected Queue<String> cloneCollection() {
    return new LinkedList<String>(queue);
  }

  @Override
  protected String random() {
    return String.valueOf(RAND.nextInt());
  }

  @Override
  protected void assertEquivalent(Queue<String> queue, PersistentQueue<String> pQueue) {
    super.assertEquivalent(queue, pQueue);

    Queue<String> newQueue = new LinkedList<String>();
    while (!pQueue.isEmpty()) {
      newQueue.offer(pQueue.peek());
      pQueue = pQueue.pop();
    }

    Assert.assertEquals(queue, newQueue);
  }
}
