package clojure.collections;

import java.util.Collection;
import java.util.Stack;

import org.junit.Assert;

public class SingleLinkedListTest extends CollectionTest<String> {
  Stack<String> stack = new Stack<String>();

  @Override
  protected PersistentCollection<String> empty() {
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
  protected void assertEquivalent(PersistentCollection<String> pCollection) {
    Stack<String> reverse = new Stack<String>();
    for (String v : pCollection) {
      reverse.push(v);
    }

    Stack<String> stack = new Stack<String>();
    for (String v : reverse) {
      stack.push(v);
    }

    Assert.assertEquals(this.stack, stack);
  }
}
