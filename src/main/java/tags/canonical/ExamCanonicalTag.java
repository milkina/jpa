package tags.canonical;

import model.AbstractExam;
import model.AbstractQuestionEntry;
import model.Category;

import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;

public class ExamCanonicalTag extends CanonicalTag {
    @Override
    public Category getCategory() {
        AbstractExam exam = (AbstractExam)
                pageContext.getSession().getAttribute(CURRENT_EXAM_ATTRIBUTE);
        AbstractQuestionEntry questionEntry = exam.getCurrentQuestionEntry();
        return questionEntry.getCategory();
    }
}
