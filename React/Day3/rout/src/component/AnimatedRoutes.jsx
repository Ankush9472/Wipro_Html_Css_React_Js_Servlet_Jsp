import React, { useRef } from 'react'
import { Routes, Route, useLocation } from 'react-router-dom'
import { CSSTransition, TransitionGroup } from 'react-transition-group'
import Home from '../pages/Home'
import Login from '../pages/Login'
import { ProtectAdmin, ProtectEmployee, ProtectCourses } from '../pages/ProtectedRoutes'

function AnimatedRoutes() {
  const location = useLocation();
  const ref = useRef(null);

  return (
    <div>
      <TransitionGroup>
        <CSSTransition
          key={location.pathname}
          classNames="fade"
          timeout={300}
          nodeRef={ref}
        >
          <div ref={ref}>
            <Routes location={location}>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/admin" element={<ProtectAdmin />} />
              <Route path="/employees" element={<ProtectEmployee />} />
              <Route path="/courses" element={<ProtectCourses />} />
            </Routes>
          </div>
        </CSSTransition>
      </TransitionGroup>
    </div>
  )
}

export default AnimatedRoutes