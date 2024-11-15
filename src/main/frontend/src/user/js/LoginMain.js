import React from "react";
import "../css/LoginMain.css";
import {useNavigate} from "react-router-dom";

const LoginMain = () => {
    const navigate = useNavigate();

    const moveRegister = () =>{
        navigate('/register');
    }

    const checkLogin = () => {
        const idValue= document.getElementsByName('id')[0].value;
        const pwValue = document.getElementsByName('pw')[0].value;

        if (idValue.trim() === '' || pwValue.trim() === '') {
            //로그인 실패 로직
            alert('아이디와 비밀번호를 입력해주세요.');
        } else {
            //로그인 성공 로직
           navigate("/");
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
                                        <input type="text" className="input_id" name="id" placeholder="ID"/>
                                    </div>
                                    <div className="input_item_id" name="item_pw">
                                        <input type="password" className="input_pw" name="pw" placeholder="PassWord"/>
                                    </div>
                                </div>
                            </div>
                            {/* 아이디/비밀번호 찾기 */}
                            <nav role="navigation" aria-label="find or signUp">
                                <div className="find_wrap">
                                    <li><a href="/idFind" className="find_text">아이디 찾기</a></li>
                                    <li><a href="#none" className="find_text">비밀번호 찾기</a></li>
                                    <li><a href="/register" className="find_text">회원 가입</a></li>
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
