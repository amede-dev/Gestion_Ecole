# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Oxc](https://oxc.rs)
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/)

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.
## Configuration Supabase

La connexion administrative utilise Supabase Auth : le mot de passe est stocké et vérifié par Supabase, jamais dans le code React.

1. Copiez `.env.example` vers `.env.local` et renseignez l’URL du projet ainsi que la clé `anon` publique.
2. Dans Supabase, allez dans **Authentication > Users > Add user** et créez l’utilisateur `admin@gestionecole.local` avec le mot de passe `admin123`. Le mot `admin` saisi dans l’application est automatiquement converti en cette adresse.
3. Exécutez [supabase-setup.sql](supabase-setup.sql) dans le SQL Editor. Il active RLS et autorise uniquement les utilisateurs connectés à accéder à `mytable`.

Ne mettez jamais la clé `service_role` dans `.env.local` ou dans le code frontend.
