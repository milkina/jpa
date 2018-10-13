CREATE TABLE question_answer(
    `entry_id` INT(11) NOT NULL AUTO_INCREMENT,
	`answer_id` INT(11) NOT NULL,
	`is_correct` TINYINT(1),
	PRIMARY KEY (`entry_id`)
);

ALTER TABLE question_answer ADD FOREIGN KEY (answer_id) REFERENCES answer_text (id);

ALTER TABLE answer_text add column is_correct TINYINT(1) DEFAULT 1;
ALTER TABLE answer_text add column entry_id INT(11);

insert into question_answer(entry_id, answer_id )
select entry_id, answer_id from questions;

update answer_text set entry_id = (select qa.entry_id from question_answer qa where qa.answer_id=id)

ALTER TABLE questions DROP COLUMN answer_id;

UPDATE questions set TYPE='QUESTION';

UPDATE question_answer set is_correct=1;

drop table question_answer;