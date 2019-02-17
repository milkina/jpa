package spring.services.answer;

import model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.repositories.AnswerRepository;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer getAnswer(int id) {
        return answerRepository.findOne(id);
    }

    public void removeAnswer(Answer answer) {
        answerRepository.delete(answer.getId());
    }

    @Transactional
    @Override
    public void removeAnswers(List<Answer> answers) {
        for (Answer answer : answers) {
            removeAnswer(answer);
        }
    }
}
