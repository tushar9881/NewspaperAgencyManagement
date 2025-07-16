import React, { useEffect, useState } from 'react';
import { Box, Button, Dialog, DialogActions, DialogContent, DialogTitle,TextField, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, IconButton, Grid} from '@mui/material';
import { Edit, Delete } from '@mui/icons-material';

const DeliveryExecutiveTab = () => {
  const [executives, setExecutives] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [searchId, setSearchId] = useState('');
  const [openDialog, setOpenDialog] = useState(false);
  const [formMode, setFormMode] = useState('onboard');
  const [updateDEid, setUpdateDEid] = useState(null);
  const [confirmDelete, setConfirmDelete] = useState(false);
  const [executiveToDelete, setExecutiveToDelete] = useState(null);

  const [formData, setFormData] = useState({
    userName: '',
    password: '',
    fullName: '',
    email: '',
    contactNumber: '',
    role: 'delivery_executive',
    zoneAssigned: ''
  });

  useEffect(() => {
    fetchExecutives();
  }, []);

  const fetchExecutives = async () => {
    const res = await fetch('http://localhost:8080/api/admin/delivery_executive');
    const data = await res.json();
    console.log("API Response:", data);
    setExecutives(data);
    setFiltered(data);
  };

  const handleSearch = () => {
    if (searchId.trim() === '') return setFiltered(executives);
    const result = executives.filter(e => e.userId.toString() === searchId);
    setFiltered(result);
  };

  const handleOpenDialog = (mode, executive = null) => {
      setFormMode(mode);
      setUpdateDEid(executive);

      if (executive) {
        setFormData({
          userName: executive.userName || '',
          password: executive.password || '',
          fullName: executive.fullName || '',
          email: executive.email || '',
          contactNumber: executive.contactNumber || '',
          role: executive.role || '',
          zoneAssigned: executive.zoneAssigned || ''
        });
      } else {
        setFormData({
          userName: '',
          password: '',
          fullName: '',
          email: '',
          contactNumber: '',
          role: '',
          zoneAssigned: ''
        });
      }

    setOpenDialog(true);
  };


  const handleSave = async () => {
    const payload = { ...formData,fullName: formData.fullName.trim(),userName: formData.userName.trim(),email: formData.email.trim(),contactNumber: formData.contactNumber.trim(),zoneAssigned: formData.zoneAssigned.trim()};

    if (formMode === 'update' && (formData.password === updateDEid.password || !formData.password)) {
      delete payload.password;
    }
    console.log("Payload being sent:", payload);
    const url =
      formMode === 'onboard'
        ? 'http://localhost:8080/api/admin/onboardDeliveryExecutive'
        : `http://localhost:8080/api/admin/deliveryexecutive/${updateDEid.userId}`;

    const method = formMode === 'onboard' ? 'POST' : 'PUT';

    await fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload),
    });
    setOpenDialog(false);
    fetchExecutives();
  };


  const handleDelete = async () => {
    await fetch(`http://localhost:8080/api/admin/deliveryexecutive/${executiveToDelete.userId}`, {
      method: 'DELETE',
    });

    setConfirmDelete(false);
    fetchExecutives();
  };


  return (
    <Box p={3}>

      <Typography variant="h4" fontWeight={600} gutterBottom>Delivery Executive Management</Typography>

      {/* Header */}
      <Box sx={{ display: 'flex',alignItems: 'center',justifyContent: 'space-between',flexWrap: 'wrap',gap: 2,mb: 3,p: 2,backgroundColor: '#f9f9f9',borderRadius: 2,boxShadow: 1,}}>
        {/* Search Section */}
        <Box sx={{ display: 'flex', gap: 2, alignItems: 'center' }}>

          <TextField label="Search by ID" value={searchId} onChange={(e) => setSearchId(e.target.value)} size="small"/>

          <Button variant="outlined" onClick={handleSearch}>
            Search
          </Button>

        </Box>

        {/* Onboard Button */}
        <Button variant="contained" onClick={() => handleOpenDialog('onboard')} sx={{ whiteSpace: 'nowrap' }}>
          Onboard New Executive
        </Button>
        
      </Box>


      {/* Table Start*/}
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Username</TableCell>
              <TableCell>Password</TableCell>
              <TableCell>Full Name</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Contact</TableCell>
              <TableCell>Role</TableCell>
              <TableCell>Zone Assigned</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filtered.length === 0 ? (
              <TableRow>
                <TableCell colSpan={9} align="center">
                  No records found.
                </TableCell>
              </TableRow>
            ) : (
              filtered.map((e,index) => (
                <TableRow key={`${e.userId}-${index}`}>
                  <TableCell>{e.userId}</TableCell>
                  <TableCell>{e.userName}</TableCell>
                  <TableCell>{e.password}</TableCell>
                  <TableCell>{e.fullName}</TableCell>
                  <TableCell>{e.email}</TableCell>
                  <TableCell>{e.contactNumber}</TableCell>
                  <TableCell>{e.role}</TableCell>
                  <TableCell>{e.zoneAssigned}</TableCell>
                  <TableCell>
                    <IconButton onClick={() => handleOpenDialog('update', e)}><Edit /></IconButton>
                    <IconButton onClick={() => { setExecutiveToDelete(e); setConfirmDelete(true); }}><Delete /></IconButton>
                  </TableCell>
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
      </TableContainer>
      {/* Table End */}


      {/* Form Dialog */}
      <Dialog open={openDialog} onClose={() => setOpenDialog(false)} maxWidth="sm" fullWidth>

        <DialogTitle>{formMode === 'onboard' ? 'Onboard New Executive' : 'Update Executive'}</DialogTitle>

        <DialogContent>
          <TextField
            fullWidth
            margin="dense"
            label="Username"
            value={formData.userName}
            onChange={(e) => setFormData({ ...formData, userName: e.target.value })}
          />
          <TextField
            fullWidth
            margin="dense"
            label="Password"
            type="password"
            value={formData.password}
            onChange={(e) => setFormData({ ...formData, password: e.target.value })}
          />
          <TextField
            fullWidth
            margin="dense"
            label="Full Name"
            value={formData.fullName || ""}
            onChange={(e) => setFormData({ ...formData, fullName: e.target.value })}
          />
          <TextField
            fullWidth
            margin="dense"
            label="Email"
            value={formData.email}
            onChange={(e) => setFormData({ ...formData, email: e.target.value })}
          />
          <TextField
            fullWidth
            margin="dense"
            label="Contact"
            value={formData.contactNumber}
            onChange={(e) => setFormData({ ...formData, contactNumber: e.target.value })}
          />
          <TextField
            fullWidth
            margin="dense"
            label="Role"
            value={formData.role}
            onChange={(e) => setFormData({ ...formData, role: e.target.value })}
          />
          <TextField
            fullWidth
            margin="dense"
            label="Zone Assigned"
            value={formData.zoneAssigned}
            onChange={(e) => setFormData({ ...formData, zoneAssigned: e.target.value })}
          />
        </DialogContent>

        <DialogActions>

          <Button onClick={() => setOpenDialog(false)}>Cancel</Button>
          <Button onClick={handleSave} variant="contained">
            {formMode === 'onboard' ? 'Onboard' : 'Update'}
          </Button>

        </DialogActions>

      </Dialog>



      {/* Delete Confirmation Dialog */}
      <Dialog open={confirmDelete} onClose={() => setConfirmDelete(false)}>

        <DialogTitle>Confirm Deletion</DialogTitle>

        <DialogContent>Are you sure you want to delete this delivery executive?</DialogContent>

        <DialogActions>
          <Button onClick={() => setConfirmDelete(false)}>Cancel</Button>
          <Button onClick={handleDelete} variant="contained" color="error">Delete</Button>
        </DialogActions>

      </Dialog>

    </Box>
  );
};

export default DeliveryExecutiveTab;
