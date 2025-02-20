import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import QRCode from "qrcode.react";
import toast, { Toaster } from "react-hot-toast";
import Payimage from "../Categories/Payimage.jpg"; // Ensure this path is correct

const MainScreen = styled.div`
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #dfdbe5;
  background-image: url("https://wallpaperaccess.com/full/3063067.png");
  color: #963e7b;
  align-items: center;
  justify-content: center;
`;

const Card = styled.div`
  width: 60rem;
  background: white;
  border-radius: 1.5rem;
  box-shadow: 4px 3px 20px rgba(53, 53, 53, 0.55);
  display: flex;
  flex-direction: row;
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
  transform-style: preserve-3d;

  &:hover {
    transform: scale(1.05);
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

const PaymentImage = styled.img`
  width: 360px;
  height: 360px;
  border-radius: 50%;
  object-fit: cover;
`;

const RightSide = styled.div`
  background-color: #ffffff;
  width: 35rem;
  border-bottom-right-radius: 1.5rem;
  border-top-right-radius: 1.5rem;
  padding: 1rem 2rem 3rem 3rem;
  text-align: center;
`;

const Input = styled.input`
  width: 100%;
  padding: 0.5rem;
  border: none;
  border-bottom: 1.5px solid #ccc;
  margin-bottom: 1rem;
  border-radius: 0.3rem;
  font-size: 1.1rem;
  font-weight: 500;
  outline: none;
  transition: all 0.3s ease-in-out;
  &:focus {
    border-bottom: 1.5px solid #753370;
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.15);
  }
`;

const Select = styled.select`
  width: 100%;
  padding: 0.5rem;
  border: none;
  border-bottom: 1.5px solid #ccc;
  margin-bottom: 1rem;
  font-size: 1.1rem;
  font-weight: 500;
  outline: none;
  transition: all 0.3s ease-in-out;
  &:focus {
    border-bottom: 1.5px solid #753370;
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.15);
  }
`;

const Button = styled.button`
  background: linear-gradient(135deg, #753370 0%, #298096 100%);
  padding: 15px;
  border: none;
  border-radius: 50px;
  color: white;
  font-weight: 400;
  font-size: 1.2rem;
  margin-top: 10px;
  width: 100%;
  letter-spacing: 0.11rem;
  outline: none;
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
  &:hover {
    transform: scale(1.05) translateY(-3px);
    box-shadow: 3px 3px 6px rgba(56, 55, 55, 0.52);
  }
`;

const QRCodeContainer = styled.div`
  margin-top: 20px;
`;


// import { useState, useEffect } from "react";
// import { useNavigate } from "react-router-dom";
// import { toast } from "react-hot-toast";
// import QRCode from "qrcode.react";

const PaymentPage = () => {
  const [upiId, setUpiId] = useState("");
  const [paymentMethod, setPaymentMethod] = useState("upi");
  const [qrCodeValue, setQrCodeValue] = useState(null);
  const navigate = useNavigate();

  // Retrieve final price from session storage
  const storedFinalPrice = JSON.parse(sessionStorage.getItem("finalPrice")) || 0;

  // Retrieve payment method from session storage on component mount
  useEffect(() => {
    const storedPaymentMethod = sessionStorage.getItem("paymentMethod");
    if (storedPaymentMethod) {
      setPaymentMethod(storedPaymentMethod);
    }
  }, []);

  // Store payment method in session storage whenever it changes
  const handlePaymentMethodChange = (e) => {
    const selectedMethod = e.target.value;
    setPaymentMethod(selectedMethod);
    sessionStorage.setItem("paymentMethod", selectedMethod);
  };

  const generateQRCode = () => {
    let selectedAmount = storedFinalPrice;

    if (!selectedAmount || (paymentMethod === "PAYPAL" && !upiId)) {
      toast.error("Please enter all required fields.");
      return;
    }

    let qrValue = "";

    if (paymentMethod === "PAYPAL") {
      const upiUrl = `upi://pay?pa=${upiId}&pn=Merchant&mc=&tid=&tr=&tn=Payment&am=${selectedAmount}&cu=INR`;
      qrValue = upiUrl;
    } else if (paymentMethod === "CREDIT_CARD") {
      qrValue = `Card Payment: ${selectedAmount} INR`;
    } else if (paymentMethod === "COD") {
      qrValue = `Cash on Delivery: ${selectedAmount} INR`;
    }

    setQrCodeValue(qrValue);

    setTimeout(() => {
      navigate("/OrderConfirmationPage");
    }, 15000);
  };

  return (
    <MainScreen>
      <Card>
        <LeftSide>
          <PaymentImage src={Payimage} alt="Payimage" />
        </LeftSide>
        <RightSide>
          <h2>Payment Page</h2>
          <Select onChange={handlePaymentMethodChange} value={paymentMethod}>
            <option value="PAYPAL">UPI Payment</option>
            <option value="CREDIT_CARD">Debit Card/Credit Card</option>
            <option value="COD">Cash on Delivery (COD)</option>
          </Select>

          {paymentMethod === "PAYPAL" && (
            <Input
              type="text"
              placeholder="Enter UPI ID"
              value={upiId}
              onChange={(e) => setUpiId(e.target.value)}
            />
          )}

          {paymentMethod === "CREDIT_CARD" && (
            <>
              <Input type="number" placeholder="Enter Card Number" />
              <Input type="text" placeholder="Enter Card Expiry Date (MM/YY)" />
              <Input type="text" placeholder="Enter Card CVV" />
            </>
          )}

          {paymentMethod === "COD" && (
            <p>Cash on Delivery selected. Pay the amount in cash upon delivery.</p>
          )}

          <p>Amount: {storedFinalPrice}</p>

          <Button onClick={generateQRCode}>Generate QR Code</Button>
          {qrCodeValue && (
            <QRCodeContainer>
              <QRCode value={qrCodeValue} size={200} />
              <p>Redirecting in 15 seconds...</p>
            </QRCodeContainer>
          )}

          <Toaster position="top-right" />
        </RightSide>
      </Card>
    </MainScreen>
  );
};

export default PaymentPage;
