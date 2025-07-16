import React, { useEffect, useState } from 'react';
import AppBarOnly from './ReusableAppbarOnly/Appbaronly';
import {
  Box,
  Button,
  Snackbar,
  Alert,
  Typography,
  TextField,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper
} from '@mui/material';
import TodaysDeliveries from './TodaysDeliveries/TodaysDeliveries';

const DeliveryExecutiveDashboard = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  const deliveryExecutiveId = user?.id;

  const [customerId, setCustomerId] = useState('');
  const [snackbar, setSnackbar] = useState({ open: false, message: '', severity: 'success' });
  const [deliveries, setDeliveries] = useState([]);


  const handleDelivery = async () => {
    if (!customerId) return;

    try {
      const res = await fetch(
        `http://localhost:8080/deliveries/create?userId=${customerId}&deliveryExecutiveId=${deliveryExecutiveId}`,
        { method: 'POST' }
      );
      const newDelivery = await res.json();
      console.log(newDelivery);

      setSnackbar({ open: true, message: 'Delivery successful!', severity: 'success' });
      setCustomerId('');
      setDeliveries((prev) => [...prev, newDelivery]);
    } catch (err) {
      setSnackbar({ open: true, message: 'Delivery failed', severity: 'error' });
    }
  };

  return (
    <Box p={4}>

      <AppBarOnly fullName={user.fullName} role={user.role} title="Nwespaper Agency Management" />

      <Typography variant="h5" gutterBottom>
        Delivery Executive Dashboard
      </Typography><br />

      <Typography variant='h6' >Hi,{user.userName}</Typography> <br />

      <Box display="flex" alignItems="center" gap={2} mb={4}>

        <TextField
          label="Customer ID"
          variant="outlined"
          value={customerId}
          onChange={(e) => setCustomerId(e.target.value)}
        />

        <Button variant="contained" color="primary" onClick={handleDelivery}>
          Deliver
        </Button>
        
      </Box>

      <Typography variant="h6" gutterBottom>
        Most Recent delivery will be shown here after clicking on deliver button.
      </Typography>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Delivery ID</TableCell>
              <TableCell>Customer ID</TableCell>
              <TableCell>Customer Name</TableCell>
              <TableCell>Address</TableCell>
              <TableCell>Delivered</TableCell>
              <TableCell>Delivery Time</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {deliveries.map((delivery) => (
              <TableRow key={delivery.id}>
                <TableCell>{delivery.id}</TableCell>
                <TableCell>{delivery.customerId}</TableCell>
                <TableCell>{delivery.userName}</TableCell>
                <TableCell>{delivery.address}</TableCell>
                <TableCell>{delivery.delivered ? 'Yes' : 'No'}</TableCell>
                <TableCell>
                  {new Date(delivery.deliveryTime).toLocaleString('en-IN', {
                    hour12: false,
                    year: 'numeric',
                    month: '2-digit',
                    day: '2-digit',
                    hour: '2-digit',
                    minute: '2-digit',
                    second: '2-digit',
                    fractionalSecondDigits: 3
                  })}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <Snackbar
        open={snackbar.open}
        autoHideDuration={3000}
        onClose={() => setSnackbar({ ...snackbar, open: false })}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
      >
        <Alert severity={snackbar.severity} onClose={() => setSnackbar({ ...snackbar, open: false })}>
          {snackbar.message}
        </Alert>
      </Snackbar>

      <TodaysDeliveries deliveryExecutiveId={deliveryExecutiveId}/>

    </Box>
  );
};

export default DeliveryExecutiveDashboard;
