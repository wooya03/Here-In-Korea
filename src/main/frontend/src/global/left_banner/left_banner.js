import React, { useState, useEffect } from "react";
import "./left_banner.css";

const leftBanner1 = `${process.env.PUBLIC_URL}/Image/left_banner1.png`;
const leftBanner2 = `${process.env.PUBLIC_URL}/Image/left_banner2.png`;
const leftBanner3 = `${process.env.PUBLIC_URL}/Image/left_banner3.png`;

const banners = [leftBanner1, leftBanner2, leftBanner3];

const LeftBanner = () => {
    const [currentIndex, setCurrentIndex] = useState(0);

    useEffect(() => {
        const interval = setInterval(() => {
            nextBanner();
        }, 5000); // 3초마다 배너 변경

        return () => clearInterval(interval);
    }, []);

    const nextBanner = () => {
        setCurrentIndex((prevIndex) => (prevIndex + 1) % banners.length);
    };

    const prevBanner = () => {
        setCurrentIndex((prevIndex) =>
            (prevIndex - 1 + banners.length) % banners.length
        );
    };

    return (
        <div className="left-banner">
            <div className="left-carousel">
                {banners.map((banner, index) => (
                    <div
                        key={index}
                        className={`left-carousel-slide ${index === currentIndex ? 'active' : ''}`}
                    >
                        <img src={banner} alt="배너" className="left-banner-image" />
                    </div>
                ))}
                <button onClick={prevBanner} className="left-control-prev">◀</button>
                <button onClick={nextBanner} className="left-control-next">▶</button>
            </div>
        </div>
    );
};

export default LeftBanner;
