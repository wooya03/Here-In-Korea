import React, {useEffect, useState} from "react";
import "../css/RegisterAuth.css";
import {useNavigate, useLocation} from "react-router-dom";

const RegisterAuth = () =>{
    const navigate = useNavigate();
    const { state } = useLocation(); // Register.js에서 전달된 데이터
    const [inputCode, setInputCode] = useState(""); // 입력된 인증 코드
    const [error, setError] = useState("");

    const { memId, memPass, memName, birth, email, gender, verificationCode } = state; // 전달된 데이터 구조 분해

    // formData 상태 초기화
    const [formData, setFormData] = useState({
        memId: "", // 사용자 아이디
        memPass: "", // 비밀번호
        memName: "", // 이름
        birth: "", // 생년월일
        email: "", // 이메일
        gender: "", // 성별
    });

    // 컴포넌트가 마운트될 때 전달받은 데이터를 formData에 저장
    useEffect(() => {
        setFormData({
            memId,
            memPass,
            memName,
            birth,
            email,
            gender,
        });
    }, [memId, memPass, memName, birth, email, gender]);

    const handleRegister = async () => {
        if (inputCode === verificationCode) {
            // 회원가입 데이터를 서버로 전송
            // 주소는 차후 숨겨서 보안 관리
            try {
                const response = await fetch("http://localhost:8080/user/registerauth", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(formData),
                });

                if (response.ok) {
                    // 서버 응답 처리
                    const result = await response.text();
                    console.log(result);
                    alert("회원가입이 완료되었습니다.");
                    console.log("회원가입 데이터:", formData);
                    navigate("/");
                } else {
                    const errorMessage = await response.text();
                    alert("회원가입 실패: " + errorMessage);
                }
            } catch (error) {
                alert("서버와의 통신 중 오류가 발생했습니다: " + error.message);
            }
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