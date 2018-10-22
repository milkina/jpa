alter table QUESTIONS add column QTYPE varchar(11) not null;
update QUESTIONS set QTYPE=`TYPE`;
alter table	Category add column `qcount` INT(11) NOT NULL DEFAULT '0';
alter table	Category add column `tcount` INT(11) NOT NULL DEFAULT '0';

 update  Category as c1,
 (
 select count(*) as cc,Category.id from Category
inner join QUESTIONS on Category.id=QUESTIONS.category_id
 where QUESTIONS.QTYPE='TEST'
 group by Category.id ) as src
  set c1.tcount=src.cc  where c1.id=src.id;

  update Category as c1,
  (
  select count(*) as cc,Category.id from Category
 inner join QUESTIONS on Category.id=QUESTIONS.category_id
  where QUESTIONS.QTYPE='QUESTION'
  group by Category.id ) as src
   set c1.qcount=src.cc  where c1.id=src.id;

   alter table questions drop column `TYPE`;