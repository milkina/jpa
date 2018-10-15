CREATE TABLE question_answer(
    `entry_id` INT(11) NOT NULL AUTO_INCREMENT,
	`answer_id` INT(11) NOT NULL,
	`is_correct` TINYINT(1),
	PRIMARY KEY (`entry_id`)
)ENGINE=MyISAM;

ALTER TABLE question_answer ADD FOREIGN KEY (answer_id) REFERENCES ANSWER_TEXT (id);

ALTER TABLE ANSWER_TEXT add column is_correct TINYINT(1) DEFAULT 1;
ALTER TABLE ANSWER_TEXT add column entry_id INT(11);

insert into question_answer(entry_id, answer_id )
select entry_id, answer_id from QUESTIONS;

update ANSWER_TEXT set entry_id = (select qa.entry_id from question_answer qa where qa.answer_id=id);

ALTER TABLE QUESTIONS DROP COLUMN answer_id;

ALTER TABLE QUESTIONS add column TYPE VARCHAR(11);
UPDATE QUESTIONS set TYPE='QUESTION';

UPDATE question_answer set is_correct=1;

drop table question_answer;




CREATE TABLE `exam` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`date` DATE NULL DEFAULT NULL,
	`person_id` INT(11) NULL DEFAULT NULL,
	`percent` INT(11) NOT NULL,
	`TYPE` VARCHAR(31) NOT NULL,
	`category_id` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=MyISAM;

ALTER TABLE exam add foreign key (category_id) REFERENCES Category (id);

ALTER TABLE QUESTIONS ENGINE MyISAM;

ALTER TABLE QUESTIONS add foreign key (category_id) REFERENCES Category (id);