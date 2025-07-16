import * as React from 'react';
import { styled, useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import MuiDrawer from '@mui/material/Drawer';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import { Card } from '@mui/material';
import List from '@mui/material/List';
import Avatar from '@mui/material/Avatar';
import CssBaseline from '@mui/material/CssBaseline';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import { Link, Routes, Route } from 'react-router-dom';
import LogoutIcon from '@mui/icons-material/Logout';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import PersonIcon from '@mui/icons-material/Person';
import DashboardIcon from '@mui/icons-material/Dashboard';
import PeopleIcon from '@mui/icons-material/People';
import LocalShippingIcon from '@mui/icons-material/LocalShipping';

import DeliveryLog from './Pages/DeliveryLog';
import Customer from './Pages/Customer';
import DeliveryExecutive from './Pages/DeliveryExecutive';

const drawerWidth = 300;

const openedMixin = (theme) => ({
  width: drawerWidth,
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.enteringScreen,
  }),
  overflowX: 'hidden',
});

const closedMixin = (theme) => ({
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  overflowX: 'hidden',
  width: `calc(${theme.spacing(7)} + 1px)`,
  [theme.breakpoints.up('sm')]: {
    width: `calc(${theme.spacing(8)} + 1px)`,
  },
});

const DrawerHeader = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'flex-end',
  padding: theme.spacing(0, 1),
  ...theme.mixins.toolbar,
}));

const StyledAppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})(({ theme }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
}));

const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
    boxSizing: 'border-box',
    ...(open && {
      ...openedMixin(theme),
      '& .MuiDrawer-paper': openedMixin(theme),
    }),
    ...(!open && {
      ...closedMixin(theme),
      '& .MuiDrawer-paper': closedMixin(theme),
    }),
  })
);

export default function MiniDrawer() {

  const user = JSON.parse(localStorage.getItem('user')) || {};
  const fullName = user.fullName || 'User';
  const role = user.role || '';


  const theme = useTheme();
  const [open, setOpen] = React.useState(true);

  const navItems = [
    { text: 'Dashboard', path: '/admin', icon: <DashboardIcon /> },
    { text: 'Customer', path: '/admin/customer', icon: <PeopleIcon /> },
    { text: 'Delivery Executive', path: '/admin/deliveryexecutive', icon: <LocalShippingIcon /> },
  ];
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/');
  };

  return (
    <Box sx={{ display: 'flex' }}>

      <CssBaseline />

      <StyledAppBar position="fixed">

        <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>

          <Typography variant="h6" noWrap component="div">
            Newspaper Agency Management
          </Typography>

          <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>

            <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>

              <Avatar sx={{ bgcolor: 'white', color: 'primary.main', width: 30, height: 30 }}>
                <PersonIcon fontSize="small" />
              </Avatar>

              <Typography variant="body1" sx={{ color: 'white' }}>
                {fullName} ({role})
              </Typography>

            </Box>

            <Button
              color="inherit"
              startIcon={<LogoutIcon />}
              onClick={handleLogout}
            >
              Logout
            </Button>

          </Box>

        </Toolbar>

      </StyledAppBar>

      <Drawer variant="permanent" open={open}>

        <DrawerHeader>

          <IconButton onClick={() => setOpen(!open)}>
            {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
          </IconButton>

        </DrawerHeader>

        <Divider />

        <List>
          {navItems.map((item) => (
            <ListItem key={item.text} disablePadding sx={{ display: 'block' }}>
              <ListItemButton
                component={Link}
                to={item.path}
                sx={{
                  minHeight: 64,
                  justifyContent: open ? 'initial' : 'center',
                  px: 2.5,
                }}
              >
                <ListItemIcon
                  sx={{
                    minWidth: 0,
                    mr: open ? 3 : 'auto',
                    justifyContent: 'center',
                  }}
                >
                  {item.icon}
                </ListItemIcon>
                <ListItemText
                  primary={item.text}
                  sx={{ opacity: open ? 1 : 0 }}
                />
              </ListItemButton>
            </ListItem>
          ))}
        </List>

      </Drawer>

      <Box component="main" sx={{flexGrow: 1,p: 3,minHeight: '100vh',width: '100%',boxSizing: 'border-box',overflow: 'auto',backgroundColor: '#f9f9f9' }}>

        <DrawerHeader />

        <Routes>
          <Route path="/" element={<DeliveryLog />} />
          <Route path="deliverylog" element={<DeliveryLog />} />
          <Route path="customer" element={<Customer />} />
          <Route path="deliveryexecutive" element={<DeliveryExecutive />} />
        </Routes>

      </Box>
      
    </Box>
  );
}
