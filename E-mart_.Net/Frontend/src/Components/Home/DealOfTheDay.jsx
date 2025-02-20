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
    autoplaySpeed: 3000, // Changes slide every 3 seconds
    arrows: true
  };

  return (
    <Container className="py-4">
      <h3 className="text-center mb-4">Deal of the Day</h3>
      <Slider {...settings}>
        <div>
          <img className="d-block w-100" src="/images/dealday1.jpg" alt="Deal 1" />
        </div>
        <div>
          <img className="d-block w-100" src="/images/dealday2.jpg" alt="Deal 2" />
        </div>
      </Slider>
    </Container>
  );
};

export default DealOfTheDay;
