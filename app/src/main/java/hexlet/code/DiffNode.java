package hexlet.code;

import java.util.Objects;

public class DiffNode {
    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final String status;

    public DiffNode(String key, Object oldValue, Object newValue, String status) {
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DiffNode diffNode = (DiffNode) obj;
        return Objects.equals(key, diffNode.key) &&
                Objects.equals(oldValue, diffNode.oldValue) &&
                Objects.equals(newValue, diffNode.newValue) &&
                Objects.equals(status, diffNode.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, oldValue, newValue, status);
    }

    @Override
    public String toString() {
        return "DiffNode{" +
                "key='" + key + '\'' +
                ", oldValue=" + oldValue +
                ", newValue=" + newValue +
                ", status='" + status + '\'' +
                '}';
    }
}
