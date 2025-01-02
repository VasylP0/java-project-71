package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class DiffNodeTest {

    @Test
    void testDiffNodeEquality() {
        DiffNode node1 = new DiffNode("key1", "oldValue1", "newValue1", "updated");
        DiffNode node2 = new DiffNode("key1", "oldValue1", "newValue1", "updated");

        assertThat(node1).isEqualTo(node2); // Should pass
    }

    @Test
    void testDiffNodeToString() {
        DiffNode node = new DiffNode("key1", "oldValue1", "newValue1", "updated");
        String expected = "DiffNode{key='key1', oldValue=oldValue1, newValue=newValue1, status='updated'}";
        assertThat(node.toString()).isEqualTo(expected);
    }
}
