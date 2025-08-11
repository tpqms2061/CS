package configuos_memory;

public class MemoryBlock {
    int size;
    boolean isAllocated;

    MemoryBlock(int size) {
        this.size = size;
        this.isAllocated =false;

    }

    public String toString() {
        return isAllocated ? "[사용중 " + size + "MB]" : "[빈 공간 " + size + "MB]";
    }
}
