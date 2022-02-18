package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));


//        printDeadlinesWithStream(tasksData);

        ArrayList<Task> filteredList = filterTasksByString(tasksData,"11");
        System.out.println(filteredList);
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlinesWithStream(ArrayList<Task> tasks) {
        System.out.println("printing deadlines with streams sorted");
        tasks.stream()
                .filter((t)-> t instanceof Deadline)
                .sorted((a,b)-> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);
        //the lambda here takes two parameters to compare the two descriptions

    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    private static ArrayList<Task> filterTasksByString(ArrayList<Task> tasks, String s) {
        System.out.println("Printing filteredlist of data");
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t)-> t.getDescription().contains(s))
                .collect(toList());
        //collect returns a list but we need arraylist of type task so we cast it as such
        //collect function will collect those tsaks in stream

        return filteredList;
    }
}
