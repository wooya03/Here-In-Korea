import React, {useState} from "react"
import {useNavigate} from "react-router-dom";
import "../css/PwFind.css"

const pwFind = () => {
        // const navigate = useNavigate();
        return(
            <div className="content_find_pw_main">
                <div className="form_find_pw">
                    <div className="list_wrap_pw">
                        <p>아이디</p>
                        <input type="text" className="form_name"/>
                    </div>
                </div>
                <div className="btn_wrap">
                    <button className="next_btn">다음</button>
                </div>
            </div>
        )

}

export default pwFind