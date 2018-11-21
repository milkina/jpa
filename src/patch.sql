drop table category_description;

CREATE TABLE exam_category(
	`exam_id` INT(11) NOT NULL,
	`category_id` INT(11) NOT NULL,
	`count` INT(3) NOT NULL
);

INSERT INTO exam_category
SELECT id,category_id,20 FROM exam;

ALTER TABLE exam drop column category_id;

ALTER TABLE exam_category ENGINE=MyISAM;

ALTER TABLE exam_category DROP column count;

ALTER TABLE exam ADD column amount INT(3);

UPDATE exam set amount=20;
