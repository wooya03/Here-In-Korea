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
        email:"",
        gender:"",
    });

// 유효성 검사 함수
    const validate = async () => {
        let newErrors = {};

        if (!formData.id) {
            newErrors.id = "아이디를 입력해 주세요.";
        } else {
            // 서버로 아이디 중복 체크 요청
            const isDuplicate = await checkIdDuplicate(formData.id);
            if (isDuplicate) {
                newErrors.id = "이미 사용 중인 아이디입니다.";
            }
        }

        if (!formData.pw) {
            newErrors.pw = "비밀번호를 입력해 주세요.";
        }

        if (!formData.pwCheck) {
            newErrors.pwCheck = "비밀번호 확인을 입력해 주세요.";
        } else if (formData.pw !== formData.pwCheck) {
            newErrors.pwCheck = "비밀번호가 일치하지 않습니다.";
        }

        if (!formData.uName) {
            newErrors.uName = "이름을 입력해 주세요.";
        }

        if (!formData.birth) {
            newErrors.birth = "생년월일을 입력해 주세요.";
        } else if (!/^\d{8}$/.test(formData.birth)) {
            newErrors.birth = "생년월일은 YYYYMMDD 형식의 8자리 숫자로 입력해 주세요.";
        } else if (!isValidDate(formData.birth)) {
            newErrors.birth = "유효하지 않은 생년월일입니다.";
        }

        if (!formData.email) {
            newErrors.email = "이메일을 입력해 주세요.";
        } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
            newErrors.email = "유효한 이메일 주소를 입력해 주세요.";
        }

        if (!formData.gender) {
            newErrors.gender = "성별을 선택해 주세요.";
        }

        setErrors(newErrors);

        if (Object.keys(newErrors).length > 0) {
            console.log("유효성 검사 실패: ");
            Object.entries(newErrors).forEach(([field, errorMessage]) => {
                console.log(`- ${field}: ${errorMessage}`);
            });
        }

        return newErrors;
    };

    const isValidDate = (dateString) => {
        const year = parseInt(dateString.substring(0, 4), 10);
        const month = parseInt(dateString.substring(4, 6), 10);
        const day = parseInt(dateString.substring(6, 8), 10);

        // 월과 일의 범위를 확인
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > 31) return false;

        // 각 월의 최대 일자 확인
        const daysInMonth = [31, (year % 4 === 0 && (year % 100 !== 0 || year % 400 === 0)) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        return day <= daysInMonth[month - 1];
    };

    // 서버와 통신하여 아이디 중복 체크
    const checkIdDuplicate = async (id) => {
        // 이 함수는 실제 서버 API와 연동해야 함
        // 임시로 false(중복 아님)로 설정
        return false;
    };

    const sendVerificationCode = async (email) => {
        alert(`${email}로 인증 코드가 전송되었습니다.`);
        // 이메일 인증코드 생성후 이메일 발송
    };

    // 인증 버튼 클릭 시 호출
    const handleRegister = async () => {
        const validationErrors = await validate();

        if (Object.keys(validationErrors).length > 0) {
            // 첫 번째 에러 메시지 출력
            const firstErrorMessage = Object.values(validationErrors)[0];
            alert(firstErrorMessage);
            return;
        }

        // 이메일 인증 코드 전송
        const verificationCode = Math.floor(100000 + Math.random() * 900000).toString();
        await sendVerificationCode(formData.email);

        console.log("생성된 인증 코드:", verificationCode);

        // 폼 데이터를 확인 후 이동 (임시로 navigate 사용)
        console.log("전달된 데이터:", formData);
        // navigate("/user/registerauth",{
        //     state:{formData, verificationCode}
        // });
        navigate("/user/registerauth", {
            state: {
                memId: formData.id,
                memPass: formData.pw,
                memName: formData.uName,
                birth: formData.birth,
                email: formData.email,
                gender: formData.gender,
                verificationCode: verificationCode,
            },
        });
    };

    return(
        <div className="content">
            <div className="join_form">
                <div className="form_content">
                    <div className="form_list">
                        <div className="form_item id">
                            {/*아이디*/}
                            <input type="text" className="id login_form_input" name="id" maxLength="16"
                                   placeholder={errors.id || "아이디"} value={formData.id} onChange={(e) =>
                                setFormData({ ...formData, id: e.target.value })
                            }/>
                        </div>

                        <div className="form_item pw">
                            {/*비밀번호*/}
                            <input type="password" className="pw login_form_input" name="pw" maxLength="20"
                                   placeholder={errors.pw || "비밀번호"} value={formData.pw} onChange={(e) =>
                                setFormData({ ...formData, pw: e.target.value })
                            }/>
                        </div>

                        <div className="form_item pw_ck">
                            {/*비밀번호 확인*/}
                            <input type="password" className="pwCheck login_form_input" name="pwCheck" maxLength="20"
                                   placeholder={errors.pwCheck || "비밀번호 확인"} value={formData.pwCheck} onChange={(e) =>
                                setFormData({ ...formData, pwCheck: e.target.value })
                            }/>
                        </div>
                    </div>
                    <div className="form_list">
                        {/* 이름, 성별 */}
                        {/* 생년월일 */}
                        {/* 이메일 */}
                        <div className="form_item user_name">
                            <div className="name_wrapper">
                                <input type="text" className="uName login_form_input" name="uName" maxLength="16"
                                       placeholder={errors.uName || "이름"} value={formData.uName} onChange={(e) =>
                                    setFormData({ ...formData, uName: e.target.value })
                                }/>
                            </div>
                            <div className="gender_wrapper">
                                <ul className="gender_list">
                                    <li className="radio_item">
                                        <input type="radio" id="genderM" className="gender" name="gender" value="M"
                                               onChange={(e) =>
                                            setFormData({ ...formData, gender: e.target.value })
                                        }/>
                                        <label htmlFor="genderM" className="gender_label">남</label></li>
                                    <li className="radio_item">
                                        <input type="radio" id="genderF" className="gender" name="gender" value="F"
                                               onChange={(e) =>
                                                   setFormData({ ...formData, gender: e.target.value })
                                               }/>
                                        <label htmlFor="genderF" className="gender_label">여</label></li>
                                </ul>
                            </div>
                        </div>

                        <div className="form_item birth">
                            <input type="text" className="birth login_form_input" name="birth" maxLength="20"
                                   placeholder={errors.birth || "생년월일"} value={formData.birth}
                                   onChange={(e) =>
                                       setFormData({ ...formData, birth: e.target.value })
                                   }/>
                        </div>

                        <div className="form_item email">
                            <input type="email" className="email login_form_input" name="email" maxLength="50"
                                   placeholder={errors.email || "이메일"} value={formData.email}
                                   onChange={(e) =>
                                       setFormData({ ...formData, email: e.target.value })
                                   }/>
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

