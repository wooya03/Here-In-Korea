import React, {useState} from "react";
import "../../css/findComponentCss/IdFindApiLogin.css"
import {useNavigate} from "react-router-dom";

const IdFindGoogleLogin = () => {
    const [state, setState] = useState();
    const navigate = useNavigate();

    const handleClickButton = e => {
        const classNames = e.target.className.split(" ");
        const secondClass = classNames[1];
        if(secondClass === "move_google"){
            //이후 로그인 jwt 구현시 연결 루트 수정 - 구글
            navigate("/login");
        }else if(secondClass === "move_kakao"){
            //이후 로그인 jwt 구현시 연결 루트 수정 - 카카오
            navigate("/login");
        }else{
            //이후 로그인 jwt 구현시 연결 루트 수정 - 잘못된 접근 처리
            navigate("/")
        }
        console.log(secondClass);
        setState(secondClass);
    };

    return(
        <div className="content_find_id">
            <div className="find_form">
                <button className="move_page move_google" onClick={handleClickButton}>구글 아이디 찾기 이동</button>
            </div>
        </div>
    )
}

export default IdFindGoogleLogin

// 구글 로그인 jwt 인증여부 판별
// 기록이 남아있다면 있는 기록으로 연결할지 질문
// 남아있지 않다면 자체 페이지로 이동

//메인 페이지를 제외한 다른 api 페이지는 각 페이지의 아이디 찾기 페이지로 이동