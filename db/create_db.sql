create sequence hibernate_sequence;

create table task
(
	  id          bigint       not null
	    constraint task_pkey
	    primary key,
	  created_at  timestamp,
	  description varchar(255) not null,
	  due_date    timestamp,
	  priority    integer,
	  resolved_at timestamp,
	  status      integer      not null,
	  title       varchar(255) not null,
	  updated_at  timestamp
);


