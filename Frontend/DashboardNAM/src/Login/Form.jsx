import React, { useState } from 'react';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import {Box,Button,Paper,TextField,Typography} from '@mui/material';

const Form = () => {
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');
    try {
      const res = await fetch(`${import.meta.env.VITE_API_URL}/user/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ userName, password })
      });

      if (!res.ok) throw new Error('Invalid credentials');
      const user = await res.json();

      if (!user.role) {
        setError("Login response missing role");
        return;
      }

      localStorage.setItem('user', JSON.stringify(user));
      localStorage.setItem('role', user.role);
      localStorage.setItem('token', user.token || '');

      switch (user.role.toLowerCase()) {
        case 'admin':
          navigate('/admin');
          break;
        case 'customer':
          navigate('/customer');
          break;
        case 'delivery_executive':
          navigate('/delivery_executive');
          break;
        default:
          setError('Unknown role');
      }
    } catch (err) {
      setError(err.message || 'Login failed');
    }
  };

  return (
    <Box sx={{display: 'flex',height: '100vh',width: '100vw',}}>

      
      {/* Left Side Start */}
      <Box sx={{width: '50%',background: 'linear-gradient(to bottom right, #2563eb, #1e40af)',color: 'white',display: 'flex',flexDirection: 'column',alignItems: 'center',justifyContent: 'center',p: 10,}}>

        <Typography variant="h3" component="div" sx={{fontWeight: 'bold',lineHeight: '1.2',mb: 4,textAlign: 'left',textShadow: '0 2px 4px rgba(0, 0, 0, 0.4)',whiteSpace: "pre-line",letterSpacing: 3,}}>
          {'NEWSPAPER\n AGENCY\n MANAGEMENT'}
        </Typography>

        <Typography variant="body1" sx={{fontSize: '1.25rem',fontWeight:500,color: '#cbd5e1',maxWidth: '28rem',textAlign: 'center',}}>
        "From Press to Porch — Lightning Fast." <br />
        Your Trusted Partner in Newspaper Delivery.
        </Typography>

      </Box>
      {/* Left Side End */}




      {/* Right Side Start */}
      <Box sx={{width: '50%',backgroundImage: 'url(/ImageBackground.jpg)',backgroundSize: 'cover',backgroundPosition: 'center',display: 'flex',justifyContent: 'center',alignItems: 'center',px: 4,}}>

        <Paper elevation={3} sx={{p: 4,borderRadius: 4,maxWidth: 400,width: '100%',}}>
          
          <Box textAlign="center" mb={3}>

            <Typography variant="h5" fontWeight="bold" gutterBottom>
              Welcome Back
            </Typography>

            <Typography variant="body2" color="text.secondary">
              Login to continue managing the agency
            </Typography>

          </Box>

          <Box component="form" noValidate onSubmit={handleLogin}>

            <TextField margin="normal" fullWidth label="User Name" variant="outlined" value={userName} onChange={(e) => setUserName(e.target.value)}/>

              <TextField margin="normal" fullWidth label="Password" type="password" variant="outlined" value={password} onChange={(e) => setPassword(e.target.value)} />
              {error && (
                <Typography color="error" sx={{ mt: 1 }}>{error}</Typography>
              )}
              <Button fullWidth variant="contained" color="primary" type="submit" sx={{ mt: 2 }}> Sign In</Button>

          </Box>

          <Typography variant="body2" align="center" sx={{ mt: 2 }}>
            Don’t have an account?{' '}

            <Box
              component="span" sx={{ color: 'primary.main', cursor: 'pointer' }} onClick={()=>{alert("Contact Admin for Registration,Subscription,Onboarding.etc  Thank You!!!")}}
            >
              Register
            </Box>

          </Typography>

        </Paper>

      </Box>
      {/* Right Side End */}



    </Box>
  );
};

export default Form;
