package clojure.collections;

import java.util.Collection;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class SingleLinkedListTest extends CollectionTest<String, PersistentStack<String>> {
  Stack<String> stack = new Stack<String>();

  @Test
  public void pushPeekTest() {
    push(random());
    assertEquivalent();
  }

  @Test
  public void pushPushPushPeekTest() {
    push(random());
    push(random());
    assertEquivalent();
  }

  @Test
  public void pushPushPopPeekTest() {
    push(random());
    push(random());
    pop();
    assertEquivalent();
  }

  public void push(String value) {
    pCollection = pCollection.push(value);
    stack.push(value);
  }

  public void pop() {
    pCollection = pCollection.pop();
    stack.pop();
  }

  @Override
  protected PersistentStack<String> empty() {
    stack.clear();
    return SingleLinkedList.empty();
  }

  @Override
  protected String random() {
    return String.valueOf(RAND.nextInt());
  }

  @Override
  protected Collection<String> getCollection() {
    return stack;
  }

  @Override
  protected void assertEquivalent(PersistentStack<String> pStack) {
    super.assertEquivalent(pStack);

    Stack<String> reverse = new Stack<String>();
    while (!pStack.isEmpty()) {
      reverse.push(pStack.peek());
      pStack = pStack.pop();
    }

    Stack<String> stack = new Stack<String>();
    while (!reverse.isEmpty()) {
      stack.push(reverse.pop());
    }

    Assert.assertEquals(this.stack, stack);
  }
}
