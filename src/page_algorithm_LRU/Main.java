package page_algorithm_LRU;

import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        int[] pages = {1, 3, 0, 3, 5, 6};
        int capacity = 3;

        LinkedHashSet<Integer> memory = new LinkedHashSet<>();

        int pageFault = 0;

        for (int page : pages) {
            if (!memory.contains(page)) {
                if (memory.size() == capacity) {
                    int first = memory.iterator().next(); //반복을 사용해서 next를 하게되면 시간순서상 LinkedHahsset에서 가지고 있다. -> 가장 먼저거가 출력되고 지우면됨
                    memory.remove(first);
                }
                pageFault++;
            }else {
                memory.remove(page);
            }
            memory.add(page);
            System.out.println("페이지" + page + " 로드 :" + memory);

        }
        System.out.println("총 페이지 폴트 : " + pageFault);

    }
}
