import React, {useState} from "react";
import "../css/Register.css";
import {useNavigate} from "react-router-dom";

const Register = () =>{
    const navigate = useNavigate();
    //에러 메세지 저장 상태 추가
    const [errors, setErrors] = useState({});
    //state 세팅
    const [formData, setFormData] = useState({
        id: "",
        pw: "",
        pwCheck: "",
        uName: "",
        birth: "",
    });

// 유효성 검사 로직 함수
//     const validate = () => {
//         let newErrors = {};
//
//         if (!formData.id) {
//             newErrors.id = "아이디를 입력해 주세요.";
//         } else if (formData.id.length < 5) {
//             newErrors.id = "아이디는 최소 5자 이상이어야 합니다.";
//         }
//
//         if (!formData.pw) {
//             newErrors.pw = "비밀번호를 입력해 주세요.";
//         } else if (formData.pw.length < 8) {
//             newErrors.pw = "비밀번호 형식이 맞지 않습니다.";
//         }
//
//         if(!formData.pwCheck){
//             newErrors.pwCheck = "비밀번호를 한번 더 입력해 주세요."
//         } else if (formData.pw !== formData.pwCheck) {
//             newErrors.pwCheck = "비밀번호가 일치하지 않습니다.";
//         }
//
//         if (!formData.uName) {
//             newErrors.uName = "이름을 입력해 주세요.";
//         }
//
//         if (!formData.birth) {
//             newErrors.birth = "생년월일을 입력해 주세요.";
//         } else if (!/^\d{4}-\d{2}-\d{2}$/.test(formData.birth)) {
//             newErrors.birth = "생년월일은 8자리로 입력해주세요";
//         }
//
//         setErrors(newErrors);
//
//         // 에러가 없으면 true를 반환
//         return Object.keys(newErrors).length === 0;
//     };

    // 인증 버튼 클릭 시 호출
    const handleRegister = () => {
        alert("인증요청되었습니다.")
        navigate("/registerAuth")
        // if (validate()) {
        //     console.log("유효성 검사를 통과했습니다.");
        //     navigate("/");  // 예시로 '/next' 페이지로 이동
        // } else {
        //     console.log("유효성 검사를 통과하지 못했습니다.");
        //     setFormData({
        //         ...formData,
        //         ...(Object.keys(errors).reduce((acc, key) => {
        //             if (errors[key]) acc[key] = "";  // 에러가 있는 필드는 value를 지움
        //             return acc;
        //         }, {})),
        //     });
        // }
    };

    return(
        <div className="content">
            <div className="join_form">
                <div className="form_content">
                    <div className="form_list">
                        <div className="form_item id">
                            {/*아이디*/}
                            <input type="text" className="id login_form_input" name="id" maxLength="16"
                                   placeholder={errors.id || "아이디"} value={formData.id}/>
                        </div>

                        <div className="form_item pw">
                            {/*비밀번호*/}
                            <input type="password" className="pw login_form_input" name="pw" maxLength="20"
                                   placeholder={errors.pw || "비밀번호"} value={formData.pw}/>
                        </div>

                        <div className="form_item pw_ck">
                            {/*비밀번호 확인*/}
                            <input type="password" className="pwCheck login_form_input" name="pwCheck" maxLength="20"
                                   placeholder={errors.pwCheck || "비밀번호 확인"} value={formData.pwCheck}/>
                        </div>
                    </div>
                    <div className="form_list">
                        {/*이름, 성별*/}
                        {/*생년월일*/}
                        <div className="form_item user_name">
                            <input type="text" className="uName login_form_input" name="uName" maxLength="16"
                                   placeholder={errors.uName || "이름"} value={formData.uName}/>
                        </div>

                        <div className="form_item birth">
                            <input type="text" className="birth login_form_input" name="birth" maxLength="20"
                                   placeholder={errors.birth || "생년월일"} value={formData.birth}/>
                        </div>
                    </div>

                    <div className="auth_request_btn">
                        <button className="auth_request_btn" onClick={handleRegister}>인증 요청</button>
                    </div>
                </div>
            </div>

        </div>
    )
}

export default Register;

