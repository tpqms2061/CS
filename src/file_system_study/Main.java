package file_system_study;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. 기본 파일 정보 조회
            System.out.println("=== 📁 파일 시스템 기본 정보 ===");
            displaySystemInfo();

            // 2. 디렉터리 생성 및 관리
            System.out.println("\n=== 📂 디렉터리 생성 및 관리 ===");
            manageDirectories();

            // 3. 파일 생성 및 속성 조회
            System.out.println("\n=== 📄 파일 생성 및 속성 조회 ===");
            manageFiles();

            // 4. 디렉터리 탐색
            System.out.println("\n=== 🔍 디렉터리 탐색 ===");
            exploreDirectory("./test_folder");

            // 5. 파일 복사 및 이동
            System.out.println("\n=== 📋 파일 복사 및 이동 ===");
            copyAndMoveFiles();

        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 시스템 기본 정보 표시
    static void displaySystemInfo() {
        // 현재 작업 디렉터리
        String currentDir = System.getProperty("user.dir");
        System.out.println("📍 현재 작업 디렉터리: " + currentDir);

        // 홈 디렉터리
        String homeDir = System.getProperty("user.home");
        System.out.println("🏠 사용자 홈 디렉터리: " + homeDir);

        // 임시 디렉터리
        String tempDir = System.getProperty("java.io.tmpdir");
        System.out.println("🗂 임시 디렉터리: " + tempDir);

        // 파일 구분자
        String separator = File.separator;
        System.out.println("📁 파일 구분자: '" + separator + "'");

        // 사용 가능한 드라이브 정보
        File[] roots = File.listRoots();
        System.out.println("💾 사용 가능한 루트:");
        for (File root : roots) {
            long totalSpace = root.getTotalSpace() / (1024 * 1024 * 1024);
            long freeSpace = root.getFreeSpace() / (1024 * 1024 * 1024);
            System.out.printf("   %s - 전체: %dGB, 여유: %dGB%n",
                    root.getPath(), totalSpace, freeSpace);
        }
    }

    // 디렉터리 생성 및 관리
    static void manageDirectories() throws IOException {
        // 테스트용 디렉터리 구조 생성
        File testFolder = new File("test_folder");
        File subFolder1 = new File(testFolder, "documents");
        File subFolder2 = new File(testFolder, "images");
        File subFolder3 = new File(testFolder, "backup");

        // 디렉터리 생성
        if (testFolder.mkdirs()) {
            System.out.println("✅ 메인 폴더 생성: " + testFolder.getName());
        }

        if (subFolder1.mkdir()) {
            System.out.println("✅ 하위 폴더 생성: " + subFolder1.getName());
        }

        if (subFolder2.mkdir()) {
            System.out.println("✅ 하위 폴더 생성: " + subFolder2.getName());
        }

        if (subFolder3.mkdir()) {
            System.out.println("✅ 하위 폴더 생성: " + subFolder3.getName());
        }
    }

    // 파일 생성 및 속성 조회
    static void manageFiles() throws IOException {
        // 다양한 파일 생성
        File textFile = new File("test_folder/documents/sample.txt");
        File dataFile = new File("test_folder/documents/data.csv");
        File imageFile = new File("test_folder/images/photo.jpg");

        // 텍스트 파일 생성 및 내용 작성
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFile))) {
            writer.write("안녕하세요! 이것은 테스트 파일입니다.\n");
            writer.write("파일 시스템 실습을 위한 샘플 텍스트입니다.\n");
            writer.write("생성 시간: " + new Date().toString() + "\n");
        }

        // CSV 파일 생성
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            writer.write("이름,나이,직업\n");
            writer.write("김철수,30,개발자\n");
            writer.write("이영희,25,디자이너\n");
            writer.write("박민수,35,기획자\n");
        }

        // 가짜 이미지 파일 생성 (실제로는 텍스트)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(imageFile))) {
            writer.write("이것은 실제 이미지가 아닌 텍스트 파일입니다.");
        }

        System.out.println("📄 생성된 파일들:");

        // 파일 속성 정보 출력
        File[] files = {textFile, dataFile, imageFile};
        for (File file : files) {
            displayFileInfo(file);
        }
    }

    // 파일 정보 출력
    static void displayFileInfo(File file) {
        if (file.exists()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            System.out.println("📄 파일: " + file.getName());
            System.out.println("   📍 절대 경로: " + file.getAbsolutePath());
            System.out.println("   📏 크기: " + file.length() + " 바이트");
            System.out.println("   📅 마지막 수정: " + sdf.format(new Date(file.lastModified())));
            System.out.println("   🔒 권한: " +
                    (file.canRead() ? "R" : "-") +
                    (file.canWrite() ? "W" : "-") +
                    (file.canExecute() ? "X" : "-"));
            System.out.println("   📁 디렉터리 여부: " + (file.isDirectory() ? "예" : "아니오"));
            System.out.println("   👻 숨김 파일 여부: " + (file.isHidden() ? "예" : "아니오"));
            System.out.println();
        }
    }

    // 디렉터리 탐색
    static void exploreDirectory(String dirPath) {
        File directory = new File(dirPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("❌ 디렉터리가 존재하지 않습니다: " + dirPath);
            return;
        }

        System.out.println("📂 디렉터리 탐색: " + directory.getAbsolutePath());

        File[] contents = directory.listFiles();
        if (contents != null) {
            // 파일과 디렉터리 분류
            int fileCount = 0;
            int dirCount = 0;
            long totalSize = 0;

            for (File item : contents) {
                String type = item.isDirectory() ? "📁" : "📄";
                String size = item.isDirectory() ? "<DIR>" : item.length() + "B";

                System.out.printf("   %s %-20s %s%n", type, item.getName(), size);

                if (item.isDirectory()) {
                    dirCount++;
                } else {
                    fileCount++;
                    totalSize += item.length();
                }
            }

            System.out.println("📊 통계:");
            System.out.println("   📁 디렉터리: " + dirCount + "개");
            System.out.println("   📄 파일: " + fileCount + "개");
            System.out.println("   📏 총 크기: " + totalSize + " 바이트");
        }
    }

    // 파일 복사 및 이동
    static void copyAndMoveFiles() throws IOException {
        // 원본 파일
        File sourceFile = new File("test_folder/documents/sample.txt");

        // 복사본 생성
        File copyFile = new File("test_folder/backup/sample_copy.txt");
        if (copyFile.getParentFile().mkdirs() || copyFile.getParentFile().exists()) {
            copyFile(sourceFile, copyFile);
            System.out.println("✅ 파일 복사 완료: " + copyFile.getName());
        }

        // 파일 이름 변경 (이동)
        File renamedFile = new File("test_folder/backup/sample_renamed.txt");
        if (copyFile.renameTo(renamedFile)) {
            System.out.println("✅ 파일 이름 변경: " + renamedFile.getName());
        }

        // 새로운 디렉터리로 이동
        File movedFile = new File("test_folder/images/moved_sample.txt");
        if (renamedFile.renameTo(movedFile)) {
            System.out.println("✅ 파일 이동 완료: " + movedFile.getName());
        }
    }

    // 파일 복사 유틸리티 메서드
    static void copyFile(File source, File destination) throws IOException {
        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(destination)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }


}