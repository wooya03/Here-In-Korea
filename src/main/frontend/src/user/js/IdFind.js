import React, {useState} from "react";
import "../css/IdFind.css";
import IdFindMainLogin from "../js/findComponent/IdFindMainLogin"
import IdFindGoogleLogin from "../js/findComponent/IdFindGoogleLogin"
import IdFindKakaoLogin from "../js/findComponent/IdFindKakaoLogin"

const IdFind = () => {
    const selectComponent = {
        hik_log:<IdFindMainLogin/>,
        google_log:<IdFindGoogleLogin/>,
        kakao_log:<IdFindKakaoLogin/>,
    }
    const [content, setContent] = useState(null);

    const handleClickButton = e => {
        const classNames = e.target.className.split(" ");
        const secondClass = classNames[1];
        console.log(secondClass);
        setContent(secondClass);
    };
    const handleBack = () => {
        setContent(null); // content를 null로 설정해 기본 화면으로 돌아가기
    };

    return(
        <div className="content_find">
            <div className="form_wrap">
                {!content && (<p className="info_text">찾고자 하는 아이디를 선택해 주세요</p>)}
                {!content && (<div className="btn_wrap">
                    <button className="move_find_page hik_log" onClick={handleClickButton}>
                        <span>한국여기로 아이디</span>
                    </button>
                    <button className="move_find_page google_log" onClick={handleClickButton}>
                        <span>구글 아이디</span>
                    </button>
                    <button className="move_find_page kakao_log" onClick={handleClickButton}>
                        <span>카카오 아이디</span>
                    </button>
                </div>)}
                <div className="component_wrap">
                    {/* 조건부 렌더링 */}
                    {content && selectComponent[content]}
                    {content && <button onClick={handleBack} className="back_btn">
                        뒤로 가기
                    </button>}
                </div>
            </div>
        </div>
    )
}

export default IdFind;