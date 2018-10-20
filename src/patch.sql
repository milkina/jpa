alter table questions add column QTYPE varchar(11) not null;
update questions set QTYPE=`TYPE`;
alter table	category add column `qcount` INT(11) NOT NULL DEFAULT '0';
alter table	category add column `tcount` INT(11) NOT NULL DEFAULT '0';

 update category as c1,
 (
 select count(*) as cc,category.id from category
inner join questions on category.id=questions.category_id
 where questions.QTYPE='TEST'
 group by category.name ) as src
  set c1.tcount=src.cc;

  update category as c1,
  (
  select count(*) as cc,category.id from category
 inner join questions on category.id=questions.category_id
  where questions.QTYPE='QUESTION'
  group by category.name ) as src
   set c1.qcount=src.cc;

   alter table questions drop column `TYPE`;