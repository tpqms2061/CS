package Paging_study;

import java.util.HashMap;
import java.util.Map;

public class Main {
    static final int PAGE_SIZE =256;

    public static void main(String[] args) {
        Map<Integer, Integer> pageTable = new HashMap<>();
        pageTable.put(0, 2);
        pageTable.put(1,5);
        pageTable.put(2,8);

        int virtualAddress = 0X1A3;
        int pageNumber = virtualAddress / PAGE_SIZE;
        int offset = virtualAddress % PAGE_SIZE;

        if (pageTable.containsKey(pageNumber)) {
            int frameNumber = pageTable.get(pageNumber);
            int physicalAddress = frameNumber * PAGE_SIZE +offset;
            System.out.printf("가상 주소 %d -> 페이지 %d , 오프셋 %d -> 물리주소 %d \n",
                    virtualAddress, pageNumber, offset, physicalAddress);
        }else {
            System.out.println("페이지 폴트 발생!");

        }
    }
}
