import React from "react";
import "../css/Register.css";

const Register = () =>{

    return(
        <div className="content">
            <div className="join_form">
                <div className="form_content">
                    <div className="form_list">
                        {/*아이디*/}
                        {/*비밀번호*/}
                        {/*비밀번호 확인*/}
                        <div className="form_item id">
                            <input type="text" className="id" id="id" maxLength="16" placeholder="아이디"/>
                        </div>

                        <div className="form_item pw">
                            <input type="password" className="pw" id="pw" maxLength="20" placeholder="비밀번호"/>
                        </div>

                        <div className="form_item pw_ck">
                            <input type="password" className="pwck" id="pwck" maxLength="20" placeholder="비밀번호 확인"/>
                        </div>
                    </div>
                    <div className="form_list">
                        {/*이름, 성별*/}
                        {/*생년월일*/}

                    </div>
                    <div className="form_list">
                        {/*휴대폰 지역*/}
                        {/*휴대폰 번호*/}
                        {/*이메일 선택사항*/}
                    </div>
                </div>
            </div>

        </div>
    )
}

export default Register;

