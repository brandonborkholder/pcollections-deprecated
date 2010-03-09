package pcollections;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import pcollections.PersistentStack;
import pcollections.SingleLinkedList;

public class SingleLinkedListTest extends CollectionTest<String, PersistentStack<String>, Stack<String>> {
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
    checkpoint();
  }

  public void pop() {
    pCollection = pCollection.pop();
    stack.pop();
    checkpoint();
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
  protected Stack<String> getCollection() {
    return stack;
  }

  @Override
  protected Stack<String> cloneCollection() {
    return (Stack) stack.clone();
  }

  @Override
  protected void assertEquivalent(Stack<String> stack, PersistentStack<String> pStack) {
    super.assertEquivalent(stack, pStack);

    Stack<String> reverse = new Stack<String>();
    while (!pStack.isEmpty()) {
      reverse.push(pStack.peek());
      pStack = pStack.pop();
    }

    Stack<String> otherStack = new Stack<String>();
    while (!reverse.isEmpty()) {
      otherStack.push(reverse.pop());
    }

    Assert.assertEquals(stack, otherStack);
  }
}
