import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeManagementSystem {
    private static ArrayList<Student> records = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    while (true) {
        printMenu();
        int choice = getValidMenuChoice();

        switch (choice) {
            case 1:
                addStudentRecord();
                break;
            case 2:
                queryStudentRecord();
                break;
            case 3:
                statisticsCourse();
                break;
            case 4:
                System.out.println("退出系统。");
                scanner.close();
                return;
            default:
                System.out.println("无效的选项，请重新输入。");
        }
    }
}

private static void printMenu() {
    System.out.println("\n学生成绩管理系统");
    System.out.println("1. 录入学生成绩");
    System.out.println("2. 查询学生成绩");
    System.out.println("3. 统计课程成绩");
    System.out.println("4. 退出系统");
    System.out.print("请选择操作：");
}

private static int getValidMenuChoice() {
    while (true) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.print("请输入有效的数字选项：");
            scanner.next(); // 清除无效输入
        }
    }
}

private static void addStudentRecord() {
    scanner.nextLine(); // 清除换行符
    System.out.print("请输入学生姓名：");
    String name = scanner.nextLine();
    System.out.print("请输入学号：");
    String id = scanner.nextLine();

    if (isDuplicateId(id)) {
        System.out.println("错误：学号已存在！");
        return;
    }

    System.out.print("请输入课程名称：");
    String course = scanner.nextLine();

    int score;
    while (true) {
        System.out.print("请输入成绩（0-100）：");
        try {
            score = scanner.nextInt();
            if (score >= 0 && score <= 100) {
                break;
            } else {
                System.out.println("错误：成绩必须在0到100之间！");
            }
        } catch (InputMismatchException e) {
            System.out.println("错误：请输入有效的数字！");
            scanner.next(); // 清除无效输入
        }
    }

    records.add(new Student(name, id, course, score));
    System.out.println("成绩录入成功！");
}

private static boolean isDuplicateId(String id) {
    for (Student record : records) {
        if (record.getStudentId().equals(id)) {
            return true;
        }
    }
    return false;
}

private static void queryStudentRecord() {
    scanner.nextLine(); // 清除换行符
    System.out.print("请输入学生姓名、学号或课程名称进行查询：");
    String keyword = scanner.nextLine().toLowerCase();

    boolean found = false;
    for (Student record : records) {
        if (record.getStudentName().toLowerCase().contains(keyword) ||
                record.getStudentId().toLowerCase().contains(keyword) ||
                record.getCourseName().toLowerCase().contains(keyword)) {
            System.out.printf("姓名：%s，学号：%s，课程：%s，成绩：%d%n",
                    record.getStudentName(),
                    record.getStudentId(),
                    record.getCourseName(),
                    record.getScore());
            found = true;
        }
    }

    if (!found) {
        System.out.println("未找到相关记录。");
    }
}

private static void statisticsCourse() {
    scanner.nextLine(); // 清除换行符
    System.out.print("请输入要统计的课程名称：");
    String course = scanner.nextLine();

    int sum = 0, count = 0;
    int maxScore = -1, minScore = 101;

    for (Student record : records) {
        if (record.getCourseName().equalsIgnoreCase(course)) {
            int score = record.getScore();
            sum += score;
            count++;
            if (score > maxScore) maxScore = score;
            if (score < minScore) minScore = score;
        }
    }

    if (count == 0) {
        System.out.println("未找到该课程的相关记录。");
        return;
    }

    double average = (double) sum / count;
    System.out.printf("课程：%s%n", course);
    System.out.printf("平均分：%.2f%n", average);
    System.out.printf("最高分：%d%n", maxScore);
    System.out.printf("最低分：%d%n", minScore);
}
}
