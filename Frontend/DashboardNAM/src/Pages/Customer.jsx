import React, { useState, useEffect } from 'react';
import { Box, Button, Dialog, DialogActions,Card, CardContent, Grid, DialogContent, DialogTitle, TextField, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, IconButton,Typography} from '@mui/material';
import { Edit, Delete,People,Newspaper,PersonAdd,Search} from '@mui/icons-material';

const CustomerTab = () => {
  const [customers, setCustomers] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [searchId, setSearchId] = useState('');
  const [openDialog, setOpenDialog] = useState(false);
  const [formMode, setFormMode] = useState('onboard');
  const [formData, setFormData] = useState({
    userName: '', password: '', fullName: '', email: '',
    contactNumber: '', role: '', fullAddress: '', sectorZone: ''
  });
  const [updateid, setUpdateid] = useState(null);//for update
  const [confirmDelete, setConfirmDelete] = useState(false);
  const [deleteid, setDeleteid] = useState(null);//for delete


  useEffect(() => {
    fetchCustomers();
  }, []);


  const fetchCustomers = async () => {
    const res = await fetch('http://localhost:8080/api/admin/customers');
    const data = await res.json();
    setCustomers(data);
    setFiltered(data);
  };


  const handleSearch = () => {
    if (searchId.trim() === '') return setFiltered(customers);
    const result = customers.filter(c => c.id.toString() === searchId);
    setFiltered(result);
  };

  const handleOpenDialog = (mode, customer = null) => {
    setFormMode(mode);
    if (customer) {
      setUpdateid(customer.id);
      setFormData({
        userName: customer.userName || '',
        password: customer.password || '',
        fullName: customer.fullName || '',
        email: customer.email || '',
        contactNumber: customer.contactNumber || '',
        role: customer.role || '',
        fullAddress: customer.fullAddress || '',
        sectorZone: customer.sectorZone || '',
        registrationDate: customer.registrationDate || '',
        subscriptions: customer.subscriptions || []
      });
    } else {
      setFormData({
        userName: '', password: '', fullName: '', email: '',
        contactNumber: '', role: '', fullAddress: '', sectorZone: '',
        registrationDate: '',
        subscriptions: [] 
      });
    }
    setOpenDialog(true);
  };


  const handleSave = async () => {
    const method = formMode === 'onboard' ? 'POST' : 'PUT';
    const url = formMode === 'onboard'
      ? 'http://localhost:8080/api/admin/onboardcustomer'
      : `http://localhost:8080/api/admin/${updateid}`;

    await fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formData),
    });

    setOpenDialog(false);
    fetchCustomers();
  };


  const handleDelete = async () => {
    await fetch(`http://localhost:8080/api/admin/${deleteid}`, {
      method: 'DELETE',
    });
    setConfirmDelete(false);
    fetchCustomers();
  };


  const customerCount = filtered.length;
  const subscriptionCount = filtered.reduce((total, customer) => {
    return total + (customer.subscriptions?.length || 0);
  }, 0);


  return (
    
    <Box p={3}>

      <Typography variant="h4" fontWeight={600} gutterBottom>Customer Management</Typography>
      
      {/* Summary Tiles Start */}
      <Grid container spacing={2} mb={2}>

        <Grid item xs={12} md={6}>

          <Card sx={{background: 'linear-gradient(135deg, #42a5f5, #478ed1)',color: '#fff',borderRadius: 3,boxShadow: 3,}}>

            <CardContent sx={{ display: 'flex', alignItems: 'center' }}>

              <People sx={{ fontSize: 40, mr: 2 }} />

              <Box>
                <Typography variant="h6" fontWeight="bold">Total Customers</Typography>
                <Typography variant="h4" fontWeight={600}>{customerCount}</Typography>
              </Box>

            </CardContent>

          </Card>

        </Grid>

        <Grid item xs={12} md={6}>

          <Card sx={{background: 'linear-gradient(135deg, #66bb6a, #43a047)',color: '#fff',borderRadius: 3,boxShadow: 3,}}>

            <CardContent sx={{ display: 'flex', alignItems: 'center' }}>

              <Newspaper sx={{ fontSize: 40, mr: 2 }} />

              <Box>
                <Typography variant="h6" fontWeight="bold">Total Subscriptions</Typography>
                <Typography variant="h4" fontWeight={600}>{subscriptionCount}</Typography>
              </Box>

            </CardContent>

          </Card>

        </Grid>

      </Grid>
      {/* Summary Tiles End */}
      
      {/* Searchbox and Onboard Customer start */}
      <Box component={Paper} elevation={2} sx={{p: 2,mb: 2,display: 'flex',alignItems: 'center',gap: 2,borderRadius: 2,}}>

        <TextField label="Search by Customer ID" size="small" value={searchId} onChange={(e) => setSearchId(e.target.value)} sx={{ minWidth: 250 }}/>

        <Button variant="outlined" startIcon={<Search />} onClick={handleSearch} sx={{ whiteSpace: 'nowrap' }}>Search</Button>

        <Box ml="auto">

          <Button variant="contained" startIcon={<PersonAdd />} onClick={() => handleOpenDialog('onboard')}>Onboard New Customer</Button>

        </Box>

      </Box>
      {/* Searchbox and Onboard Customer End */}

      {/* Table Start */}
      <TableContainer component={Paper}>
        <Table size="small">
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Username</TableCell>
              <TableCell>Password</TableCell>
              <TableCell>Full Name</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Contact</TableCell>
              <TableCell>Role</TableCell>
              <TableCell>Address</TableCell>
              <TableCell>Sector</TableCell>
              <TableCell>Reg. Date</TableCell>
              <TableCell>Paper Name</TableCell>
              <TableCell>Start Date</TableCell>
              <TableCell>End Date</TableCell>
              <TableCell>Price</TableCell>
              <TableCell>Quantity</TableCell>
              <TableCell>Total</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filtered.length === 0 ? (
              <TableRow>
                <TableCell colSpan={17} align="center">No records found</TableCell>
              </TableRow>
            ) : (
              filtered.flatMap(customer =>
                customer.subscriptions?.length > 0
                  ? customer.subscriptions.map((sub, index) => (
                      <TableRow key={`${customer.id}-${index}`}>
                        <TableCell>{customer.id}</TableCell>
                        <TableCell>{customer.userName}</TableCell>
                        <TableCell>{customer.password}</TableCell>
                        <TableCell>{customer.fullName}</TableCell>
                        <TableCell>{customer.email}</TableCell>
                        <TableCell>{customer.contactNumber}</TableCell>
                        <TableCell>{customer.role}</TableCell>
                        <TableCell>{customer.fullAddress}</TableCell>
                        <TableCell>{customer.sectorZone}</TableCell>
                        <TableCell>{customer.registrationDate}</TableCell>
                        <TableCell>{sub.paperName}</TableCell>
                        <TableCell>{sub.startDate}</TableCell>
                        <TableCell>{sub.endDate}</TableCell>
                        <TableCell>{sub.price}</TableCell>
                        <TableCell>{sub.quantity}</TableCell>
                        <TableCell>{sub.total}</TableCell>
                        <TableCell>
                          <IconButton onClick={() => handleOpenDialog('update', customer)}><Edit /></IconButton>
                          <IconButton onClick={() => {
                            setDeleteid(customer.id);
                            setConfirmDelete(true);
                          }}><Delete /></IconButton>
                        </TableCell>
                      </TableRow>
                    ))
                  : [
                      <TableRow key={customer.id}>
                        <TableCell>{customer.id}</TableCell>
                        <TableCell>{customer.userName}</TableCell>
                        <TableCell>{customer.password}</TableCell>
                        <TableCell>{customer.fullName}</TableCell>
                        <TableCell>{customer.email}</TableCell>
                        <TableCell>{customer.contactNumber}</TableCell>
                        <TableCell>{customer.role}</TableCell>
                        <TableCell>{customer.fullAddress}</TableCell>
                        <TableCell>{customer.sectorZone}</TableCell>
                        <TableCell>{customer.registrationDate}</TableCell>
                        <TableCell colSpan={6} align="center">No Subscription taken yet.It will get reflected after taking it.</TableCell>
                        <TableCell>
                          <IconButton onClick={() => handleOpenDialog('update', customer)}><Edit /></IconButton>
                          <IconButton onClick={() => {
                            setDeleteid(customer.id);
                            setConfirmDelete(true);
                          }}><Delete /></IconButton>
                        </TableCell>
                      </TableRow>
                    ]
              )
            )}
          </TableBody>
        </Table>
      </TableContainer>
      {/* Table End */}


      {/* Dialog for Onboard / Update */}
      <Dialog open={openDialog} onClose={() => setOpenDialog(false)} maxWidth="sm" fullWidth>

        <DialogTitle>{formMode === 'onboard' ? 'Onboard Customer' : 'Update Customer'}</DialogTitle>

        <DialogContent>
          {['userName', 'password', 'fullName', 'email', 'contactNumber', 'role', 'fullAddress', 'sectorZone','registrationDate'].map(field => (
            <TextField
              key={field}
              label={field.charAt(0).toUpperCase() + field.slice(1)}
              fullWidth
              margin="dense"
              value={formData[field]}
              onChange={(e) => setFormData({ ...formData, [field]: e.target.value })}
            />
          ))}

          {/* Render subscriptions, if present */}
          {formData.subscriptions?.length > 0 && (
            <>
              <Typography variant="h6" mt={2}>Subscriptions</Typography>
              {formData.subscriptions.map((sub, index) => (
                <Box key={index} mb={2} p={1} border={1} borderRadius={1} borderColor="#ccc">
                  {['paperName', 'startDate', 'endDate', 'price', 'quantity', 'total'].map(field => (
                    <TextField
                      key={field}
                      label={`${field.charAt(0).toUpperCase() + field.slice(1)} (${index + 1})`}
                      fullWidth
                      margin="dense"
                      value={sub[field]}
                      onChange={(e) => {
                        const updatedSubs = [...formData.subscriptions];
                        updatedSubs[index] = { ...updatedSubs[index], [field]: e.target.value };
                        setFormData({ ...formData, subscriptions: updatedSubs });
                      }}
                    />
                  ))}
                </Box>
              ))}
            </>
          )}
        </DialogContent>

        <DialogActions>

          <Button onClick={() => setOpenDialog(false)}>Cancel</Button>
          <Button variant="contained" onClick={handleSave}>
            {formMode === 'onboard' ? 'Onboard' : 'Update'}
          </Button>

        </DialogActions>

      </Dialog>

      {/* Confirmation Dialog */}
      <Dialog open={confirmDelete} onClose={() => setConfirmDelete(false)}>

        <DialogTitle>Confirm Deletion</DialogTitle>

        <DialogContent>Are you sure you want to delete this customer?</DialogContent>

        <DialogActions>
          <Button onClick={() => setConfirmDelete(false)}>Cancel</Button>
          <Button onClick={handleDelete} variant="contained" color="error">Delete</Button>
        </DialogActions>
        
      </Dialog>

    </Box>
  );
};

export default CustomerTab;
