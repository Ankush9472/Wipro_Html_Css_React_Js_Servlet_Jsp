import { useEffect, useState } from 'react'
import { mock } from '../../api/mock'

function Courses() {
    const [arr, setArr] = useState([])
    const [show, setShow] = useState(false)
    const [edit, setEdit] = useState(null)
    const [s, setS] = useState('')
    const [cat, setCat] = useState('All')
    const [busy, setBusy] = useState(false)
    const [err, setErr] = useState('')
    const [msg, setMsg] = useState('')
    const [obj, setObj] = useState({
        code: '', title: '', description: '', category: '',
        instructor: '', duration: '', fee: 0, courseLink: ''
    })

    useEffect(() => {
        run()
    }, [])

    const run = async () => {
        setBusy(true)
        try {
            const r = await mock.listCourses()
            setArr(r)
        } catch (e) {
            console.log(e)
        }
        setBusy(false)
    }

    const m1 = () => {
        setEdit(null)
        setObj({
            code: '', title: '', description: '', category: '',
            instructor: '', duration: '', fee: 0, courseLink: ''
        })
        setErr('')
        setShow(true)
    }

    const m2 = (c) => {
        setEdit(c)
        setObj({
            code: c.code, title: c.title, description: c.description || '',
            category: c.category, instructor: c.instructor, duration: c.duration,
            fee: c.fee || 0, courseLink: c.courseLink || ''
        })
        setErr('')
        setShow(true)
    }

    const fun = async (e) => {
        e.preventDefault()
        setErr('')
        try {
            if (edit) {
                await mock.editCourse(edit.id, obj)
                setMsg('Course updated successfully')
            } else {
                await mock.addCourse(obj)
                setMsg('Course added successfully')
            }
            setShow(false)
            run()
            setTimeout(() => setMsg(''), 3000)
        } catch (ex) {
            setErr(ex.message)
        }
    }

    const check = async (id) => {
        if (!window.confirm('Delete this course? All enrollments will also be removed.')) return
        try {
            await mock.delCourse(id)
            setMsg('Course deleted successfully')
            run()
            setTimeout(() => setMsg(''), 3000)
        } catch (e) {
            alert('Failed to delete')
        }
    }

    const cats = ['All', ...new Set(arr.map((c) => c.category))]

    const res = arr.filter((c) => {
        const a = c.title.toLowerCase().includes(s.toLowerCase()) ||
            c.code.toLowerCase().includes(s.toLowerCase()) ||
            c.instructor.toLowerCase().includes(s.toLowerCase())
        const b = cat === 'All' || c.category === cat
        return a && b
    })

    return (
        <div>
            <div className="page-header">
                <div>
                    <div className="page-title">Course Management</div>
                    <div className="page-subtitle">Total: {arr.length} courses available</div>
                </div>
                <button className="btn btn-orange" onClick={m1}>+ Add New Course</button>
            </div>

            {msg && <div className="alert alert-success">{msg}</div>}

            <div className="box">
                <input
                    type="text"
                    placeholder="Search by title, code or instructor..."
                    value={s}
                    onChange={(e) => setS(e.target.value)}
                    style={{ width: '100%', padding: '8px 10px', border: '1px solid #d0d7de', borderRadius: 3, marginBottom: 10 }}
                />
                <div>
                    <span style={{ fontSize: 13, color: '#666', marginRight: 8 }}>Filter:</span>
                    {cats.map((x) => (
                        <button
                            key={x}
                            className={'btn btn-small ' + (cat === x ? 'btn-primary' : 'btn-gray')}
                            style={{ marginRight: 5 }}
                            onClick={() => setCat(x)}
                        >
                            {x}
                        </button>
                    ))}
                </div>
            </div>

            {busy ? (
                <div className="loading">Loading courses...</div>
            ) : (
                <table className="tbl">
                    <thead>
                    <tr>
                        <th>Code</th>
                        <th>Title</th>
                        <th>Category</th>
                        <th>Instructor</th>
                        <th>Duration</th>
                        <th>Fee (₹)</th>
                        <th>Link</th>
                        <th>Enrolled</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {res.length === 0 ? (
                        <tr>
                            <td colSpan="9" className="empty">No courses found</td>
                        </tr>
                    ) : (
                        res.map((c) => (
                            <tr key={c.id}>
                                <td><strong>{c.code}</strong></td>
                                <td>{c.title}</td>
                                <td><span className="tag tag-orange">{c.category}</span></td>
                                <td>{c.instructor}</td>
                                <td>{c.duration}</td>
                                <td>₹{c.fee?.toLocaleString('en-IN') || 0}</td>
                                <td>
                                    {c.courseLink ? (
                                        <a href={c.courseLink} target="_blank" rel="noreferrer"
                                           style={{ color: '#ff8c42', fontWeight: 'bold' }}>
                                            Open ↗
                                        </a>
                                    ) : (
                                        <span style={{ color: '#999', fontSize: 12 }}>—</span>
                                    )}
                                </td>
                                <td><span className="tag tag-green">{c.studentCount}</span></td>
                                <td>
                                    <button
                                        className="btn btn-orange btn-small"
                                        style={{ marginRight: 4 }}
                                        onClick={() => m2(c)}
                                    >
                                        Edit
                                    </button>
                                    <button
                                        className="btn btn-red btn-small"
                                        onClick={() => check(c.id)}
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
                    <div className="modal-box" onClick={(e) => e.stopPropagation()} style={{ maxWidth: 580 }}>
                        <div className="modal-head">
                            <h3>{edit ? 'Edit Course' : 'Add New Course'}</h3>
                            <button className="modal-close" onClick={() => setShow(false)}>×</button>
                        </div>

                        {err && <div className="alert alert-error">{err}</div>}

                        <form onSubmit={fun}>
                            <div className="field-row">
                                <div className="field">
                                    <label>Course Code *</label>
                                    <input type="text" required
                                           value={obj.code}
                                           onChange={(e) => setObj({ ...obj, code: e.target.value })}
                                           placeholder="e.g. JFS101"/>
                                </div>
                                <div className="field">
                                    <label>Category *</label>
                                    <input type="text" required
                                           value={obj.category}
                                           onChange={(e) => setObj({ ...obj, category: e.target.value })}
                                           placeholder="e.g. Backend"/>
                                </div>
                            </div>
                            <div className="field">
                                <label>Title *</label>
                                <input type="text" required
                                       value={obj.title}
                                       onChange={(e) => setObj({ ...obj, title: e.target.value })}
                                       placeholder="e.g. Java Full Stack Development"/>
                            </div>
                            <div className="field">
                                <label>Description</label>
                                <textarea rows="3"
                                          value={obj.description}
                                          onChange={(e) => setObj({ ...obj, description: e.target.value })}
                                          placeholder="Brief course description"/>
                            </div>
                            <div className="field-row">
                                <div className="field">
                                    <label>Instructor *</label>
                                    <input type="text" required
                                           value={obj.instructor}
                                           onChange={(e) => setObj({ ...obj, instructor: e.target.value })}
                                           placeholder="e.g. Rohit Kumar"/>
                                </div>
                                <div className="field">
                                    <label>Duration *</label>
                                    <input type="text" required
                                           value={obj.duration}
                                           onChange={(e) => setObj({ ...obj, duration: e.target.value })}
                                           placeholder="e.g. 8 Weeks"/>
                                </div>
                            </div>
                            <div className="field">
                                <label>Fee (₹)</label>
                                <input type="number" min="0"
                                       value={obj.fee}
                                       onChange={(e) => setObj({ ...obj, fee: parseInt(e.target.value) || 0 })}/>
                            </div>
                            <div className="field">
                                <label>Course Link (YouTube / Udemy URL)</label>
                                <input type="url"
                                       value={obj.courseLink}
                                       onChange={(e) => setObj({ ...obj, courseLink: e.target.value })}
                                       placeholder="https://youtube.com/... or https://udemy.com/..."/>
                            </div>

                            <div className="modal-actions">
                                <button type="button" className="btn btn-gray" onClick={() => setShow(false)}>
                                    Cancel
                                </button>
                                <button type="submit" className="btn btn-primary">
                                    {edit ? 'Update Course' : 'Save Course'}
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

export default withAuth(withLayout(Courses), true)