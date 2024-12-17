import React, { useState } from "react";
import "../css/IdFind.css";
import { useNavigate } from "react-router-dom";

const IdFind = () => {
    const navigate = useNavigate();

    // 상태 관리: 입력값, 결과 상태, 경고 메시지
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [userId, setUserId] = useState(null);
    const [errorMessage, setErrorMessage] = useState("");

    // 아이디 찾기 및 "다음" 버튼 클릭 시 실행되는 함수
    const handleSubmit = async () => {
        if (!name || !email) {
            setErrorMessage("이름과 이메일을 모두 입력해 주세요.");
            return;
        }

        try {
            // 서버에서 이름과 이메일에 맞는 아이디를 조회
            const response = await fetch("http://localhost:8080/user/find/id/request", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ memName: name, email: email }),
            });

            const data = await response.json();
            console.log("서버 응답 데이터:", data);

            if (response.ok) {
                if (data) {
                    setUserId(data.id); // 응답 받은 아이디만 저장
                    setErrorMessage("");
                } else {
                    setErrorMessage("존재하지 않는 아이디입니다.");
                    setName("");
                    setEmail("");
                }
            } else {
                setErrorMessage("서버 오류가 발생했습니다. 다시 시도해주세요.");
            }
        } catch (error) {
            console.error("Error details:", error); // 에러 세부 사항 출력
            setErrorMessage("서버와의 연결에 문제가 발생했습니다.");
        }
    };

    // 로그인 페이지로 이동
    const goToLogin = () => {
        navigate("/user/login");
    };

    // 비밀번호 찾기 페이지로 이동
    const goToPasswordFind = () => {
        navigate("/user/find/pw");
    };

    return (
        <div className="content_find">
            <div className="form_wrap">
                {/* 이름과 이메일 입력 부분 */}
                {!userId && (
                    <div className="content_find_id_main">
                        <div className="form_find_id">
                            <div className="list_wrap">
                                <p>이름</p>
                                <input
                                    type="text"
                                    className="form_name"
                                    value={name}
                                    onChange={(e) => setName(e.target.value)}
                                />
                            </div>

                            <div className="list_wrap">
                                <p>이메일</p>
                                <input
                                    type="text"
                                    className="form_id"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                            </div>
                        </div>
                    </div>
                )}

                {/* 에러 메시지 또는 아이디 표시 */}
                {errorMessage && <p className="error_message">{errorMessage}</p>}
                {userId && (
                    <p className="user_id_message">
                        아이디 : {userId}
                    </p>
                )}

                {/* 아이디 찾기 버튼 */}
                {!userId && (
                    <button onClick={handleSubmit} className="next_btn">
                        아이디 찾기
                    </button>
                )}

                {/* 아이디가 출력되면 로그인 및 비밀번호 찾기 버튼 표시 */}
                {userId && (
                    <div>
                        <button onClick={goToLogin} className="next_btn">
                            로그인
                        </button>
                        <button onClick={goToPasswordFind} className="next_btn">
                            비밀번호 찾기
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
};

export default IdFind;