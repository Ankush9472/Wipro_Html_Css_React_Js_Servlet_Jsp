import { useEffect, useState } from 'react'
import { useSearchParams } from 'react-router-dom'
import { mock } from '../../api/mock'

function Enrollments() {
    const [params] = useSearchParams()
    const [arr, setArr] = useState([])
    const [nums, setNums] = useState([])
    const [sel, setSel] = useState(null)
    const [s, setS] = useState('')
    const [busy, setBusy] = useState(false)
    const [msg, setMsg] = useState('')

    useEffect(() => {
        run()
    }, [])

    useEffect(() => {
        const x = params.get('student')
        if (x && arr.length > 0) {
            const found = arr.find((a) => a.id === parseInt(x))
            if (found) setSel(found)
        }
    }, [params, arr])

    const run = async () => {
        setBusy(true)
        try {
            const a = await mock.listStudents()
            setArr(a)
            const b = await mock.listCourses()
            setNums(b)
        } catch (e) {
            console.log(e)
        }
        setBusy(false)
    }

    const m1 = async (cId) => {
        try {
            const res = await mock.enroll(sel.id, cId)
            setSel(res)
            const a = await mock.listStudents()
            setArr(a)
            setMsg('Course enrolled successfully')
            setTimeout(() => setMsg(''), 2000)
        } catch (e) {
            alert(e.message || 'Failed to enroll')
        }
    }

    const m2 = async (cId) => {
        if (!window.confirm('Remove this course enrollment?')) return
        try {
            const res = await mock.unenroll(sel.id, cId)
            setSel(res)
            const a = await mock.listStudents()
            setArr(a)
            setMsg('Course removed successfully')
            setTimeout(() => setMsg(''), 2000)
        } catch (e) {
            alert('Failed to remove')
        }
    }

    const ids = sel?.courses?.map((c) => c.id) || []
    const enrolled = nums.filter((c) => ids.includes(c.id))
    const available = nums.filter((c) => !ids.includes(c.id))

    const res = arr.filter((st) =>
        st.name.toLowerCase().includes(s.toLowerCase()) ||
        st.rollNumber.toLowerCase().includes(s.toLowerCase())
    )

    return (
        <div>
            <div className="page-header">
                <div>
                    <div className="page-title">Enrollment Management</div>
                    <div className="page-subtitle">Assign or remove courses for students</div>
                </div>
            </div>

            {msg && <div className="alert alert-success">{msg}</div>}

            <div className="two-col">
                <div>
                    <div className="box">
                        <div className="box-title">Select a Student</div>
                        <input
                            type="text"
                            placeholder="Search students..."
                            value={s}
                            onChange={(e) => setS(e.target.value)}
                            style={{ width: '100%', padding: '7px 10px', border: '1px solid #d0d7de', borderRadius: 3, marginBottom: 10 }}
                        />
                        {busy ? (
                            <div className="loading">Loading...</div>
                        ) : res.length === 0 ? (
                            <div className="empty">No students</div>
                        ) : (
                            <div style={{ maxHeight: 500, overflowY: 'auto' }}>
                                {res.map((st) => (
                                    <div
                                        key={st.id}
                                        onClick={() => setSel(st)}
                                        style={{
                                            padding: '10px 12px',
                                            border: '1px solid #d0d7de',
                                            borderRadius: 3,
                                            marginBottom: 6,
                                            cursor: 'pointer',
                                            backgroundColor: sel?.id === st.id ? '#fff4ec' : 'white',
                                            borderColor: sel?.id === st.id ? '#ff8c42' : '#d0d7de'
                                        }}
                                    >
                                        <div style={{ fontWeight: 'bold', fontSize: 13 }}>{st.name}</div>
                                        <div style={{ fontSize: 12, color: '#666', marginTop: 2 }}>
                                            {st.rollNumber} · {st.courses?.length || 0} courses
                                        </div>
                                    </div>
                                ))}
                            </div>
                        )}
                    </div>
                </div>

                <div>
                    {!sel ? (
                        <div className="box">
                            <div className="empty">
                                <div style={{ fontSize: 40, marginBottom: 10 }}>👈</div>
                                <p>Please select a student from the left panel</p>
                            </div>
                        </div>
                    ) : (
                        <>
                            <div className="box">
                                <div className="box-title">Selected Student</div>
                                <table style={{ width: '100%' }}>
                                    <tbody>
                                    <tr>
                                        <td style={{ width: 120, color: '#666', fontSize: 13, padding: '4px 0' }}>
                                            Name:
                                        </td>
                                        <td style={{ fontSize: 13 }}><strong>{sel.name}</strong></td>
                                    </tr>
                                    <tr>
                                        <td style={{ color: '#666', fontSize: 13, padding: '4px 0' }}>Roll Number:</td>
                                        <td style={{ fontSize: 13 }}>{sel.rollNumber}</td>
                                    </tr>
                                    <tr>
                                        <td style={{ color: '#666', fontSize: 13, padding: '4px 0' }}>Email:</td>
                                        <td style={{ fontSize: 13 }}>{sel.email}</td>
                                    </tr>
                                    <tr>
                                        <td style={{ color: '#666', fontSize: 13, padding: '4px 0' }}>City:</td>
                                        <td style={{ fontSize: 13 }}>{sel.city || '-'}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>

                            <div className="box">
                                <div className="box-title">
                                    Enrolled Courses ({enrolled.length})
                                </div>
                                {enrolled.length === 0 ? (
                                    <div className="empty">No courses enrolled</div>
                                ) : (
                                    <table className="tbl">
                                        <thead>
                                        <tr>
                                            <th>Code</th>
                                            <th>Title</th>
                                            <th>Instructor</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {enrolled.map((c) => (
                                            <tr key={c.id}>
                                                <td><strong>{c.code}</strong></td>
                                                <td>{c.title}</td>
                                                <td>{c.instructor}</td>
                                                <td>
                                                    <button
                                                        className="btn btn-red btn-small"
                                                        onClick={() => m2(c.id)}
                                                    >
                                                        Remove
                                                    </button>
                                                </td>
                                            </tr>
                                        ))}
                                        </tbody>
                                    </table>
                                )}
                            </div>

                            <div className="box">
                                <div className="box-title">
                                    Available Courses ({available.length})
                                </div>
                                {available.length === 0 ? (
                                    <div className="empty">Student is enrolled in all available courses</div>
                                ) : (
                                    <table className="tbl">
                                        <thead>
                                        <tr>
                                            <th>Code</th>
                                            <th>Title</th>
                                            <th>Instructor</th>
                                            <th>Fee</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {available.map((c) => (
                                            <tr key={c.id}>
                                                <td><strong>{c.code}</strong></td>
                                                <td>{c.title}</td>
                                                <td>{c.instructor}</td>
                                                <td>₹{c.fee?.toLocaleString('en-IN') || 0}</td>
                                                <td>
                                                    <button
                                                        className="btn btn-orange btn-small"
                                                        onClick={() => m1(c.id)}
                                                    >
                                                        Enroll
                                                    </button>
                                                </td>
                                            </tr>
                                        ))}
                                        </tbody>
                                    </table>
                                )}
                            </div>
                        </>
                    )}
                </div>
            </div>
        </div>
    )
}

import withAuth from '../../hoc/withAuth'
import withLayout from '../../hoc/withLayout'

export default withAuth(withLayout(Enrollments), true)
