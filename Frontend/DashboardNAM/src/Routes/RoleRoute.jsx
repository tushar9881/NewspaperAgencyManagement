import { Navigate } from 'react-router-dom';

const RoleRoute = ({ children, allowedRoles }) => {
  const user = JSON.parse(localStorage.getItem('user'));
  const role = user?.role;

  return allowedRoles.includes(role) ? children : <Navigate to="/" />;
};

export default RoleRoute;
