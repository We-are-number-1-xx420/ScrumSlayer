package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.Question;

public class QuestionRoom extends Room {
    private Question question;

    public QuestionRoom(World parent){
        super(parent);
    }

    public boolean tryAnswer(String input){
        return this.question.isCorrectAnswer(Integer.parseInt(input));
    }

    public void summonMonster(){
        //do shit
    }

}