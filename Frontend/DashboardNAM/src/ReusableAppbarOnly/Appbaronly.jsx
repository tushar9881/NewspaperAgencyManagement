import React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import { Box } from '@mui/material';
import LogoutIcon from '@mui/icons-material/Logout';
import Avatar from '@mui/material/Avatar';
import PersonIcon from '@mui/icons-material/Person';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';

export default function AppBarOnly({ title,fullName,role}) {

  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/');
  };


  return (
    <AppBar position="fixed">

      <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>

        <Typography variant="h6" component="div">
          {title}
        </Typography>

        {/* Right side: User info + Logout */}
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>

          <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>

            <Avatar sx={{ bgcolor: 'white', color: 'primary.main', width: 30, height: 30 }}>
              <PersonIcon fontSize="small" />
            </Avatar>

            <Typography variant="body1" sx={{ color: 'white' }}>
              {fullName} ({role})
            </Typography>

          </Box>

          <Button color="inherit" startIcon={<LogoutIcon />} onClick={handleLogout}>
            Logout
          </Button>

        </Box>

      </Toolbar>
      
    </AppBar>
  );
}
