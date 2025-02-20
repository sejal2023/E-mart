import React from "react";
import Slider from "react-slick";
import { Container } from "react-bootstrap";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const DealOfTheDay = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000,
    arrows: true,
  };

  return (
    <Container className="py-4">
     <h3 style={{ color: "marun", textAlign: "center", marginBottom: "1rem" }}>
  Exciting Offers
</h3>
      <Slider {...settings}>
        <div style={{ width: "100%", height: "300px", overflow: "hidden" }}>
          <img
            src="src\Components\Home\deal1.jpg"
            alt="Deal 1"
            style={{
              width: "100%",
              height: "100%",
              objectFit: "unset", // Crops the image
              borderRadius: "10px",
            }}
          />
        </div>
        <div style={{ width: "100%", height: "300px", overflow: "hidden" }}>
          <img
            src="src\Components\Home\deal2.jpg"
            alt="Deal 2"
            style={{
              width: "100%",
              height: "100%",
              objectFit: "cover", // Crops the image
              borderRadius: "10px",
            }}
          />
        </div>
      </Slider>
    </Container>
  );
};

export default DealOfTheDay;
