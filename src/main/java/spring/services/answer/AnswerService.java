package spring.services.answer;

import model.Answer;

import java.util.List;

public interface AnswerService {

    Answer getAnswer(int id);

    void removeAnswers(List<Answer> answers);
}
