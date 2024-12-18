import React, {useState} from "react";
import '../css/AdminLogin.css';
import {useNavigate} from "react-router-dom";

const AdminLogin = () => {
    const baseUrl = "http://localhost:8080";
    const navigate = useNavigate();
    const [item, setItem] = useState({
        id: '',
        pw: ''
    });

    const handleIdChange = (event) => {
        setItem((prev) => ({ ...prev, id: event.target.value }));
      };

      const handlePwChange = (event) => {
        setItem((prev) => ({ ...prev, pw: event.target.value }));
      };

    const handleLogin = async () => {
        if (item.id.trim() === '' || item.pw.trim() === '') {
            alert('아이디와 비밀번호를 입력해주세요.');
            return;
        }

        try {
            const response = await fetch(baseUrl+"/admin/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                }, 
                body: JSON.stringify({
                    memId: item.id,
                    memPass: item.pw,
                }),
            });

            if(response.ok){
                const data = await response.json();
                console.log("응답 데이터: ", data);
                if (data.accessToken && data.refreshToken) {
                    localStorage.setItem('token', data.accessToken);
                    localStorage.setItem('refreshToken', data.refreshToken); // refreshToken 저장
                    navigate("/admin");
                } else {
                    alert('응답에 토큰 정보가 없습니다.');
                }
            } else {
                const error = await response.json();
                alert(error.message || '로그인에 실패했습니다.');
            }
        } catch (error) {
            console.error('로그인 요청 중 오류 발생:',
                error);
                alert('서버와 연결할 수 없습니다. 나중에 다시 시도해주세요.');

        }
    }
    return (
        <div className='admin-login-container'> 
            <form>
                <h2 id='login_title'> 로그인</h2>
                <div>
                    <div>
                        {/* 아이디 */}
                        <div className='admin-login-input'>
                                <input type='text' className="input-field" maxLength='20' value={item.id} onChange={handleIdChange}
                                    placeholder="ID" />
                        </div>

                        {/* 비밀번호 */}
                        <div className='admin-login-input'>
                                <input type='password' className="input-field" maxLength='15' value={item.pw} onChange={handlePwChange}
                                    placeholder="PASSWORD" />
                        </div>
                    </div>
                </div>
                <div className="admin-login">
                    {/* 버튼 클릭 시 handleLogin 함수 호출 */}
                    <button type="button" id="sbtn" onClick={handleLogin}>로그인</button>
                </div>
            </form>
        </div>
    )
}

export default AdminLogin;