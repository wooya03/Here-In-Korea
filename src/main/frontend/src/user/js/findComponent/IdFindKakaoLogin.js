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