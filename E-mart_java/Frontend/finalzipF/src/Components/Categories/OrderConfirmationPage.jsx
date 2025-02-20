

import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

const OrderConfirmationPage = () => {
  const navigate = useNavigate();
  const user = JSON.parse(sessionStorage.getItem("user")) || {};
  const totalAmount = JSON.parse(sessionStorage.getItem("totalAmount")) || 0;
  // const cart = JSON.parse(sessionStorage.getItem("cartData")) || 0;
  const cartIds = JSON.parse(sessionStorage.getItem("cartIds")) || [];
  const paymentMethod = sessionStorage.getItem("paymentMethod") || "unknown";

  const [orderId, setOrderId] = useState(null);
  const [remainingSuperCoins, setRemainingSuperCoins] = useState(user?.supercoin || 0);
  const [earnedSuperCoins, setEarnedSuperCoins] = useState(0); 

  // const supercoin = cart.usedSuperCoins;
  // console.log("heyyyyyyyyyyyy",cart);
const [Items, setItems] = useState([]);

  useEffect(() => {
      const storedCartData = JSON.parse(sessionStorage.getItem("cartData")) || [];
      setItems(storedCartData);
      // setSupercoinBalance(user?.supercoin || 0);
    }, []);

    console.log(Items.usedSuperCoins);

  useEffect(() => {
    setRemainingSuperCoins(Items.usedSuperCoins);

    if (cartIds.length > 0 && orderId === null) {
      const singleCartId = cartIds[0]; 

      console.log("Sending Order Data:", {
        userId: user.id,
        cartId: singleCartId,
        amount: totalAmount,
        paymentMethod: paymentMethod,
      });

      
      fetch("http://localhost:8282/orders/place", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          userId: user.id,
          cartId: singleCartId,
          amount: totalAmount,
          paymentMethod: paymentMethod,
        }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.json();
        })
        .then((data) => {
          console.log("Order stored successfully:", data);
          setOrderId(data.user.id);

          
          const earnedCoins = Math.floor(totalAmount * 0.10);
          setEarnedSuperCoins(earnedCoins);

          
          const newSuperCoinBalance = (user.supercoin-Items.usedSuperCoins) + earnedCoins;
          updateSuperCoinBalance(user.id, newSuperCoinBalance, earnedCoins);
        })
        .catch((error) => console.error("Error storing order:", error.message));
    }
  }, [user.supercoin, cartIds, paymentMethod, orderId]);

  console.log("Order ID:", orderId);

  
  const updateSuperCoinBalance = (userId, newBalance, earnedCoins) => {
    fetch(`http://localhost:8282/update-supercoin/${userId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        supercoin: newBalance,
        earnedSuperCoins: earnedCoins,
      }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        console.log("SuperCoin balance updated successfully:", data);
        
        const updatedUser = { ...user, supercoin: newBalance };
        
        sessionStorage.setItem("user", JSON.stringify(updatedUser));
        setUser(updatedUser); // Important: Update React state
      })
      .catch((error) => console.error("Error updating SuperCoin balance:", error.message));
  };
  

  const handleDownloadInvoice = () => {
    window.location.href = `http://localhost:8282/api/invoice/download/${orderId}`;
  };


  

  return (
    <div className="container mt-5 text-center position-relative">
      <div className="card shadow-lg p-4">
        <div className="text-success display-3">✅</div>
        <h2 className="text-success mt-3">Order Confirmed!</h2>
        <p className="text-muted">Thank you for your purchase. Your order has been successfully placed.</p>

        <div className="mt-4">
          <h4 className="fw-bold">Order Summary</h4>
          <p>Order ID: <strong>{orderId}</strong></p>
          <p>Total Amount Paid: <strong>₹{totalAmount}</strong></p>
          <p className="text-primary">Used SuperCoins: <strong>{Items.usedSuperCoins}</strong></p>
          <p className="text-success">Earned SuperCoins: <strong>{earnedSuperCoins}</strong></p>
          {/* <p className="text-success">Updated SuperCoins: <strong>{user.supercoin-Items.usedSuperCoins}</strong></p> */}
          <p className="text-info">Payment Method: <strong>{paymentMethod}</strong></p>
        </div>

        <button className="btn btn-primary mt-4" onClick={() => navigate("/")}>Go to Home</button>
        {Boolean(orderId) && (
          <button className="btn btn-secondary mt-3 ms-3" onClick={handleDownloadInvoice}>
            Download Invoice
          </button>
        )}
      </div>
    </div>
  );
};

export default OrderConfirmationPage;





// ====================================================================================================================






// import React from "react";
// import { useNavigate } from "react-router-dom";
// import styled from "styled-components";

// const ConfirmationContainer = styled.div`
//   min-height: 100vh;
//   display: flex;
//   flex-direction: column;
//   align-items: center;
//   justify-content: center;
//   background-color: #f0f0f0;
//   color: #333;
//   text-align: center;
// `;

// const Button = styled.button`
//   background: #4caf50;
//   color: white;
//   border: none;
//   padding: 10px 20px;
//   margin-top: 20px;
//   border-radius: 5px;
//   cursor: pointer;
//   font-size: 1rem;

//   &:hover {
//     background: #45a049;
//   }
// `;

// const OrderConfirmationPage = () => {
//   const navigate = useNavigate();

//   return (
//     <ConfirmationContainer>
//       <h1>✅ Payment Successful!</h1>
//       <p>Your order has been confirmed. Thank you for your purchase.</p>
//       <Button onClick={() => navigate("/")}>Back to Home</Button>
//     </ConfirmationContainer>
//   );
// };

// export default OrderConfirmationPage;



