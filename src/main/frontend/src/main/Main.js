import React from "react";
import "./main.css";
import LeftBanner from "../global/left_banner/left_banner"; // 경로 수정

class Main extends React.Component {
    render() {
        return (
            <div className="main-container">
                <div className="left-section">
                    <h2 className="small-heading">여행지 추천</h2>
                    <p className="normal-heading">이런 곳은 어떨까요?</p>
                    <LeftBanner />
                </div>
                <div className="divider"></div>
                <div className="right-section">
                    <h2 className="small-heading">특별한 추억!</h2>
                    <p className="normal-heading">각종 행사에 참여해 보시는건 어떨까요?</p>
                
                </div>
            </div>
        );
    }
}

export default Main;
