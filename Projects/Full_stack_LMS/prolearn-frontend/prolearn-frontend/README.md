# ProLearn - LMS Frontend (React)

A complete React frontend for the Spring Boot LMS backend.

## Tech & Pattern
- React 18 + Vite + React Router v6
- **HOC (Higher-Order Component) pattern** for route protection
- Mock backend built into the frontend (localStorage based)
- Pure CSS (no Tailwind, no UI library)

## Setup

```bash
cd prolearn-frontend
npm install
npm run dev
```

Opens at `http://localhost:5173`

## Login Credentials

| Role    | Username | Password    |
|---------|----------|-------------|
| ADMIN   | admin    | admin123    |
| STUDENT | student  | student123  |

Just open the login page and click on the **Admin Login** or **Student Login** tab — credentials auto-fill.

## Features

### Admin
- Dashboard with stats (total students, courses, enrollments, avg rating)
- Student CRUD - add / edit / delete / search
- Course CRUD - add / edit / delete / filter by category
- Enrollment management - two-panel assign/remove courses

### Student
- Welcome home page with featured courses
- Browse all courses with category filter & search
- View all learners on the platform

## HOC Pattern

Two HOCs are used to compose every protected page:

**`withAuth(Component, adminOnly)`** - checks if user is logged in (and admin if needed)
**`withLayout(Component)`** - adds the top navbar + footer wrapper

Example usage in a page:
```jsx
import withAuth from '../../hoc/withAuth'
import withLayout from '../../hoc/withLayout'

function AdminDashboard() {
    return <div>...</div>
}

// Compose HOCs - withLayout adds layout, withAuth adds protection
export default withAuth(withLayout(AdminDashboard), true)
```

## File Structure

```
prolearn-frontend/
├── index.html
├── package.json
├── vite.config.js
└── src/
    ├── App.jsx
    ├── main.jsx
    ├── styles.css
    ├── hoc/
    │   ├── withAuth.jsx        ← Route protection HOC
    │   └── withLayout.jsx      ← Layout wrapper HOC
    ├── api/
    │   ├── axiosClient.js      ← For real backend (when ready)
    │   ├── auth.js             ← Login/logout helpers
    │   └── mock.js             ← Fake backend (localStorage)
    └── pages/
        ├── Login.jsx
        ├── admin/
        │   ├── AdminDashboard.jsx
        │   ├── Students.jsx
        │   ├── Courses.jsx
        │   └── Enrollments.jsx
        └── student/
            ├── StudentHome.jsx
            ├── BrowseCourses.jsx
            └── Learners.jsx
```

## Reset Demo Data

Open browser DevTools console and run:
```javascript
localStorage.clear()
```
Then refresh the page - fresh seed data (Indian names) will load.

## Connecting to Real Backend

When your Spring Boot backend is ready, replace `import { mock } from '../../api/mock'` with `import api from '../../api/axiosClient'` in each page, and adjust the calls. The `axiosClient.js` is already configured with Basic Auth headers and 401 redirect.

## UI Design

- **Color palette**: Deep green (`#0d4f3c`) + orange (`#ff8c42`) — different from typical purple/blue
- **Layout**: Top horizontal navbar (no sidebar)
- **Style**: Basic, table-heavy, no gradients, no shadows
- **Indian context**: Rupee symbols (₹), Indian instructor names, Indian student names, Indian cities
