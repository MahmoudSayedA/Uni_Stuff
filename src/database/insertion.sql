use Uni_Stuff;
go
insert into Subject(name,code)
values('Software engineer advanced','csse2')
insert into Subject(name,code)
values('Database administration','isdb2')
insert into Subject(name,code)
values('File management','isfiles')
insert into Subject(name,code)
values('System analysis and design','issa&d')
insert into Subject(name,code)
values('Algorithms analysis and design','csalgo')
insert into Subject(name,code)
values('Operating system','csos')
insert into Subject(name,code)
values('data structure advanced','csds2')
insert into Subject(name,code)
values('Computer architecture','csca')
insert into Subject(name,code)
values('Data Compression','itdc')
insert into Subject(name,code)
values('Decision support','dsds')
insert into Subject(name,code)
values('Linear programming','dslp')
insert into Subject(name,code)
values('Learning from data','dslfd')
insert into Subject(name,code)
values('computational intelligence','dsci')
insert into Subject(name,code)
values('Data analytics','dsda')
insert into Subject(name,code)
values('Signals','its')
insert into Subject(name,code)
values('Data communication','itdc')
insert into Subject(name,code)
values('Network advanced','itn2');

select *from Subject where code like 'ds%'

insert into DepartmentSubject
values('is','isdb2')
insert into DepartmentSubject
values('is','issa&d')
insert into DepartmentSubject
values('is','isfiles')

insert into DepartmentSubject
values('it','csalgo')
insert into DepartmentSubject
values('it','csca')
insert into DepartmentSubject
values('cs','csds2')
insert into DepartmentSubject
values('ds','csos')
insert into DepartmentSubject
values('is','csse2')

insert into DepartmentSubject
values('it','itdc')

insert into DepartmentSubject
values('it','itn2')
insert into DepartmentSubject
values('it','its')

insert into DepartmentSubject
values('ds','dsci')
insert into DepartmentSubject
values('ds','dsda')
insert into DepartmentSubject
values('ds','dsds')
insert into DepartmentSubject
values('ds','dslfd')
insert into DepartmentSubject
values('ds','dslp')


select *from Subject
where Code like 'ds%'
delete from Subject where code='dsci'



