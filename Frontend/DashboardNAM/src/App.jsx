import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Form from './Login/Form';
import Dashboard from './Dashboard';
import CustomerDashboard from './CustomerDashboard';
import PrivateRoute from './Routes/PrivateRoute';
import RoleRoute from './Routes/RoleRoute';
import DeliveryExexDashboard from './DeliveryExecDashboard';

function App() {
  return (
    <BrowserRouter>

      <Routes>
        {/* Public Route */}
        <Route path="/" element={<Form />} />

        {/* Admin */}
        <Route
          path="/admin/*"
          element={
            <PrivateRoute>
              <RoleRoute allowedRoles={['admin']}>
                <Dashboard />
              </RoleRoute>
            </PrivateRoute>
          }
        />

        {/* Customer */}
        <Route
          path="/customer/*"
          element={
            <PrivateRoute>
              <RoleRoute allowedRoles={['customer']}>
                <CustomerDashboard />
              </RoleRoute>
            </PrivateRoute>
          }
        />

        {/* Delivery Executive */}
        <Route
          path="/delivery_executive/*"
          element={
            <PrivateRoute>
              <RoleRoute allowedRoles={['delivery_executive']}>
                <DeliveryExexDashboard />
              </RoleRoute>
            </PrivateRoute>
          }
        />
      </Routes>
      
    </BrowserRouter>
  );
}

export default App;
