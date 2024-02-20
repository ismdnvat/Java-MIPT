import java.util.*;
import java.util.function.Predicate;

public class Student<T> {
    public Student(String name, ArrayList<T> grades, Predicate<T> isCorrectGrade) {
        this.name = name;
        this.grades = new ArrayList<>();
        for (T grade : grades) {
            if (!isCorrectGrade.test(grade)) {
                throw new IllegalArgumentException("Incorrect grade: " + grade);
            } else {
                this.grades.add(grade);
            }
        }
        this.nameHistory = new ArrayDeque<>();
        this.gradesHistory = new ArrayDeque<>();
        saveState();
    }
    public Student() {
        this("", new ArrayList<>(), grade -> true);
        saveState();
    }

    public Student(String name) {
        this(name, new ArrayList<>(), grade -> true);
        saveState();
    }

    public Student(String name, ArrayList<T> grades) {
        this(name, grades, grade -> true);
        saveState();
    }

    public Student(String name, Predicate<T> isCorrect) {
        this(name, new ArrayList<>(), isCorrect);
        saveState();
    }

    public void changeName(String name) {
        this.name = name;
        saveState();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<T> getGrades() { return new ArrayList<>(this.grades); }

    public void addGrade(T grade) {
        if (!this.isCorrectGrade.test(grade)) {
            throw new IllegalArgumentException("Incorrect grade: " + grade);
        } else {
            this.grades.add(grade);
        }
        saveState();
    }

    public void deleteGrade(T grade) {
        for (int i = this.grades.size() - 1; i >= 0; --i) {
            if (this.grades.get(i) == grade) {
                this.grades.remove(i);
            }
        }
        saveState();
    }

    public boolean isEqual(Student<T> another) {
        if (this.grades.size() != another.grades.size() ||
                !Objects.equals(another.name, this.name)) {
            return false;
        }
        for (int i = 0; i < grades.size(); ++i) {
            if (this.grades.get(i) != another.grades.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder textRepresentation;
        textRepresentation = new StringBuilder(this.name);
        String grade;
        textRepresentation.append(": ");
        textRepresentation.append("[");
        for (int i = 0; i < this.grades.size(); ++i) {
            grade = this.grades.get(i).toString();
            textRepresentation.append(grade);
            if (i != this.grades.size() - 1) {
                textRepresentation.append(", ");
            }
        }
        textRepresentation.append("]");
        return textRepresentation.toString();
    }

    public void undoLastAction() {
        if (!nameHistory.isEmpty() && !gradesHistory.isEmpty()) {
            name = nameHistory.pop();
            grades = gradesHistory.pop();
        }
    }

    private void saveState() {
        nameHistory.push(name);
        gradesHistory.push(new ArrayList<>(grades));
    }

    private String name;
    private ArrayList<T> grades;
    private Predicate<T> isCorrectGrade;
    private final Deque<String> nameHistory;
    private final Deque<ArrayList<T>> gradesHistory;
}
