CREATE TABLE public.t_salary
(
    user_id varchar(255) NOT NULL,
    hourly_wage float8 NULL,
    CONSTRAINT t_salary_pkey PRIMARY KEY (user_id)
);

CREATE TABLE public.t_timelog
(
    id int8 NOT NULL,
    "comment"    varchar(255) NULL,
    "date"       date         NULL,
    date_added   timestamp    NULL,
    date_updated timestamp    NULL,
    end_time     time         NULL,
    start_time   time         NULL,
    user_id      varchar(255) NULL,
    "version" int2 NOT NULL,
    CONSTRAINT t_timelog_pkey PRIMARY KEY (id)
);

CREATE TABLE public.t_user
(
    id            varchar(255) NOT NULL,
    city          varchar(255) NULL,
    employer_id   varchar(255) NULL,
    first_name    varchar(255) NULL,
    kind          varchar(255) NULL,
    last_name     varchar(255) NULL,
    street        varchar(255) NULL,
    street_number varchar(255) NULL,
    zip           varchar(255) NULL,
    CONSTRAINT t_user_pkey PRIMARY KEY (id)
);
