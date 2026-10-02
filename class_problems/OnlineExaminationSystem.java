import java.util.*;

abstract class Question {
    private int number;
    private String text;
    private int marks;
    Question(int number, String text, int marks) {
        this.number = number;
        this.text = text;
        this.marks = marks;
    }
    int getNumber() {
        return number;
    }
    int getMarks() {
        return marks;
    }
    abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;
    MultipleChoiceQuestion(int number, String text, int marks, String correctAnswer) {
        super(number, text, marks);
        this.correctAnswer = correctAnswer;
    }
    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;
    TrueFalseQuestion(int number, String text, int marks, boolean correctAnswer) {
        super(number, text, marks);
        this.correctAnswer = correctAnswer;
    }
    boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class ShortAnswerQuestion extends Question {
    private String correctAnswer;
    ShortAnswerQuestion(int number, String text, int marks, String correctAnswer) {
        super(number, text, marks);
        this.correctAnswer = correctAnswer;
    }
    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class Student {
    private String name;
    Student(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
}

class Examination {
    private String name;
    private List<Question> questions;
    Examination(String name) {
        this.name = name;
        questions = new ArrayList<>();
    }
    void addQuestion(Question question) {
        questions.add(question);
    }
    String getName() {
        return name;
    }
    List<Question> getQuestions() {
        return questions;
    }
    int getTotalMarks() {
        int total = 0;
        for (Question question : questions) {
            total += question.getMarks();
        }
        return total;
    }
}
enum AttemptStatus {
    InProgress, Submitted
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Integer, String> answers;
    private AttemptStatus status;
    Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        answers = new HashMap<>();
        status = AttemptStatus.InProgress;
    }
    void start() {
        System.out.println(examination.getName() +
                " started by " + student.getName() + ".");
    }
    void answer(int questionNumber, String answer) {
        if (status == AttemptStatus.Submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        answers.put(questionNumber, answer);
        System.out.println("Answer recorded for Question " + questionNumber + ".");
    }
    void submit() {
        if (status == AttemptStatus.Submitted) {
            return;
        }
        status = AttemptStatus.Submitted;
        System.out.println(examination.getName() +
                " submitted by " + student.getName() + ".");
        int score = 0;
        for (Question question : examination.getQuestions()) {
            String answer = answers.get(question.getNumber());
            if (answer != null && question.evaluate(answer)) {
                score += question.getMarks();
                System.out.println("Result: Question " + question.getNumber() +
                        ": Correct (" + question.getMarks() + " points)");
            } else {
                System.out.println("Result: Question " + question.getNumber() +
                        ": Incorrect (0 points)");
            }
        }
        System.out.println("Total score: " + score +
                "/" + examination.getTotalMarks());
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Student student = new Student("Student 1");
        Examination exam = new Examination("Exam A");
        exam.addQuestion(new MultipleChoiceQuestion(
                1, "Which is correct?", 5, "C"));
        exam.addQuestion(new TrueFalseQuestion(
                2, "Java is a programming language.", 5, false));
        Attempt attempt = new Attempt(student, exam);
        attempt.start();
        attempt.answer(1, "C");
        attempt.answer(2, "True");
        attempt.submit();
        attempt.answer(1, "A");
    }
}
