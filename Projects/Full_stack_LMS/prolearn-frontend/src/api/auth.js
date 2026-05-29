import axios from 'axios'

const API_URL = 'http://localhost:8080/api'

const NAME_MAP = {
    'admin': 'Niti',
    'user': 'Ankush',
    'student': 'Ankush'
}

export const doLogin = async (uname, pwd) => {
    try {
        const res = await axios.post(API_URL + '/auth/login', {
            username: uname,
            password: pwd
        })

        const data = res.data

        localStorage.setItem('authToken', data.token)
        localStorage.setItem('authUser', data.username)
        localStorage.setItem('authRole', data.role)
        localStorage.setItem('authName', NAME_MAP[data.username] || data.username)

        return {
            username: data.username,
            role: data.role,
            fullName: NAME_MAP[data.username] || data.username
        }
    } catch (e) {
        if (e.response && e.response.data && e.response.data.message) {
            throw new Error(e.response.data.message)
        }
        throw new Error('Cannot connect to server. Is backend running on port 8080?')
    }
}

export const doLogout = () => {
    localStorage.removeItem('authToken')
    localStorage.removeItem('authUser')
    localStorage.removeItem('authRole')
    localStorage.removeItem('authName')
    localStorage.removeItem('studentEmail')
}

export const isLogged = () => !!localStorage.getItem('authToken')
export const isAdmin = () => localStorage.getItem('authRole') === 'ADMIN'

export const getUser = () => ({
    username: localStorage.getItem('authUser'),
    role: localStorage.getItem('authRole'),
    fullName: localStorage.getItem('authName'),
    email: localStorage.getItem('studentEmail')
})