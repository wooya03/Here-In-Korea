import React, {useState} from "react";
import "../css/LoginMain.css";
import {useNavigate} from "react-router-dom";

const LoginMain = () => {
    const navigate = useNavigate();
    const [idValue, setIdValue] = useState('');
    const [pwValue, setPwValue] = useState('');

    const moveRegister = () =>{
        navigate('/user/register');
    }

    const checkLogin = async () => {
        if (idValue.trim() === '' || pwValue.trim() === '') {
            alert('아이디와 비밀번호를 입력해주세요.');
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/user/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    memId: idValue,
                    memPass: pwValue,
                }),
            });

            if (response.ok) {
                const data = await response.json();
                console.log("응답 데이터:", data); // 응답 확인
                if (data.accessToken && data.refreshToken) {
                    localStorage.setItem('token', data.accessToken);
                    localStorage.setItem('refreshToken', data.refreshToken); // refreshToken 저장
                    navigate("/");
                } else {
                    alert('응답에 토큰 정보가 없습니다.');
                }
            } else {
                const error = await response.json();
                alert(error.message || '로그인에 실패했습니다.');
            }
        } catch (error) {
            console.error('로그인 요청 중 오류 발생:', error);
            alert('서버와 연결할 수 없습니다. 나중에 다시 시도해주세요.');
        }
    };


    return (
        <div className="content">
            <div className="login_wrap">
                <ul className="menu_wrap">
                    {/* 로그인 방법 선택 패널*/}
                    <li className="menu_item">
                        <a href="#none" className="menu_id">
                            <span className="menu_text">ID로그인</span>
                        </a>
                    </li>
                    <li className="menu_item">
                        <a href="#none" className="menu_id_google">
                            <span className="menu_text">GOOGLE</span>
                        </a>
                    </li>
                    <li className="menu_item">
                        <a href="#none" className="menu_id_kakao">
                            <span className="menu_text">KAKAO</span>
                        </a>
                    </li>
                </ul>

                {/* 로그인 ID/Pass 입력 위치 폼*/}
                <form className="fm_login" id="fm_login">
                    <ul className="panel_wrap">
                    <li className="panel_item">
                        <div className="panel_inner">
                            <div className="login_form_box">
                                <div className="login_box">
                                    <div className="input_item_id" name="item_id">
                                        <input type="text" className="input_id" name="id" placeholder="ID" value={idValue}
                                               onChange={(e) => setIdValue(e.target.value)}/>
                                    </div>
                                    <div className="input_item_id" name="item_pw">
                                        <input type="password" className="input_pw" name="pw" placeholder="PassWord" value={pwValue}
                                               onChange={(e) => setPwValue(e.target.value)}/>
                                    </div>
                                </div>
                            </div>
                            {/* 아이디/비밀번호 찾기 */}
                            <nav role="navigation" aria-label="find or signUp">
                                <div className="find_wrap">
                                    <li><a href="/user/find/id" className="find_text">아이디 찾기</a></li>
                                    <li><a href="/user/find/pw" className="find_text">비밀번호 찾기</a></li>
                                    <li><a href="/user/register" className="find_text">회원 가입</a></li>
                                </div>
                            </nav>
                        {/* 로그인 버튼*/}
                                <div className="btn_login_wrap">
                                    <button type="button" className="btn_login" name="btn_login" onClick={checkLogin}>
                                        <span className="btn_text">로그인</span>
                                    </button>
                                </div>
                            </div>
                        </li>
                    </ul>
                </form>

            </div>

        </div>


    )
}

export default LoginMain;
