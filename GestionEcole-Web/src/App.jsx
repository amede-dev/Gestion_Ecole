import { useMemo, useState } from 'react'
import './App.css'

const emptyStudent = { id: '', nom: '', prenom: '', mention: '', parcour: '', niveau: 'L1', date_naissance: '', telephone: '', argent: '' }
const demoStudents = [{ id: 1, nom: 'Rakoto', prenom: 'Aina', mention: 'Informatique', parcour: 'Développement', niveau: 'L1', date_naissance: '2003-05-14', telephone: '0340000000', argent: 150000 }]

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(() => sessionStorage.getItem('gestion-ecole-auth') === 'true')
  const [login, setLogin] = useState({ identifiant: '', password: '' })
  const [showPassword, setShowPassword] = useState(false)
  const [loginError, setLoginError] = useState('')
  const [students, setStudents] = useState(demoStudents)
  const [form, setForm] = useState(emptyStudent)
  const [search, setSearch] = useState('')
  const [editing, setEditing] = useState(false)
  const [message, setMessage] = useState('Mode démo local')

  const visibleStudents = useMemo(() => {
    const term = search.trim().toLowerCase()
    return term ? students.filter((student) => Object.values(student).some((value) => String(value).toLowerCase().includes(term))) : students
  }, [search, students])

  function handleLogin(event) {
    event.preventDefault()
    if (login.identifiant.trim().toLowerCase() !== 'admin' || login.password !== 'admin123') {
      setLoginError('Identifiant ou mot de passe incorrect.')
      return
    }
    sessionStorage.setItem('gestion-ecole-auth', 'true')
    setIsAuthenticated(true)
    setLoginError('')
  }

  function handleLogout() {
    sessionStorage.removeItem('gestion-ecole-auth')
    setIsAuthenticated(false)
    setLogin({ identifiant: '', password: '' })
  }

  function updateField(event) {
    const { name, value } = event.target
    setForm((current) => ({ ...current, [name]: value }))
  }

  function clearForm() {
    setForm(emptyStudent)
    setEditing(false)
    setMessage('Formulaire effacé')
  }

  function saveStudent(event) {
    event.preventDefault()
    if (!form.id || !form.nom || !form.prenom || !form.telephone || !form.date_naissance) {
      setMessage('Complète les champs obligatoires : ID, nom, prénom, téléphone et date.')
      return
    }
    const student = { ...form, id: Number(form.id), argent: Number(form.argent || 0) }
    if (editing) {
      setStudents((current) => current.map((item) => (item.id === student.id ? student : item)))
      setMessage('Étudiant modifié avec succès')
    } else if (students.some((item) => item.id === student.id)) {
      setMessage('Cet ID existe déjà')
      return
    } else {
      setStudents((current) => [...current, student])
      setMessage('Étudiant ajouté avec succès')
    }
    clearForm()
  }

  function selectStudent(student) {
    setForm({ ...student, argent: String(student.argent ?? '') })
    setEditing(true)
    setMessage(`Modification de ${student.nom} ${student.prenom}`)
  }

  function deleteStudent(id) {
    setStudents((current) => current.filter((student) => student.id !== id))
    if (Number(form.id) === id) clearForm()
    setMessage('Étudiant supprimé')
  }

  if (!isAuthenticated) {
    return (
      <main className="login-page">
        <section className="login-card" aria-labelledby="login-title">
          <div className="login-brand"><span className="login-icon" aria-hidden="true">🎓</span><span>GESTION D&apos;ÉCOLE</span></div>
          <div className="login-heading"><p className="eyebrow">ESPACE ADMINISTRATIF</p><h1 id="login-title">Bienvenue</h1><p>Connectez-vous pour gérer les étudiants et les données de votre établissement.</p></div>
          <form className="login-form" onSubmit={handleLogin}>
            <label htmlFor="identifiant">Identifiant<input id="identifiant" name="identifiant" value={login.identifiant} onChange={(event) => { setLogin({ ...login, identifiant: event.target.value }); setLoginError('') }} placeholder="Votre identifiant" autoComplete="username" autoFocus required /></label>
            <label htmlFor="password">Mot de passe<div className="password-field"><input id="password" name="password" value={login.password} onChange={(event) => { setLogin({ ...login, password: event.target.value }); setLoginError('') }} type={showPassword ? 'text' : 'password'} placeholder="Votre mot de passe" autoComplete="current-password" required /><button type="button" onClick={() => setShowPassword(!showPassword)} aria-label={showPassword ? 'Masquer le mot de passe' : 'Afficher le mot de passe'}>{showPassword ? 'Masquer' : 'Afficher'}</button></div></label>
            {loginError && <p className="login-error" role="alert">{loginError}</p>}
            <button className="login-button" type="submit">Se connecter <span aria-hidden="true">→</span></button>
          </form>
          <p className="login-note"><span aria-hidden="true">🔒</span> Accès réservé aux administrateurs</p>
        </section>
      </main>
    )
  }

  return (
    <main className="app-shell">
      <header className="app-header"><span className="header-icon" aria-hidden="true">🎓</span><div><h1>GESTION D&apos;ÉCOLE</h1><p>Inscription et suivi des étudiants</p></div><button className="logout-button" type="button" onClick={handleLogout}>Déconnexion</button></header>
      <div className="content-grid">
        <section className="card form-card">
          <h2>Inscrire un étudiant</h2>
          <form onSubmit={saveStudent}><div className="form-grid">
            <label>ID<input name="id" value={form.id} onChange={updateField} type="number" /></label>
            <label>Nom<input name="nom" value={form.nom} onChange={updateField} /></label>
            <label>Prénom<input name="prenom" value={form.prenom} onChange={updateField} /></label>
            <label>Mention<input name="mention" value={form.mention} onChange={updateField} /></label>
            <label>Parcours<input name="parcour" value={form.parcour} onChange={updateField} /></label>
            <label>Niveau<select name="niveau" value={form.niveau} onChange={updateField}>{['L1', 'L2', 'L3', 'M1', 'M2', 'Docteur'].map((level) => <option key={level}>{level}</option>)}</select></label>
            <label>Date de naissance<input name="date_naissance" value={form.date_naissance} onChange={updateField} type="date" /></label>
            <label>Téléphone<input name="telephone" value={form.telephone} onChange={updateField} /></label>
            <label>Argent<input name="argent" value={form.argent} onChange={updateField} type="number" min="0" /></label>
          </div><div className="action-row"><button className="button success" type="submit">{editing ? '✓ Modifier' : '＋ Ajouter'}</button><button className="button neutral" type="button" onClick={clearForm}>Effacer</button></div></form>
        </section>
        <section className="card table-card"><div className="table-toolbar"><div><h2>Liste des étudiants</h2><span className="count">{visibleStudents.length} résultat(s)</span></div><input className="search" placeholder="Rechercher..." value={search} onChange={(event) => setSearch(event.target.value)} /></div><p className="status">{message}</p>
          <div className="table-wrap"><table><thead><tr>{['ID', 'Nom', 'Prénom', 'Mention', 'Parcours', 'Niveau', 'Date', 'Téléphone', 'Argent', 'Action'].map((heading) => <th key={heading}>{heading}</th>)}</tr></thead><tbody>
            {visibleStudents.length === 0 ? <tr><td className="empty" colSpan="10">Aucun étudiant trouvé</td></tr> : visibleStudents.map((student) => <tr key={student.id} onClick={() => selectStudent(student)} className={Number(form.id) === student.id ? 'selected' : ''}><td>{student.id}</td><td>{student.nom}</td><td>{student.prenom}</td><td>{student.mention}</td><td>{student.parcour}</td><td>{student.niveau}</td><td>{student.date_naissance}</td><td>{student.telephone}</td><td>{student.argent.toLocaleString('fr-FR')} Ar</td><td><button className="delete-link" type="button" onClick={(event) => { event.stopPropagation(); deleteStudent(student.id) }}>Supprimer</button></td></tr>)}
          </tbody></table></div>
          <div className="student-list" aria-label="Liste mobile des étudiants">
            {visibleStudents.length === 0 ? <p className="empty">Aucun étudiant trouvé</p> : visibleStudents.map((student) => <article className={`student-card ${Number(form.id) === student.id ? 'selected' : ''}`} key={student.id} onClick={() => selectStudent(student)}>
              <div className="student-card-header"><div><strong>{student.prenom} {student.nom}</strong><span>ID {student.id} · {student.niveau}</span></div><button className="delete-button" type="button" onClick={(event) => { event.stopPropagation(); deleteStudent(student.id) }}>Supprimer</button></div>
              <dl><div><dt>Mention</dt><dd>{student.mention || '—'}</dd></div><div><dt>Parcours</dt><dd>{student.parcour || '—'}</dd></div><div><dt>Téléphone</dt><dd>{student.telephone}</dd></div><div><dt>Argent</dt><dd>{student.argent.toLocaleString('fr-FR')} Ar</dd></div></dl>
            </article>)}
          </div><small className="hint">Touchez une fiche pour la modifier.</small>
        </section>
      </div>
    </main>
  )
}

export default App
