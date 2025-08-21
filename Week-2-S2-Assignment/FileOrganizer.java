import java.util.*;
import java.text.SimpleDateFormat;

public class FileOrganizer {

    static class FileInfo {
        String originalName;
        String baseName;
        String extension;
        String category;
        String newName;
        String subCategory;
        boolean valid;

        FileInfo(String original) {
            this.originalName = original;
            this.baseName = "";
            this.extension = "";
            this.category = "Unknown";
            this.newName = "";
            this.subCategory = "None";
            this.valid = true;
        }
    }

    public static FileInfo extractFileComponents(String file) {
        FileInfo fi = new FileInfo(file);
        int dot = file.lastIndexOf('.');
        if (dot == -1 || dot == 0 || dot == file.length() - 1) {
            fi.valid = false;
            return fi;
        }
        fi.baseName = file.substring(0, dot);
        fi.extension = file.substring(dot + 1).toLowerCase();
        for (int i = 0; i < fi.baseName.length(); i++) {
            char c = fi.baseName.charAt(i);
            if (!(Character.isLetterOrDigit(c) || c == '_' || c == '-')) {
                fi.valid = false;
                break;
            }
        }
        return fi;
    }

    public static void categorizeFiles(FileInfo fi) {
        switch (fi.extension) {
            case "txt": case "doc": case "pdf": fi.category = "Document"; break;
            case "jpg": case "jpeg": case "png": case "gif": fi.category = "Image"; break;
            case "mp3": case "wav": fi.category = "Audio"; break;
            case "mp4": case "avi": fi.category = "Video"; break;
            case "zip": case "rar": fi.category = "Archive"; break;
            case "java": case "cpp": case "py": fi.category = "Code"; break;
            default: fi.category = "Unknown";
        }
    }

    public static void generateNewName(FileInfo fi, Map<String, Integer> counter) {
        if (!fi.valid) {
            fi.newName = "INVALID_NAME";
            return;
        }
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int count = counter.getOrDefault(fi.category, 0) + 1;
        counter.put(fi.category, count);
        StringBuilder sb = new StringBuilder();
        sb.append(fi.category).append("_").append(date).append("_").append(count).append(".").append(fi.extension);
        fi.newName = sb.toString();
    }

    public static void simulateContentAnalysis(FileInfo fi) {
        if (fi.category.equals("Document") && fi.baseName.toLowerCase().contains("resume")) fi.subCategory = "Resume";
        else if (fi.category.equals("Document") && fi.baseName.toLowerCase().contains("report")) fi.subCategory = "Report";
        else if (fi.category.equals("Code")) fi.subCategory = "Source Code";
        else fi.subCategory = "General";
    }

    public static void displayReport(List<FileInfo> files) {
        System.out.printf("%-20s %-12s %-12s %-20s %-12s\n", "Original Name", "Category", "Valid", "New Name", "SubCategory");
        for (FileInfo f : files) {
            System.out.printf("%-20s %-12s %-12s %-20s %-12s\n", f.originalName, f.category, f.valid, f.newName, f.subCategory);
        }
    }

    public static void generateBatchCommands(List<FileInfo> files) {
        System.out.println("\nBatch Rename Commands:");
        for (FileInfo f : files) {
            if (f.valid && !f.newName.equals("INVALID_NAME")) {
                System.out.println("rename " + f.originalName + " " + f.newName);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FileInfo> files = new ArrayList<>();
        Map<String, Integer> counter = new HashMap<>();
        System.out.println("Enter number of files:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("Enter filename:");
            String name = sc.nextLine();
            FileInfo fi = extractFileComponents(name);
            categorizeFiles(fi);
            generateNewName(fi, counter);
            simulateContentAnalysis(fi);
            files.add(fi);
        }
        displayReport(files);
        generateBatchCommands(files);
        sc.close();
    }
}
