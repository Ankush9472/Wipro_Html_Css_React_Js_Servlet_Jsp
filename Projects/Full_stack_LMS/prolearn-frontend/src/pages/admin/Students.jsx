import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { mock } from '../../api/mock'

function Students() {
    const [arr, setArr] = useState([])
    const [show, setShow] = useState(false)
    const [edit, setEdit] = useState(null)
    const [s, setS] = useState('')
    const [busy, setBusy] = useState(false)
    const [err, setErr] = useState('')
    const [msg, setMsg] = useState('')
    const [obj, setObj] = useState({ name: '', email: '', rollNumber: '', phone: '', city: '' })

    useEffect(() => {
        run()
    }, [])

    const run = async () => {
        setBusy(true)
        try {
            const r = await mock.listStudents()
            setArr(r)
        } catch (e) {
            console.log(e)
        }
        setBusy(false)
    }

    const m1 = () => {
        setEdit(null)
        setObj({ name: '', email: '', rollNumber: '', phone: '', city: '' })
        setErr('')
        setShow(true)
    }

    const m2 = (st) => {
        setEdit(st)
        setObj({
            name: st.name,
            email: st.email,
            rollNumber: st.rollNumber,
            phone: st.phone || '',
            city: st.city || ''
        })
        setErr('')
        setShow(true)
    }

    const fun = async (e) => {
        e.preventDefault()
        setErr('')
        try {
            if (edit) {
                await mock.editStudent(edit.id, obj)
                setMsg('Student updated successfully')
            } else {
                await mock.addStudent(obj)
                setMsg('Student added successfully')
            }
            setShow(false)
            run()
            setTimeout(() => setMsg(''), 3000)
        } catch (ex) {
            setErr(ex.message)
        }
    }

    const check = async (id) => {
        if (!window.confirm('Are you sure you want to delete this student?')) return
        try {
            await mock.delStudent(id)
            setMsg('Student deleted successfully')
            run()
            setTimeout(() => setMsg(''), 3000)
        } catch (e) {
            alert('Failed to delete')
        }
    }

    const res = arr.filter((x) =>
        x.name.toLowerCase().includes(s.toLowerCase()) ||
        x.rollNumber.toLowerCase().includes(s.toLowerCase()) ||
        x.email.toLowerCase().includes(s.toLowerCase()) ||
        (x.city || '').toLowerCase().includes(s.toLowerCase())
    )

    return (
        <div>
            <div className="page-header">
                <div>
                    <div className="page-title">Student Management</div>
                    <div className="page-subtitle">Total: {arr.length} students registered</div>
                </div>
                <button className="btn btn-orange" onClick={m1}>+ Add New Student</button>
            </div>

            {msg && <div className="alert alert-success">{msg}</div>}

            <div className="box">
                <input
                    type="text"
                    placeholder="Search by name, roll number, email or city..."
                    value={s}
                    onChange={(e) => setS(e.target.value)}
                    style={{ width: '100%', padding: '8px 10px', border: '1px solid #d0d7de', borderRadius: 3 }}
                />
            </div>

            {busy ? (
                <div className="loading">Loading students...</div>
            ) : (
                <table className="tbl">
                    <thead>
                    <tr>
                        <th>Roll No</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>City</th>
                        <th>Courses</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {res.length === 0 ? (
                        <tr>
                            <td colSpan="7" className="empty">No students found</td>
                        </tr>
                    ) : (
                        res.map((st) => (
                            <tr key={st.id}>
                                <td><strong>{st.rollNumber}</strong></td>
                                <td>{st.name}</td>
                                <td>{st.email}</td>
                                <td>{st.phone}</td>
                                <td>{st.city || '-'}</td>
                                <td>
                                    <span className="tag tag-green">
                                        {st.courses?.length || 0}
                                    </span>
                                </td>
                                <td>
                                    <Link
                                        to={'/admin/enrollments?student=' + st.id}
                                        className="btn btn-primary btn-small"
                                        style={{ marginRight: 4 }}
                                    >
                                        Enroll
                                    </Link>
                                    <button
                                        className="btn btn-orange btn-small"
                                        style={{ marginRight: 4 }}
                                        onClick={() => m2(st)}
                                    >
                                        Edit
                                    </button>
                                    <button
                                        className="btn btn-red btn-small"
                                        onClick={() => check(st.id)}
                                    >
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))
                    )}
                    </tbody>
                </table>
            )}

            {show && (
                <div className="modal-bg" onClick={() => setShow(false)}>
                    <div className="modal-box" onClick={(e) => e.stopPropagation()}>
                        <div className="modal-head">
                            <h3>{edit ? 'Edit Student' : 'Add New Student'}</h3>
                            <button className="modal-close" onClick={() => setShow(false)}>×</button>
                        </div>

                        {err && <div className="alert alert-error">{err}</div>}

                        <form onSubmit={fun}>
                            <div className="field">
                                <label>Full Name *</label>
                                <input type="text" required
                                       value={obj.name}
                                       onChange={(e) => setObj({ ...obj, name: e.target.value })}
                                       placeholder="e.g. Aarav Patel"/>
                            </div>
                            <div className="field-row">
                                <div className="field">
                                    <label>Roll Number *</label>
                                    <input type="text" required
                                           value={obj.rollNumber}
                                           onChange={(e) => setObj({ ...obj, rollNumber: e.target.value })}
                                           placeholder="e.g. STU010"/>
                                </div>
                                <div className="field">
                                    <label>Phone *</label>
                                    <input type="tel" required
                                           value={obj.phone}
                                           onChange={(e) => setObj({ ...obj, phone: e.target.value })}
                                           placeholder="10-digit number"/>
                                </div>
                            </div>
                            <div className="field">
                                <label>Email *</label>
                                <input type="email" required
                                       value={obj.email}
                                       onChange={(e) => setObj({ ...obj, email: e.target.value })}
                                       placeholder="e.g. student@gmail.com"/>
                            </div>
                            <div className="field">
                                <label>City</label>
                                <input type="text"
                                       value={obj.city}
                                       onChange={(e) => setObj({ ...obj, city: e.target.value })}
                                       placeholder="e.g. Mumbai"/>
                            </div>

                            <div className="modal-actions">
                                <button type="button" className="btn btn-gray" onClick={() => setShow(false)}>
                                    Cancel
                                </button>
                                <button type="submit" className="btn btn-primary">
                                    {edit ? 'Update Student' : 'Save Student'}
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </div>
    )
}

import withAuth from '../../hoc/withAuth'
import withLayout from '../../hoc/withLayout'

export default withAuth(withLayout(Students), true)
