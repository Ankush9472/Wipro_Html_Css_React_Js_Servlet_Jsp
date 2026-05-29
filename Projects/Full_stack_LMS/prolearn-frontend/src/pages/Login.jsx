import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { doLogin, isLogged, isAdmin } from '../api/auth'

export default function Login() {
    const nav = useNavigate()
    const [u, setU] = useState('')
    const [p, setP] = useState('')
    const [err, setErr] = useState('')
    const [busy, setBusy] = useState(false)
    const [askEmail, setAskEmail] = useState(false)
    const [email, setEmail] = useState('')

    useEffect(() => {
        if (isLogged()) {
            if (isAdmin()) nav('/admin')
            else nav('/student')
        }
    }, [])

    const fun = async (e) => {
        e.preventDefault()
        setErr('')
        setBusy(true)
        try {
            const res = await doLogin(u, p)
            if (res.role === 'ADMIN') {
                nav('/admin')
            } else {
                // For student, ask for email to identify them
                setAskEmail(true)
                setBusy(false)
            }
        } catch (ex) {
            setErr(ex.message)
            setBusy(false)
        }
    }

    const m1 = (e) => {
        e.preventDefault()
        if (!email.trim()) {
            setErr('Please enter your registered email')
            return
        }
        localStorage.setItem('studentEmail', email.trim())
        nav('/student')
    }

    if (askEmail) {
        return (
            <div className="login-bg">
                <div className="login-box">
                    <div className="login-logo">
                        <div className="login-logo-circle">P</div>
                        <h1>ProLearn</h1>
                        <p>Identify yourself</p>
                    </div>

                    {err && <div className="alert alert-error">{err}</div>}

                    <div className="alert alert-info">
                        Please enter the email your admin used to register you.
                    </div>

                    <form onSubmit={m1}>
                        <div className="field">
                            <label>Your Registered Email</label>
                            <input type="email" value={email}
                                   onChange={(e) => setEmail(e.target.value)}
                                   placeholder="e.g. ankush@gmail.com" required autoFocus/>
                        </div>
                        <button type="submit" className="btn btn-primary"
                                style={{ width: '100%', padding: '10px', fontSize: '14px' }}>
                            Continue
                        </button>
                    </form>
                </div>
            </div>
        )
    }

    return (
        <div className="login-bg">
            <div className="login-box">
                <div className="login-logo">
                    <div className="login-logo-circle">P</div>
                    <h1>ProLearn</h1>
                    <p>Empowering education across India</p>
                </div>

                {err && <div className="alert alert-error">{err}</div>}

                <form onSubmit={fun}>
                    <div className="field">
                        <label>Username</label>
                        <input type="text" value={u}
                               onChange={(e) => setU(e.target.value)}
                               placeholder="Enter your username" required/>
                    </div>
                    <div className="field">
                        <label>Password</label>
                        <input type="password" value={p}
                               onChange={(e) => setP(e.target.value)}
                               placeholder="Enter your password" required/>
                    </div>
                    <button type="submit" className="btn btn-primary"
                            disabled={busy}
                            style={{ width: '100%', padding: '10px', fontSize: '14px' }}>
                        {busy ? 'Signing in...' : 'Sign In'}
                    </button>
                </form>

                <div style={{
                    marginTop: 18,
                    paddingTop: 14,
                    borderTop: '1px solid #e1e4e8',
                    textAlign: 'center',
                    fontSize: 12,
                    color: '#666'
                }}>
                    Single sign-in for Admins & Students
                </div>
            </div>
        </div>
    )
}