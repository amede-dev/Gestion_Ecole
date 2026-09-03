-- À exécuter dans Supabase SQL Editor.
-- Crée la table si elle n'existe pas encore, puis active sa protection RLS.
create table if not exists public.mytable (
  id integer primary key,
  nom text not null,
  prenom text not null,
  mention text,
  parcour text,
  niveau text,
  date_naissance date,
  telephone text,
  argent integer default 0
);

alter table public.mytable enable row level security;

drop policy if exists "authenticated users can read students" on public.mytable;
create policy "authenticated users can read students"
  on public.mytable for select to authenticated using (true);

drop policy if exists "authenticated users can add students" on public.mytable;
create policy "authenticated users can add students"
  on public.mytable for insert to authenticated with check (true);

drop policy if exists "authenticated users can update students" on public.mytable;
create policy "authenticated users can update students"
  on public.mytable for update to authenticated using (true) with check (true);

drop policy if exists "authenticated users can delete students" on public.mytable;
create policy "authenticated users can delete students"
  on public.mytable for delete to authenticated using (true);
