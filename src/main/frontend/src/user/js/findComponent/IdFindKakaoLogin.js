import React, {useState} from "react";
import "../../css/findComponentCss/IdFindApiLogin.css"
import {useNavigate} from "react-router-dom";

const IdFindKakaoLogin = () => {
    const [state, setState] = useState();
    const navigate = useNavigate();

    const handleClickButton = e => {
        const classNames = e.target.className.split(" ");
        const secondClass = classNames[1];
        if(secondClass == "move_google"){
            navigate("/login");
        }else if(secondClass == "move_kakao"){
            navigate("/login");
        }else{
            navigate("/")
        }
        console.log(secondClass);
        setState(secondClass);
    };

    return(
        <div className="content_find_id">
            <div className="find_form">
                <button className="move_page move_kakao" onClick={handleClickButton}>카카오 아이디 찾기 이동</button>
            </div>
        </div>
    )
}

export default IdFindKakaoLogin

// 카카오 로그인 jwt 인증여부 판별
// 기록이 남아있다면 있는 기록으로 연결할지 질문
// 남아있지 않다면 자체 페이지로 이동

//메인 페이지를 제외한 다른 api 페이지는 각 페이지의 아이디 찾기 페이지로 이동