import React, { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

const ProductPage = () => {
  const { categoryId, subcategoryId } = useParams(); 
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchProducts = async () => {
      let url = "";

      
      if (subcategoryId) {
        url = `http://localhost:8282/api/products/subcategories/${subcategoryId}`;
      } else {
        url = `http://localhost:8282/api/products/categories/${categoryId}`;
      }

      try {
        const response = await fetch(url);

        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        console.log(data);
        setProducts(data);
      } catch (error) {
        console.error("Error fetching products:", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchProducts();
  }, [categoryId, subcategoryId]);

  return (
    <div className="container-fluid">
      <div className="row">
        {/* Sidebar Filters */}
        <nav className="col-md-3 col-lg-2 d-md-block bg-light sidebar p-3 border-end">
          <h5 className="fw-bold">Filters</h5>
          <div className="mb-3">
            <h6 className="text-primary">Brand</h6>
            <select className="form-select">
              <option>Samsung</option>
              <option>Apple</option>
              <option>LG</option>
              <option>Sony</option>
            </select>
          </div>
          <div className="mb-3">
            <h6 className="text-primary">Price Range</h6>
            <input type="range" className="form-range" min="0" max="100000" />
          </div>
          <div className="mb-3">
            <h6 className="text-primary">Storage Specification</h6>
            <select className="form-select">
              <option>64GB</option>
              <option>128GB</option>
              <option>256GB</option>
              <option>512GB</option>
            </select>
          </div>
        </nav>

        {/* Main Content */}
        <main className="col-md-9 col-lg-10 px-md-4">
          <h4 className="mt-3 fw-bold">
            Products in {subcategoryId ? `Subcategory ${subcategoryId}` : `Category ${categoryId}`}
          </h4>

          {isLoading ? (
            <p className="text-center text-muted">Loading products...</p>
          ) : products.length > 0 ? (
            <>
              {/* Newly Launched Products */}
              <div className="my-4">
                <h4 className="fw-bold text-success">Newly Launched Products</h4>
                <div className="d-flex gap-3 overflow-auto">
                  {products.slice(0, 3).map((product) => (
                    <div key={product.id} className="card p-2 shadow-sm border rounded" style={{ width: "15rem" }}>
                      <img
                        src={product.imageUrl}
                        alt={product.name}
                        className="card-img-top"
                        style={{ maxHeight: "150px", objectFit: "contain" }}
                      />
                      <h6 className="mt-2 text-center">{product.name}</h6>
                    </div>
                  ))}
                </div>
              </div>

              {/* All Category Products */}
              <div className="row">
                {products.map((product) => (
                  <div key={product.id} className="col-md-4 mb-4">
                    <div className="card shadow-lg p-3 border rounded">
                      {/* Product Image */}
                      <img
                        src={product.imageUrl}
                        alt={product.name}
                        className="card-img-top"
                        style={{ maxHeight: "200px", objectFit: "contain" }}
                      />
                      <div className="card-body">
                        <h5 className="fw-bold">{product.name}</h5>
                        <p className="text-muted">Price: <span className="fw-bold">₹{product.price}</span></p>
                        <p className="text-danger fw-bold">Discounted Price: ₹{(product.price - product.discount).toFixed(2)}</p>
                        <p className="text-success">You Save: ₹{product.discount}</p>
                        <p className="text-dark fw-bold">Stocks Available: {product.stocks}</p>
                        <div className="d-flex justify-content-between">
                          <button className="btn btn-success">Buy Now</button>
                          <button className="btn btn-warning">Add to Cart</button>
                        </div>
                        {/* Link to Product Details Page */}
                        <Link to={`/product/${product.id}`} className="btn btn-info mt-3 w-100">
                          View Details
                        </Link>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            </>
          ) : (
            <p className="text-center text-muted">No products available.</p>
          )}
        </main>
      </div>
    </div>
  
  );
};

export default ProductPage;
