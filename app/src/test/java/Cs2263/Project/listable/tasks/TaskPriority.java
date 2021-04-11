/**
 * Task Priority Enum
 *
 * For some reason I recall Priority needing to be represented with and int.
 * Thus I have designed this with a simple int value. May be revised
 *
 * @author  Traae
 * @version 1.0
 * @since 3/25/2021
 */

package Cs2263.Project.listable.tasks;

public enum TaskPriority {
    Low (1),
    Medium (2),
    High (3),
    Highest (4);

    private final int value;
    TaskPriority(int v){
        this.value = v;
    }
    public int getValue() {
        return value;
    }
}
