import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import styled from "styled-components";
import signupimage from '../Home/signupimage.png';

const Signup = () => {
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [address, setAddress] = useState("");
  const [phone, setPhone] = useState("");
  const [loyalMember, setLoyalMember] = useState(false);
  const [superCoins, setSuperCoins] = useState("");
  const [signupSuccess, setSignupSuccess] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      setErrorMessage("❌ Passwords do not match!");
      return;
    }
    const signupData = { email, username, passwordHash: password, address, phone, loyalty: loyalMember, supercoin: superCoins };
    try {
      const response = await fetch("http://localhost:8282/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(signupData),
      });
      if (response.ok) {
        setSignupSuccess(true);
        setTimeout(() => navigate("/login"), 2000);
      } else {
        const errorText = await response.text();
        setErrorMessage(errorText || "Signup failed. Try again.");
      }
    } catch (error) {
      setErrorMessage("An error occurred. Please try again.");
    }
  };

  return (
    <MainScreen>
      <Card>
        <LeftSide>
          <ProductImage src={signupimage} alt="signupimage" />
        </LeftSide>
        <RightSide>
          <h2 style={{ textAlign: "center", color: "#753370" }}>Sign Up</h2>
          {errorMessage && <ErrorText>{errorMessage}</ErrorText>}
          {signupSuccess && <SuccessText>✅ Signup successful! Redirecting...</SuccessText>}
          <form onSubmit={handleSubmit}>
            <Input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
            <Input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} required />
            <Input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required />
            <Input type="password" placeholder="Confirm Password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} required />
            <Input type="text" placeholder="Address" value={address} onChange={(e) => setAddress(e.target.value)} required />
            <Input type="tel" placeholder="Phone Number" value={phone} onChange={(e) => setPhone(e.target.value)} required />
            <CheckboxContainer>
              <input type="checkbox" id="loyalMember" checked={loyalMember} onChange={(e) => setLoyalMember(e.target.checked)} />
              <label htmlFor="loyalMember">Loyal Member?</label>
            </CheckboxContainer>
            <Input type="number" placeholder="SuperCoins" value={superCoins} onChange={(e) => setSuperCoins(e.target.value)} min="0" required />
            <Button type="submit">Sign Up</Button>
          </form>
          <p style={{ textAlign: "center" }}>Already have an account? <Link to="/login" style={{ color: "#007bff" }}>Login</Link></p>
        </RightSide>
      </Card>
    </MainScreen>
  );
};

const MainScreen = styled.div`
  min-height: 100vh;
  display: flex;
  padding: 25px;
  justify-content: center;
  align-items: center;
  background-color: #dfdbe5;
  background-image: url("https://wallpaperaccess.com/full/3063067.png");
  color: #963e7b;
`;

const Card = styled.div`
  width: 60rem;
  background: white;
  border-radius: 1.5rem;
  box-shadow: 4px 3px 20px rgba(53, 53, 53, 0.55);
  display: flex;
  flex-direction: row;
  transition: transform 0.3s ease-in-out;
  transform-style: preserve-3d;
  &:hover {
  transform: scale(1.05);
  transition: transform 0.3s ease-in-out;
  box-shadow: 6px 6px 25px rgba(53, 53, 53, 0.7);
}

`;

const LeftSide = styled.div`
  background: rgb(247, 244, 244);
  width: 25rem;
  display: flex;
  align-items: center;
  justify-content: center;
  border-top-left-radius: 1.5rem;
  border-bottom-left-radius: 1.5rem;
`;

const ProductImage = styled.img`
  width: 360px;
  height: 600px;
  border-radius: 10px;
  object-fit: cover;
`;

const RightSide = styled.div`
  background-color: #ffffff;
  width: 35rem;
  border-bottom-right-radius: 1.5rem;
  border-top-right-radius: 1.5rem;
  padding: 2rem;
`;

const Input = styled.input`
  width: 100%;
  padding: 0.75rem;
  border: none;
  border-bottom: 1.5px solid #ccc;
  margin-bottom: 1rem;
  border-radius: 0.3rem;
  font-size: 1rem;
  outline: none;
  transition: all 0.3s ease-in-out;
  &:focus {
    border-bottom: 1.5px solid #753370;
  }
`;

const Button = styled.button`
  background: linear-gradient(135deg, #753370 0%, #298096 100%);
  padding: 15px;
  border: none;
  border-radius: 50px;
  color: white;
  font-size: 1.2rem;
  width: 100%;
  transition: 0.3s;
  &:hover {
    transform: scale(1.05);
  }
`;

const ErrorText = styled.p`
  color: red;
  text-align: center;
`;

const SuccessText = styled.p`
  color: green;
  text-align: center;
`;

const CheckboxContainer = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
  input {
    margin-right: 0.5rem;
  }
`;


export default Signup;
