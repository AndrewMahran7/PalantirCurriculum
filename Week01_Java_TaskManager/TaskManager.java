
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
class Task {
    public Integer id;
    public String title;
    public LocalDate dueDate;
    public int priority;
    public boolean completed;
    public String description;

    public Task(String title, LocalDate dueDate, int priority, boolean completed, String description) {
        id += 1;
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = completed;
        this.description = description;
    
    }
    public String printTask() {
        return "Task: " + title + ", Due: " + dueDate + ", Priority: " + priority + ", Completed: " + completed;
    }
    
}

class TaskManager {
    List<Task> tasks;

    TaskManager() {
        this.tasks = new ArrayList<>();
    }

    void addTask(Task task) {
        if (task != null) {
            this.tasks.add(task);
        } else {
            System.out.println("Task cannot be null.");
        }
    }
    public String getTasks() {
        String taskList = "";
        for (Task task : tasks) {
            taskList += task.printTask() + "\n";
        }
        return taskList;
    }

    public void markAsCompleted(String title) {
        for (Task task : tasks) {
            if (task.title.equals(title)) {
                task.completed = true;
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void showOptions() {
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. Exit");
    }
    public void getCSV() {
        String filePath = "tasks.csv";
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine();
            System.out.println(header);
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\n");
                for (String value : data) {
                    String[] taskData =  value.split(",");
                    System.out.println("Task Data: " + value);
                    if (taskData.length == 5) {
                        String title = taskData[0];
                        LocalDate dueDate = LocalDate.parse(taskData[1]);
                        int priority = Integer.parseInt(taskData[2]);
                        boolean completed = Boolean.parseBoolean(taskData[3]);
                        String description = taskData[4];
                        Task task = new Task(title, dueDate, priority, completed, description);
                        addTask(task);
                    } else {
                        System.out.println("Invalid task data: " + value);
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void uploadCSV() {
        // Delete old file if it exists
        // Create a new file and write tasks to it
        try (FileWriter writer = new FileWriter("tasks.csv")) {
            writer.write("Title,Due Date,Priority,Completed\n");
            for (Task task : tasks) {
                writer.write(task.title + "," + task.dueDate + "," + task.priority + "," + task.completed + "\n");
            }
            System.out.println("Tasks uploaded to tasks.csv");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        String filePath = "tasks.csv";
        String[] header = {"Title", "Due Date", "Priority", "Completed"};
        String[][] data = new String[tasks.size()][4];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            data[i][0] = task.title;
            data[i][1] = task.dueDate.toString();
            data[i][2] = String.valueOf(task.priority);
            data[i][3] = String.valueOf(task.completed);
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            for (String column : header) {
                writer.write(column + ",");
            }
            writer.write("\n");

            // Write data
            for (String[] row : data) {
                for (String value : row) {
                    writer.write(value + ",");
                }
                writer.write("\n");
            }
            System.out.println("Tasks uploaded to " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void main(String[] args) {

    }
}

