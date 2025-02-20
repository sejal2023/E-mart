// import React, { useState, useEffect } from "react";
// import "bootstrap/dist/css/bootstrap.min.css";
// import { useNavigate } from "react-router-dom";

// const CartPage = () => {
//   const [cartItems, setCartItems] = useState([]);
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState(null);
//   const [quantities, setQuantities] = useState({});
//   const [paymentOptions, setPaymentOptions] = useState({});
//   const [supercoinBalance, setSupercoinBalance] = useState(0);
//   const navigate = useNavigate();

//   const user = JSON.parse(sessionStorage.getItem("user"));
//   const userId = user ? user.id : null;
//   const isLoyaltyMember = user?.loyalty || false;

//   useEffect(() => {
//     setSupercoinBalance(user?.supercoin || 0);
    
//     const fetchCartItems = async () => {
//       if (!userId) return;
//       try {
//         const response = await fetch(`http://localhost:8282/api/cart/view/${userId}`);
//         if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

//         const data = await response.json();
//         setCartItems(data);
//       } catch (error) {
//         setError(error.message);
//       } finally {
//         setLoading(false);
//       }
//     };

//     fetchCartItems();
//   }, [userId]);

//   const handleQuantityChange = (cartItemId, change) => {
//     setQuantities(prev => {
//       const newQuantity = Math.max(1, (prev[cartItemId] || 1) + change);
//       return { ...prev, [cartItemId]: newQuantity };
//     });
//   };

//   const handlePaymentOptionChange = (cartItemId, option) => {
//     setPaymentOptions(prev => ({ ...prev, [cartItemId]: option }));
//   };

//   const calculatePrice = (cartItem) => {
//     const basePrice = cartItem.product.price - cartItem.product.discount;
//     const quantity = quantities[cartItem.id] || 1;
//     let finalPrice = basePrice * quantity;
//     if (isLoyaltyMember) finalPrice *= 0.9;
//     return finalPrice;
//   };

//   if (loading) return <div className="container"><p>Loading cart items...</p></div>;
//   if (error) return <div className="container"><p>Error: {error}</p></div>;

//   return (
//     <div className="container">
//       <h2>Your Cart</h2>
//       <p>Loyalty Member: {isLoyaltyMember ? "✅ Yes (10% Discount Available)" : "❌ No"}</p>
//       <p>SuperCoin Balance: {supercoinBalance}</p>

//       <div className="row">
//         {cartItems.map((cartItem) => {
//           const finalPrice = calculatePrice(cartItem);
//           return (
//             <div key={cartItem.id} className="col-md-4 mb-4">
//               <div className="card p-3">
//                 <img
//                   src={cartItem.product.imageUrl}
//                   alt={cartItem.product.name}
//                   className="card-img-top"
//                   style={{ maxHeight: "200px", objectFit: "contain" }}
//                 />
//                 <h5>{cartItem.product.name}</h5>
//                 <p>Original Price: ₹{cartItem.product.price}</p>
//                 <p className="text-danger">Discounted Price: ₹{finalPrice.toFixed(2)}</p>
//                 <p>Stocks Available: {cartItem.product.stocks}</p>
//                 <div className="mt-2">
//                   <button className="btn btn-secondary" onClick={() => handleQuantityChange(cartItem.id, -1)}>-</button>
//                   <span className="mx-2">{quantities[cartItem.id] || 1}</span>
//                   <button className="btn btn-secondary" onClick={() => handleQuantityChange(cartItem.id, 1)}>+</button>
//                 </div>
//                 <div className="mt-2">
//                   <label className="fw-bold">Choose Payment Option:</label>
//                   <select
//                     className="form-control"
//                     value={paymentOptions[cartItem.id] || "regular"}
//                     onChange={(e) => handlePaymentOptionChange(cartItem.id, e.target.value)}
//                   >
//                     <option value="regular">Regular Price</option>
//                     {supercoinBalance * 2 === finalPrice && (
//                       <option value="supercoin_full">Pay with SuperCoins</option>
//                     )}
//                     <option value="combo">50% Cash + 50% SuperCoins</option>
//                     {isLoyaltyMember && <option value="loyalty">Loyalty Discount (10% Off)</option>}
//                   </select>
//                 </div>
//                 <button 
//                   className="btn btn-danger mt-2"
//                   onClick={() => setCartItems(cartItems.filter(item => item.id !== cartItem.id))}
//                 >
//                   Remove
//                 </button>
//               </div>
//             </div>
//           );
//         })}
//       </div>
//     </div>
//   );
// };

// export default CartPage;



// // ======================================================================================/


// import React, { useState, useEffect } from "react";
// import "bootstrap/dist/css/bootstrap.min.css";
// import { useNavigate } from "react-router-dom";

// const CartPage = () => {
//   const [cartItems, setCartItems] = useState([]);
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState(null);
//   const [quantities, setQuantities] = useState({});
//   const [paymentOptions, setPaymentOptions] = useState({});

//   const [supercoinBalance, setSupercoinBalance] = useState(0);
  
//   const navigate = useNavigate();

  
//   const user = JSON.parse(sessionStorage.getItem("user"));
//   const userId = user ? user.id : null;
//   const isLoyaltyMember = user?.loyalty || false; 
  

//   useEffect(() => {

//     const storedCart = JSON.parse(sessionStorage.getItem("cart")) || [];
//     setCartItems(storedCart);


//     setSupercoinBalance(user?.supercoin || 0);
//     console.log("Initial SuperCoin Balance:", user?.supercoin || 0);
    
//     const fetchCartItems = async () => {
//       if (!userId) return;
//       try {
//         const response = await fetch(`http://localhost:8282/api/cart/view/${userId}`);
//         if (!response.ok) {
//           throw new Error(`HTTP error! Status: ${response.status}`);
//         }
//         const data = await response.json();
//         console.log("Fetched Cart Data:", data); 
//         setCartItems(data);
        
//         if (data.length > 0) {
//           const cartIds = data.map(cart => cart.id);
//           sessionStorage.setItem("cartIds", JSON.stringify(cartIds));
//           console.log("Stored Cart IDs:", cartIds);
//         } else {
//           console.warn("Cart IDs not found in response");
//         }
//       } catch (error) {
//         setError(error.message);
//       } finally {
//         setLoading(false);
//       }
//     };
//     fetchCartItems();
//     const handleCartUpdate = () => fetchCartItems();
//     window.addEventListener("cartUpdated", handleCartUpdate);
//     return () => {
//       window.removeEventListener("cartUpdated", handleCartUpdate);
//     };
//   }, [userId]);

//   const handleQuantityChange = (cartItemId, change) => {
//     setQuantities((prevQuantities) => {
//       const newQuantity = Math.max(1, (prevQuantities[cartItemId] || 1) + change);
//       return { ...prevQuantities, [cartItemId]: newQuantity };
//     });
//   };

//   const handlePaymentOptionChange = (cartItemId, option) => {
//         if (option === "supercoins") {
//           const itemPrice = parseFloat(calculatePrice(cartItems.find(item => item.id === cartItemId)));
//           // if (supercoinBalance >= itemPrice) {
//           //   setSupercoinBalance(prevBalance => {
//           //     const newBalance = prevBalance - itemPrice;
//           //     console.log("Updated SuperCoin Balance:", newBalance);
//           //     updateSupercoinBalance(userId, newBalance);
//           //     return newBalance;
//           //   });
//           // } else {
//           //   alert("Insufficient SuperCoins");
//           //   return;
//           // }
//         }
//         setPaymentOptions((prev) => ({
//           ...prev,
//           [cartItemId]: option,
//         }));
//       };

//       const updateSupercoinBalance = async (userId, newBalance) => {
//             try {
//               await fetch(`http://localhost:8282/update-supercoin/${userId}`, {
//                 method: "PUT",
//                 headers: { "Content-Type": "application/json" },
//                 body: JSON.stringify({ supercoin: newBalance }),
//               });
//               console.log("SuperCoin balance updated in backend:", newBalance);
//             } catch (error) {
//               console.error("Error updating SuperCoin balance:", error);
//             }
//           };
        

//   const handleRemove = async (cartItemId) => {
//     try {
//       const response = await fetch(`http://localhost:8282/api/cart/remove/${cartItemId}`, {
//         method: "DELETE",
//       });

//       if (response.ok) {
//         setCartItems(cartItems.filter(item => item.id !== cartItemId));
//       } else {
//         const errorData = await response.json();
//         alert(errorData.message);
//       }
//     } catch (error) {
//       console.error("Error removing product from cart:", error);
//     }
//   };

  
//   const calculatePrice = (cartItem) => {
//     console.log(cartItem.product.discount);
//     console.log(cartItem.product.price);

//     const basePrice = cartItem.product.price - cartItem.product.discount;
//     const quantity = quantities[cartItem.id] || 1;
//     const paymentOption = paymentOptions[cartItem.id];
//     let finalPrice = basePrice * quantity;

//     if (supercoinBalance > 0 && paymentOption === "supercoins") {
//         if (isLoyaltyMember) {
            
//             finalPrice *= 0.9;
//         }

        
//         const appliedSuperCoins = Math.min(supercoinBalance, finalPrice);
//         finalPrice -= appliedSuperCoins;
//     } 
//     else if (paymentOption === "loyalty") {
//         finalPrice *= 0.9;  
//     }

//     return finalPrice.toFixed(2); 
// };

//   const calculateTotalPrice = () => {
//     return cartItems.reduce((total, cartItem) => total + parseFloat(calculatePrice(cartItem)), 0);
//   };

//   if (loading) return <div className="container"><p>Loading cart items...</p></div>;
//   if (error) return <div className="container"><p>Error: {error}</p></div>;

//   return (
//     <div className="container">
//       <h2>Your Cart</h2>
//       {/* <p>Loyalty Member: {isLoyaltyMember ? "✅ Yes (10% Discount Available)" : "❌ No"}</p>  */}


//       <p>SuperCoin Balance: {supercoinBalance}</p>

//       <div className="row">
//         {cartItems.map((cartItem, index) => (
//           <div key={index} className="col-md-4 mb-4">
//             <div className="card p-3">
//               <img
//                 src={cartItem.product.imageUrl}
//                 alt={cartItem.product.name}
//                 className="card-img-top"
//                 style={{ maxHeight: "200px", objectFit: "contain" }}
//               />
//               <h5>{cartItem.product.name}</h5>
//               <p>Original Price: ₹{cartItem.product.price}</p>
//               <p className="text-danger">Discounted Price: ₹{calculatePrice(cartItem)}</p>

              
//               <p>Stocks Available: {cartItem.product.stocks}</p>
//               <div className="mt-2">
//                 <button className="btn btn-secondary" onClick={() => handleQuantityChange(cartItem.id, -1)}>-</button>
//                 <span className="mx-2">{quantities[cartItem.id]}</span>
//                 <button className="btn btn-secondary" onClick={() => handleQuantityChange(cartItem.id, 1)}>+</button>
//               </div>
//               {/* <div className="mt-2">
//                 <label className="fw-bold">Choose Payment Option:</label>
//                 <select
//                   className="form-control"
//                   value={paymentOptions[cartItem.id]}
//                   onChange={(e) => handlePaymentOptionChange(cartItem.id, e.target.value)}
//                 >
//                   <option value="regular">Regular Price</option>
//                   <option value="supercoins">SuperCoins </option>
//                   {isLoyaltyMember && <option value="loyalty">Loyalty Member (10% Off)</option>}
//                 </select>
//               </div> */}
//               <button 
//                 className="btn btn-danger mt-2"
//                 onClick={() => handleRemove(cartItem.id)}
//               >
//                 Remove
//               </button>
//             </div>
//           </div>
//         ))}
//       </div>

//       <div className="mt-4 text-end">
//         <h4>Total Amount: ₹{calculateTotalPrice()}</h4>
//         {/* <button className="btn btn-primary mt-3" onClick={() => navigate("/payment")}>
//           Proceed to Payment
//         </button> */}
//         <button className="btn btn-primary mt-3" onClick={() => {
//   const totalAmount = calculateTotalPrice();
//   const usedSuperCoins = Math.min(supercoinBalance, totalAmount);
//   const newSupercoinBalance = supercoinBalance - usedSuperCoins;

//   console.log("Used SuperCoins:", usedSuperCoins);
//   console.log("Remaining SuperCoins:", newSupercoinBalance);

//   sessionStorage.setItem("totalAmount", JSON.stringify(totalAmount));
//   navigate("/payment");
// }}>
//   Proceed to Payment
// </button>
//       </div>
//     </div>
//   );
// };

// export default CartPage;




// =====================================================================================
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

const CartPage = () => {
  const navigate = useNavigate();
  const [cartItems, setCartItems] = useState([]);
  const [totalAmount, setTotalAmount] = useState(0);

  useEffect(() => {
    const cartData = JSON.parse(sessionStorage.getItem("cartData"));
    if (cartData) {
      setCartItems([{ ...cartData, usedSuperCoins: parseInt(cartData.usedSuperCoins, 10) }]);
      setTotalAmount(parseFloat(cartData.finalPrice));
      sessionStorage.setItem("finalPrice", cartData.finalPrice);
    }
  }, []);

  const handleCheckout = () => navigate("/payment");

  const handleRemoveItem = () => {
    sessionStorage.removeItem("cartData");
    sessionStorage.removeItem("finalPrice");
    setCartItems([]);
    setTotalAmount(0);
  };

  return (
    <div className="container mt-5">
      <h2 className="fw-bold text-center mb-4">Shopping Cart</h2>
      {cartItems.length > 0 ? (
        <div className="card p-4 shadow-lg">
          {cartItems.map((item, index) => (
            <div key={index} className="mb-3">
              <h5>{item.product.productname}</h5>
              <p>{item.product.description}</p>
              <p><strong>Price:</strong> ₹{item.finalPrice.toLocaleString()}</p>
              <p><strong>SuperCoins Used:</strong> {item.usedSuperCoins}</p>
              <button className="btn btn-danger" onClick={handleRemoveItem}>Remove</button>
            </div>
          ))}
          <hr />
          <h4 className="text-end">Total: ₹{totalAmount.toLocaleString()}</h4>
          <button className="btn btn-success w-100 mt-3" onClick={handleCheckout}>Proceed to Checkout</button>
        </div>
      ) : (
        <p className="text-center text-muted">Your cart is empty</p>
      )}
    </div>
  );
};

export default CartPage;
