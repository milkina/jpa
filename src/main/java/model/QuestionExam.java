package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Tatyana on 29.04.2016.
 */
@Entity
@DiscriminatorValue("QUESTION")
public class QuestionExam extends AbstractExam {

}
