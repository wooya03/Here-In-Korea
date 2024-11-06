import React from "react";
import "../css/Register.css";

const Register = () =>{

    return(
        <div className="content">
            <div className="join_form">
                <div className="form_content">
                    <div className="form_list">
                        <div className="form_item id">
                            {/*아이디*/}
                            <input type="text" className="id" id="id" maxLength="16" placeholder="아이디"/>
                        </div>

                        <div className="form_item pw">
                            {/*비밀번호*/}
                            <input type="password" className="pw" id="pw" maxLength="20" placeholder="비밀번호"/>
                        </div>

                        <div className="form_item pw_ck">
                            {/*비밀번호 확인*/}
                            <input type="password" className="pwck" id="pwck" maxLength="20" placeholder="비밀번호 확인"/>
                        </div>
                    </div>
                    <div className="form_list">
                        {/*이름, 성별*/}
                        {/*생년월일*/}
                        <div className="form_item user_name">
                            <input type="text" className="uname" id="uname" maxLength="16" placeholder="이름"/>
                        </div>

                        <div className="form_item birth">
                            <input type="password" className="birth" id="birth" maxLength="20" placeholder="생년월일"/>
                        </div>

                    </div>
                    <div className="form_list">
                        <div className="form_item tel_area">
                            {/*휴대폰 지역*/}
                        </div>

                        <div className="form_item tel">
                            {/*휴대폰 번호*/}
                            <div className="item_wrap tel">
                                <input type="text" className="tel" id="tel" maxLength="16" placeholder="전화번호"/>
                                <button className="btn_tel_check">본인 확인</button>
                            </div>
                        </div>

                        <div className="form_item email">
                            {/*이메일 선택사항*/}
                            <div className="item_wrap email">
                                <input type="password" className="email" id="email" maxLength="20" placeholder="이메일"/>
                                <button className="btn_email_check">이메일 인증</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}

export default Register;

