import api from './axiosClient'

const handle = (err) => {
    throw new Error(err.response?.data?.message || 'Request failed')
}

const getRole = () => localStorage.getItem('authRole')
const isAdmin = () => getRole() === 'ADMIN'
const myEmail = () => localStorage.getItem('studentEmail') || ''

export const mock = {
    listCourses: async () => {
        try {
            const url = isAdmin() ? '/admin/courses' : '/user/courses'
            const res = await api.get(url)
            return res.data
        } catch (e) { handle(e) }
    },

    addCourse: async (d) => {
        try {
            const res = await api.post('/admin/courses', d)
            return res.data
        } catch (e) { handle(e) }
    },

    editCourse: async (id, d) => {
        try {
            const res = await api.put('/admin/courses/' + id, d)
            return res.data
        } catch (e) { handle(e) }
    },

    delCourse: async (id) => {
        try {
            await api.delete('/admin/courses/' + id)
            return true
        } catch (e) { handle(e) }
    },

    listStudents: async () => {
        try {
            const url = isAdmin() ? '/admin/students' : '/user/students'
            const res = await api.get(url)
            return res.data
        } catch (e) { handle(e) }
    },

    addStudent: async (d) => {
        try {
            const res = await api.post('/admin/students', d)
            return res.data
        } catch (e) { handle(e) }
    },

    editStudent: async (id, d) => {
        try {
            const res = await api.put('/admin/students/' + id, d)
            return res.data
        } catch (e) { handle(e) }
    },

    delStudent: async (id) => {
        try {
            await api.delete('/admin/students/' + id)
            return true
        } catch (e) { handle(e) }
    },

    enroll: async (sId, cId) => {
        try {
            const res = await api.post('/admin/enroll/' + sId + '/' + cId)
            return res.data
        } catch (e) { handle(e) }
    },

    unenroll: async (sId, cId) => {
        try {
            const res = await api.delete('/admin/enroll/' + sId + '/' + cId)
            return res.data
        } catch (e) { handle(e) }
    },

    stats: async () => {
        try {
            const res = await api.get('/admin/dashboard/stats')
            return res.data
        } catch (e) { handle(e) }
    },

    // NEW: my-courses for logged-in student
    myCourses: async () => {
        try {
            const res = await api.get('/user/my-courses?email=' + encodeURIComponent(myEmail()))
            return res.data
        } catch (e) { handle(e) }
    },

    myEnrolledIds: async () => {
        try {
            const res = await api.get('/user/my-enrolled-ids?email=' + encodeURIComponent(myEmail()))
            return res.data
        } catch (e) { handle(e) }
    }
}