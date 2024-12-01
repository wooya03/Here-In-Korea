import React from "react";
import "../../css/findComponentCss/IdFindMainLogin.css"


//이름과 이메일 검증 함수 제작 필요
const IdFindMainLogin = () => {
    return(
        <div className="content_find_id_main">
            <div className="form_find_id">
                <div className="list_wrap">
                    <p>이름</p>
                    <input type="text" className="form_name"/>
                </div>

                <div className="list_wrap">
                    <p>이메일</p>
                    <input type="text" className="form_id"/>
                </div>
            </div>
        </div>
    )
}

export default IdFindMainLogin