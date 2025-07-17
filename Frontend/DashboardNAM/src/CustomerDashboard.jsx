import React, { useEffect, useState } from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Button from '@mui/material/Button';
import { Typography, Box, TextField, IconButton, InputAdornment,CircularProgress, Paper } from '@mui/material';
import AppBarOnly from './ReusableAppbarOnly/Appbaronly';
import Alert from '@mui/material/Alert';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';

function CustomerDashboard() {

  const storedUser = JSON.parse(localStorage.getItem('user'));
  const [customer, setCustomer] = useState(null);
  const [subscriptions, setSubscriptions] = useState([]);
  const [openEdit, setOpenEdit] = useState(false);
  const [formData, setFormData] = useState({});
  const [alert, setAlert] = useState('');
  const [deliveries, setDeliveries] = useState([]);
  const [loading, setLoading] = useState(true);

  console.log(storedUser);


  useEffect(() => {
    if (storedUser?.id) {
      fetch(`${import.meta.env.VITE_API_URL}/api/customer/${storedUser.id}`)
        .then(res => res.json())
        .then(data => {
          setCustomer(data);
          setFormData(data);
        });

      fetch(`${import.meta.env.VITE_API_URL}/api/customer/subscriptions/my/${storedUser.userName}`)
        .then(res => res.json())
        .then(data => setSubscriptions(data));
    }
  }, [storedUser?.id]);

  const handleUpdate = () => {
    fetch(`${import.meta.env.VITE_API_URL}/api/customer/updateinfo`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formData)
    })
      .then(res => res.json())
      .then(data => {
        setCustomer(data);
        setOpenEdit(false);
      });
  };

  useEffect(() => {
    const fetchTodaysDeliveries = async () => {
      try {
        const response = await fetch(`${import.meta.env.VITE_API_URL}/api/customer/deliveries/today/customer/${storedUser.id}`);
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setDeliveries(data);
      } catch (error) {
        console.error('Error fetching today’s deliveries:', error);
      } finally {
        setLoading(false);
      }
    };

    if (storedUser?.id) {
      fetchTodaysDeliveries();
    }
  }, [storedUser?.id]);


  const [showPassword, setShowPassword] = useState(false);

  const handleAbort = () => {
    setAlert("Please contact the admin to abort your subscription.");
  };

  const user = JSON.parse(localStorage.getItem('user')) || {};
  const fullName = user.fullName || 'User';
  const role = user.role || '';
  return (
  <Box sx={{ display: 'flex', width: '100%' }}>

    <CssBaseline />

    <AppBarOnly fullName={user.fullName} role={user.role} title="Newspaper Agency Management" />

    {/* Main Content */}
    <Box component="main" sx={{ flexGrow: 1, p: 3, mt: 10, minHeight: '100vh', maxWidth: '100%' }}>
      
      <Typography variant="h3" gutterBottom>
        Welcome, {customer ? customer.fullName : 'N/A'}
      </Typography>

      <Box
        sx={{display: 'flex',gap: 4,flexWrap: 'wrap',justifyContent: 'space-between',alignItems: 'flex-start',mt: 2,width: '100%',}}>

        {/* Customer Info Box */}
        <Box sx={{flex: 1,minWidth: '48%',bgcolor: '#f9f9f9',p: 3,borderRadius: 2,boxShadow: 3,}}>

          <Typography variant="h5" gutterBottom>Customer Information</Typography>

          {customer ? (
            <>
              <Typography><strong>Username:</strong> {customer.userName}</Typography>
              <Typography><strong>Full Name:</strong> {customer.fullName}</Typography>
              <Typography><strong>Email:</strong> {customer.email}</Typography>
              <Typography><strong>Contact:</strong> {customer.contactNumber}</Typography>
              <Typography><strong>Address:</strong> {customer.fullAddress}</Typography>
              <Typography><strong>Zone:</strong> {customer.sectorZone}</Typography>
              <Typography><strong>Registration Date:</strong> {customer.registrationDate}</Typography>

              <TextField
                type={showPassword ? 'text' : 'password'}
                label="Password"
                value={customer.password || ''}
                disabled
                sx={{ mt: 2, width: '100%' }}
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton onClick={() => setShowPassword(!showPassword)} edge="end">
                        {showPassword ? <VisibilityOff /> : <Visibility />}
                      </IconButton>
                    </InputAdornment>
                  ),
                }}
              />
              <Button variant="contained" sx={{ mt: 2 }} onClick={() => setOpenEdit(true)}>
                Update Info
              </Button>
            </>
          ) : (
            <Typography>Loading customer info...</Typography>
          )}
        </Box>

        {/* Subscriptions Box */}
        <Box sx={{flex: 1, minWidth: '48%',bgcolor: '#f9f9f9',p: 3,borderRadius: 2,boxShadow: 3,}}>

          <Typography variant="h5" gutterBottom>Subscriptions</Typography>

          {subscriptions.length > 0 ? (
            subscriptions.map((sub, index) => (
              <Box key={index} sx={{ border: '1px solid #ccc', borderRadius: 2, p: 2, mb: 2 }}>
                <Typography>Subscription ID: {sub.id}</Typography>
                <Typography>Newspaper: {sub.paperName}</Typography>
                <Typography>Start Date: {sub.startDate}</Typography>
                <Typography>End Date: {sub.endDate}</Typography>
                <Typography>Quantity: {sub.quantity}</Typography>
                <Typography>Price(per unit): ₹{sub.price}</Typography>
                <Button
                  variant="outlined"
                  color="error"
                  sx={{ mt: 1 }}
                  onClick={handleAbort}
                >
                  Abort Subscription
                </Button>
              </Box>
            ))
          ) : (
            <Typography>No subscriptions found. Please contact Admin to enroll.</Typography>
          )}
        </Box>

      </Box>

      {/* Optional Alert */}
      {alert && <Alert severity="warning" sx={{ mt: 4 }}>{alert}</Alert>}

      {/* Today's Deliveries Section */}
      <Box sx={{ mt: 6 }}>

        <Typography variant="h5" gutterBottom>
          Today's Deliveries
        </Typography>

        {deliveries.length > 0 ? (
          <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap', mt: 2 }}>
            {deliveries.map((delivery) => (
              <Paper key={delivery.id} elevation={3} sx={{ p: 2, minWidth: 300, flex: 1 }}>
                <Typography><strong>Delivery ID:</strong> {delivery.id}</Typography>
                <Typography><strong>Address:</strong> {delivery.address}</Typography>
                <Typography><strong>Time:</strong> {new Date(delivery.deliveryTime).toLocaleString()}</Typography>
                <Typography><strong>Status:</strong> {delivery.delivered ? 'Delivered' : 'Pending'}</Typography>
              </Paper>
            ))}
          </Box>
        ) : (
          <Typography sx={{ mt: 2 }}>No deliveries done yet today.</Typography>
        )}
      </Box>

    </Box>
    
  </Box>

  )
}

export default CustomerDashboard
