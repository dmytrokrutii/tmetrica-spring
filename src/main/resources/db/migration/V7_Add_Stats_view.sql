CREATE OR REPLACE VIEW public.stats_view
AS
SELECT a.id AS activity_id,
       a.name AS activity_name,
       a.status AS activity_status,
       sum(l.end_time - l.start_time) AS "time",
       l.user_id
FROM activities a
         JOIN activities_logs l ON a.id = l.activity_id
GROUP BY a.id, a.name, l.user_id;

ALTER TABLE public.stats_view
    OWNER TO postgres;