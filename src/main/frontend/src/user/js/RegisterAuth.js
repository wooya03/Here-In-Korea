import React from "react";
import "../css/RegisterAuth.css";
import {useNavigate} from "react-router-dom";

const RegisterAuth = () =>{
    const navigate = useNavigate();

    const handleRegister = () => {
        alert("회원가입이 완료 되었습니다.");
        navigate("/");
        // 컴포넌트 간의 state 공유 해서 값을 받아와서 함께 바뀌도록 해야함
    };

    return(
        <div className="content">
            <div className="form_wrap">
                    <div className="auth_form">
                        <p>인증번호 입력</p>
                        <input type="text" className="auth" name="auth" maxLength="16"/>
                    </div>

                <div className="register_btn_form">
                    <button className="register_btn" onClick={handleRegister}>인증 요청</button>
                </div>
            </div>
        </div>
    )
}

export default RegisterAuth