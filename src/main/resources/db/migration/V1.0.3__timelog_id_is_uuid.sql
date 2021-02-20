CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE public.t_timelog
    ALTER COLUMN id TYPE varchar(255);

UPDATE public.t_timelog
SET id = uuid_generate_v1();
