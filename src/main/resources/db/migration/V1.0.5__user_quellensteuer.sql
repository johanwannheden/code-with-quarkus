ALTER TABLE public.t_user
    ADD quellensteuerRequired SMALLINT;

UPDATE public.t_user
SET quellensteuerRequired = 0
WHERE id in ('5d10a7cf-9b34-4990-bd39-0a7b9196aecb','ee66927c-a75f-4310-8582-a68f9dd96040');
