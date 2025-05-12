package io.github.wearenumberone.scrumslayer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Question {
    private final String question;
    private final Set<String> options = new HashSet<>();
    private final int answer;

    public Question(String question, String[] options, int answer) {
        this.question = question;
        this.options.addAll(Arrays.asList(options));
        this.answer = answer;
    }
    public Question(String question, String[] options, String answer) {
        this(question, options, Integer.parseInt(answer));
    }

    public boolean isCorrectAnswer(int answer) {
        return this.answer == answer;
    }
}