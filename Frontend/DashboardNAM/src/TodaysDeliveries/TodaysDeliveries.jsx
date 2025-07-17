import React, { useEffect, useState } from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Typography, CircularProgress, Box} from '@mui/material';

const TodaysDeliveries = ({ deliveryExecutiveId }) => {

  const [deliveries, setDeliveries] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${import.meta.env.VITE_API_URL}/deliveries/today/${deliveryExecutiveId}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        setDeliveries(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Fetch error:', error);
        setLoading(false);
      });
  }, [deliveryExecutiveId]);


  if (loading) {
    return (
      <Box display="flex" justifyContent="center" mt={4}>
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Box m={4}>

      <Typography variant="h5" gutterBottom>
        Deliveries Done Today
      </Typography>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Customer ID</TableCell>
              <TableCell>Customer Name</TableCell>
              <TableCell>Address</TableCell>
              <TableCell>Delivery Time</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {deliveries.length > 0 ? (
              deliveries.map((delivery, index) => (
                <TableRow key={index}>
                  <TableCell>{delivery.customerId}</TableCell>
                  <TableCell>{delivery.userName}</TableCell>
                  <TableCell>{delivery.address}</TableCell>
                  <TableCell>{new Date(delivery.deliveryTime).toLocaleString()}</TableCell>
                </TableRow>
              ))
            ) : (
              <TableRow>
                <TableCell colSpan={4} align="center">
                  No deliveries made today
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
      
    </Box>
  );
};

export default TodaysDeliveries;
