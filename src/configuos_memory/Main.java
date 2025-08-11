package configuos_memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<MemoryBlock> memory = new ArrayList<>(
                Arrays.asList(
                        new MemoryBlock(100),
                        new MemoryBlock(200),
                        new MemoryBlock(300)
                )
        );
        allocate(memory, 150);  //1
        allocate(memory, 50);  //2
        allocate(memory, 400);  //3

        System.out.println(" 메모리 상태");
        memory.forEach(System.out::println);

    }

    static void allocate(List<MemoryBlock> memory, int size) {
        for (MemoryBlock block : memory) {
            if (!block.isAllocated && block.size >= size) {
                System.out.println("✅" + size + "MB 할당 성공");
                block.isAllocated = true;
                return;
            }
        }
        System.out.println("❌ " + size + "MB 할당 실패 (빈 공간 없음)");
    }
}

