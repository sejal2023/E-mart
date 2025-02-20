import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

const ProductDetails = () => {
  const { productId } = useParams();
  const [product, setProduct] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [selectedPrice, setSelectedPrice] = useState(null);

  const navigate = useNavigate();

  const user = JSON.parse(sessionStorage.getItem("user"));
  const userId = user ? user.userid : null;
  const isLoyaltyMember = Boolean(user.is_loyalty == false? false : true);

  let supercoinBalance = user ? user.supercoin : 0;


  console.log("User loyalty status:", isLoyaltyMember);


  useEffect(() => {
    const fetchProductDetails = async () => {
      try {
        const response = await fetch(`http://localhost:5208/api/Product/${productId}`);
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();
        setProduct(data);
      } catch (error) {
        console.error("Error fetching product details:", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchProductDetails();
  }, [productId]);

  const calculatePriceOptions = () => {
    if (!product) return [];
    
    let finalPrice = parseFloat(product.price).toFixed(2);
    const priceOptions = [{ label: "Original Price", amount: finalPrice }];

    if (isLoyaltyMember) {
        let loyaltyPrice = (finalPrice - finalPrice * 0.1).toFixed(2);
        priceOptions.push({ label: "Loyalty Member Price", amount: loyaltyPrice });

        if (supercoinBalance * 2 >= loyaltyPrice) {
            priceOptions.push({ label: "SuperCoin Payment (0 Balance)", amount: "0.00" });
        }

        let discountBySuperCoins = (supercoinBalance * 0.8).toFixed(2);
        let priceAfterSuperCoins = (loyaltyPrice - discountBySuperCoins).toFixed(2);
        priceOptions.push({ label: "Loyalty Price + SuperCoins", amount: priceAfterSuperCoins });

    } else {
        if (supercoinBalance * 2 >= finalPrice) {
            priceOptions.push({ label: "SuperCoin Payment (0 Balance)", amount: "0.00" });
        }

        let discountBySuperCoins = (supercoinBalance * 0.8).toFixed(2);
        let priceAfterSuperCoins = (finalPrice - discountBySuperCoins).toFixed(2);
        priceOptions.push({ label: "Final Price + SuperCoins", amount: priceAfterSuperCoins });
    }

    return priceOptions;
  };

  const handleAddToCart = async () => {
    if (!userId) {
        console.log("User not logged in");
        navigate("/login"); 
        return;
    }
  
    if (product) {
        let usedSuperCoins = 0;
        let finalPrice = selectedPrice !== null ? selectedPrice : product.price;

        if (selectedPrice === 0) {
            usedSuperCoins = finalPrice * 2;
        } else if (selectedPrice < product.price) {
            if(isLoyaltyMember){
              let loyaltyPrice = product.price - (product.price * 0.1);
              usedSuperCoins = (supercoinBalance * 0.8);
            } else {
              usedSuperCoins = (supercoinBalance * 0.8);
            }
        }

        const cartData = {
            user: { id: userId },
            product: product,
            quantity: 1,
            finalPrice: finalPrice, 
            usedSuperCoins: usedSuperCoins
        };

        // ✅ Store data in sessionStorage before navigating
        sessionStorage.setItem("cartData", JSON.stringify(cartData));
        navigate("/cart");

        try {
            const response = await fetch("http://localhost:5208/api/cart/add", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(cartData),
            });

            if (response.ok) {
                console.log(" Product added to cart!");
                window.dispatchEvent(new Event("cartUpdated"));
            } else {
                const errorData = await response.text();
                console.error(" Error adding product:", errorData);
                alert(errorData);
            }
        } catch (error) {
            console.error(" Network error:", error);
        }
    }
  };

  const handlePriceSelection = (amount) => {
    setSelectedPrice(amount);
  };

  return (
    <div className="container mt-5">
      {isLoading ? (
        <p className="text-center text-muted">Loading product details...</p>
      ) : product ? (
        <div className="row d-flex justify-content-center">
          <div className="col-md-6 text-center">
            <div className="card shadow-lg border rounded p-3">
              <div className="border p-3 rounded bg-light">
                <img
                  src={product.image || "https://via.placeholder.com/400"}
                  alt={product.name}
                  className="img-fluid rounded"
                  style={{ maxHeight: "400px", objectFit: "contain" }}
                />
              </div>
            </div>
          </div>

          <div className="col-md-6">
            <div className="card p-4 shadow-lg border rounded">
              <h2 className="fw-bold">{product.productname}</h2>
              <p className="text-muted">{product.description}</p>

              <h4 className="fw-bold">Available Pricing Options:</h4>
              <ul className="list-group mb-3">
                {calculatePriceOptions().map((option, index) => (
                  <li key={index} className="list-group-item d-flex justify-content-between align-items-center">
                    <div>
                      {option.label !== "Original Price" && (
                        <input
                          type="radio"
                          name="priceOption"
                          checked={selectedPrice === option.amount}
                          onChange={() => handlePriceSelection(option.amount)}
                          className="me-2"
                        />
                      )}
                      {option.label}
                    </div>
                    <strong>
                      {option.label === "Original Price" ? (
                        <del>₹{option.amount.toLocaleString()}</del>
                      ) : (
                        `₹${option.amount.toLocaleString()}`
                      )}
                    </strong>
                  </li>
                ))}
              </ul>

              {selectedPrice !== null && selectedPrice !== product.price && (
                <div className="alert alert-success text-center">
                  🎉 You saved ₹{(product.price - selectedPrice).toLocaleString()}!
                </div>
              )}

              <div className="d-flex gap-3 mt-3">
                <button className="btn btn-success btn-lg w-50">Buy Now</button>
                <button className="btn btn-warning btn-lg w-50" onClick={handleAddToCart}>
                  {userId ? "Add to Cart" : "Login to Add"}
                </button>
              </div>
            </div>
          </div>
        </div>
      ) : (
        <p className="text-center text-muted">Product not found</p>
      )}
    </div>
  );
};

export default ProductDetails;
