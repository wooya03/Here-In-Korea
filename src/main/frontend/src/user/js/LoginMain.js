import React from "react";
import "../css/LoginMain.css";
import {useNavigate} from "react-router-dom";

const LoginMain = () => {
    const navigate = useNavigate();
    
    const handleLogin = () => {
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
                </ul>
                <form className="fm_login" id="fm_login">
                    <ul className="panel_wrap">
                        <li className="panel_item">
                            <div className="panel_inner">
                                <div className="login_from">
                                    <div className="login_box">
                                        <div className="input_item_id">
                                            <input type="text" className="input_id" name="id"/>
                                        </div>
                                        <div className="input_item_id">
                                            <input type="password" className="input_pw" name="pw"/>
                                        </div>
                                    </div>
                                </div>
                                <div className="btn_login_wrap">
                                    <button type="button" className="btn_login" name="btn_login" onClick={handleLogin}>
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
