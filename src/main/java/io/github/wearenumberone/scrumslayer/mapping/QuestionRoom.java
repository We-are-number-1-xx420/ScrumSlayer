package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.Question;
import io.github.wearenumberone.scrumslayer.entities.QuestionMonster;

public class QuestionRoom extends Room {
    private Question question;

    public QuestionRoom(World parent){
        super(parent);
    }

    public boolean tryAnswer(String input){
        return this.question.isCorrectAnswer(Integer.parseInt(input));
    }

    public void summonMonster(){
        this.parent.setMovementStatus(true);
        //ToDo: pop up monster and start follow up puzzle
    }

    public void killMonster(){
        this.entities.removeIf(QuestionMonster.class::isInstance);
        this.parent.setMovementStatus(false);
    }

}