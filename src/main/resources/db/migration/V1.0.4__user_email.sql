ALTER TABLE public.t_user
    ADD email varchar(255) not null default first_name || '.' || last_name || '@gmail.com';
