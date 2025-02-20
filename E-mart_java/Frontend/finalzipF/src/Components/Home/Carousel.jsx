import React from "react";
import { Carousel } from "react-bootstrap";
import styled from "styled-components";

// Styled Components
const CarouselContainer = styled.div`
  max-width: 90%;
  margin: auto;
  border-radius: 15px;
  overflow: hidden;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
  transition: transform 0.5s ease-in-out, box-shadow 0.5s ease-in-out;
  perspective: 1000px; /* Adds depth for 3D effect */
  
  &:hover {
    transform: rotateY(5deg) rotateX(3deg) scale(1.03);
    box-shadow: 0 15px 40px rgba(0, 0, 0, 0.4);
  }

  /* Adding a floating animation for extra 3D feel */
  @keyframes floatEffect {
    0% {
      transform: translateY(0px);
    }
    50% {
      transform: translateY(-5px);
    }
    100% {
      transform: translateY(0px);
    }
  }

  /* Apply floating animation */
  &:hover {
    animation: floatEffect 3s infinite ease-in-out;
  }
`;


const StyledCarousel = styled(Carousel)`
  .carousel-item img {
    border-radius: 15px;
    transition: transform 0.5s ease-in-out, opacity 0.5s ease-in-out;
  }

  .carousel-item-next, .carousel-item-prev, .carousel-item.active {
    animation: fadeZoom 1s ease-in-out;
  }

  @keyframes fadeZoom {
    from {
      opacity: 0.6;
      transform: scale(0.95);
    }
    to {
      opacity: 1;
      transform: scale(1);
    }
  }
`;

const SpecialDealsCarousel = () => {
  return (
    <CarouselContainer>
      <StyledCarousel interval={1500} pause="hover">
        <Carousel.Item>
          <img className="d-block w-100" src="https://rukminim1.flixcart.com/fk-p-flap/1620/270/image/43e26378e18b32a2.jpg?q=20" alt="Deal 1" />
        </Carousel.Item>
        <Carousel.Item>
          <img className="d-block w-100" src="https://rukminim1.flixcart.com/fk-p-flap/1620/270/image/c928b14a5cddaf18.jpg?q=20" alt="Deal 2" />
        </Carousel.Item>
        <Carousel.Item>
          <img className="d-block w-100" src="https://rukminim2.flixcart.com/fk-p-flap/1010/170/image/5fbe79d96b10223e.jpg?q=20" alt="Deal 3" />
        </Carousel.Item>
      </StyledCarousel>
    </CarouselContainer>
  );
};

export default SpecialDealsCarousel;
