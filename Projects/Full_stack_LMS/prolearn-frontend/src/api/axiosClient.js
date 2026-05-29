import axios from 'axios'

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: { 'Content-Type': 'application/json' }
})

api.interceptors.request.use((cfg) => {
    const t = localStorage.getItem('authToken')
    if (t) cfg.headers.Authorization = 'Basic ' + t
    return cfg
})

api.interceptors.response.use(
    (r) => r,
    (e) => {
        if (e.response && e.response.status === 401) {
            localStorage.clear()
            if (window.location.pathname !== '/login') {
                window.location.href = '/login'
            }
        }
        return Promise.reject(e)
    }
)

export default api