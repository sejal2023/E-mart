import React, { useState, useEffect } from "react";
import { Navbar, Nav, Form, FormControl, Button, Container } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import cartimage from "../Home/cartimage.png";
import ProfileDropdown from "./ProfileDropdown";

const NavigationBar = () => {
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const checkLoginStatus = () => {
    const token = sessionStorage.getItem("token");
    setIsLoggedIn(!!token);
  };

  useEffect(() => {
    checkLoginStatus();

    const handleStorageChange = () => {
      checkLoginStatus();
    };

    window.addEventListener("storage", handleStorageChange);
    
    return () => {
      window.removeEventListener("storage", handleStorageChange);
    };
  }, []);

  const handleLogout = () => {
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("user");
    setIsLoggedIn(false);
    navigate("/login");
  };

  return (
    <Navbar expand="lg" style={{ background: "linear-gradient(135deg, #753370 0%, #298096 100%)" }}>
      <Container>
        <Navbar.Brand as={Link} to="/"> <img 
            src={cartimage} 
            alt="e-Mart Logo" 
            style={{ width: "50px", height: "50px", marginRight: "10px" }} 
          />
             <span style={{ color: "white", fontWeight: "bold", fontSize: "1.5rem" }}>E͎-͎m͎a͎r͎t͎</span>
          </Navbar.Brand>
        <Form className="d-flex ms-auto" style={{ width: '50%' }}>
          <FormControl type="search" placeholder="Search products..." className="me-2" />
          <Button variant="outline-light">Search</Button>
        </Form>
        <Nav className="ms-auto">
       
          <Nav.Link as={Link} to="/cart">
           <strong style={{color:"darkgoldenrod"}}> Cart 🛒</strong>
          </Nav.Link>
          {isLoggedIn ? (
            <ProfileDropdown onLogout={handleLogout} />
          ) : (
            <Nav.Link as={Link} to="/login">
              <Button variant="outline-light">Login</Button>
            </Nav.Link>
          )}
        </Nav>
      </Container>
    </Navbar>
  );
};

export default NavigationBar;
