import React from 'react'

function withAuth(Component) {
  return function WrappedComponent(props) {
    const isLoggedIn = localStorage.getItem("loggedIn");
    if (!isLoggedIn)
      return <h3> You are not authorized</h3>
    else
      return <Component {...props} />
  }
}

export default withAuth