ALTER TABLE public.t_salary
    ADD id varchar(255);

UPDATE
    public.t_salary
SET id = (
    SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring))
WHERE id IS null;

ALTER TABLE public.t_salary
    ALTER COLUMN id SET NOT null;

ALTER TABLE public.t_salary
    DROP CONSTRAINT t_salary_pkey;

ALTER TABLE public.t_salary
    ADD CONSTRAINT t_salary_pkey PRIMARY KEY (id);

ALTER TABLE public.t_salary
    ADD valid_from date;

ALTER TABLE public.t_salary
    ALTER COLUMN valid_from SET NOT null;

ALTER TABLE public.t_salary
    ADD valid_until date;
