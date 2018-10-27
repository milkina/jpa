alter table QUESTIONS add column approved TINYINT(1) not null default 0;

UPDATE QUESTIONS set approved=1;