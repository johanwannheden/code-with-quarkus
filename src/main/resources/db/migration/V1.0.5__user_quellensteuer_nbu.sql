-- quellensteuer
ALTER TABLE public.t_user
    ADD quellensteuer_required boolean;

UPDATE public.t_user
SET quellensteuer_required = true
WHERE id in ('ee66927c-a75f-4310-8582-a68f9dd96040','5d10a7cf-9b34-4990-bd39-0a7b9196aecb');

-- nbu
ALTER TABLE public.t_user
    ADD nbu_required boolean;

UPDATE public.t_user
SET nbu_required = false
WHERE id = 'ee66927c-a75f-4310-8582-a68f9dd96040';

UPDATE public.t_user
SET nbu_required = false
WHERE id = 'ee66927c-a75f-4310-8582-a68f9dd96040';

UPDATE public.t_user
SET nbu_required = true
WHERE id = '5d10a7cf-9b34-4990-bd39-0a7b9196aecb';
