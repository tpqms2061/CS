package page_algorithm_FIFO;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        int[] pages = {1, 3, 0, 3, 5, 6};
        int capacity =3 ;



        Queue<Integer> memory = new LinkedList<>();

        int pageFault = 0;

        for (int page : pages) {
            if (!memory.contains(page)) {
                if (memory.size() == capacity) { //최대용량차지
                    memory.poll(); //가장 오래된 페이지 제거
                }
                memory.add(page);
                pageFault++;
                System.out.println("페이지 " + page + "로드 : " + memory);
            }
        }

        System.out.println("총 페이지 폴트 : " + pageFault);

    }
}
