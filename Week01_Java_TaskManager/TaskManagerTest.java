import java.time.LocalDate;
import java.util.Scanner;
class TaskManagerTest {
    
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.getCSV();
        
        System.out.println(taskManager.getTasks());
        while (true) {
            taskManager.showOptions();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    // Simulate adding a task
                    scanner = new Scanner(System.in);
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    LocalDate dueDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter task priority (1-5): ");
                    int priority = scanner.nextInt();
                    Task newTask = new Task(title, dueDate, priority, false);
                    taskManager.addTask(newTask);
                }
                case 2 -> // View tasks
                    System.out.println(taskManager.getTasks());
                case 3 -> {
                    // Mark a task as completed
                    System.out.println(taskManager.getTasks());
                    scanner = new Scanner(System.in);
                    System.out.print("Enter the title of the task to mark as completed: ");
                    String taskTitle = scanner.nextLine();
                    taskManager.markAsCompleted(taskTitle);
                }
                case 4 -> {
                    // Exit the loop
                    taskManager.uploadCSV();
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
            if (choice == 4) {
                break;
            }
        }
        
    }

}