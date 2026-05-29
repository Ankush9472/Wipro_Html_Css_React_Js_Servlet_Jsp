import { useEffect, useState } from 'react'
import { mock } from '../../api/mock'

function BrowseCourses() {
    const [arr, setArr] = useState([])
    const [enrolledIds, setEnrolledIds] = useState([])
    const [s, setS] = useState('')
    const [cat, setCat] = useState('All')
    const [busy, setBusy] = useState(false)

    useEffect(() => {
        run()
    }, [])

    const run = async () => {
        setBusy(true)
        try {
            const r = await mock.listCourses()
            setArr(r)
            const ids = await mock.myEnrolledIds()
            setEnrolledIds(ids)
        } catch (e) {
            console.log(e)
        }
        setBusy(false)
    }

    const cats = ['All', ...new Set(arr.map((c) => c.category))]

    const res = arr.filter((c) => {
        const a = c.title.toLowerCase().includes(s.toLowerCase()) ||
            c.instructor.toLowerCase().includes(s.toLowerCase())
        const b = cat === 'All' || c.category === cat
        return a && b
    })

    return (
        <div>
            <div className="page-header">
                <div>
                    <div className="page-title">All Courses</div>
                    <div className="page-subtitle">{arr.length} courses available · You are enrolled in {enrolledIds.length}</div>
                </div>
            </div>

            <div className="alert alert-info">
                <strong>Note:</strong> You can only access course content for courses your admin has enrolled you in. Locked courses show a lock icon.
            </div>

            <div className="box">
                <input
                    type="text"
                    placeholder="Search courses by title or instructor..."
                    value={s}
                    onChange={(e) => setS(e.target.value)}
                    style={{ width: '100%', padding: '8px 10px', border: '1px solid #d0d7de', borderRadius: 3, marginBottom: 10 }}
                />
                <div>
                    <span style={{ fontSize: 13, color: '#666', marginRight: 8 }}>Category:</span>
                    {cats.map((x) => (
                        <button
                            key={x}
                            className={'btn btn-small ' + (cat === x ? 'btn-orange' : 'btn-gray')}
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
            ) : res.length === 0 ? (
                <div className="box empty">No courses match your search</div>
            ) : (
                <table className="tbl">
                    <thead>
                    <tr>
                        <th>Code</th>
                        <th>Title</th>
                        <th>Category</th>
                        <th>Instructor</th>
                        <th>Duration</th>
                        <th>Fee</th>
                        <th>Status</th>
                        <th>Access</th>
                    </tr>
                    </thead>
                    <tbody>
                    {res.map((c) => {
                        const enrolled = enrolledIds.includes(c.id)
                        return (
                            <tr key={c.id}>
                                <td><strong>{c.code}</strong></td>
                                <td>
                                    <div>{c.title}</div>
                                    <div style={{ fontSize: 11, color: '#888', marginTop: 2 }}>
                                        {c.description}
                                    </div>
                                </td>
                                <td><span className="tag tag-orange">{c.category}</span></td>
                                <td>{c.instructor}</td>
                                <td>{c.duration}</td>
                                <td>₹{c.fee?.toLocaleString('en-IN') || 0}</td>
                                <td>
                                    {enrolled ? (
                                        <span className="tag tag-green">✓ Enrolled</span>
                                    ) : (
                                        <span className="tag" style={{ background: '#f0f0f0', color: '#666' }}>
                                            🔒 Locked
                                        </span>
                                    )}
                                </td>
                                <td>
                                    {enrolled && c.courseLink ? (
                                        <a href={c.courseLink} target="_blank" rel="noreferrer"
                                           className="btn btn-orange btn-small">
                                            Watch Now ↗
                                        </a>
                                    ) : !enrolled ? (
                                        <span style={{ color: '#999', fontSize: 12 }}>Not enrolled</span>
                                    ) : (
                                        <span style={{ color: '#999', fontSize: 12 }}>No link</span>
                                    )}
                                </td>
                            </tr>
                        )
                    })}
                    </tbody>
                </table>
            )}
        </div>
    )
}

import withAuth from '../../hoc/withAuth'
import withLayout from '../../hoc/withLayout'

export default withAuth(withLayout(BrowseCourses))