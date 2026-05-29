import { useEffect, useState } from 'react'
import { mock } from '../../api/mock'

function Learners() {
    const [arr, setArr] = useState([])
    const [s, setS] = useState('')
    const [busy, setBusy] = useState(false)

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

    const res = arr.filter((x) =>
        x.name.toLowerCase().includes(s.toLowerCase()) ||
        x.rollNumber.toLowerCase().includes(s.toLowerCase()) ||
        (x.city || '').toLowerCase().includes(s.toLowerCase())
    )

    return (
        <div>
            <div className="page-header">
                <div>
                    <div className="page-title">Our Learners</div>
                    <div className="page-subtitle">{arr.length} students are learning with us</div>
                </div>
            </div>

            <div className="box">
                <input
                    type="text"
                    placeholder="Search learners..."
                    value={s}
                    onChange={(e) => setS(e.target.value)}
                    style={{ width: '100%', padding: '8px 10px', border: '1px solid #d0d7de', borderRadius: 3 }}
                />
            </div>

            {busy ? (
                <div className="loading">Loading learners...</div>
            ) : (
                <table className="tbl">
                    <thead>
                    <tr>
                        <th>Roll No</th>
                        <th>Name</th>
                        <th>City</th>
                        <th>Enrolled Courses</th>
                    </tr>
                    </thead>
                    <tbody>
                    {res.length === 0 ? (
                        <tr>
                            <td colSpan="4" className="empty">No learners found</td>
                        </tr>
                    ) : (
                        res.map((st) => (
                            <tr key={st.id}>
                                <td><strong>{st.rollNumber}</strong></td>
                                <td>{st.name}</td>
                                <td>{st.city || '-'}</td>
                                <td>
                                    {st.courses?.length === 0 ? (
                                        <span style={{ color: '#888', fontSize: 12 }}>Not enrolled yet</span>
                                    ) : (
                                        st.courses?.map((c) => (
                                            <span key={c.id} className="tag tag-blue" style={{ marginRight: 4 }}>
                                                {c.code}
                                            </span>
                                        ))
                                    )}
                                </td>
                            </tr>
                        ))
                    )}
                    </tbody>
                </table>
            )}
        </div>
    )
}

import withAuth from '../../hoc/withAuth'
import withLayout from '../../hoc/withLayout'

export default withAuth(withLayout(Learners))
