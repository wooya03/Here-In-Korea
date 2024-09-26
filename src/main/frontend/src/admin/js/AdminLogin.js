import React from "react";
import '../css/AdminLogin.css';

class AdminLogin extends React.Component {
    handleLogin = () => {
        // 아이디와 비밀번호 필드 값을 가져오기
        const adminId = document.getElementsByName('admin_id')[0].value;
        const adminPassword = document.getElementsByName('admin_password')[0].value;

        // 아이디 또는 비밀번호가 비어있거나 공백만 있는지 확인
        if (adminId.trim() === '' || adminPassword.trim() === '') {
            alert('아이디와 비밀번호를 입력해주세요.'); // 경고창 띄우기
        } else {
            // 로그인 성공 로직 (여기서는 페이지 이동)
            window.location.href = "/admin"; // 또는 다른 페이지 이동 방식 사용 가능
        }
    };

    render() {
        return (
            <div className='login-container'> 
                <form>
                    <h2 id='login_title'> 로그인</h2>
                    <div>
                        <div>
                            {/* 아이디 */}
                            <div>
                                <input type='text' className="input-field" maxLength='20' name='admin_id'
                                    placeholder="ID" />
                            </div>

                            {/* 비밀번호 */}
                            <div>
                                <input type='password' className="input-field" maxLength='15' name='admin_password' 
                                    placeholder="PASSWORD" />
                            </div>
                        </div>
                    </div>
                    <div>
                        {/* 버튼 클릭 시 handleLogin 함수 호출 */}
                        <button type="button" id="sbtn" onClick={this.handleLogin}>로그인</button>
                    </div>
                </form>
            </div>
        )
    }
}

export default AdminLogin;