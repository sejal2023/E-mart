import React, { useState, useEffect } from "react";
// import CategoriesBar.c
import './CategoriesBar.css';

import { NavDropdown, Nav, Container, Image } from "react-bootstrap";
import { Link } from "react-router-dom";

// KOMAL pleasse add images here

const categoryImageMap = {
  // 1: "https://cdn-icons-png.flaticon.com/512/5819/5819387.png",
  1: "https://cdn-icons-png.flaticon.com/512/2278/2278984.png",
  // 2: "https://cdn-icons-png.flaticon.com/512/1692/1692708.png",
  2: "https://th.bing.com/th/id/OIP.Nh0zXSl-80Gw9vw-f-G9SQHaHa?rs=1&pid=ImgDetMain",
  3: "https://cdn-icons-png.flaticon.com/512/3300/3300371.png",
  4: "https://th.bing.com/th/id/OIP._iePko1T0X7nwlRbq2ekwAHaHa?rs=1&pid=ImgDetMain"
};

const CategoriesBar = () => {
  const [categories, setCategories] = useState([]);
  const [subcategories, setSubcategories] = useState({});
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await fetch("http://localhost:5208/api/Categories");

        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        console.log("Fetched Categories:", data); // Log categories data
        console.log(data[0].categoryid);
        setCategories(data);
      } catch (error) {
        console.error("Error fetching categories:", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchCategories();
  }, []);

  // Function to fetch subcategories when a category is clicked
  const fetchSubcategories = async (categoryId) => {
    try {
      const response = await fetch(`http://localhost:5208/api/SubCategory/category/${categoryId}`);
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const data = await response.json();
      console.log(`Fetched Subcategories for category ${categoryId}:`, data); // Log subcategories data for the clicked category
      setSubcategories(prevState => ({
        ...prevState,
        [categoryId]: data
      }));
    } catch (error) {
      console.error("Error fetching subcategories:", error);
    }
  };

  if (isLoading) {
    return (
      <Container fluid className="bg-light py-2">
        <Nav className="justify-content-center">
          <p>Loading categories...</p>
        </Nav>
      </Container>
    );
  }

  return (
    <Container fluid className="bg-light py-2">
      <Nav className="justify-content-center">
        {categories.length > 0 ? (
          categories.map((category) => (
            <NavDropdown
              key={category.id}
              title={
                <span>
                  <Image
                    // src="https://rukminim1.flixcart.com/flap/80/80/image/22fddf3c7da4c4f4.png?q=100"
                    src={categoryImageMap[category.id] || "https://rukminim1.flixcart.com/flap/80/80/image/22fddf3c7da4c4f4.png?q=100"} // Use mapping or fallback image

                    width="30"
                    height="30"
                    className="me-2"
                  />
                  {category.categoryname}
                </span>
              }
              id={`category-${category.id}-dropdown`}
              onClick={() => fetchSubcategories(category.categoryid)} // Fetch subcategories when clicked
            >
              <NavDropdown.Item as={Link} to={`/category/${category.categoryid}`}>
                View All {category.categoryname}
              </NavDropdown.Item>
              {/* Dynamically render subcategories */}
              {subcategories[category.categoryid] && subcategories[category.categoryid].length > 0 ? (
                subcategories[category.categoryid].map((subcategory) => (
                  <NavDropdown.Item
                    key={subcategory.id}
                    as={Link} 
                    to={`/category/${category.categoryid}/subcategory/${subcategory.subcategoryid}`} // Link to subcategory-based products
                  >
                    {subcategory.subcategoryname}
                  </NavDropdown.Item>
                ))
              ) : (
                <NavDropdown.Item disabled>No subcategories available</NavDropdown.Item>
              )}
            </NavDropdown>
          ))
        ) : (
          <p>No categories available</p>
        )}
      </Nav>
    </Container>
  );
};

export default CategoriesBar;
