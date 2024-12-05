import React,{useState} from "react";
import "../css/RegisterAuth.css";
import {useNavigate, useLocation} from "react-router-dom";

const RegisterAuth = () =>{
    const navigate = useNavigate();
    const { state } = useLocation(); // Register.js에서 전달된 데이터
    const {formData, verificationCode } = state; // 전달받은 데이터 구조 분해
    const [inputCode, setInputCode] = useState(""); // 입력된 인증 코드
    const [error, setError] = useState("");

    const handleRegister = () => {
        if (inputCode === verificationCode) {
            alert("회원가입이 완료되었습니다.");
            console.log("회원가입 데이터:", formData);
            navigate("/");
        } else {
            console.log("회원가입 데이터:", formData);
            console.log("인증코드", verificationCode);
            setError("인증 코드가 올바르지 않습니다.");
            alert(error);
        }
    };

    return(
        <div className="content">
            <div className="form_wrap">
                    <div className="auth_form">
                        <p>인증번호 입력</p>
                        <input type="text" className="auth" name="auth" maxLength="6"
                        value={inputCode}
                        onChange={(e) =>{ setInputCode(e.target.value)}}/>
                    </div>

                <div className="register_btn_form">
                    <button className="register_btn" onClick={handleRegister}>인증 요청</button>
                </div>
            </div>
        </div>
    )
}

export default RegisterAuth