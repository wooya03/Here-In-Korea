import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/PwFind.css";

const PwFind = () => {
    const navigate = useNavigate();
    const [memId, setMemId] = useState("");
    const [memName, setMemName] = useState("");
    const [memPass, setMemPass] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [isVerificationSent, setIsVerificationSent] = useState(false);
    const [verificationCode, setVerificationCode] = useState("");
    const [isCodeVerified, setIsCodeVerified] = useState(false);
    const [generatedCode, setGeneratedCode] = useState("");
    const [newPassword, setNewPassword] = useState(""); // 새로운 비밀번호 입력
    const [confirmPassword, setConfirmPassword] = useState("");

    //임시로 인증코드 생성
    const generateVerificationCode = () => {
        const code = Math.floor(100000 + Math.random() * 900000); // 6자리 랜덤 숫자
        return code.toString();
    };
    // 아이디와 이름 입력 후 서버에 아이디 확인 요청
    const handleIdSubmit = async () => {
        if (!memId || !memName) {
            setErrorMessage("아이디와 이름을 모두 입력해 주세요.");
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/user/find/pw/request", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ memId: memId, memName: memName }), // 이름도 함께 전송
            });

            if (response.ok) {
                setIsVerificationSent(true);
                setErrorMessage("");
                const code = generateVerificationCode();
                setGeneratedCode(code);
                console.log("인증 코드:", code);
            } else {
                const error = await response.text();
                setErrorMessage(error.message || "아이디 또는 이름이 잘못되었습니다.");
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
        if (verificationCode === generatedCode) {
            setIsCodeVerified(true);
            setErrorMessage("");
            updatePassChange();
        } else {
            setErrorMessage("인증 코드가 잘못되었습니다.");
        }

        // try {
        //     const response = await fetch("http://localhost:8080/user/find/pw/verify", {
        //         method: "POST",
        //         headers: {
        //             "Content-Type": "application/json",
        //         },
        //         body: JSON.stringify({ memId: userId, code: verificationCode }),
        //     });
        //
        //     if (response.ok) {
        //         setIsCodeVerified(true);
        //         setErrorMessage("");
        //         navigate("/user/reset-password"); // 비밀번호 변경 페이지로 이동
        //     } else {
        //         const error = await response.json();
        //         setErrorMessage(error.message || "인증 코드가 잘못되었습니다.");
        //     }
        // } catch (error) {
        //     console.error("서버와 연결할 수 없습니다.", error);
        //     setErrorMessage("서버와 연결할 수 없습니다. 다시 시도해주세요.");
        // }
    };

    const updatePassChange = async () => {
        if (!newPassword || !confirmPassword) {
            setErrorMessage("새로운 비밀번호와 비밀번호 확인을 모두 입력해 주세요.");
            return;
        }

        if (newPassword !== confirmPassword) {
            setErrorMessage("비밀번호가 일치하지 않습니다.");
            return;
        }

        setMemPass(newPassword);
        console.log("새비밀번호" + memPass);
        console.log("새비밀번호" + newPassword);

        try {
            const response = await fetch("http://localhost:8080/user/find/pw/update", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ memId, memPass: newPassword }),
            });

            if (response.ok) {
                alert("비밀번호가 성공적으로 변경되었습니다.");
                navigate("/user/login"); // 비밀번호 변경 후 로그인 페이지로 리디렉션
            } else {
                const error = await response.json();
                setErrorMessage(error.message || "비밀번호 변경에 실패했습니다.");
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
                        value={memId}
                        onChange={(e) => setMemId(e.target.value)}
                        disabled={isVerificationSent} // 인증 코드 발송 후 아이디 수정 불가
                    />
                </div>

                <div className="list_wrap_pw">
                    <p>이름</p>
                    <input
                        type="text"
                        className="form_name"
                        value={memName}
                        onChange={(e) => setMemName(e.target.value)}
                        disabled={isVerificationSent} // 인증 코드 발송 후 이름 수정 불가
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

                {isCodeVerified && (
                    <>
                        <div className="list_wrap_pw">
                            <p>새로운 비밀번호</p>
                            <input
                                type="password"
                                className="form_name"
                                value={newPassword}
                                onChange={(e) => setNewPassword(e.target.value)}
                            />
                        </div>

                        <div className="list_wrap_pw">
                            <p>비밀번호 확인</p>
                            <input
                                type="password"
                                className="form_name"
                                value={confirmPassword}
                                onChange={(e) => setConfirmPassword(e.target.value)}
                            />
                        </div>
                    </>
                )}
            </div>

            <div className="btn_wrap">
                {!isVerificationSent ? (
                    <button className="next_btn" onClick={handleIdSubmit}>
                        다음
                    </button>
                ) : !isCodeVerified ? (
                    <button className="next_btn" onClick={handleVerificationSubmit}>
                        인증 코드 확인
                    </button>
                ) : (
                    <button className="next_btn" onClick={updatePassChange}>
                        비밀번호 변경
                    </button>
                )}
            </div>

            {errorMessage && <p className="error_message">{errorMessage}</p>}
        </div>
    );
};

export default PwFind;