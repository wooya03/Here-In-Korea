import React from "react";
import { Carousel } from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css'; // 슬라이드 스타일 적용
import './Banner.css';

function Banner() {
  return (
    <Carousel 
      autoPlay 
      infiniteLoop 
      showThumbs={false} 
      showStatus={false} 
      interval={5000} // 슬라이드 전환 간격 (5초)
    >
      <div className="carousel-slide">
        <img src={`${process.env.PUBLIC_URL}/Image/banner1.jpg`} alt="banner1" className="banner-image" />
        <img src={`${process.env.PUBLIC_URL}/Image/banner2.jpg`} alt="banner2" className="banner-image" />
        <img src={`${process.env.PUBLIC_URL}/Image/banner3.jpg`} alt="banner3" className="banner-image" />
      </div>
      <div className="carousel-slide">
        <img src={`${process.env.PUBLIC_URL}/Image/banner2.jpg`} alt="banner2" className="banner-image" />
        <img src={`${process.env.PUBLIC_URL}/Image/banner1.jpg`} alt="banner3" className="banner-image" />
        <img src={`${process.env.PUBLIC_URL}/Image/banner3.jpg`} alt="banner1" className="banner-image" />
      </div>
      <div className="carousel-slide">
        <img src={`${process.env.PUBLIC_URL}/Image/banner3.jpg`} alt="banner3" className="banner-image" />
        <img src={`${process.env.PUBLIC_URL}/Image/banner2.jpg`} alt="banner1" className="banner-image" />
        <img src={`${process.env.PUBLIC_URL}/Image/banner1.jpg`} alt="banner2" className="banner-image" />
      </div>
    </Carousel>
  );
}

export default Banner;
