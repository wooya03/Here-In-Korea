import React, { useState, useEffect } from "react";
import "./right_banner.css";

// 위쪽 배너 이미지 파일
const rightBanner1 = `${process.env.PUBLIC_URL}/Image/right_up_banner1.png`;
const rightBanner2 = `${process.env.PUBLIC_URL}/Image/right_up_banner2.png`;
const rightBanner3 = `${process.env.PUBLIC_URL}/Image/right_up_banner3.png`;

// 아래쪽 배너 이미지 파일
const rightDownBanner1 = `${process.env.PUBLIC_URL}/Image/right_down_banner1.png`;
const rightDownBanner2 = `${process.env.PUBLIC_URL}/Image/right_down_banner2.png`;
const rightDownBanner3 = `${process.env.PUBLIC_URL}/Image/right_down_banner3.png`;

const rightUpBanners = [rightBanner1, rightBanner2, rightBanner3];
const rightDownBanners = [rightDownBanner1, rightDownBanner2, rightDownBanner3];

const RightBanner = () => {
    const [currentUpIndex, setCurrentUpIndex] = useState(0);
    const [currentDownIndex, setCurrentDownIndex] = useState(0);

    useEffect(() => {
        const interval = setInterval(() => {
            nextUpBanner();
            nextDownBanner();
        }, 3000); // 3초마다 배너 변경

        return () => clearInterval(interval);
    }, []);

    const nextUpBanner = () => {
        setCurrentUpIndex((prevIndex) => (prevIndex + 1) % rightUpBanners.length);
    };

    const prevUpBanner = () => {
        setCurrentUpIndex((prevIndex) =>
            (prevIndex - 1 + rightUpBanners.length) % rightUpBanners.length
        );
    };

    const nextDownBanner = () => {
        setCurrentDownIndex((prevIndex) => (prevIndex + 1) % rightDownBanners.length);
    };

    const prevDownBanner = () => {
        setCurrentDownIndex((prevIndex) =>
            (prevIndex - 1 + rightDownBanners.length) % rightDownBanners.length
        );
    };

    return (
        <div className="right-banner-container">
            {/* 위쪽 배너 */}
            <div className="right-banner">
                <div className="right-carousel">
                    {rightUpBanners.map((banner, index) => (
                        <div
                            key={index}
                            className={`right-carousel-slide ${index === currentUpIndex ? 'active' : ''}`}
                        >
                            <img src={banner} alt="Right Up Banner" className="right-banner-image" />
                        </div>
                    ))}
                    <button onClick={prevUpBanner} className="right-control-prev">◀</button>
                    <button onClick={nextUpBanner} className="right-control-next">▶</button>
                </div>
            </div>

            {/* 아래쪽 배너 */}
            <div className="right-banner">
                <div className="right-carousel">
                    {rightDownBanners.map((banner, index) => (
                        <div
                            key={index}
                            className={`right-carousel-slide ${index === currentDownIndex ? 'active' : ''}`}
                        >
                            <img src={banner} alt="Right Down Banner" className="right-banner-image" />
                        </div>
                    ))}
                    <button onClick={prevDownBanner} className="right-control-prev">◀</button>
                    <button onClick={nextDownBanner} className="right-control-next">▶</button>
                </div>
            </div>
        </div>
    );
};

export default RightBanner;
