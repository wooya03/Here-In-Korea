import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/PwFind.css";

const PwFind = () => {
    const navigate = useNavigate();
    const [userId, setUserId] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [isVerificationSent, setIsVerificationSent] = useState(false);
    const [verificationCode, setVerificationCode] = useState("");
    const [isCodeVerified, setIsCodeVerified] = useState(false);

    // 아이디 입력 후 서버에 아이디 확인 요청
    const handleIdSubmit = async () => {
        if (!userId) {
            setErrorMessage("아이디를 입력해 주세요.");
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/user/find/pw/request", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ memId: userId }),
            });

            if (response.ok) {
                setIsVerificationSent(true);
                setErrorMessage("");
            } else {
                const error = await response.json();
                setErrorMessage(error.message || "아이디가 존재하지 않습니다.");
            }
        } catch (error) {
            console.error("서버와 연결할 수 없습니다.", error);
            setErrorMessage("서버와 연결할 수 없습니다. 다시 시도해주세요.");
        }
    };

    // 인증 코드 입력 후 서버에 코드 확인 요청
    const handleVerificationSubmit = async () => {
        if (!verificationCode) {
            setErrorMessage("인증 코드를 입력해 주세요.");
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/user/find/pw/verify", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ memId: userId, code: verificationCode }),
            });

            if (response.ok) {
                setIsCodeVerified(true);
                setErrorMessage("");
                navigate("/user/reset-password"); // 비밀번호 변경 페이지로 이동
            } else {
                const error = await response.json();
                setErrorMessage(error.message || "인증 코드가 잘못되었습니다.");
            }
        } catch (error) {
            console.error("서버와 연결할 수 없습니다.", error);
            setErrorMessage("서버와 연결할 수 없습니다. 다시 시도해주세요.");
        }
    };

    return (
        <div className="content_find_pw_main">
            <div className="form_find_pw">
                <div className="list_wrap_pw">
                    <p>아이디</p>
                    <input
                        type="text"
                        className="form_name"
                        value={userId}
                        onChange={(e) => setUserId(e.target.value)}
                        disabled={isVerificationSent} // 인증 코드 발송 후 아이디 수정 불가
                    />
                </div>

                {isVerificationSent && (
                    <div className="list_wrap_pw">
                        <p>인증 코드</p>
                        <input
                            type="text"
                            className="form_name"
                            value={verificationCode}
                            onChange={(e) => setVerificationCode(e.target.value)}
                        />
                    </div>
                )}
            </div>

            <div className="btn_wrap">
                {!isVerificationSent ? (
                    <button className="next_btn" onClick={handleIdSubmit}>
                        다음
                    </button>
                ) : (
                    <button className="next_btn" onClick={handleVerificationSubmit}>
                        인증 코드 확인
                    </button>
                )}
            </div>

            {errorMessage && <p className="error_message">{errorMessage}</p>}
        </div>
    );
};

export default PwFind;