package io.github.wearenumberone.scrumslayer.entities;

import io.github.wearenumberone.scrumslayer.Question;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionMonster extends LivingEntity{
    private static final Random  RANDOM = new Random();
    private List<Question> questionList = new ArrayList<>();

    public QuestionMonster(List<Question> questionList){
        super(1);
        this.questionList = new ArrayList<>(questionList);
    }

    public QuestionMonster(){
        super(1);
    }

    public Grid<StyledCharacter> render(){
        return Grid.ofSingle(new StyledCharacter('☠'));
    }

    public Question getRandomQuestion(){
        return this.questionList.get(RANDOM.nextInt(0, questionList.size()));
    }

    public void addQuestion(Question question){
        this.questionList.add(question);
    }

    public void removeQuestion(Question question){
        this.questionList.remove(question);
    }
}