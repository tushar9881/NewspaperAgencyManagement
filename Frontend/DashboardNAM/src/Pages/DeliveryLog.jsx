import React, { useState, useEffect } from 'react';
import { Box,Tabs,Tab,TextField,Button,Table,TableBody,TableCell,TableContainer,TableHead,TableRow,Paper,Typography,Container} from '@mui/material';
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';
import CancelOutlinedIcon from '@mui/icons-material/CancelOutlined';


const DeliveryLog = () => {
  const [selectedDate, setSelectedDate] = useState(() => {
    const today = new Date();
    return today.toISOString().split('T')[0];
  });

  const [activeTab, setActiveTab] = useState('delivered');
  const [deliveredTable, setDeliveredTable] = useState([]);
  const [undeliveredTable, setUndeliveredTable] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (!selectedDate) {
      setDeliveredTable([]);
      setUndeliveredTable([]);
      return;
    }

    const fetchData = async () => {
      setLoading(true);
      try {
        const endpoint =
          activeTab === 'delivered'
            ? `http://localhost:8080/api/admin/by-date?date=${selectedDate}`
            : `http://localhost:8080/api/admin/no-deliveries?date=${selectedDate}`;

        const res = await fetch(endpoint);
        const data = await res.ok ? await res.json() : [];

        if (activeTab === 'delivered') {
          setDeliveredTable(Array.isArray(data) ? data : []);
          setUndeliveredTable([]);
        } else {
          setUndeliveredTable(Array.isArray(data) ? data : []);
          setDeliveredTable([]);
        }
      } catch (err) {
        console.error('Fetch error:', err);
        setDeliveredTable([]);
        setUndeliveredTable([]);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [activeTab, selectedDate]);

  const activeData = activeTab === 'delivered' ? deliveredTable : undeliveredTable;

  const user = JSON.parse(localStorage.getItem('user')) || {};
  const fullName = user.fullName || 'User';
  const role = user.role || '';

  return (
    <Box sx={{minHeight: 'calc(100vh - 64px)',width: '100%',py: { xs: 2, md: 3 },bgcolor: '#f9f9f9',overflowY: 'auto',boxSizing: 'border-box',}}>

    <Typography variant='h6' >Hi,{user.userName}</Typography> <br />

    <Typography variant="h4" fontWeight={600} gutterBottom>Daily Delivery Log</Typography>

      <Box sx={{display: 'flex',gap: 3,mb: 3, flexWrap: 'wrap',}}>

        <Paper elevation={3} sx={{flex: 1,minWidth: 250,p: 3,borderRadius: 3,textAlign: 'center',background: 'linear-gradient(to right, #e0f7fa, #ffffff)',}}>

          <CheckCircleOutlineIcon color="success" sx={{ fontSize: 40, mb: 1 }} />

          <Typography variant="subtitle2" color="textSecondary">
            Delivered Records
          </Typography>

          <Typography variant="h4" fontWeight={700} color="success.main">
            {deliveredTable.length}
          </Typography>

        </Paper>

        <Paper elevation={3} sx={{flex: 1,minWidth: 250,p: 3,borderRadius: 3,textAlign: 'center',background: 'linear-gradient(to right, #ffebee, #ffffff)',}}>

          <CancelOutlinedIcon color="error" sx={{ fontSize: 40, mb: 1 }} />

          <Typography variant="subtitle2" color="textSecondary">
            Undelivered Records
          </Typography>

          <Typography variant="h4" fontWeight={700} color="error.main">
            {undeliveredTable.length}
          </Typography>

        </Paper>

      </Box>

      <Box sx={{display: 'flex',alignItems: 'center',justifyContent: 'space-between',flexWrap: 'wrap',gap: 2,mb: 3,p: 2,backgroundColor: '#f9f9f9',borderRadius: 2,boxShadow: 1,}}>

        {/* Tabs */}
        <Tabs value={activeTab} onChange={(e, newVal) => setActiveTab(newVal)} textColor="primary" indicatorColor="primary" sx={{ minWidth: '200px' }}>

          <Tab label="Delivered" value="delivered" />
          <Tab label="Undelivered" value="undelivered" />

        </Tabs>

        {/* Date Picker + Clear Button */}
        <Box sx={{ display: 'flex', gap: 2, alignItems: 'center' }}>

          <TextField label="Select Date" type="date" size="small" InputLabelProps={{ shrink: true }} value={selectedDate} onChange={(e) => setSelectedDate(e.target.value)}/>

          <Button variant="outlined" color="secondary" onClick={() => {
              setSelectedDate('');
              setDeliveredTable([]);
              setUndeliveredTable([]);
            }}
          >
            Clear
          </Button>

        </Box>

      </Box>

      {/* Table Starts */}
      <TableContainer component={Paper} sx={{ borderRadius: 2, boxShadow: 3}}>
        <Table size="medium">
          <TableHead sx={{ position: 'sticky', top: 0, backgroundColor: '#f7f7f7', zIndex: 1 }}>
            <TableRow>
              {activeTab === 'delivered' ? (
                <>
                  <TableCell>ID</TableCell>
                  <TableCell>Username</TableCell>
                  <TableCell>Address</TableCell>
                  <TableCell>Status</TableCell>
                  <TableCell>Time</TableCell>
                  <TableCell>Log ID</TableCell>
                  <TableCell>Customer ID</TableCell>
                </>
              ) : (
                <>
                  <TableCell>ID</TableCell>
                  <TableCell>Username</TableCell>
                  <TableCell>Full Name</TableCell>
                  <TableCell>Address</TableCell>
                  <TableCell>Zone</TableCell>
                </>
              )}
            </TableRow>
          </TableHead>
          <TableBody>
            {loading ? (
              <TableRow>
                <TableCell colSpan={activeTab === 'delivered' ? 7 : 5} align="center">
                  Loading...
                </TableCell>
              </TableRow>
            ) : activeData.length === 0 ? (
              <TableRow>
                <TableCell colSpan={activeTab === 'delivered' ? 7 : 5} align="center">
                  No {activeTab} records found for selected date i.e All Deliveries done for the day.
                </TableCell>
              </TableRow>
            ) : (
              activeData.slice(0, 10).map((entry) => (
                <TableRow key={entry.id}>
                  {activeTab === 'delivered' ? (
                    <>
                      <TableCell>{entry.id}</TableCell>
                      <TableCell>{entry.userName}</TableCell>
                      <TableCell>{entry.address}</TableCell>
                      <TableCell>{entry.delivered ? 'Yes' : 'No'}</TableCell>
                      <TableCell>{entry.deliveryTime}</TableCell>
                      <TableCell>{entry.dailyDeliveryLogid}</TableCell>
                      <TableCell>{entry.customerId}</TableCell>
                    </>
                  ) : (
                    <>
                      <TableCell>{entry.id}</TableCell>
                      <TableCell>{entry.userName}</TableCell>
                      <TableCell>{entry.fullName}</TableCell>
                      <TableCell>{entry.fullAddress}</TableCell>
                      <TableCell>{entry.sectorZone}</TableCell>
                    </>
                  )}
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
      </TableContainer>
      {/* Table Ends */}
    </Box>
  );
};

export default DeliveryLog;
