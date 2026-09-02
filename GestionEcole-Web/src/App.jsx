import { useMemo, useState } from 'react'
import './App.css'

const emptyStudent = { id: '', nom: '', prenom: '', mention: '', parcour: '', niveau: 'L1', date_naissance: '', telephone: '', argent: '' }
const demoStudents = [{ id: 1, nom: 'Rakoto', prenom: 'Aina', mention: 'Informatique', parcour: 'Développement', niveau: 'L1', date_naissance: '2003-05-14', telephone: '0340000000', argent: 150000 }]

function App() {
  const [students, setStudents] = useState(demoStudents)
  const [form, setForm] = useState(emptyStudent)
  const [search, setSearch] = useState('')
  const [editing, setEditing] = useState(false)
  const [message, setMessage] = useState('Mode démo local')

  const visibleStudents = useMemo(() => {
    const term = search.trim().toLowerCase()
    return term ? students.filter((student) => Object.values(student).some((value) => String(value).toLowerCase().includes(term))) : students
  }, [search, students])

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

  return (
    <main className="app-shell">
      <header className="app-header"><span className="header-icon">🎓</span><div><h1>GESTION D&apos;ÉCOLE</h1><p>Inscription et suivi des étudiants</p></div></header>
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
          </tbody></table></div><small className="hint">Clique sur une ligne pour la modifier.</small>
        </section>
      </div>
    </main>
  )
}

export default App
